package com.bvwstudios.sic;

import java.util.List;

interface NewsQueryCallbackInterface {
    public void returnNews(List<NewsObject> newsList);
}

class NewsQueryCallback implements NewsQueryCallbackInterface {
    @Override
    public void returnNews(List<NewsObject> newsList) {
        return;
    }
}