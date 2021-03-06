package com.hoschtettler.jacques.mynews.Controllers.Fragments;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.hoschtettler.jacques.mynews.Models.FreeSubject.Doc;
import com.hoschtettler.jacques.mynews.Models.FreeSubject.FreeSubjectStructure;
import com.hoschtettler.jacques.mynews.Models.FreeSubject.Response;
import com.hoschtettler.jacques.mynews.Models.News;
import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Views.NewsAdapter;
import com.hoschtettler.jacques.mynews.Utils.NewsStreams;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class

ScienceFragment extends NewsPage {
    private Disposable mDisposable;
    private Response mSciencetResults;
    private ArrayList<News> mNews;

    // Required empty constructor
    public ScienceFragment() {
    }

    @Override
    protected void LoadingNews() {
        this.mDisposable = NewsStreams.ScienceStream(0)
                .subscribeWith(new DisposableObserver<FreeSubjectStructure>() {
                    @Override
                    public void onNext(FreeSubjectStructure freeSubjectStructure) {
                        mSciencetResults = freeSubjectStructure.getResponse();
                        UpDateAlreadyReadArticlesList();
                        UpdateRecyclerView();
                        mNewsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("News", "Science.LoadingNews : Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    @Override
    protected int GetWindowNumber()
    {
        return 2 ;
    }

    @Override
    protected void AdapterConfiguration() {
        mNews = new ArrayList<>();
        mSciencetResults = new Response() ;

        mNewsAdapter = new NewsAdapter(mNews, Glide.with(getActivity())) ;
        this.mRecyclerView.setAdapter(mNewsAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // To remove the already articles that are not yet published.
    protected void UpDateAlreadyReadArticlesList()
    {
        ArrayList<String> urlToRemoved = new ArrayList<>() ;

        for (String urlToTest : mNewsViewModel.getAlreadyReadArticlesList(GetWindowNumber()) )
        {
            boolean toRemoved = true ;
            for ( Doc result : mSciencetResults.getDocs())
            {
                if(urlToTest.equals(result.getWebUrl()))
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
    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }


    private void UpdateRecyclerView() {
        for (Doc result : mSciencetResults.getDocs()) {
            News news = new News();
            if (result.getMultimedia().size() != 0) {
                news.setImageView("https://static01.nyt.com/"+result.getMultimedia().get(2).getUrl());
            } else {
                news.setImageView("");
            }

            news.setTitle(result.getNewsDesk() + "/");
            news.setText(result.getHeadline().getMain());
            news.setUrl(result.getWebUrl());
            if(isArticleAlreadyRead(result.getWebUrl()))
            {
                news.setBackground(R.color.colorPrimaryLight) ;
                news.setAlreadyRead(true);
            }

            news.setDate(super.FrenchDate(result.getPubDate()));
            mNews.add(news);
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
        disposeWhenDestroy(mDisposable) ;
    }

}
