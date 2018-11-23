package com.hoschtettler.jacques.mynews.Models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsViewModel extends ViewModel
{
    // Variables
    public final MutableLiveData<String> mNewsUrl = new MutableLiveData<>() ;

    private int searchDisplayIndex = -1 ;
    private int dateButtonIndex = -1 ;

    public final MutableLiveData<String> beginDate = new MutableLiveData<>();

    public final MutableLiveData<String> endDate  = new MutableLiveData<>();


    // Setters and getters
        // Article URL
            public void setChoisedUrl(String newsArticle)
            {
                this.mNewsUrl.setValue(newsArticle) ;
            }

            public String getChoisedUrl()
            {
                return this.mNewsUrl.getValue();
            }

        // Index to select between the Search Articles fragment and thi Notification fragment
            public void setSearchDisplayIndex(int index)
            {
                this.searchDisplayIndex = index ;
            }

            public int getSearchDisplayIndex()
            {
                return  this.searchDisplayIndex ;
            }

        // Index to select between the Search Articles fragment and thi Notification fragment
            public void setDateButtonIndex(int index)
            {
                this.dateButtonIndex = index ;
            }

            public int getDateButtonIndex()
            {
                return  this.dateButtonIndex ;
            }

        // Begin date to searching articles
            public void setBeginDate(String date)
            {
                this.beginDate.setValue(date) ;
            }

            public String getBeginDate()
            {
                return this.beginDate.getValue() ;
            }

        // End date to searching articles
            public void setEndDate(String date)
            {
                this.endDate.setValue(date) ;
            }

            public String getEndDate()
            {
                return this.endDate.getValue() ;
            }


}
