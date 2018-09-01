package com.nagarro.nytimesarticle.network;

/**
 * Interface to handle response received by network calls
 */
public interface ResponseListener<T> {

    /**
     * Called when api is successful and holds
     * response data
     * @param t response data
     */
    void onSuccess(T t);

    /**
     * Called when api got failed.
     * @param errorType reason for api failure
     * @param error error message
     */
    void onError(int errorType, String error);
}
