package com.hoschtettler.jacques.mynews.Models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsViewModel extends ViewModel
{
    public final MutableLiveData<String> mNewsUrl = new MutableLiveData<>() ;

    private int searchDisplayIndex = -1 ;

    public final MutableLiveData<String> beginDate = new MutableLiveData<>();

    public final MutableLiveData<String> endDate  = new MutableLiveData<>();

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

    public void setBeginDate(String date)
    {
        this.beginDate.setValue(date) ;
    }

    public String getBeginDate()
    {
        return this.beginDate.getValue() ;
    }

    public void setEndDate(String date)
    {
        this.endDate.setValue(date) ;
    }

    public String getEndDate()
    {
        return this.endDate.getValue() ;
    }


}
