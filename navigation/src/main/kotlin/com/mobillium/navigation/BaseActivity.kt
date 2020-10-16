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

package com.mobillium.navigation

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.NavigationRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.mobillium.core.BaseActivity
import com.mobillium.core.ext.plus
import com.mobillium.navigation.ext.addExtras
import com.mobillium.navigation.markers.NavigationResult
import kotlin.properties.Delegates

/**
 * @author @karacca
 * @since 1.0.0
 */

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    @NavigationRes private val navigationGraphId: Int
) : BaseActivity<VDB, VM>(layoutId) {

    /**
     * The NavController associated with the current activity.
     */
    protected val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    /**
     * The initial input to be provided to the start destination fragment.
     */
    protected open val startDestinationInput: Bundle = Bundle()

    @CallSuper
    override fun init(savedInstanceState: Bundle?) {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?)
            ?.navController?.apply {
            graph = navInflater.inflate(navigationGraphId).also {
                it.addExtras(extrasBundle + startDestinationInput)
            }
        }
    }

    internal fun navigateBackWithResult(@IdRes destination: Int?, result: Bundle) {
        val childFragmentManager =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.childFragmentManager
        var backStackListener: FragmentManager.OnBackStackChangedListener by Delegates.notNull()
        backStackListener = FragmentManager.OnBackStackChangedListener {
            (childFragmentManager?.fragments)?.forEach {
                (it as? NavigationResult)?.onNavigationResult(result)
            }

            childFragmentManager?.removeOnBackStackChangedListener(backStackListener)
        }

        childFragmentManager?.addOnBackStackChangedListener(backStackListener)

        if (destination != null) {
            navController.popBackStack(destination, false)
        } else {
            navController.popBackStack()
        }
    }
}
