package com.bvwstudios.sic;

import com.gravity.goose.Article;
import com.gravity.goose.Configuration;
import com.gravity.goose.Goose;

import net.sf.classifier4J.summariser.SimpleSummariser;

import java.util.Date;

class NewsObject {
    public int mSource;
    public String mTitle;
    public String mUrl;
    public String mByline;
    public Date mPublishedDate;
    public String mImageUrl;
    public NewsObject(int source, String title, String url, String byline, String imageUrl, Date publishedDate) {
        mSource = source;
        mTitle = title;
        mUrl = url;
        mByline = byline;
        mImageUrl = imageUrl;
        mPublishedDate = publishedDate;
    }
    public String getContent() {
        Goose goose = new Goose(new Configuration());
        Article article = goose.extractContent(mUrl);
        return article.cleanedArticleText();
    }
    public String getSummary() {
        SimpleSummariser summariser = new SimpleSummariser();
        return summariser.summarise(getContent(), 2);
    }
}