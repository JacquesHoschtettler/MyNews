package com.hoschtettler.jacques.mynews.Utils;

import android.support.v4.media.session.PlaybackStateCompat.ErrorCode;

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
    @GET("search/v2/articlesearch.json?sort=newest&api-key=965b938d9e72418291dc79bbee0b1084")
    @ErrorCode
    Observable<FreeSubjectStructure> getSearchSubject11(
                @Query("q") String query,
                @Query("fq") String queryDomains,
                @Query("begin_date") String beginDate,
                @Query("end_date") String endDate);

    @GET("search/v2/articlesearch.json?sort=newest&api-key=965b938d9e72418291dc79bbee0b1084")
    @ErrorCode
    Observable<FreeSubjectStructure> getSearchSubject00(
                @Query("q") String query,
                @Query("fq") String queryDomains) ;

    @GET("search/v2/articlesearch.json?sort=newest&api-key=965b938d9e72418291dc79bbee0b1084")
    @ErrorCode
    Observable<FreeSubjectStructure> getSearchSubject10(
                @Query("q") String query,
                @Query("fq") String queryDomains,
                @Query("begin_date") String beginDate);

    @GET("search/v2/articlesearch.json?sort=newest&api-key=965b938d9e72418291dc79bbee0b1084")
    @ErrorCode
    Observable<FreeSubjectStructure> getSearchSubject01(
                @Query("q") String query,
                @Query("fq") String queryDomains,
                @Query("end_date") String endDate);


// ***************************
// Main method, with base URL.
// ***************************
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build() ;
}
// https://api.nytimes.com/svc/search/v2/articlesearch.json?query=Canada&fq=Politics&begin_date=20181120&end_date20181122&sort=newest&api-key=965b938d9e72418291dc79bbee0b1084