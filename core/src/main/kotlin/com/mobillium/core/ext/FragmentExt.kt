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

package com.mobillium.core.ext

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mobillium.core.markers.CanFetchExtras
import com.mobillium.core.markers.CanHandleBackPressEvents
import com.mobillium.core.markers.CanHandleNewIntent

/**
 * @author @karacca
 * @since 1.0.0
 */

/**
 * Passes the extras for further handling to the
 * Fragment if [CanFetchExtras] is implemented
 */
fun Fragment.handleExtras(extras: Bundle) =
    (this as? CanFetchExtras)?.fetchExtras(extras)

/**
 * Applies [handleExtras] to each of the [Fragment] in a collection.
 */
fun Collection<Fragment>.handleExtras(extras: Bundle) =
    this.forEach { it.handleExtras(extras) }

/**
 * Passes the new [Intent] for further handling to the
 * Fragment if [CanHandleNewIntent] is implemented.
 */
fun Fragment.handleNewIntent(intent: Intent) =
    (this as? CanHandleNewIntent)?.handleNewIntent(intent)

/**
 * Applies [handleNewIntent] to each of the [Fragment] in a collection.
 */
fun Collection<Fragment>.handleNewIntent(intent: Intent) =
    this.forEach { it.handleNewIntent(intent) }

/**
 * Propagates the Back Press Event for further handling to the specified [Fragment]
 * only if it (the specified [Fragment]) can handle the Back Press Event
 * (if it (the specified [Fragment]) implements the [CanHandleBackPressEvents] interface)
 *
 * @return whether the Back Press Event has been consumed or not (see: [CanHandleBackPressEvents.onBackPressed])
 */
fun Fragment.handleBackPressEvent() =
    this is CanHandleBackPressEvents && this.onBackPressed()

/**
 * Applies [handleBackPressEvent] to each [Fragment] in a collection.
 */
fun Collection<Fragment>.handleBackPressEvent(): Boolean {
    for (fragment in this) {
        if (fragment.handleBackPressEvent()) {
            return true
        }
    }

    return false
}
