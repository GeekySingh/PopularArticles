package com.article.nytimesarticle.presenter;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.Looper;

import com.article.nytimesarticle.dto.ArticleResponseDto;
import com.article.nytimesarticle.model.ArticleDataModel;
import com.article.nytimesarticle.network.NetworkManager;
import com.article.nytimesarticle.network.ResponseListener;
import com.article.nytimesarticle.network.RetrofitCallback;
import com.article.nytimesarticle.service.ArticleService;
import com.article.nytimesarticle.view.IBaseView;

import java.util.List;

/**
 * Article presenter to fetch list of articles from
 * network and send result to ArticleContract.View
 */
public class ArticlePresenter
    implements
        Presenter {

    private final IBaseView mView;
    private final ArticleService mArticleService;
    private MutableLiveData<List<ArticleDataModel>> mArticleListLiveData;

    public ArticlePresenter(Context context, IBaseView view) {
        mArticleService = NetworkManager.getInstance(context).createService(ArticleService.class);
        this.mView = view;
        this.mArticleListLiveData = new MutableLiveData<>();
    }

    /**
     * eturns live data observer to observe for article list changes.
     *
     * @return observer to observe data changes
     */
    public MutableLiveData<List<ArticleDataModel>> getArticleListObserver() {
        return mArticleListLiveData;
    }

    /**
     * Fetches article list from network
     */
    public void getArticleList() {
        // show progress dialog
        mView.showProgressDialog();
        // fetch article list from network
        mArticleService.getArticleList()
                .enqueue(new RetrofitCallback<>(mListener));

    }

    public ResponseListener<ArticleResponseDto> getNetworkInterface() {
        return mListener;
    }

    private ResponseListener<ArticleResponseDto> mListener = new ResponseListener<ArticleResponseDto>() {
        @Override
        public void onSuccess(ArticleResponseDto responseDto) {
            // hide progress dialog
            mView.hideProgressDialog();
            // send data to observer
            if(Looper.myLooper() == Looper.getMainLooper()) {
                getArticleListObserver().setValue(responseDto.getArticleList());
            } else {
                getArticleListObserver().postValue(responseDto.getArticleList());
            }
        }

        @Override
        public void onError(int errorType, String error) {
            // hide progress dialog
            mView.hideProgressDialog();
            // show error message
            mView.showError(errorType, error);
        }
    };

    @Override
    public void dispose() {
        mArticleListLiveData = null;
    }
}
