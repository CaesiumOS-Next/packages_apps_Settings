package com.android.settings.display;

import static android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE;
import static android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
import static android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;

import android.content.Context;
import android.provider.Settings;
import com.android.settings.SettingsActivity;
import com.android.settings.display.BrightnessLevelPreferenceController;

import android.util.Log;

public class AdaptiveBrightnessSuggestionActivity extends SettingsActivity {

    public static boolean isSuggestionComplete(Context context) {
        BrightnessLevelPreferenceController blpf =
                new BrightnessLevelPreferenceController(context, null);
        int brightnessMode = Settings.System.getInt(context.getContentResolver(), 
                SCREEN_BRIGHTNESS_MODE, SCREEN_BRIGHTNESS_MODE_MANUAL);

        return brightnessMode == SCREEN_BRIGHTNESS_MODE_AUTOMATIC || !blpf.isMaxBrightness();
    }
}
