package com.hoschtettler.jacques.mynews;

import com.hoschtettler.jacques.mynews.Controllers.Fragments.NewsPage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

//@RunWith(MockitoJUnitRunner.class)
public class MainActivityTests
{
    @Test
    public void translatingDateTest()
    {
        String englishDate = "2018-11-12" ;
        NewsPage newsPage = new NewsPage() {
            @Override
            protected void LoadingNews() {

            }

            @Override
            protected void AdapterConfiguration() {

            }

            @Override
            protected int GetWindowNumber() {
                return 0;
            }

            @Override
            protected void UpDateAlreadyReadArticlesList() {

            }
        } ;
        assertEquals("12/11/2018", newsPage.FrenchDate(englishDate)) ;
    }

}
