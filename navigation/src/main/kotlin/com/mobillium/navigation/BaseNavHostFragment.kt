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

import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.navigation.fragment.NavHostFragment
import com.mobillium.core.ext.handleBackPressEvent
import com.mobillium.core.ext.handleExtras
import com.mobillium.core.ext.handleNewIntent
import com.mobillium.core.markers.CanFetchExtras
import com.mobillium.core.markers.CanHandleBackPressEvents
import com.mobillium.core.markers.CanHandleNewIntent

/**
 * @author @karacca
 * @since 1.0.0
 */

open class BaseNavHostFragment :
    NavHostFragment(),
    CanHandleNewIntent,
    CanFetchExtras,
    CanHandleBackPressEvents {

    override fun handleNewIntent(intent: Intent) =
        childFragmentManager.fragments.handleNewIntent(intent)

    override fun fetchExtras(extras: Bundle) =
        childFragmentManager.fragments.handleExtras(extras)

    @CallSuper
    override fun onBackPressed() =
        childFragmentManager.fragments.handleBackPressEvent()
}
