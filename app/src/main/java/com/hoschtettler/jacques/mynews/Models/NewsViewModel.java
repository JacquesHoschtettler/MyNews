package com.hoschtettler.jacques.mynews.Models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsViewModel extends ViewModel
{
    // Variables
    public final MutableLiveData<String> mNewsUrl = new MutableLiveData<>() ;

    private int searchDisplayIndex ;
    private int dateButtonIndex ;


    private String queryTerm ;
    public MutableLiveData<String> beginDate ;
    public MutableLiveData<String> endDate ;

    private String formattedBeginDate ;
    private String formattedEndDate ;
    private String formattedQueryDomains ;

    public MutableLiveData<Integer>  mCheckedBoxesNumber ;
    private Boolean[] mCheckedBoxes ;
    private int numberOfBoxes ;

    // Constructor
    public NewsViewModel()
    {
        super() ;
        numberOfBoxes = 8 ;
        beginDate = new MutableLiveData<>() ;
        endDate = new MutableLiveData<>() ;
        formattedBeginDate = "" ;
        formattedEndDate = "" ;
        formattedQueryDomains = "" ;
        searchDisplayIndex = -1 ;
        dateButtonIndex = -1 ;
        mCheckedBoxesNumber = new MutableLiveData<>()     ;
        mCheckedBoxesNumber.setValue(0);
        mCheckedBoxes = new Boolean[numberOfBoxes] ;
        for (int i = 0 ; i < numberOfBoxes; i++)
        {
            this.setCheckedBoxes(i, false);
        }
        beginDate.setValue("");
        endDate.setValue("");
        queryTerm= "" ;
    }

    // Setters and getters
        // Article URL
            public void setChosendUrl(String newsArticle)
            {
                this.mNewsUrl.setValue(newsArticle) ;
            }

            public String getChosendUrl()
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

         // Query term for the search or the notification methods
            public void setQueryTerm(String queryTerm)
            {
                this.queryTerm = queryTerm ;
            }

            public String getQueryTerm()
            {
                return this.queryTerm ;
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

         // Fomatted begin date, parameter for the endpoint to search articles
            public void setFormattedBeginDate(String formattedDate)
            {
                this.formattedBeginDate = formattedDate ;
            }

            public String getFormattedBeginDate()
            {
                return this.formattedBeginDate ;
            }

        // Fomatted end date, parameter for the endpoint to search articles
            public void setFormattedEndDate(String formattedDate)
            {
                this.formattedEndDate = formattedDate ;
            }

            public String getFormattedEndDate()
            {
                return this.formattedEndDate ;
            }

        // Fomatted list of chosen domains, parameter for the endpoint to search articles
            public void setFormattedQueryDomains(String queryDomains)
            {
                this.formattedQueryDomains = queryDomains ;
            }

            public String getFormattedQueryDomains()
            {
                return this.formattedQueryDomains ;
            }


    // Search and notification UI : checked boxes
        public void setCheckedBoxesNumber(int checkedBoxesNumber) {this.mCheckedBoxesNumber.setValue(checkedBoxesNumber) ;}

        public int getCheckedBoxesNumber() {
                return mCheckedBoxesNumber.getValue().intValue() ;
        }

        public void setCheckedBoxes(int index, Boolean checked)
        {
            if (mCheckedBoxes != null) {
                mCheckedBoxes[index] = checked;
            }
            else
            {
                Log.d("MyNews","NewsViewModel : setCheckedBoxes : mCheckedBoxes is null ") ;
            }
        }

        public Boolean getCheckedBoxes(int index)
        {
            if (index < numberOfBoxes)
            {
                return mCheckedBoxes[index] ;
            }
            else
            {
                return false ;
            }
        }

        // getting the number of check boxes
        public int getNumberOfBoxes()
        {
            return numberOfBoxes ;
        }
}
