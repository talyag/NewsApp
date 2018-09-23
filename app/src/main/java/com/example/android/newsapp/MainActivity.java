package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Article>>{

    public static final String LOG_TAG = MainActivity.class.getName();

    public static final int ARTICLE_LOADER_ID = -1;

    /** URL for a Guardian API query **/
    private static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/search?";

    /** URL for project, used as about option **/
    public static final String ABOUT_URL =
            "https://github.com/talyag/NewsApp";

    /** Adapter for the list of articles **/
    private ArticleAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    /** ProgressBar that is displayed when the list is loading */
    private ProgressBar mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // List View
        ListView articleListView = (ListView) findViewById(R.id.list);

        // Empty text view
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        articleListView.setEmptyView(mEmptyStateTextView);

        // Loading bar
        mLoadingBar = (ProgressBar) findViewById(R.id.loading_spinner);

        // Create a new adapter. Takes empty list of articles.
        mAdapter = new ArticleAdapter(this, new ArrayList<Article>());

        // Set the adapter on the {@link ListView}
        articleListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView. Sends an intent to a web browser
        // to open the selected article's web page
        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current article that was clicked on
                Article currentArticle = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri articleUri = Uri.parse(currentArticle.getUrl());

                // Create a new intent to view the article URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, articleUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);

            }
        });

        // Get a reference to the ConnectivityManager. Check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager
            LoaderManager loaderManager = getLoaderManager();
            // Initialize the loader
            loaderManager.initLoader(ARTICLE_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // Hide loading indicator
            mLoadingBar.setVisibility(View.GONE);
            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet);
        }

    }

    @Override
    public Loader<List<Article>> onCreateLoader (int i, Bundle bundle){
        Log.v(LOG_TAG, "onCreateLoader() has been called");

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String sectionName = sharedPrefs.getString(
                getString(R.string.settings_section_key),
                getString(R.string.settings_section_default)
        );

        // Breaks apart the passed URI string
        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);

        // Prepares the parsed baseUri to accept additional query parameters
        Uri.Builder uriBuilder = baseUri.buildUpon();

        // Append query parameter and its value.
        uriBuilder.appendQueryParameter("section", sectionName);
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("show-fields", "headline");
        uriBuilder.appendQueryParameter("api-key", getString(R.string.guardian_api_key));

        return new ArticleLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articles) {
        Log.v(LOG_TAG, "onLoadFinished() ha been called");
        mLoadingBar.setVisibility(View.GONE);

        // Set empty state text
        mEmptyStateTextView.setText(R.string.no_articles);

        mAdapter.clear();

        // If a valid list of {@link Article}s is available, add them
        if (articles != null && !articles.isEmpty()) {
            mAdapter.addAll(articles);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {

        mAdapter.clear();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Find item Id for the option selected
        switch (item.getItemId()) {

            // If actions_settings selected, create new intent and start settings
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;

            // If action_about selected, send intent to launch new activity and open ABOUT_URL
            case R.id.action_about:
                Uri helpUri = Uri.parse(ABOUT_URL);
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, helpUri);
                startActivity(websiteIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
