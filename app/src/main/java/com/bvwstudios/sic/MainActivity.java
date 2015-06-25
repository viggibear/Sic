package com.bvwstudios.sic;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<NewsArticle> pullArticles(){
        List <NewsArticle> newsArticleList = new LinkedList<>();
        NewsArticle top1 = new NewsArticle("In Belgium’s Strawberry Fields, Perfection’s in the Picking", "For many Belgians, Wépion has long been synonymous with strawberries. Carefully picked berries from this village, which has been in the strawberry growing business since the mid-17th century, are renowned for their fully ripe flavor. In Brussels, 50 miles to the northwest, Wépion berries fetch a premium price, roughly twice what the competition is going for.\n" +
                "\n" +
                "In recent years, though, the Wépion strawberry has faced stiff competition from growers within Belgium as well as from producers in the Netherlands, Spain and elsewhere. Yet despite being outgunned in both volume and price, the Wépion growers maintain a strong hold on their small share of the regional strawberry market.\n" +
                "\n" +
                "The growers rally around their brand, La Criée de Wépion, and remain a presence in supermarkets in Brussels and area outdoor markets during the late spring and much of the summer. The growers’ big selling point is that with a limited distribution radius they can wait to pick berries at peak ripeness, then quickly ship the fruit to stores in refrigerated trucks. Stocks of Wépion berries often sell out.", BitmapFactory.decodeResource(MainActivity.this.getResources(),
                R.drawable.belgium_pic), "google.com", "Breaking", new Date(), new NewsSource("NYTimes", "nytimes.com", BitmapFactory.decodeResource(MainActivity.this.getResources(),
                R.drawable.nytimes)));

        NewsArticle europe1 = new NewsArticle("What would the Kaiser say?", "FOR five centuries Berlin grew out from its political centre, the castle of the Hohenzollerns, as the dynasty rose from imperial electors of Brandenburg to kings of Prussia and finally emperors of Germany. The expanding edifice reflected this. Andreas Schlüter, a Baroque star, made it grand in the 18th century. Karl-Friedrich Schinkel, a 19th-century titan, surrounded it with neoclassical temples.", BitmapFactory.decodeResource(MainActivity.this.getResources(),
                R.drawable.europe_pic), "google.com", "Politics", new Date(), new NewsSource("Economist", "economist.com", BitmapFactory.decodeResource(MainActivity.this.getResources(),
                R.drawable.economist)));

        newsArticleList.add(top1);
        newsArticleList.add(europe1);

        return newsArticleList;

    }

}
