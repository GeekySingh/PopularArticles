package com.nagarro.nytimesarticle.network;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * To manage retrofit calls and send response to caller
 */
public class RetrofitCallback<T> implements retrofit2.Callback<T> {

    private static final String ERROR = "error";

    private ResponseListener mListener;

    public RetrofitCallback(ResponseListener<T> listener) {
        this.mListener = listener;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (call.isCanceled() || mListener == null) {
            return;
        }
        final T t = response.body();

        if (response.isSuccessful() && t != null) {
            mListener.onSuccess(t);
        } else {
            try {
                mListener.onError(ErrorType.UNKNOWN, getErrorMessage(response.errorBody().string()));
            } catch (IOException | NullPointerException e) {
                mListener.onError(ErrorType.UNKNOWN, null);
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable throwable) {
        if (call.isCanceled() || mListener == null) {
            return;
        }

        if (throwable instanceof UnknownHostException) {
            mListener.onError(ErrorType.NO_NETWORK,"Internet not available!");
        } else if (throwable instanceof EOFException) {
            mListener.onError(ErrorType.BAD_RESPONSE, "Bad network response");
        } else if (throwable instanceof SocketTimeoutException) {
            mListener.onError(ErrorType.SERVER_TIMEOUT, "Server timeout");
        } else {
            mListener.onError(ErrorType.UNKNOWN, throwable.getMessage());
        }
    }

    private String getErrorMessage(String response) {
        if (TextUtils.isEmpty(response)) return response;
        try {
            final JSONObject object = new JSONObject(response);
            if (object.has(ERROR)) {
                return object.getString(ERROR);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
