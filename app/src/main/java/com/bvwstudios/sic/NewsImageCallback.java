package com.bvwstudios.sic;


interface NewsImageCallbackInterface {
    public void returnImage(String url);
}

class NewsImageCallback implements NewsImageCallbackInterface {
    @Override
    public void returnImage(String url) { return; }
}