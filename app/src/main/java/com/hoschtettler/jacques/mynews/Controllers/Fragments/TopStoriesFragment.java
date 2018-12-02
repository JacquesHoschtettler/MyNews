package com.hoschtettler.jacques.mynews.Controllers.Fragments;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.hoschtettler.jacques.mynews.Models.News;
import com.hoschtettler.jacques.mynews.Models.TopStories.TopStoriesResult;
import com.hoschtettler.jacques.mynews.Models.TopStories.TopsStoriesStructure;
import com.hoschtettler.jacques.mynews.Views.NewsAdapter;
import com.hoschtettler.jacques.mynews.Utils.NewsStreams;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class TopStoriesFragment extends NewsPage {
    private Disposable mDisposable ;
    private List<TopStoriesResult> mTopStoriesResults ;
    private ArrayList<News> mNews ;

    // Required empty constructor
    public TopStoriesFragment() {}


    @Override
    protected void LoadingNews() {
        this.mDisposable = NewsStreams.TopStoriesStream(0)
                .subscribeWith(new DisposableObserver<TopsStoriesStructure>() {
                    @Override
                    public void onNext(TopsStoriesStructure topsStoriesStructure) {
                        mTopStoriesResults = topsStoriesStructure.getResults() ;
                        UpdateRecyclerView();
                        mNewsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("News","TopStories.LoadingNews : Error : " + e.getMessage()) ;
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    private void UpdateRecyclerView ()
    {
        for (TopStoriesResult result : mTopStoriesResults) {
            News news = new News();
            if (result.getMultimedia().size() != 0) {
                news.setImageView(result.getMultimedia().get(0).getUrl());
            } else {
                news.setImageView("");
            }

            news.setTitle(result.getSection() + "/"+result.getSubsection());
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
        mTopStoriesResults = new ArrayList<>() ;

        mNewsAdapter = new NewsAdapter(mNews, Glide.with(getActivity())) ;
        this.mRecyclerView.setAdapter(mNewsAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void onDestroy()
    {
        super.onDestroy();
        disposeWhenDestroy(mDisposable) ;
    }

}
