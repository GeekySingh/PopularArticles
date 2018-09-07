package com.article.nytimesarticle.view.activity;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.article.nytimesarticle.R;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * UTC for Article list screen.
 */
@RunWith(AndroidJUnit4.class)
public class ArticleListActivityTest {

    @Rule
    public ActivityTestRule<ArticleListActivity> mActivityRule =
            new ActivityTestRule<>(ArticleListActivity.class);

    @Test
    public void showProgressBar() {
        onView(withId(R.id.progress_bar)).perform(setViewVisibility(true));
    }

    @Test
    public void hideProgressBar() {
        onView(withId(R.id.progress_bar)).perform(setViewVisibility(false));
    }

    private static ViewAction setViewVisibility(final boolean value) {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(View.class);
            }

            @Override
            public void perform(UiController uiController, View view) {
                view.setVisibility(value ? View.VISIBLE : View.GONE);
            }

            @Override
            public String getDescription() {
                return "Show / Hide View";
            }
        };
    }
}