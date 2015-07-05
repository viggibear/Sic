package com.bvwstudios.sic;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

class NewsQuery {
    public Options mOptions = null;
    public final String NEW_YORK_TIMES_APIKEY = "f12be0d3cbba08faac9f168469716218:18:72304558";
    public final String THE_GUARDIAN_APIKEY = "as4k9k569ffxhc4qhe4zh7pw";
    public final String USA_TODAY_APIKEY = "mex5zwukdcdaj8uhqycdqgm3";
    
    public final String NEW_YORK_TIMES_SEARCH_APIKEY = "8f89d3b9ef940c115c51623e68942e62:16:72304558";
    
    
    public NewsQuery(Options options) {
        mOptions = options;
    }
    public void findBreakingNews(final NewsQueryCallback callback, Context context) {
        if (mOptions == null) return;
        
        // instantiate a HTTP request queue for Volley
        RequestQueue queue = Volley.newRequestQueue(context);
        
        if ((mOptions.mNewsOption & NewsSources.NEW_YORK_TIMES) > 0) {
            StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "http://api.nytimes.com/svc/topstories/v1/home.json?api-key=" + NEW_YORK_TIMES_APIKEY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("meow", "New York Times");
                            JSONObject responseObject = new JSONObject(response);
                            Log.d("meow",response);
                            JSONArray results = responseObject.getJSONArray("results");
                            int resultsLength = responseObject.getInt("num_results");
                            List<NewsObject> newsList = new ArrayList<>();
                            for (int i = 0; i < resultsLength; i++) {
                                newsList.add(new NewsObject(
                                        NewsSources.NEW_YORK_TIMES,
                                        results.getJSONObject(i).getString("title"),
                                        results.getJSONObject(i).getString("url"),
                                        results.getJSONObject(i).getString("byline"),
                                        null,
                                        (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'-5:00'")).parse(results.getJSONObject(i).getString("updated_date"))
                                ));
                            }
                            callback.returnNews(newsList);
                        }
                        catch (JSONException e) {
                            Log.d("meow", e.getMessage());
                        }
                        catch (ParseException e) {
                            Log.d("meow", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Who cares, just don't show.
                        return;
                    }
                }
            );
            queue.add(stringRequest);
        }
        
        if ((mOptions.mNewsOption & NewsSources.THE_GUARDIAN) > 0) {
            StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "http://beta.content.guardianapis.com/search?format=json&api-key=" + THE_GUARDIAN_APIKEY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("meow", "The Guardian returns request.");
                        Log.d("meow", response);
                        try {
                            Log.d("meow", response);
                            JSONObject responseObject = new JSONObject(response);

                            JSONArray results = responseObject.getJSONObject("response").getJSONArray("results");
                            int resultsCount = responseObject.getJSONObject("response").getInt("pageSize");
                            Log.d("meow", String.valueOf(resultsCount));
                            List<NewsObject> newsList = new ArrayList<>();
                            for (int i = 0; i < resultsCount; i++) {
                                newsList.add(new NewsObject(
                                        NewsSources.THE_GUARDIAN,
                                        results.getJSONObject(i).getString("webTitle"),
                                        results.getJSONObject(i).getString("webUrl"),
                                        null,
                                        null,
                                        (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).parse(results.getJSONObject(i).getString("webPublicationDate"))
                                ));
                            }

                            callback.returnNews(newsList);
                        }
                        catch (ParseException e) {
                            Log.d("meow", e.getMessage());
                        }
                        catch (JSONException e) {
                            Log.d("meow", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // who cares;
                        return;
                    }
                }
            );
            queue.add(stringRequest);
        }
        
        if ((mOptions.mNewsOption & NewsSources.USA_TODAY) > 0) {
            StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "http://api.usatoday.com/open/articles/topnews?encoding=json&api_key=" + USA_TODAY_APIKEY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseObject = new JSONObject(response);

                            JSONArray results = responseObject.getJSONArray("stories");
                            List<NewsObject> newsList = new ArrayList<>();
                            for (int i = 0; i < results.length(); i++) {
                                newsList.add(new NewsObject(
                                    NewsSources.USA_TODAY,
                                    results.getJSONObject(i).getString("description"),
                                    results.getJSONObject(i).getJSONArray("guid").getJSONObject(0).getString("value"),
                                    null,
                                    null,
                                    (new SimpleDateFormat("EEE, d MMM yyyy kk:mm:ss 'GMT'")).parse(results.getJSONObject(i).getString("pubDate"))
                                ));
                            }

                            callback.returnNews(newsList);
                        }
                        catch (ParseException e) {
                            Log.d("meow", e.getMessage());
                        }
                        catch (JSONException e) {
                            Log.d("meow", "JSONException");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // who cares la.
                        return;
                    }
                }
            );
            queue.add(stringRequest);
        }
    }
        
    public void getSpecificCategory(final NewsQueryCallback callback, Context context) {
        if (mOptions == null) return;
        
        RequestQueue queue = Volley.newRequestQueue(context);
        if ((mOptions.mNewsOption & NewsSources.NEW_YORK_TIMES) > 0) {
            
            String NY_TIMES_QUERY_STRING = "(";
            if ((mOptions.mSubcategoryOption & Subcategories.SPORTS) > 0) NY_TIMES_QUERY_STRING = NY_TIMES_QUERY_STRING + "\"Sports\" ";
            if ((mOptions.mSubcategoryOption & Subcategories.POLITICS) > 0) NY_TIMES_QUERY_STRING = NY_TIMES_QUERY_STRING + "\"Politics\" ";
            if ((mOptions.mSubcategoryOption & Subcategories.ENTERTAINMENT) > 0) NY_TIMES_QUERY_STRING = NY_TIMES_QUERY_STRING + "\"Entertainment\" ";
            if ((mOptions.mSubcategoryOption & Subcategories.ECONOMY) > 0) NY_TIMES_QUERY_STRING = NY_TIMES_QUERY_STRING + "\"Economy\" ";
            if ((mOptions.mSubcategoryOption & Subcategories.HEALTH) > 0) NY_TIMES_QUERY_STRING = NY_TIMES_QUERY_STRING + "\"Health\" ";
            if ((mOptions.mSubcategoryOption & Subcategories.SCIENCE) > 0)NY_TIMES_QUERY_STRING = NY_TIMES_QUERY_STRING + "\"Science\" ";
            NY_TIMES_QUERY_STRING = NY_TIMES_QUERY_STRING + ")";
            
            StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "http://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=" + NEW_YORK_TIMES_SEARCH_APIKEY + "&sort=newest&fq=" + NY_TIMES_QUERY_STRING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseObject = new JSONObject(response);
                        
                            JSONArray results = responseObject.getJSONObject("response").getJSONArray("docs");
                            List<NewsObject> newsList = new ArrayList<>();
                            for (int i = 0; i < results.length(); i++) {
                                newsList.add(new NewsObject(
                                    NewsSources.NEW_YORK_TIMES,
                                    results.getJSONObject(i).getString("snippet"),
                                    results.getJSONObject(i).getString("web_url"),
                                    null,
                                    null,
                                    (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'-5:00'")).parse(results.getJSONObject(i).getString("pub_date"))
                                ));
                            }
                            callback.returnNews(newsList);
                        }
                        catch (ParseException e) {}
                        catch (JSONException e) {}
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
        
        if ((mOptions.mNewsOption & NewsSources.THE_GUARDIAN) > 0) {
            String THE_GUARDIAN_QUERY_STRING = "";
            if ((mOptions.mSubcategoryOption & Subcategories.SPORTS) > 0) THE_GUARDIAN_QUERY_STRING = THE_GUARDIAN_QUERY_STRING + "sports OR ";
            if ((mOptions.mSubcategoryOption & Subcategories.POLITICS) > 0) THE_GUARDIAN_QUERY_STRING = THE_GUARDIAN_QUERY_STRING + "politics OR ";
            if ((mOptions.mSubcategoryOption & Subcategories.ENTERTAINMENT) > 0) THE_GUARDIAN_QUERY_STRING = THE_GUARDIAN_QUERY_STRING + "entertainment OR ";
            if ((mOptions.mSubcategoryOption & Subcategories.ECONOMY) > 0) THE_GUARDIAN_QUERY_STRING = THE_GUARDIAN_QUERY_STRING + "economy OR ";
            if ((mOptions.mSubcategoryOption & Subcategories.HEALTH) > 0) THE_GUARDIAN_QUERY_STRING = THE_GUARDIAN_QUERY_STRING  + "health OR ";
            if ((mOptions.mSubcategoryOption & Subcategories.SCIENCE) > 0) THE_GUARDIAN_QUERY_STRING = THE_GUARDIAN_QUERY_STRING + "science OR ";
            if (THE_GUARDIAN_QUERY_STRING.length() > 0) THE_GUARDIAN_QUERY_STRING = THE_GUARDIAN_QUERY_STRING.substring(0, THE_GUARDIAN_QUERY_STRING.length() - 4);
            
            StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "http://beta.content.guardianapis.com/search?format=json&api-key=" + THE_GUARDIAN_APIKEY + "&tag=" + THE_GUARDIAN_QUERY_STRING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseObject = new JSONObject(response);

                            JSONArray results = responseObject.getJSONObject("response").getJSONArray("results");
                            int resultsCount = responseObject.getJSONObject("response").getInt("pageSize");
                            List<NewsObject> newsList = new ArrayList<>();
                            for (int i = 0; i < resultsCount; i++) {
                                newsList.add(new NewsObject(
                                    NewsSources.THE_GUARDIAN,
                                    results.getJSONObject(i).getString("webTitle"),
                                    results.getJSONObject(i).getString("webUrl"),
                                    null,
                                    null,
                                    (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).parse(results.getJSONObject(i).getString("webPublicationDate"))
                                ));
                            }

                            callback.returnNews(newsList);
                        }
                        catch (ParseException e) {}
                        catch (JSONException e) {}
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
        
        if ((mOptions.mNewsOption & NewsSources.USA_TODAY) > 0) {
            ArrayList<String> sectionQueries = new ArrayList<>();
            if ((mOptions.mSubcategoryOption & Subcategories.SPORTS) > 0) sectionQueries.add("sports");
            if ((mOptions.mSubcategoryOption & Subcategories.POLITICS) > 0) sectionQueries.add("washington");
            if ((mOptions.mSubcategoryOption & Subcategories.ENTERTAINMENT) > 0) sectionQueries.add("life");
            if ((mOptions.mSubcategoryOption & Subcategories.HEALTH) > 0) sectionQueries.add("health");
            if ((mOptions.mSubcategoryOption & Subcategories.ECONOMY) > 0) sectionQueries.add("money");
            if ((mOptions.mSubcategoryOption & Subcategories.SCIENCE) > 0) sectionQueries.add("tech");
            
            for (int i = 0; i < sectionQueries.size(); i++) {
                String sectionString = sectionQueries.get(i);
                
                StringRequest stringRequest = new StringRequest(
                    Request.Method.GET,
                    "http://api.usatoday.com/open/articles?api-key=" + USA_TODAY_APIKEY + "&tag=" + sectionString + "&days=3",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject responseObject = new JSONObject(response);

                                JSONArray results = responseObject.getJSONArray("stories");
                                List<NewsObject> newsList = new ArrayList<>();
                                for (int i = 0; i < results.length(); i++) {
                                    newsList.add(new NewsObject(
                                        NewsSources.USA_TODAY,
                                        results.getJSONObject(i).getString("description"),
                                        results.getJSONObject(i).getJSONArray("guid").getJSONObject(0).getString("value"),
                                        null,
                                        null,
                                        (new SimpleDateFormat("EEE, d MMM yyyy kk:mm:ss 'GMT'")).parse(results.getJSONObject(i).getString("pubDate"))
                                    ));
                                }

                                callback.returnNews(newsList);
                            }
                            catch (JSONException e) {}
                            catch (ParseException e) {}
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // who cares la.
                            return;
                        }
                    }
                );
                
                queue.add(stringRequest);
            }
        }
    }
}