package com.nagarro.nytimesarticle.view.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.nagarro.nytimesarticle.intf.IBaseView;

/**
 * Base activity to be extended by all activities
 * to use common properties.
 */
abstract class BaseActivity extends AppCompatActivity
    implements
        IBaseView {

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showError(int errorType, String error) {

    }
}
