package com.nagarro.nytimesarticle.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Model to show article media, i.e. thumbnails
 * and their sizes.
 */
public class ArticleMediaModel {

    @SerializedName("media-metadata")
    @Expose
    private List<Media> mMediaData = new ArrayList<>();

    @NonNull
    public List<Media> getMediaData() {
        return mMediaData;
    }

    public class Media {

        @SerializedName("url")
        @Expose
        private String mImageUrl;

        public String getImageUrl() {
            return mImageUrl;
        }

    }
}
