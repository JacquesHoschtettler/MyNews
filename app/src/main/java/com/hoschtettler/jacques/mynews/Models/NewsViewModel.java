package com.hoschtettler.jacques.mynews.Models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsViewModel extends ViewModel
{
    public final MutableLiveData<String> mNewsUrl = new MutableLiveData<>() ;

    public int searchDisplayIndex = 0 ;

    public void setChoisedUrl(String newsArticle)
    {
        this.mNewsUrl.setValue(newsArticle) ;
    }

    public String getChoisedUrl()
    {
        return this.mNewsUrl.getValue();
    }

    public void setSearchDisplayIndex(int index) {this.searchDisplayIndex = index ;}

    public int getSearchDisplayIndex() {return  this.searchDisplayIndex ;}
}
