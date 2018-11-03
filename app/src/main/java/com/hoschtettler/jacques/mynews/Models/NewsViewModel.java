package com.hoschtettler.jacques.mynews.Models;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class NewsViewModel extends ViewModel
{
    public final MutableLiveData<String> mNewsUrl = new MutableLiveData<>() ;

    public void setChoisedUrl(String newsArticle)
    {
        this.mNewsUrl.setValue(newsArticle) ;
    }

    public String getChoisedUrl()
    {
        return this.mNewsUrl.getValue();
    }
}
