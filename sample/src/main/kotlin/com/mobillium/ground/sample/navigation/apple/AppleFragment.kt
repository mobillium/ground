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

package com.mobillium.ground.sample.navigation.apple

import com.mobillium.ground.R
import com.mobillium.ground.databinding.FragmentAppleBinding
import com.mobillium.ground.sample.navigation.core.BaseFragment

/**
 * @author @karacca
 * @since 1.0.0
 */

class AppleFragment : BaseFragment<FragmentAppleBinding, AppleViewModel>(
    layoutId = R.layout.fragment_apple
) {

    override fun createViewModel() = AppleViewModel()
}
