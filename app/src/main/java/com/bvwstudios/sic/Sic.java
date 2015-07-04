package com.bvwstudios.sic;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by bradley on 4/7/15.
 */
public class Sic extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "ME7eM41OTSaAzfkgxshc7HDTVnvXSnnaEWnOBF6W", "BzNO66JvOPYZRNT7eXoJGNesmbVbPjluF0VAtwdF");

    }
}
