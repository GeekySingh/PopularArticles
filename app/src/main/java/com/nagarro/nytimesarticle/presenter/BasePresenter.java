package com.nagarro.nytimesarticle.presenter;

import android.content.Context;

import com.nagarro.nytimesarticle.intf.IBaseView;

/**
 * Base class for presenter
 */
public abstract class BasePresenter<T extends IBaseView> {

    public BasePresenter(Context context, IBaseView view) {
    }
}