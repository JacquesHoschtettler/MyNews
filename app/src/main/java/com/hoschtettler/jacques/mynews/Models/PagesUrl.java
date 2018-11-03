package com.hoschtettler.jacques.mynews.Models;

public class PagesUrl
{
    private String[] mPageName = new String[4] ;

    public PagesUrl()
    {
        mPageName[0]="Top Stories" ;
        mPageName[1]="Most Popular" ;
        mPageName[2]="Science & Technology" ;
        mPageName[3]="Arts & Leisure" ;
    }

    public int getNumberOfName()
    {
        return mPageName.length ;
    }

    public String getPageName(int position) {return mPageName[position] ;}
}
