package com.hoschtettler.jacques.mynews.Models;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hoschtettler.jacques.mynews.Controllers.Fragments.NewsPage;

public class PageAdapter extends FragmentPagerAdapter
{
    private PagesUrl mPagesUrl;

    public PageAdapter(FragmentManager manager, PagesUrl pagesUrl)
    {
        super(manager) ;
        this.mPagesUrl = pagesUrl ;
    }

    @Override
    public Fragment getItem(int position) {
        return NewsPage.newInstance(mPagesUrl.getPageUrl(position)) ;
    }

    @Override
    public int getCount() {
        return mPagesUrl.getNumberOfName() ;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mPagesUrl.getPageName(position) ;
    }
}
