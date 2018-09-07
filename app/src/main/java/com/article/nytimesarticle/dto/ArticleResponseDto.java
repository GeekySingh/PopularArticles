package com.article.nytimesarticle.dto;

import com.google.gson.annotations.SerializedName;
import com.article.nytimesarticle.model.ArticleDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Dto to carry articles list data received from server
 */
public class ArticleResponseDto {

    @SerializedName("results")
    private List<ArticleDataModel> mArticleList = new ArrayList<>();

    /**
     * Returns article list carried by this dto.
     * @return article list
     */
    public List<ArticleDataModel> getArticleList() {
        return mArticleList;
    }
}
