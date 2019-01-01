package com.hoschtettler.jacques.mynews;

import com.hoschtettler.jacques.mynews.Controllers.Activities.SearchAndNotificationActivity;
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

    @Test
    public void formattingDataTest()
    {
        String formattedBeginDate = "";
        String formattedEndDate = "";
        String formattedQueryDomains = "" ;

        SearchAndNotificationActivity activityClass =new SearchAndNotificationActivity() ;


        activityClass.mRawData[0] = "2018/01/01";
        activityClass.mRawData[1] = "2018/12/31";
        activityClass.mNumberOfBoxes = 8;
        activityClass.mCheckedBoxes = new boolean[8] ;
        activityClass.mCheckedBoxes[0] = false ;
        activityClass.mCheckedBoxes[1] = true ;
        activityClass.mCheckedBoxes[2] = false ;
        activityClass.mCheckedBoxes[3] = false ;
        activityClass.mCheckedBoxes[4] = false ;
        activityClass.mCheckedBoxes[5] = false ;
        activityClass.mCheckedBoxes[6] = true ;
        activityClass.mCheckedBoxes[7] = false ;

        activityClass.formattingData( 1 );

        assertEquals("(\"Business\" \"World\")", activityClass.mFormattedData[2]);

    }


}
