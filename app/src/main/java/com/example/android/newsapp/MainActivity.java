package com.example.android.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a fake list of articles.
        // TODO: remove arraylist for JSON data
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article(
                "Fortnite on Android",
                        "Games",
                "2018-08-07T11:30:46Z",
                        "Author Name",
                        "url"));
        articles.add(new Article(
                "From Kong to Kirby",
                "Games",
                "2018-08-08T13:27:37Z",
                "Author Name",
                "url"));
        articles.add(new Article(
                "Call of Duty Black Ops 4 beta",
                "Games",
                "2018-08-13T09:00:26Z",
                "Author Name",
                "url"));
        articles.add(new Article(
                "Octopath Traveller review",
                "Games",
                "2018-08-15T09:30:14Z",
                "Author Name",
                "url"));
        articles.add(new Article(
                "What video games in schools can teach us about learning",
                "Games",
                "2018-08-15T09:30:14Z",
                "Author Name",
                "url"));
        articles.add(new Article(
                "I am drawing from different sources",
                "Games",
                "2018-08-16T07:30:41Z",
                "Author Name",
                "url"));
        articles.add(new Article(
                "Parents are paying tutors to stop their kids getting owned at Fortnite",
                "Games",
                "2018-08-15T09:30:14Z",
                "Author Name",
                "url"));

        // Create a new {@link ArticleAdapter}, with {@link Activity} data source.
        ArticleAdapter adapter = new ArticleAdapter(this, articles);

        // Find the {@link ListView} in the layout
        ListView articleListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        articleListView.setAdapter(adapter);
    }
}
