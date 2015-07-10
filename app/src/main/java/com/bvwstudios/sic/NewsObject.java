package com.bvwstudios.sic;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

class NewsObject implements Parcelable {
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

    public int getSource() {
        return mSource;
    }

    public void setSource(int source) {
        mSource = source;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getByline() {
        return mByline;
    }

    public void setByline(String byline) {
        mByline = byline;
    }

    public Date getPublishedDate() {
        return mPublishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        mPublishedDate = publishedDate;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public void getSummary(Context context, int length, final NewsSummaryCallback callback) {
        String url = "http://api.smmry.com/&SM_API_KEY=075EC05235&SM_LENGTH=" + length + "&SM_URL=" + mUrl;

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("potatopie", response);
                            JSONObject responseObject = new JSONObject(response);
                            String content = responseObject.getString("sm_api_content");
                            try
                            {
                                byte spbyte[] = content.getBytes("UTF-8");
                                String midText = new String( spbyte,"UTF-8");
                                content = Html.fromHtml(midText).toString();
                            }
                            catch(IOException ioe)
                            {
                                ioe.printStackTrace();
                            }
                            Log.d("potatopie", content);
                            callback.returnSummary(content);
                        } catch (JSONException e) {
                            Log.d("potatopie", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.d("potatopie", error.getMessage());
                        return;
                    }
                }
        );
        queue.add(stringRequest);
    }

    public void getImage(Context context, final NewsImageCallback callback, final RequestQueue queue) {
        String url = "https://api.embed.ly/1/oembed?key=48c027a3cc6b4109959ed98ecea1b1cc&url=" + mUrl;
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("potatopie", response);
                            JSONObject responseObject = new JSONObject(response);
                            String content = responseObject.getString("thumbnail_url");
                            Log.d("potatopie", content);
                            callback.returnImage(content);
                        } catch (JSONException e) {
                            Log.d("potatopie", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("potatopie", error.getMessage());
                        return;
                    }
                }
        );
        queue.add(stringRequest);
    }

    protected NewsObject(Parcel in) {
        mSource = in.readInt();
        mTitle = in.readString();
        mUrl = in.readString();
        mByline = in.readString();
        long tmpMPublishedDate = in.readLong();
        mPublishedDate = tmpMPublishedDate != -1 ? new Date(tmpMPublishedDate) : null;
        mImageUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mSource);
        dest.writeString(mTitle);
        dest.writeString(mUrl);
        dest.writeString(mByline);
        dest.writeLong(mPublishedDate != null ? mPublishedDate.getTime() : -1L);
        dest.writeString(mImageUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NewsObject> CREATOR = new Parcelable.Creator<NewsObject>() {
        @Override
        public NewsObject createFromParcel(Parcel in) {
            return new NewsObject(in);
        }

        @Override
        public NewsObject[] newArray(int size) {
            return new NewsObject[size];
        }
    };

}