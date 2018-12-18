package com.hoschtettler.jacques.mynews.Controllers.Fragments;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.hoschtettler.jacques.mynews.Models.MostPopular.MostPopularResult;
import com.hoschtettler.jacques.mynews.Models.MostPopular.MostPopularStructure;
import com.hoschtettler.jacques.mynews.Models.News;
import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Utils.NewsStreams;
import com.hoschtettler.jacques.mynews.Views.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
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
                            UpDateAlreadyReadArticlesList() ;
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

    @Override
    protected int GetWindowNumber()
    {
        return 1 ;
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
            if(isArticleAlreadyRead(result.getUrl()))
            {
                news.setBackground(R.color.colorPrimaryLight) ;
            }

            news.setDate(super.FrenchDate(result.getPublishedDate()));
            mNews.add(news);
        }
    }

    // To remove the already articles that are not yet published.
    protected void UpDateAlreadyReadArticlesList()
    {
        ArrayList<String> urlToRemoved = new ArrayList<>() ;

        for (String urlToTest : mNewsViewModel.getAlreadyReadArticlesList(GetWindowNumber()) )
        {
           boolean toRemoved = true ;
           for ( MostPopularResult result : mMostPopularResults)
           {
               if(urlToTest.equals(result.getUrl()))
               {
                   toRemoved = false ;
               }
           }
           if (toRemoved)
           {
               urlToRemoved.add(urlToTest) ;
            }
        }
         for (String urlToEliminate : urlToRemoved) {
             mNewsViewModel.removeAlreadyArticleUrl(urlToEliminate, GetWindowNumber());
        }
    }

    @Override
    protected void AdapterConfiguration()
    {
        mNews = new ArrayList<>();
        mMostPopularResults = new ArrayList<>() ;

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
