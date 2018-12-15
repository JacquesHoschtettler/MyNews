package com.hoschtettler.jacques.mynews.Utils;

import com.hoschtettler.jacques.mynews.Models.FreeSubject.FreeSubjectStructure;
import com.hoschtettler.jacques.mynews.Models.MostPopular.MostPopularStructure;
import com.hoschtettler.jacques.mynews.Models.TopStories.TopsStoriesStructure;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsStreams
{
    public static Observable<TopsStoriesStructure> TopStoriesStream(int index)
    {
        NewsInterface newsInterface = NewsInterface.retrofit.create(NewsInterface.class) ;
        return newsInterface.getTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS) ;
    }

    public static Observable<MostPopularStructure> MostPopularStream(int index)
    {
        NewsInterface newsInterface = NewsInterface.retrofit.create(NewsInterface.class) ;
        return newsInterface.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS) ;
    }

    public static Observable<FreeSubjectStructure> ScienceStream(int index)
    {
        NewsInterface newsInterface = NewsInterface.retrofit.create(NewsInterface.class) ;
        return newsInterface.getScience()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS) ;
    }

    public static Observable<FreeSubjectStructure> ArtsStream(int index)
    {
        NewsInterface newsInterface = NewsInterface.retrofit.create(NewsInterface.class) ;
        return newsInterface.getArt()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS) ;
    }

    public static Observable<FreeSubjectStructure> SearchArticlesStream(int index, String queryTerm,
                String formattedBeginDate, String formattedEndDate, String formattedQueryDomains) {
        NewsInterface newsInterface = NewsInterface.retrofit.create(NewsInterface.class);
        Observable<FreeSubjectStructure> result = null ;
        if ((formattedBeginDate == "") )
        {
            if (formattedEndDate == "")
            {
                try
                {
                    result = newsInterface.getSearchSubject00(
                                    queryTerm,
                                    formattedQueryDomains)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .timeout(10, TimeUnit.SECONDS);
                } catch (Error error) {
                   // Log.e("MyNews", "Error : " + error.getMessage());
                }
            }
            else
            {
                try
                {
                    result = newsInterface.getSearchSubject01(
                                    queryTerm,
                                    formattedQueryDomains,
                                    formattedEndDate)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .timeout(10, TimeUnit.SECONDS);
                } catch (Error error) {
                   // Log.e("MyNews", "Error : " + error.getMessage());
                }
            }

        }else {
            if (formattedEndDate == "")
                try {
                    result = newsInterface.getSearchSubject10(
                            queryTerm,
                            formattedQueryDomains,
                            formattedBeginDate
                    )
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .timeout(10, TimeUnit.SECONDS);
                } catch (Error error) {
                  //  Log.e("MyNews", "Error : " + error.getMessage());
                }
            else {
                try {
                    result = newsInterface.getSearchSubject11(
                            queryTerm,
                            formattedQueryDomains,
                            formattedBeginDate,
                            formattedEndDate
                    )
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .timeout(10, TimeUnit.SECONDS);
                } catch (Error error) {
                   // Log.e("MyNews", "Error : " + error.getMessage());
                }
            }
        }
        return result;
    }
}
