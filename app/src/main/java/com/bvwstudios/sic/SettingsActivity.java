package com.bvwstudios.sic;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import com.parse.ParseUser;

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
        Preference button = findPreference("logoutButton");
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ParseUser.logOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                return true;
            }
        });

    }
}
