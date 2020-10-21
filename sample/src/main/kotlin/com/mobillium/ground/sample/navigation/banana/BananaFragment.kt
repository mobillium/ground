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

package com.mobillium.ground.sample.navigation.banana

import android.os.Bundle
import android.widget.Toast
import com.mobillium.ground.R
import com.mobillium.ground.databinding.FragmentBananaBinding
import com.mobillium.ground.sample.navigation.core.BaseFragment
import com.mobillium.navigation.markers.NavigationResult

/**
 * @author @karacca
 * @since 1.0.0
 */

class BananaFragment : BaseFragment<FragmentBananaBinding, BananaViewModel>(
    layoutId = R.layout.fragment_banana
), NavigationResult {

    override fun createViewModel() = BananaViewModel()

    override fun onNavigationResult(result: Bundle) {
        Toast.makeText(
            requireContext(),
            "result: ${result["result"]}",
            Toast.LENGTH_SHORT
        ).show()
    }
}
