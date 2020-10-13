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

package com.mobillium.core.markers

import android.os.Bundle

/**
 * @author @karacca
 * @since 1.0.0
 */

/**
 * An interface used to indicate the support for the state management.
 */
interface CanManageState {

    /**
     * Gets called when the actual process of state restoring can be performed.
     *
     * @param bundle the state containing bundle
     */
    fun onRestoreState(bundle: Bundle)

    /**
     * Gets called when the actual process of state saving can be performed.
     *
     * @param bundle the bundle to save state into
     */
    fun onSaveState(bundle: Bundle)
}
