package com.android.settings.gestures

import android.content.Context
import android.provider.Settings
import com.android.settings.R
import com.android.settings.core.TogglePreferenceController

class AdvancedRestartPreferenceController(context: Context, key: String) :
    TogglePreferenceController(context, key) {

    override fun getAvailabilityStatus(): Int = AVAILABLE

    override fun isChecked(): Boolean {
        return Settings.Secure.getInt(
            mContext.contentResolver,
            Settings.Secure.ADVANCED_REBOOT,
            SETTING_VALUE_OFF,
        ) == SETTING_VALUE_ON
    }

    override fun setChecked(isChecked: Boolean): Boolean {
        return Settings.Secure.putInt(
            mContext.contentResolver,
            Settings.Secure.ADVANCED_REBOOT,
            if (isChecked) SETTING_VALUE_ON else SETTING_VALUE_OFF,
        )
    }

    override fun getSliceHighlightMenuRes(): Int = R.string.menu_key_system

    companion object {
        private const val SETTING_VALUE_OFF = 0
        private const val SETTING_VALUE_ON = 1
    }
}
