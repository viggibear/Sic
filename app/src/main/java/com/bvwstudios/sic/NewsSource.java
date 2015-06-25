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

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Bitmap getIcon() {
        return mIcon;
    }

    public void setIcon(Bitmap icon) {
        mIcon = icon;
    }
}
