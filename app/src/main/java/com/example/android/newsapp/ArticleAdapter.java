package com.example.android.newsapp;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public static final String LOG_TAG = ArticleAdapter.class.getName();

    private static final String DATE_SEPARATOR = "T";

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

        // Find the article at the given position
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
         * Get and set publication date
         * **/
        String dateTime = new String(currentArticle.getPublicationDate());

        String publicationDate;

        if (dateTime.contains(DATE_SEPARATOR)) {
            String[] parts = dateTime.split(DATE_SEPARATOR);
            publicationDate = parts[0];

        } else {
            publicationDate = dateTime;
        }

        String formattedDate = formatDate(publicationDate);

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);

        dateView.setText(formattedDate);

        // Return the list item view that is now showing the appropriate data
        return listItemView;

    }

    /**
     * Convert date string from 2018-01-01 to Jan 1, 2018.
     */
    private String formatDate(String publicationDate) {

        // Initialize date variable
        Date date = null;

        // Define current format pattern of publicationDate
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            // Convert from String to date. If the format is incorrect, throw exception
            date = inputFormat.parse(publicationDate);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "failed to parse publication date");
        }

        // Define new format pattern
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM d, yyyy");

        // Convert date to new pattern
        String formattedDate = outputFormat.format(date);

        return formattedDate;
    }

}