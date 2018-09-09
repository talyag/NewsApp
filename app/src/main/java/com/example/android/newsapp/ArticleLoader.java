package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Article>>{

    public static final String LOG_TAG = ArticleLoader.class.getName();

    private String mUrl;

    public ArticleLoader(Context context, String url) {
        super(context);
        mUrl = url;

    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "onStartLoading() has been called");
        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {
        Log.i(LOG_TAG, "Test: loadInBackground(). Background thread active");
        if (mUrl == null) {
            return null;
        }

        List<Article> articles = QueryUtils.fetchArticleData(mUrl);
        return articles;
    }

}
