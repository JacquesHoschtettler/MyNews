package com.hoschtettler.jacques.mynews.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
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

    public void upDateNews(News newsItem, RequestManager glide)
    {
        if (newsItem.getMediaUrl() != "") {
            glide.load(newsItem.getMediaUrl()).into(mNewsImage);
        } else
        {
            mNewsImage.setImageResource(R.mipmap.poweredby_nytimes_30a);
        }
        mNewsTitle.setText(newsItem.getTitle()) ;
        mNewsDate.setText(newsItem.getDate()) ;
        mNewsArticle.setText(newsItem.getText()) ;
    }
}
