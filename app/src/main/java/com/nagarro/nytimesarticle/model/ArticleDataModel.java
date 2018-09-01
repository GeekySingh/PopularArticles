package com.nagarro.nytimesarticle.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Model to represents article data
 */
public class ArticleDataModel {

    @SerializedName("url")
    @Expose
    private String mArticleUrl;

    @SerializedName("adx_keywords")
    @Expose
    private String mKeywords;

    @SerializedName("byline")
    @Expose
    private String mAuthor;

    @SerializedName("title")
    @Expose
    private String mTitle;

    @SerializedName("abstract")
    @Expose
    private String mDescription;

    @SerializedName("published_date")
    @Expose
    private String mDate;

    @SerializedName("media")
    @Expose
    private List<ArticleMediaModel> mArticleMediaList = new ArrayList<>();

    public String getArticleUrl() {
        return mArticleUrl;
    }

    public String getKeywords() {
        return mKeywords;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getDate() {
        return mDate;
    }

    @NonNull
    public List<ArticleMediaModel> getArticleMediaList() {
        return mArticleMediaList;
    }
}
