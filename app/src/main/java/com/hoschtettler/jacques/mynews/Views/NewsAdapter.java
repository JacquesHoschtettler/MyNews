package com.hoschtettler.jacques.mynews.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.hoschtettler.jacques.mynews.Models.News;
import com.hoschtettler.jacques.mynews.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<News> mNews ;
    private RequestManager mGlide ;

    public NewsAdapter(List<News> news, RequestManager glide)
    {
        super() ;
        mNews = new ArrayList<>() ;
        mNews = news ;
        mGlide = glide ;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext()) ;
        View view = inflater.inflate(R.layout.news_item, parent, false) ;
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position)
    {
        holder.upDateNews(mNews.get(position), mGlide);
    }

    @Override
    public int getItemCount() {
        return mNews.size() ;
    }

    public String getUrl(int positon)
    {
        return mNews.get(positon).getUrl() ;
    }
}
