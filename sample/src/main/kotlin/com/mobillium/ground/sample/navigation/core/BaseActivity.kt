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
import androidx.annotation.NavigationRes
import androidx.databinding.ViewDataBinding
import com.mobillium.ground.BR
import com.mobillium.navigation.BaseActivity
import com.mobillium.navigation.BaseViewModel

/**
 * @author @karacca
 * @since 1.0.0
 */

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    @NavigationRes private val navigationGraphId: Int
) : BaseActivity<VDB, VM>(layoutId, navigationGraphId) {

    override val bindingVariable: Int
        get() = BR.viewModel
}
