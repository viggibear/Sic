package com.bvwstudios.sic;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Vignesh Ravi on 14/6/2015.
 */
public class NewsArticle {
    private String mTitle;
    private String mContent;
    private Bitmap mImage;
    private String mUrl;
    private String mCategory;
    private Date mTime;
    private NewsSource mSource;

    public NewsArticle(){}

    public NewsArticle(String mTitle, String mContent, Bitmap mImage, String mUrl, String mCategory, Date mTime, NewsSource mSource) {
        super();
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mImage = mImage;
        this.mUrl = mUrl;
        this.mCategory = mCategory;
        this.mSource = mSource;
        this.mTime = mTime;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Date getTime() {
        return mTime;
    }

    public void setTime(Date time) {
        mTime = time;
    }

    public Bitmap getImage() {
        return mImage;
    }

    public void setImage(Bitmap image) {
        mImage = image;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public NewsSource getSource() {
        return mSource;
    }

    public void setSource(NewsSource source) {
        mSource = source;
    }
}
