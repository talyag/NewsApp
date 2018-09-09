package com.example.android.newsapp;

public class Article {

    // Title of the article
    private String mArticleTitle;

    // Name of the section within Guardian API
    private String mSectionName;

    // Date the article was published.
    private String mPublicationDate;

    // Name of the author
    private String mAuthorName;

    // Website URL of the article
    private String mUrl;

    /**
     * Constructs a new {@link Article} object.
     *
     * @param articleTitle is the name of the article
     * @param sectionName is the section the article is from
     * @param publicationDate is the date when the article was published
     * @param authorName is the name of the author
     * @param url is the url of the article
     *
     */
    public Article(String articleTitle, String sectionName, String publicationDate,
                   String authorName, String url)
    {
        mArticleTitle = articleTitle;
        mSectionName = sectionName;
        mPublicationDate = publicationDate;
        mAuthorName = authorName;
        mUrl = url;

    }

    /** Get the article title value */
    public String getArticleTitle() {
        return mArticleTitle;
    }

    /** Get the section name value */
    public String getSectionName() {
        return mSectionName;
    }

    /** Get the publication date value */
    public String getPublicationDate() {
        return mPublicationDate;
    }

    /** Get the author name value */
    public String getAuthorName() {
        return mAuthorName;
    }

    /** Get the article url value */
    public String getUrl() {
        return mUrl;
    }




}

