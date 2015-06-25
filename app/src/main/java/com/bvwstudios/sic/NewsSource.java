package com.bvwstudios.sic;

import android.graphics.Bitmap;

/**
 * Created by Vignesh Ravi on 14/6/2015.
 */
public class NewsSource {
    private String mName;
    private String mUrl;
    private Bitmap mIcon;

    public NewsSource(){}

    public NewsSource(String mName, String mUrl, Bitmap mIcon){
        super();
        this.mName = mName;
        this.mUrl = mUrl;
        this.mIcon = mIcon;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public Bitmap getmIcon() {
        return mIcon;
    }

    public void setmIcon(Bitmap mIcon) {
        this.mIcon = mIcon;
    }
}
