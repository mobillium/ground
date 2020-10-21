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
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.mobillium.core.BaseFragment
import com.mobillium.core.markers.Route

/**
 * @author @karacca
 * @since 1.0.0
 */

abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : BaseFragment<VDB, VM>(layoutId) {

    private fun navigate(directions: NavDirections) =
        findNavController().navigate(directions)

    private fun navigateBack(
        @IdRes destination: Int? = null,
        result: Bundle? = null
    ) {
        if (result != null) {
            (activity as? BaseActivity<*, *>)?.navigateBackWithResult(destination, result)
        } else {
            if (destination != null) {
                findNavController().popBackStack(destination, false)
            } else {
                findNavController().popBackStack()
            }
        }
    }

    protected fun popBackStack() = navigateBack(null, null)

    override fun onRoute(route: Route) {
        if (route is NavigationRoute) {
            when (route) {
                is NavigationRoute.NavigateTo -> navigate(route.directions)
                is NavigationRoute.NavigateBack -> navigateBack(route.destination, route.result)
            }
        }
    }
}
