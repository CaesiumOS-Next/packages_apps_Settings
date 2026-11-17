/*
 * Copyright (C) 2026 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.development

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import com.android.settings.Settings.AdbWirelessDebuggingActivity
import com.android.settings.SettingsActivity
import com.android.settings.core.gateway.SettingsGateway
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class AdbWirelessDebuggingActivityTest {

    private val context = RuntimeEnvironment.getApplication()

    @Test
    fun wirelessDebuggingAction_opensWirelessDebuggingPage() {
        val pm = context.packageManager
        val resolveInfo =
            pm.resolveActivity(Intent("com.android.settings.ADB_WIRELESS_DEBUGGING_SETTINGS"), 0)

        assertThat(resolveInfo?.activityInfo?.name)
            .isEqualTo(AdbWirelessDebuggingActivity::class.java.name)

        val activityInfo =
            pm.getActivityInfo(
                ComponentName(context, AdbWirelessDebuggingActivity::class.java),
                PackageManager.GET_META_DATA,
            )
        val fragment =
            activityInfo.metaData.getString(SettingsActivity.META_DATA_KEY_FRAGMENT_CLASS)
        assertThat(fragment).isEqualTo(AdbWirelessDebuggingFragment::class.java.name)
        // SettingsActivity refuses to show fragments missing from the gateway.
        assertThat(SettingsGateway.ENTRY_FRAGMENTS).asList().contains(fragment)
    }
}
