package com.hoschtettler.jacques.mynews.Utils;

import com.hoschtettler.jacques.mynews.Models.FreeSubject.FreeSubjectStructure;
import com.hoschtettler.jacques.mynews.Models.MostPopular.MostPopularStructure;
import com.hoschtettler.jacques.mynews.Models.TopStories.TopsStoriesStructure;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInterface
{
// https://api.nytimes.com/svc/topstories/v2/home.json?api-key=965b938d9e72418291dc79bbee0b1084
    @GET ("topstories/v2/home.json?api-key=965b938d9e72418291dc79bbee0b1084")
    Observable<TopsStoriesStructure> getTopStories() ;

// https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/1.json?api-key=965b938d9e72418291dc79bbee0b1084
    @GET ("mostpopular/v2/mostviewed/all-sections/1.json?api-key=965b938d9e72418291dc79bbee0b1084")
    Observable<MostPopularStructure> getMostPopular() ;

// https://api.nytimes.com/svc/search/v2/articlesearch.json?fq=news_desk:("Technology" "Science")&sort=newest&api-key=965b938d9e72418291dc79bbee0b1084
    @GET ("search/v2/articlesearch.json?fq=news_desk:(\"Technology\" \"Science\")&sort=newest&api-key=965b938d9e72418291dc79bbee0b1084")
    Observable<FreeSubjectStructure> getScience() ;

// https://api.nytimes.com/svc/search/v2/articlesearch.json?fq=news_desk:("Arts")&sort=newest&api-key=965b938d9e72418291dc79bbee0b1084
    @GET ("search/v2/articlesearch.json?fq=news_desk:\"Arts\"&sort=newest&api-key=965b938d9e72418291dc79bbee0b1084")
    Observable<FreeSubjectStructure> getArt() ;

    // https://api.nytimes.com/svc/search/v2/articlesearch.json?sort=newest&api-key=965b938d9e72418291dc79bbee0b1084
    @GET ("search/v2/articlesearch.json?sort=newest&api-key=965b938d9e72418291dc79bbee0b1084")
    Observable<FreeSubjectStructure> getSearchSubject(@Query("q") String query,
                                                    @Query("begin_date") String beginDate, @Query("end_date") String endDate) ;



    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build() ;
}