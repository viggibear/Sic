package com.bvwstudios.sic;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Vignesh Ravi on 4/7/2015.
 */
public class SettingsActivity extends PreferenceActivity {

    public static final String KEY_ARTICLE_SIZE = "articleSize";
    public static final String KEY_TEXT_SIZE = "textSize";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

    }
}
