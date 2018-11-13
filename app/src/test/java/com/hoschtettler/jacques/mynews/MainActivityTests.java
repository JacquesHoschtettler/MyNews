package com.hoschtettler.jacques.mynews;

import com.hoschtettler.jacques.mynews.Controllers.Fragments.NewsPage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MainActivityTests
{
    @Test
    public void translating_date()
    {
        String englishDate = "2018-11-12" ;
        NewsPage newsPage = new NewsPage() {
            @Override
            protected void LoadingNews() {

            }

            @Override
            protected void AdapterConfiguration() {

            }
        } ;
        assertEquals("12/11/2018", newsPage.FrenchDate(englishDate)) ;
    }
}
