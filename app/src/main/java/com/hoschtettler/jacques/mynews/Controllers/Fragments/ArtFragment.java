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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtFragment extends NewsPage {
    private Disposable mDisposable ;
    private Response mFreeSubjectResults;
    private ArrayList<News> mNews;

    // Required empty constructor
    public ArtFragment() {
    }

    @Override
    protected void LoadingNews()
    {

        mDisposable = NewsStreams.ArtsStream(0)
                .subscribeWith(new DisposableObserver<FreeSubjectStructure>() {
                    @Override
                    public void onNext(FreeSubjectStructure freeSubjectStructure) {
                        mFreeSubjectResults = freeSubjectStructure.getResponse();
                        UpDateAlreadyArticlesList();
                        UpdateRecyclerView();
                        mNewsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("News", "FreeSubject.LoadingNews : Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    protected int GetWindowNumber()
    {
        return 3 ;
    }


    @Override
    protected void AdapterConfiguration() {
        mNews = new ArrayList<>();
        mFreeSubjectResults = new Response() ;

        mNewsAdapter = new NewsAdapter(mNews, Glide.with(getActivity())) ;
        this.mRecyclerView.setAdapter(mNewsAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void UpdateRecyclerView() {
        for (Doc result : mFreeSubjectResults.getDocs()) {
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
            }

            news.setDate(super.FrenchDate(result.getPubDate()));
            mNews.add(news);
        }
    }

    // To remove the already articles that are not yet published.
    protected void UpDateAlreadyArticlesList()
    {
        boolean toRemoved = true ;
        for (String urlToTest : mNewsViewModel.getAlreadyReadArticlesList(GetWindowNumber()) )
        {
            for ( Doc result : mFreeSubjectResults.getDocs())
            {
                if(urlToTest.equals(result.getWebUrl()))
                {
                    toRemoved = false ;
                }
            }
            if (toRemoved)
            {
                mNewsViewModel.removeAlreadyArticleUrl(urlToTest,GetWindowNumber()) ;
            }
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
        disposeWhenDestroy(mDisposable) ;
    }

}
