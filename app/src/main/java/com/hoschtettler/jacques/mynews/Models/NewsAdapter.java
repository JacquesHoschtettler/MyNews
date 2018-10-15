package com.hoschtettler.jacques.mynews.Models;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Views.NewsViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.hoschtettler.jacques.mynews.R.layout.news_item;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<News> mNews ;

    public NewsAdapter(List<News> news)
    {
        super() ;
        mNews = new ArrayList<>() ;
        mNews = news ;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext()) ;
        View view = inflater.inflate(news_item, parent, false) ;
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position)
    {
        holder.upDateNews(mNews.get(position));
    }


    @Override
    public int getItemCount() {
        return mNews.size() ;
    }
}
