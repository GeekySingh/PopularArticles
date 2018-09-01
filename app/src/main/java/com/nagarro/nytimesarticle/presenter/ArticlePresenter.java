package com.nagarro.nytimesarticle.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.nagarro.nytimesarticle.contract.ArticleContract;
import com.nagarro.nytimesarticle.dto.ArticleResponseDto;
import com.nagarro.nytimesarticle.intf.IBaseView;
import com.nagarro.nytimesarticle.model.ArticleDataModel;
import com.nagarro.nytimesarticle.network.NetworkManager;
import com.nagarro.nytimesarticle.network.ResponseListener;
import com.nagarro.nytimesarticle.network.RetrofitCallback;
import com.nagarro.nytimesarticle.service.ArticleService;

import java.util.List;

/**
 * Article presenter to fetch list of articles from
 * network and send result to ArticleContract.View
 */
public class ArticlePresenter extends ArticleContract.Presenter {

    private final IBaseView mView;
    private final ArticleService mArticleService;
    private MutableLiveData<List<ArticleDataModel>> mArticleListLiveData;

    public ArticlePresenter(Context context, IBaseView view) {
        super(context, view);
        this.mView = view;
        mArticleService = NetworkManager.getInstance(context).createService(ArticleService.class);
    }

    @Override
    public LiveData<List<ArticleDataModel>> getArticleListObserver() {
        mArticleListLiveData = new MutableLiveData<>();
        getArticleList();
        return mArticleListLiveData;
    }

    /**
     * Fetches article list from network
     */
    private void getArticleList() {
        // show progress dialog
        mView.showProgressDialog();
        // fetch article list from network
        mArticleService.getArticleList()
                .enqueue(new RetrofitCallback<>(
                        new ResponseListener<ArticleResponseDto>() {
                            @Override
                            public void onSuccess(ArticleResponseDto responseDto) {
                                // hide progress dialog
                                mView.hideProgressDialog();
                                // send data to observer
                                mArticleListLiveData.setValue(responseDto.getArticleList());
                            }

                            @Override
                            public void onError(int errorType, String error) {
                                // hide progress dialog
                                mView.hideProgressDialog();
                                // show error message
                                mView.showError(errorType, error);
                            }
                        }));
    }
}
