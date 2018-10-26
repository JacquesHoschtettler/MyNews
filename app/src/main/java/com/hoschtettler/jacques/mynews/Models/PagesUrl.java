package com.hoschtettler.jacques.mynews.Models;

public class PagesUrl
{
    private String[] mPageUrl = new String[3] ;
    private String[] mPageName = new String[3] ;
    private String mApiKey = "?api-key=965b938d9e72418291dc79bbee0b1084" ;

    public PagesUrl()
    {
        mPageUrl[0] = "topstories/v2/home.json" + mApiKey ;
        mPageUrl[1] = "mostpopular/v2/mostviewed/all-sections/1.json"+ mApiKey ;
        mPageUrl[2] = "topstories/v2/science.json"+ mApiKey;

        mPageName[0]="Top Stories" ;
        mPageName[1]="Most Popular" ;
        mPageName[2]="Free Subject" ;
    }

    public void setPageName(String names, int position)
    {
        mPageUrl[position] = names ;
    }

    public String getPageUrl(int position)
    {
        return mPageUrl[position];
    }

    public int getNumberOfName()
    {
        return mPageUrl.length ;
    }

    public String getPageName(int position) {return mPageName[position] ;}
}
