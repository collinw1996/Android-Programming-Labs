package edu.towson.cosc431.collinwoodruff.labs.settings;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import edu.towson.cosc431.collinwoodruff.labs.R;

/**
 * Created by Collin on 12/4/2017.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
        }
}
