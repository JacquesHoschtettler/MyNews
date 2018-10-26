package com.hoschtettler.jacques.mynews.Utils;

import com.hoschtettler.jacques.mynews.Models.FreeSubject.FreeSubjectStructure;
import com.hoschtettler.jacques.mynews.Models.MostPopular.MostPopularStructure;
import com.hoschtettler.jacques.mynews.Models.PagesUrl;
import com.hoschtettler.jacques.mynews.Models.TopStories.TopsStoriesStructure;
import com.hoschtettler.jacques.mynews.Utils.NewsInterface;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsStreams
{
    private static PagesUrl mPagesUrl = new PagesUrl() ;

    public static Observable<TopsStoriesStructure> TopStoriesStream(int index)
    {
        NewsInterface newsInterface = NewsInterface.retrofit.create(NewsInterface.class) ;
        return newsInterface.getTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(20, TimeUnit.SECONDS) ;
    }

    public static Observable<MostPopularStructure> MostPopularStream(int index)
    {
        NewsInterface newsInterface = NewsInterface.retrofit.create(NewsInterface.class) ;
        return newsInterface.getMostPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS) ;
    }

    public static Observable<FreeSubjectStructure> FreeSubjectStream(int index)
    {
        NewsInterface newsInterface = NewsInterface.retrofit.create(NewsInterface.class) ;
        return newsInterface.getFreeSubject()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS) ;
    }


}
