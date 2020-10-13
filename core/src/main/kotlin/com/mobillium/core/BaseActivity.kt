/*
 * Copyright 2020 http://www.mobillium.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mobillium.core

import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import com.mobillium.core.ext.currentFragment
import com.mobillium.core.ext.handleBackPressEvent
import com.mobillium.core.ext.handleNewIntent
import com.mobillium.core.ext.observe
import com.mobillium.core.ext.observeEvent
import com.mobillium.core.markers.CanFetchExtras
import com.mobillium.core.markers.CanHandleNewIntent
import com.mobillium.core.markers.CanManageState
import com.mobillium.core.markers.Command
import com.mobillium.core.markers.Route
import com.mobillium.core.markers.ViewState

/**
 * @author @karacca
 * @since 1.0.0
 */

/**
 * A base Activity to be used with a concrete implementation of [BaseViewModel].
 */
@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel>(
  @LayoutRes private val layoutId: Int
) : AppCompatActivity(), CanFetchExtras, CanHandleNewIntent, CanManageState {

    /**
     * Retrieves binding variable for the [BaseViewModel].
     * (This should correspond to the id of the ViewModel
     * variable defined in your xml layout file (i.e: BR.viewModel))
     */
    protected open val bindingVariable: Int = 0

    /**
     * Override this property in order to enable/disable
     * the DataBinding for the Activity.
     */
    protected open val isDataBindingEnabled = true

    var viewDataBinding: VDB? = null
    var viewModel: VM? = null

    var extrasBundle = Bundle()
        private set

    // region Activity

    final override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        intent?.extras?.let(::fetchExtras)
        preInit()
        super.onCreate(savedInstanceState)
        initView()
        init(savedInstanceState)
        postInit()
        performDataBinding()
        registerObservables()
        onRegisterObservables()
    }

    final override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let(::handleNewIntent)
    }

    @CallSuper
    override fun onBackPressed() {
        val isConsumedByViewModel: Boolean = viewModel?.onBackPressed() ?: false
        val isConsumedByChild: Boolean = supportFragmentManager.fragments.handleBackPressEvent()

        if (!isConsumedByViewModel && !isConsumedByChild) {
            super.onBackPressed()
        }
    }

    final override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel?.onSaveState(outState)
        onSaveState(outState)
    }

    final override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel?.onRestoreState(savedInstanceState)
        onRestoreState(savedInstanceState)
    }

    // endregion

    // region CanFetchExtras

    /**
     * Gets called before pre-initialization stage (with [preInit] method call)
     * if the received [Bundle] is not null
     *
     * @param extras the bundle of arguments
     */
    @CallSuper
    override fun fetchExtras(extras: Bundle) {
        extrasBundle = extras
    }

    // endregion

    // region CanHandleNewIntent

    /**
     * Gets called by [onNewIntent].
     */
    @CallSuper
    override fun handleNewIntent(intent: Intent) {
        supportFragmentManager.currentFragment?.handleNewIntent(intent)
    }

    // endregion

    // region CanManageState

    override fun onSaveState(bundle: Bundle) {}

    override fun onRestoreState(bundle: Bundle) {}

    // endregion

    /**
     * Creates the concrete version of the [BaseViewModel].
     *
     * @return the created concrete version of the [BaseViewModel]
     */
    protected abstract fun createViewModel(): VM

    /**
     * Gets called when it's right time to inject dependencies.
     */
    open fun injectDependencies() {}

    /**
     * Gets called right before the UI initialization.
     */
    protected open fun preInit() {}

    /**
     * Get's called when it's the right time for you to initialize the UI elements.
     *
     * @param savedInstanceState state bundle brought from the [android.app.Activity.onCreate]
     */
    protected open fun init(savedInstanceState: Bundle?) {}

    /**
     * Gets called right after the UI initialization.
     */
    protected open fun postInit() {}

    /**
     * Gets called whenever the [ViewState] change event arrives from the [BaseViewModel].
     *
     * @param state the new view state
     */
    protected open fun onViewStateChanged(state: ViewState) {}

    /**
     * Gets called whenever new [Command] arrives from [BaseViewModel].
     *
     * @param command arrived command
     */
    protected open fun onHandleCommand(command: Command) {}

    /**
     * Gets called whenever new [Route] arrives from [BaseViewModel].
     *
     * @param route arrived route
     */
    protected open fun onRoute(route: Route) {}

    /**
     * Gets called when it's the right time to register the [ObservableField]s of your [androidx.lifecycle.ViewModel].
     */
    protected open fun onRegisterObservables() {}

    private fun initView() {
        if (isDataBindingEnabled) {
            viewDataBinding = viewDataBinding ?: DataBindingUtil.setContentView(this, layoutId)
        } else {
            setContentView(layoutId)
        }

        viewModel = viewModel ?: createViewModel()

        if (isDataBindingEnabled) {
            viewDataBinding?.setVariable(bindingVariable, viewModel)
            viewDataBinding?.lifecycleOwner = this
        }
    }

    private fun performDataBinding() {
        if (isDataBindingEnabled) {
            viewDataBinding?.executePendingBindings()
        }
    }

    private fun registerObservables() {
        viewModel?.command?.let { observeEvent(it, ::onHandleCommand) }
        viewModel?.route?.let { observeEvent(it, ::onRoute) }
        viewModel?.viewState?.let { observe(it, ::onViewStateChanged) }
    }
}
