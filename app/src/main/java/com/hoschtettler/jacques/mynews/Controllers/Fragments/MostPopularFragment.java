package com.hoschtettler.jacques.mynews.Controllers.Fragments;

import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.hoschtettler.jacques.mynews.Models.MostPopular.MostPopularResult;
import com.hoschtettler.jacques.mynews.Models.MostPopular.MostPopularStructure;
import com.hoschtettler.jacques.mynews.Models.News;
import com.hoschtettler.jacques.mynews.Utils.NewsAdapter;
import com.hoschtettler.jacques.mynews.Utils.NewsStreams;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


public class MostPopularFragment extends NewsPage
{
    private Disposable mDisposable ;
    private List<MostPopularResult> mMostPopularResults ;
    private ArrayList<News> mNews ;

 // Required empty constructor
    public MostPopularFragment() {}

    @Override
    protected void LoadingNews() {
            mDisposable = NewsStreams.MostPopularStream(0)
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


    @Override
    protected void AdapterConfiguration()
    {
        mNews = new ArrayList<>();
        mMostPopularResults = new ArrayList<>() ;

        mNewsAdapter = new NewsAdapter(mNews, Glide.with(this)) ;
        this.mRecyclerView.setAdapter(mNewsAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void onDestroy()
    {
        super.onDestroy();
        disposeWhenDestroy(mDisposable) ;
    }

}
