package com.hoschtettler.jacques.mynews.Controllers.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.hoschtettler.jacques.mynews.Models.MostPopular.MostPopularResult;
import com.hoschtettler.jacques.mynews.Models.MostPopular.MostPopularStructure;
import com.hoschtettler.jacques.mynews.Models.News;
import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Utils.NewsAdapter;
import com.hoschtettler.jacques.mynews.Utils.NewsStreams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


public class MostPopularFragment extends NewsPage {

    private Disposable mDisposable ;
    private MostPopularStructure mMostPopularStructure ;
    private List<MostPopularResult> mMostPopularResults ;
    private ArrayList<News> mNews ;

    private NewsAdapter mNewsAdapter ;

    @BindView(R.id.fragment_most_popular_recycler_view) RecyclerView mRecyclerView ;

    // Required empty constructor
    public MostPopularFragment() {}


    @Override
    public NewsPage newsInstance() {
        return new MostPopularFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_most_popular;
    }

    @Override
    protected void LoadingNews() {
            this.mDisposable = NewsStreams.MostPopularStream(0)
                    .subscribeWith(new DisposableObserver<MostPopularStructure>() {
                        @Override
                        public void onNext(MostPopularStructure mostPopularStructure) {
                            mMostPopularResults = mostPopularStructure.getResults() ;
                            UpdateRecyclerView();
                            mNewsAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("News","MostPopular.LoadingNews : Error : " + e.getMessage()) ;
                        }

                        @Override
                        public void onComplete() {
                        }
                    });

    }


    private void UpdateRecyclerView ()
    {
        for (MostPopularResult result : mMostPopularResults) {
            News news = new News();
            if (result.getMedia().size() != 0) {
                news.setImageView(result.getMedia().get(0).getMediaMetadata().get(0).getUrl());
            } else {
                news.setImageView("");
            }

            news.setTitle(result.getSection() + "/");
            news.setText(result.getTitle());
            news.setUrl(result.getUrl());
            news.setDate(super.FrenchDate(result.getPublishedDate()));
            mNews.add(news);
        }

    }


    public void onDestroy()
    {
        super.onDestroy();
        disposeWhenDestroy(mDisposable) ;
    }

    @Override
    protected void AdapterConfiguration()
    {
        mNews = new ArrayList<>();
        mMostPopularResults = new ArrayList<>() ;

        mNewsAdapter = new NewsAdapter(mNews, Glide.with(this)) ;
        this.mRecyclerView.setAdapter(mNewsAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



}
