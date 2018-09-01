package com.nagarro.nytimesarticle.view.fragment;

import android.app.Activity;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(ARTICLE_URL)) {
            // get article url
            final String articleUrl = bundle.getString(ARTICLE_URL);

            final Activity activity = this.getActivity();
            if (activity != null) {
                final WebView webView = activity.findViewById(R.id.web_view);
                if(webView != null) {
                    webView.loadUrl(articleUrl);
                }
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.article_detail, container, false);

//        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.article_detail)).setText(mItem.details);
//        }

        return rootView;
    }
}
