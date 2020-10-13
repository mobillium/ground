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

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobillium.core.markers.CanHandleBackPressEvents
import com.mobillium.core.markers.CanManageState
import com.mobillium.core.markers.Command
import com.mobillium.core.markers.Route
import com.mobillium.core.markers.ViewModelLifecycle
import com.mobillium.core.markers.ViewState
import com.mobillium.core.utils.Event

/**
 * @author @karacca
 * @since 1.0.0
 */

/**
 * An abstract implementation of [BaseViewModel].
 *
 * @param defaultViewState the default view state will be delivered
 */
abstract class BaseViewModel(
    defaultViewState: ViewState? = null
) : ViewModel(), ViewModelLifecycle, CanManageState, CanHandleBackPressEvents {

    /**
     * A [LiveData] for sending [Command] [Event]s.
     * Actual handling of the emitted [Command]s is up to subscribing side (i.e: Activity, Fragment, View)
     */
    private val _command = MutableLiveData<Event<Command>>()
    val command: LiveData<Event<Command>>
        get() = _command

    /**
     * A [LiveData] for sending [Route] [Event]s.
     * Actual handling of the emitted [Route]s is up to subscribing side (i.e: Activity, Fragment, View)
     */
    private val _route = MutableLiveData<Event<Route>>()
    val route: LiveData<Event<Route>>
        get() = _route

    /**
     * A [LiveData] for the [ViewState] to notify observers for view state changes.
     * Actual handling of the emitted [ViewState]s is up to subscribing side (i.e: Activity, Fragment, View)
     */
    private val _viewState =
        defaultViewState?.let { MutableLiveData(it) } ?: MutableLiveData()
    val viewState: LiveData<ViewState>
        get() = _viewState

    /**
     * Dispatches new command to the owning View.
     * The [Command] changes will be delivered if and only if,
     * the owning view has an active subscription to the current ViewModel
     *
     * @param command command to be dispatched
     */
    protected fun command(command: Command) =
        _command.postValue(Event(command))

    /**
     * Dispatches new route to the owning View.
     * The [Route] changes will be delivered if and only if,
     * the owning view has an active subscription to the current ViewModel
     *
     * @param route route to be dispatched
     */
    protected fun route(route: Route) =
        _route.postValue(Event(route))

    /**
     * Changes the [ViewState] to the specified one.
     * The [Route] changes will be delivered if and only if,
     * the owning view has an active subscription to the current ViewModel
     *
     * @param viewState new view state to be
     */
    protected fun viewState(viewState: ViewState) =
        _viewState.postValue(viewState)

    @CallSuper
    override fun onStart() {
    }

    @CallSuper
    override fun onStop() {
    }

    @CallSuper
    override fun onRestoreState(bundle: Bundle) {
    }

    @CallSuper
    override fun onSaveState(bundle: Bundle) {
    }

    @CallSuper
    override fun onBackPressed() = false
}
