package com.nagarro.nytimesarticle.network;

/**
 * Interface to handle response received by network calls
 */
public interface ResponseListener<T> {

    void onSuccess(T t);
    void onError(int errorType, String error);
}
