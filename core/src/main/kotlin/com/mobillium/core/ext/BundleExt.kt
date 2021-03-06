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

import android.os.Bundle

/**
 * @author @karacca
 * @since 1.0.0
 */

/**
 * Adds the contents of the specified [Bundle] into
 * the current [Bundle].
 *
 * @return the current [Bundle] with appended content
 */
operator fun Bundle.plus(other: Bundle): Bundle {
    return this.also { it.putAll(other) }
}
