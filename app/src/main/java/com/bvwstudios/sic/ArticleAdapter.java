package com.bvwstudios.sic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by bradley on 25/6/15.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<NewsArticle> mArticles;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvTitle;
        protected TextView tvSource;
        protected TextView tvTime;
        protected ImageView ivThumbnail;

        public ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.titleText);
            tvSource = (TextView) v.findViewById(R.id.sourceText);
            tvTime = (TextView) v.findViewById(R.id.timeText);

            ivThumbnail = (ImageView) v.findViewById(R.id.thumbnailImage);
        }
    }

    public ArticleAdapter(List<NewsArticle> articles) {
        mArticles = articles;
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        NewsArticle article = mArticles.get(position);
        viewHolder.tvTitle.setText(article.getTitle());
        Date date = article.getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM");
        viewHolder.tvTime.setText(df.format(date));
        viewHolder.tvSource.setText(article.getSource().getName());
        //let's leave image alone first
    }

    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.article_cardview, parent, false);

        return new ViewHolder(v);
    }

    public void addArticles(List<NewsArticle> articles) {
        mArticles.addAll(articles);
        notifyDataSetChanged();
    }

    public void addArticle(NewsArticle article) {
        mArticles.add(article);
        notifyItemInserted(mArticles.size() - 1);
    }

    public void setArticle(int position, NewsArticle article) {
        mArticles.set(position, article);
        notifyItemChanged(position);
    }

    public NewsArticle getArticle(int position) {
        return mArticles.get(position);
    }
}
