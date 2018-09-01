package com.nagarro.nytimesarticle.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nagarro.nytimesarticle.R;
import com.nagarro.nytimesarticle.model.ArticleDataModel;
import com.nagarro.nytimesarticle.view.activity.ArticleDetailActivity;
import com.nagarro.nytimesarticle.view.activity.ArticleListActivity;
import com.nagarro.nytimesarticle.view.fragment.ArticleDetailFragment;

import java.util.List;

public class ArticleListAdapter
        extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    private final ArticleListActivity mParentActivity;
    private final List<ArticleDataModel> mValues;
    private final boolean mTwoPane;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ArticleDataModel item = (ArticleDataModel) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
//                arguments.putString(ArticleDetailFragment.ARG_ITEM_ID, item.id);
                ArticleDetailFragment fragment = new ArticleDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.article_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, ArticleDetailActivity.class);
//                intent.putExtra(ArticleDetailFragment.ARG_ITEM_ID, item.id);

                context.startActivity(intent);
            }
        }
    };

    public ArticleListAdapter(ArticleListActivity parent,
                              List<ArticleDataModel> items,
                              boolean twoPane) {
        mValues = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ArticleDataModel model = mValues.get(position);
        holder.setArticle(model);
        holder.itemView.setTag(model);
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setArticleList(List<ArticleDataModel> articleList) {
        mValues.clear();
        mValues.addAll(articleList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImgNews;
        private final TextView mTxtHeadline;
        private final TextView mTxtDescription;
        private final TextView mTxtAuthor;
        private final TextView mTxtDate;

        ViewHolder(View view) {
            super(view);
            mImgNews = view.findViewById(R.id.img_news);
            mTxtHeadline = view.findViewById(R.id.txt_title);
            mTxtDescription = view.findViewById(R.id.txt_description);
            mTxtAuthor = view.findViewById(R.id.txt_author);
            mTxtDate = view.findViewById(R.id.txt_date);
        }

        void setArticle(ArticleDataModel article) {
            // load article image
            final String imageUrl = article.getArticleMediaList().get(0).getMediaData().get(0).getImageUrl();
            Glide.with(mImgNews).load(imageUrl).into(mImgNews);
            // show article details
            mTxtHeadline.setText(article.getTitle());
            mTxtDescription.setText(article.getDescription());
            mTxtAuthor.setText(article.getAuthor());
            mTxtDate.setText(article.getDate());
        }
    }
}