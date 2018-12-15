package com.hoschtettler.jacques.mynews.Controllers.Fragments;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.hoschtettler.jacques.mynews.Models.News;
import com.hoschtettler.jacques.mynews.Models.TopStories.TopStoriesResult;
import com.hoschtettler.jacques.mynews.Models.TopStories.TopsStoriesStructure;
import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Utils.NewsStreams;
import com.hoschtettler.jacques.mynews.Views.NewsAdapter;

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
                        UpDateAlreadyArticlesList();
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

    @Override
    protected int GetWindowNumber()
    {
        return 0 ;
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
            if(isArticleAlreadyRead(result.getUrl()))
            {
                news.setBackground(R.color.colorPrimaryLight) ;
            }

            news.setDate(super.FrenchDate(result.getPublishedDate()));

            mNews.add(news);
        }
    }

    // To remove the already articles that are not yet published.
    protected void UpDateAlreadyArticlesList()
    {
        boolean toRemoved = true ;
        for (String urlToTest : mNewsViewModel.getAlreadyReadArticlesList(GetWindowNumber()) )
        {
            for ( TopStoriesResult result : mTopStoriesResults)
            {
                if(urlToTest.equals(result.getUrl()))
                {
                    toRemoved = false ;
                }
            }
            if (toRemoved)
            {
                mNewsViewModel.removeAlreadyArticleUrl(urlToTest, GetWindowNumber()) ;
            }
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
