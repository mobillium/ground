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

package com.mobillium.ground.sample.navigation.core

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.mobillium.core.markers.Route
import com.mobillium.ground.BR
import com.mobillium.navigation.BaseFragment
import com.mobillium.navigation.BaseViewModel

/**
 * @author @karacca
 * @since 1.0.0
 */

abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : BaseFragment<VDB, VM>(layoutId) {

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun onRoute(route: Route) {
        super.onRoute(route)
    }
}
