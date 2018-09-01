package com.nagarro.nytimesarticle.contract;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.nagarro.nytimesarticle.intf.IBaseView;
import com.nagarro.nytimesarticle.model.ArticleDataModel;
import com.nagarro.nytimesarticle.presenter.BasePresenter;

import java.util.List;

/**
 * Article contract to communicate between
 * article presenter and view
 */
public interface ArticleContract {

    abstract class Presenter extends BasePresenter {

        public Presenter(Context context, IBaseView view) {
            super(context, view);
        }

        public abstract LiveData<List<ArticleDataModel>> getArticleListObserver();
    }

}
