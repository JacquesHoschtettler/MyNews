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

    /*
    @Test
    public void formattingDataTest()
    {
        String formattedBeginDate = "";
        String formattedEndDate = "";
        String formattedQueryDomains = "" ;

        SearchAndNotificationActivity activityClass =new SearchAndNotificationActivity() ;

        NewsViewModel mNewsViewModel = mock(NewsViewModel.class) ;

        when(mNewsViewModel.getBeginDate()).thenReturn("2018/01/01");
        when(mNewsViewModel.getEndDate()).thenReturn("2018/12/31");
        when(mNewsViewModel.getNumberOfBoxes()).thenReturn(8);
        when(mNewsViewModel.getCheckedBoxes(0)).thenReturn(false) ;
        when(mNewsViewModel.getCheckedBoxes(1)).thenReturn(true) ;
        when(mNewsViewModel.getCheckedBoxes(2)).thenReturn(false) ;
        when(mNewsViewModel.getCheckedBoxes(3)).thenReturn(false) ;
        when(mNewsViewModel.getCheckedBoxes(4)).thenReturn(false) ;
        when(mNewsViewModel.getCheckedBoxes(5)).thenReturn(false) ;
        when(mNewsViewModel.getCheckedBoxes(6)).thenReturn(true) ;
        when(mNewsViewModel.getCheckedBoxes(7)).thenReturn(false) ;

        activityClass.formattingData( 1 );

        assertEquals("(\"Business\" \"World\")", mNewsViewModel.getFormattedQueryDomains());

    }
    */

}
