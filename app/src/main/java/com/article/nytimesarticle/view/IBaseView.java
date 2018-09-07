package com.article.nytimesarticle.view;

/**
 * Base view skeleton
 */
public interface IBaseView {

    /**
     * Shows progress dialog.
     */
    void showProgressDialog();

    /**
     * Hides progress dialog
     */
    void hideProgressDialog();

    /**
     * Shows error message to user
     * @param errorType error type
     * @param error error message
     */
    void showError(int errorType, String error);

}
