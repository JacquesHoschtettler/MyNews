package com.hoschtettler.jacques.mynews.Utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bumptech.glide.RequestManager;
import com.hoschtettler.jacques.mynews.Controllers.Fragments.ArtFragment;
import com.hoschtettler.jacques.mynews.Controllers.Fragments.ScienceFragment;
import com.hoschtettler.jacques.mynews.Controllers.Fragments.MostPopularFragment;
import com.hoschtettler.jacques.mynews.Controllers.Fragments.TopStoriesFragment;
import com.hoschtettler.jacques.mynews.Models.PagesUrl;

public class PageAdapter extends FragmentPagerAdapter
{
    private PagesUrl mPagesUrl;
    private RequestManager glide ;

    public PageAdapter(FragmentManager manager, PagesUrl pagesUrl)
    {
        super(manager) ;
        this.mPagesUrl = pagesUrl ;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 1 :
                return new MostPopularFragment() ;
            case 2 :
                return new ScienceFragment() ;
            case 3 :
                return new ArtFragment() ;
            default:
                return new TopStoriesFragment() ;
        }
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
