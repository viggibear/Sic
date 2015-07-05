package com.bvwstudios.sic;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    private FrameLayout mContainer;
    private static final int RESULT_SETTINGS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ParseUser.getCurrentUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mContainer = (FrameLayout) findViewById(R.id.container);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        if (position == 0)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ArticleListFragment()).commit();
        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(i, RESULT_SETTINGS);
        }

        return super.onOptionsItemSelected(item);
    }

    public void pullBreakingNewsArticles(final ArticleAdapter adapter) {
        NewsQuery articleQuery = new NewsQuery(new Options(NewsSources.NEW_YORK_TIMES | NewsSources.THE_GUARDIAN, Subcategories.ECONOMY | Subcategories.SCIENCE));
        articleQuery.findBreakingNews(new NewsQueryCallback() {
            @Override
            public void returnNews(List<NewsObject> newsObjects) {
                //Log.d("meow", String.valueOf(newsObjects.size()));
                adapter.addArticles(newsObjects);
            }
        }, MainActivity.this);
    }

    public void pullArticles(final ArticleAdapter adapter) {
        NewsQuery articleQuery = new NewsQuery(new Options(NewsSources.NEW_YORK_TIMES | NewsSources.THE_GUARDIAN, Subcategories.ECONOMY | Subcategories.SCIENCE));
        articleQuery.getSpecificCategory(new NewsQueryCallback() {
            @Override
            public void returnNews(List<NewsObject> newsObjects) {
                adapter.addArticles(newsObjects);
            }
        }, MainActivity.this);
    }

}
