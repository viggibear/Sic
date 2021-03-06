package com.bvwstudios.sic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.text.method.ScrollingMovementMethod;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import java.io.InputStream;


public class ArticleActivity extends AppCompatActivity {
    private NewsObject mNewsObject;

    private TextView mTitle;
    private TextView mContent;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        mNewsObject = getIntent().getExtras().getParcelable("newsObject");

        mTitle = (TextView) findViewById(R.id.title);
        mContent = (TextView) findViewById(R.id.content);
        mImage = (ImageView) findViewById(R.id.image);

        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(mNewsObject.getImageUrl()).getContent());
            mImage.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mTitle.setText(mNewsObject.mTitle);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String preference = sharedPref.getString(SettingsActivity.KEY_ARTICLE_SIZE, "10");
        int sentences = Integer.parseInt(preference);

        mNewsObject.getSummary(this, sentences, new NewsSummaryCallback() {
            @Override
            public void returnSummary(String summary) {
                mContent.setText(summary);
                int lineCount = mContent.getLineCount();
                mContent.setMaxLines(lineCount + 1);
                mContent.setMovementMethod(new ScrollingMovementMethod());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(ArticleActivity.this);

        mNewsObject.getImage(this, new NewsImageCallback() {
            @Override
            public void returnImage(String url) {
                new DownloadImageTask((ImageView) findViewById(R.id.image)).execute(url);
            }
        }, queue);

        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mToolbar.setTitle(NewsSources.getName(mNewsObject.mSource));
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(ArticleActivity.this, SettingsActivity.class);
            startActivityForResult(i, 1);
            return true;
        }

        if (id == R.id.action_web) {
            String url = mNewsObject.getUrl();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}

