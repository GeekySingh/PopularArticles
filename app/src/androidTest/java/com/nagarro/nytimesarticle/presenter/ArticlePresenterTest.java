package com.nagarro.nytimesarticle.presenter;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.nagarro.nytimesarticle.dto.ArticleResponseDto;
import com.nagarro.nytimesarticle.model.ArticleDataModel;
import com.nagarro.nytimesarticle.network.ResponseListener;
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

import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        ArticlePresenter presenter = spy(mPresenter);

        when(presenter.getArticleListObserver()).thenReturn(new MutableLiveData<List<ArticleDataModel>>());

        presenter.getArticleList();
        ResponseListener<ArticleResponseDto> listener = presenter.getNetworkInterface();
        ArticleResponseDto dto = new ArticleResponseDto();
        listener.onSuccess(dto);

        verify(mView).hideProgressDialog();
    }

    @Test
    public void getArticleList_failure() {
        ArticlePresenter presenter = spy(mPresenter);
        presenter.getArticleList();
        int errorId = -1;
        String errorMsg = "Something went wrong.";
        ResponseListener listener = presenter.getNetworkInterface();
        listener.onError(errorId, errorMsg);

        verify(mView).hideProgressDialog();
        verify(mView).showError(errorId, errorMsg);
    }

    @After
    public void tearDown() throws Exception {
        mPresenter.dispose();
        mPresenter = null;
    }
}