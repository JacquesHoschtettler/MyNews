package com.hoschtettler.jacques.mynews.Controllers.Fragments;

import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hoschtettler.jacques.mynews.Models.FreeSubject.Doc;
import com.hoschtettler.jacques.mynews.Models.FreeSubject.FreeSubjectStructure;
import com.hoschtettler.jacques.mynews.Models.FreeSubject.Response;
import com.hoschtettler.jacques.mynews.Models.News;
import com.hoschtettler.jacques.mynews.Models.NewsViewModel;
import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Utils.NewsStreams;
import com.hoschtettler.jacques.mynews.Views.NewsAdapter;

import java.util.ArrayList;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class SearchArticlesFragment extends NewsPage {
    private Disposable mDisposable ;
    private Response mFreeSubjectResults;
    private ArrayList<News> mNews;
    private NewsViewModel mNewsViewModel ;

    // Required empty constructor
    public SearchArticlesFragment() {
    }

    @Override
    protected void LoadingNews()
    {
        final String queryTerm ;
        final String formattedBeginDate ;
        final String formattedEndDate ;
        final String formattedQueryDomains ;

        mNewsViewModel = new NewsViewModel() ;
        mNewsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);

        queryTerm = mNewsViewModel.getQueryTerm() ;
        formattedBeginDate = mNewsViewModel.getFormattedBeginDate() ;
        formattedEndDate = mNewsViewModel.getFormattedEndDate() ;
        formattedQueryDomains = mNewsViewModel.getFormattedQueryDomains() ;

        mDisposable = NewsStreams.SearchArticlesStream(0, queryTerm, formattedBeginDate,
                formattedEndDate, formattedQueryDomains)
                   .subscribeWith(new DisposableObserver<FreeSubjectStructure>() {
                    @Override
                    public void onNext(FreeSubjectStructure freeSubjectStructure) {
                        mFreeSubjectResults = freeSubjectStructure.getResponse();
                        if (mFreeSubjectResults.getDocs().size() != 0)
                        {
                            UpDateAlreadyArticlesList();
                            UpdateRecyclerView();
                            mNewsAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            String message = getString(R.string.none_articles_found ) ;
                            message += queryTerm ;
                            if (formattedBeginDate != "") {
                                message += getString(R.string.search_and_notification_begin_date);
                                message += mNewsViewModel.getBeginDate() ;
                            }
                            if (formattedEndDate != "") {
                                message += getString(R.string.search_article_end_date);
                                message += mNewsViewModel.getEndDate() ;
                            }
                            message += getString(R.string.article_query_filters) ;
                            message += formattedQueryDomains.substring(1,
                                                formattedQueryDomains.length()-1) ;
                            for (int i = 0 ; i <1 ; i++) {
                                Toast.makeText(getContext(), message, Toast.LENGTH_LONG)
                                        .show();
                            }
                            mNewsViewModel.incrementingUnsuccessfulRequestCounter();
                        }
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

    @Override
    protected int GetWindowNumber()
    {
        return 4 ;
    }


    // To remove the already articles that are not yet published.
    protected void UpDateAlreadyArticlesList()
    {
        boolean toRemoved = true ;
        for (String urlToTest : mNewsViewModel.getAlreadyReadArticlesList(4) )
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
                mNewsViewModel.removeAlreadyArticleUrl(urlToTest, 4) ;
            }
        }
    }


    public void onDestroy()
    {
        super.onDestroy();
        disposeWhenDestroy(mDisposable) ;
    }
}
