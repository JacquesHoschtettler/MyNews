package com.hoschtettler.jacques.mynews.Views;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoschtettler.jacques.mynews.Models.News;
import com.hoschtettler.jacques.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsViewHolder extends RecyclerView.ViewHolder
{
    @BindView(R.id.news_item_image) ImageView mNewsImage ;
    @BindView(R.id.news_item_title) TextView mNewsTitle ;
    @BindView(R.id.news_item_date) TextView mNewsDate ;
    @BindView(R.id.news_item_article) TextView mNewsArticle ;

    public NewsViewHolder(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this,itemView) ;
    }

    public void upDateNews(News newsItem)
    {
        mNewsImage.setImageDrawable(newsItem.getImageView()) ;
        mNewsTitle.setText(newsItem.getTitle()) ;
        mNewsDate.setText(newsItem.getDate()) ;
        mNewsArticle.setText(newsItem.getText()) ;
    }
}
