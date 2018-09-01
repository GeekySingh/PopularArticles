package com.nagarro.nytimesarticle.service;

import com.nagarro.nytimesarticle.dto.ArticleResponseDto;
import com.nagarro.nytimesarticle.network.ApiEndPoint;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Article api to fetch articles from NY Times server
 */
public interface ArticleService {

    @GET(ApiEndPoint.ARTICLE_URL)
    Call<ArticleResponseDto> getArticleList();

}
