package com.hoschtettler.jacques.mynews.Models;

public class PagesUrl
{
    private String[] mPageUrl = new String[3] ;
    private String[] mPageName = new String[3] ;

    public PagesUrl()
    {
        mPageUrl[0] = "Url of Top Stories" ;
        mPageUrl[1] = "Url of Most Popular" ;
        mPageUrl[2] = "Url of Books";

        mPageName[0]="Top Stories" ;
        mPageName[1]="Most Popular" ;
        mPageName[2]="Books" ;
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
