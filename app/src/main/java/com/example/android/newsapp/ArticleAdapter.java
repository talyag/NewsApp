package com.example.android.newsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ArticleAdapter extends ArrayAdapter<Article> {

    /**
     * @param context The current context. Used to inflate the layout file.
     * @param articles A list of article objects
     */
    public ArticleAdapter(Activity context, ArrayList<Article> articles) {
        super(context, 0, articles);

    }

    /**
     * @param position    The position in the list.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup used for inflation.
     * @return The View for the position in the AdapterView.
     */

    public View getView(int position, View convertView, ViewGroup parent) {
        // Either reuse view or inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        Article currentArticle = getItem(position);

        /**
         *  Get and set article title
         * */
        // Find the TextView with view ID article_title
        TextView titleView = (TextView) listItemView.findViewById(R.id.article_title);
        // Get the title from the current Article object and set text
        titleView.setText(currentArticle.getArticleTitle());

        /**
         *  Get and set author name
         * */
        // Find the TextView with view ID author_name
        TextView authorView = (TextView) listItemView.findViewById(R.id.author_name);
        // Get the author from the current Article object and set text
        authorView.setText(currentArticle.getAuthorName());

        /**
         *  Get and set section name
         * */
        // Find the TextView with view ID author_name
        TextView sectionView = (TextView) listItemView.findViewById(R.id.section_name);
        // Get the author from the current Article object and set text
        sectionView.setText(currentArticle.getSectionName());

        /**
         *  Get and set publication date
         * */
        // Find the TextView with view ID author_name
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);

        dateView.setText(currentArticle.getPublicationDate());

        // Return the list item view that is now showing the appropriate data
        return listItemView;

    }

}