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
import androidx.navigation.NavDirections
import com.mobillium.core.BaseViewModel
import com.mobillium.core.markers.ViewState

/**
 * @author @karacca
 * @since 1.0.0
 */

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseViewModel(
    defaultViewState: ViewState? = null
) : BaseViewModel(defaultViewState) {

    protected fun navigate(directions: NavDirections) =
        route(NavigationRoute.NavigateTo(directions))

    protected fun navigateBack(
        @IdRes destination: Int? = null,
        result: Bundle? = null
    ) = route(NavigationRoute.NavigateBack(destination, result))

    fun popBackStack() = navigateBack()
}
