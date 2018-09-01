package com.nagarro.nytimesarticle.view.activity;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.nagarro.nytimesarticle.R;
import com.nagarro.nytimesarticle.view.IBaseView;
import com.nagarro.nytimesarticle.model.ArticleDataModel;
import com.nagarro.nytimesarticle.presenter.ArticlePresenter;
import com.nagarro.nytimesarticle.view.adapter.ArticleListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Articles. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ArticleDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ArticleListActivity extends AppCompatActivity
        implements
        IBaseView {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private boolean mShouldExecuteOnResume = true;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private ArticlePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        toolbar.setLogo(R.mipmap.ic_launcher_round);

        if (findViewById(R.id.article_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        mProgressBar = findViewById(R.id.progress_bar);

        mRecyclerView = findViewById(R.id.article_list);
        assert mRecyclerView != null;
        setupRecyclerView(mRecyclerView);

        // init article presenter
        mPresenter = new ArticlePresenter(this, this);
        // setup data list observer
        setupDataObserver();
    }

    /**
     * Setup article list recycler view
     *
     * @param recyclerView recycler view
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ArticleListAdapter(this,
                new ArrayList<ArticleDataModel>(), mTwoPane));
    }

    /**
     * Setup article list data observer
     * to observe list changes
     */
    private void setupDataObserver() {
        mPresenter.getArticleListObserver()
                .observe(this, new Observer<List<ArticleDataModel>>() {
                    @Override
                    public void onChanged(@Nullable List<ArticleDataModel> articleList) {
                        final ArticleListAdapter adapter = (ArticleListAdapter) mRecyclerView.getAdapter();
                        adapter.setArticleList(articleList);
                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mShouldExecuteOnResume) {
            // fetch article list
            mPresenter.getArticleList();
            mShouldExecuteOnResume = false;
        }
    }

    @Override
    public void showProgressDialog() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressDialog() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(int errorType, String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.error_title));
        builder.setMessage(error);
        builder.setPositiveButton(getString(R.string.dialog_ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mPresenter.dispose();
    }
}