
package de.j4velin.pedometer.util;

import android.os.Bundle;
import android.preference.Preference;

import de.j4velin.pedometer.ui.Activity_Main;

/**
 * Class to wrap some Google Play related stuff in the SettingsFragment
 */
public class PlaySettingsWrapper {

    public static void setupAccountSetting(final Preference account,
                                           final Bundle savedInstanceState,
                                           final Activity_Main main) {
        account.setSummary("This feature is not available on the F-Droid version of the app");
        account.setEnabled(false);
    }

    public static void onSavedInstance(final Bundle outState, final Activity_Main main) {

    }

}
