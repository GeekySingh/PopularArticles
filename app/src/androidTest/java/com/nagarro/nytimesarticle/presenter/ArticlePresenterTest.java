package com.nagarro.nytimesarticle.presenter;

import android.content.Context;

import com.nagarro.nytimesarticle.network.ErrorType;
import com.nagarro.nytimesarticle.view.IBaseView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

/**
 * UTC for Article presenter
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticlePresenterTest {

    @Rule
    public MockitoRule mMockitoRule = MockitoJUnit.rule();
    @Mock
    private IBaseView mView;
    @Mock
    private Context mContext;

    private ArticlePresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        mPresenter = new ArticlePresenter(mContext, mView);
    }

    @Test
    public void getArticleList_success() {
        mView.showProgressDialog();
        mPresenter.getArticleList();
        mView.hideProgressDialog();
    }

    @Test
    public void getArticleList_failure_no_network() {
        mView.showProgressDialog();
        mPresenter.getArticleList();
        mView.hideProgressDialog();
        mView.showError(ErrorType.NO_NETWORK, "Network not available!");
    }

    @Test
    public void getArticleList_failure_bad_response() {
        mView.showProgressDialog();
        mPresenter.getArticleList();
        mView.hideProgressDialog();
        mView.showError(ErrorType.BAD_RESPONSE, "Bad network response!");
    }

    @Test
    public void getArticleList_failure_server_timeout() {
        mView.showProgressDialog();
        mPresenter.getArticleList();
        mView.hideProgressDialog();
        mView.showError(ErrorType.SERVER_TIMEOUT, "Network not available!");
    }

    @After
    public void tearDown() throws Exception {
        mPresenter.dispose();
        mPresenter = null;
    }
}