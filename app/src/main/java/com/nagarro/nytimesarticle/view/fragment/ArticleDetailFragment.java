package com.nagarro.nytimesarticle.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.nagarro.nytimesarticle.R;
import com.nagarro.nytimesarticle.view.activity.ArticleDetailActivity;
import com.nagarro.nytimesarticle.view.activity.ArticleListActivity;

/**
 * A fragment representing a single Article detail screen.
 * This fragment is either contained in a {@link ArticleListActivity}
 * in two-pane mode (on tablets) or a {@link ArticleDetailActivity}
 * on handsets.
 */
public class ArticleDetailFragment extends Fragment {
    /**
     * The fragment argument representing the article url that this fragment
     * represents.
     */
    public static final String ARTICLE_URL = "article_url";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticleDetailFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.article_detail, container, false);

        final Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(ARTICLE_URL)) {
            // get article url
            final String articleUrl = bundle.getString(ARTICLE_URL);

            final WebView webView = rootView.findViewById(R.id.web_view);
            if (webView != null) {
                webView.loadUrl(articleUrl);
            }
        }

        return rootView;
    }
}
