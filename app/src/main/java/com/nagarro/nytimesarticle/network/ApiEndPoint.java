package com.nagarro.nytimesarticle.network;

/**
 * Api endpoints
 */
public interface ApiEndPoint {

    String API_VERSION  = "v2";
    String SECTION      = "all-sections";
    String PERIOD       = "7";

    /**
     * Base url for all apis
     */
    String BASE_URL     = "http://api.nytimes.com/";

    /**
     * Article url
     */
    String ARTICLE_URL  = BASE_URL + "svc/mostpopular/" + API_VERSION + "/mostviewed/" + SECTION + "/" + PERIOD + ".json?api-key=7d72edfbcfcc4adab66ada84b3181841";
}
