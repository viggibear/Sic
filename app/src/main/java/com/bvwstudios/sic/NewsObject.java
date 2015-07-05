package com.bvwstudios.sic;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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
    public void getSummary(Context context, final NewsSummaryCallback callback) {
        String url = "http://api.smmry.com/&SM_API_KEY=D16C0D&SM_LENGTH=7&SM_URL=" + mUrl;

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseObject = new JSONObject(response);
                            String content = responseObject.getString("sm_api_content");
                            callback.returnSummary(content);
                        } catch (JSONException e) {}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        return;
                    }
                }
        );
        queue.add(stringRequest);
    }
}