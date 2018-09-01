package com.nagarro.nytimesarticle.view.activity;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.nagarro.nytimesarticle.R;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * UTC for Article list screen.
 */
@RunWith(AndroidJUnit4.class)
public class ArticleListActivityTest {

    @Rule
    public ActivityTestRule<ArticleListActivity> mActivityRule =
            new ActivityTestRule<>(ArticleListActivity.class);

//    private ArticlePresenter mPresenter;

    @Test
    public void showProgressDialogTest() {
        onView(withId(R.id.progress_bar)).perform(setViewVisibility(true));
//        onView(withId(R.id.progress_bar)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void hideProgressDialogTest() {
        onView(withId(R.id.progress_bar)).perform(setViewVisibility(false));
    }

    @Test
    public void fetchArticleList() {

    }

    @Test
    public void showNetworkErrorTest() {
        onView(withText("No network available!"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
//                .perform(click());
//        onView(withText("No network available!")).check(matches(isDisplayed()));
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