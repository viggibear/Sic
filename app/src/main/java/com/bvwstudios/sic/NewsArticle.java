package com.bvwstudios.sic;

import android.graphics.Bitmap;

/**
 * Created by Vignesh Ravi on 14/6/2015.
 */
public class NewsArticle {
    private String mTitle;
    private String mContent;
    private Bitmap mImage;
    private String mUrl;
    private String mCategory;
    private NewsSource mSource;

    public NewsArticle(){}

    public NewsArticle(String mTitle, String mContent, Bitmap mImage, String mUrl, String mCategory, NewsSource mSource) {
        super();
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mImage = mImage;
        this.mUrl = mUrl;
        this.mCategory = mCategory;
        this.mSource = mSource;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public Bitmap getmImage() {
        return mImage;
    }

    public void setmImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public NewsSource getmSource() {
        return mSource;
    }

    public void setmSource(NewsSource mSource) {
        this.mSource = mSource;
    }
}
