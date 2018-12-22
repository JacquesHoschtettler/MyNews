package com.hoschtettler.jacques.mynews;

import com.hoschtettler.jacques.mynews.Controllers.Fragments.NewsPage;
import com.hoschtettler.jacques.mynews.Models.FreeSubject.FreeSubjectStructure;
import com.hoschtettler.jacques.mynews.Utils.NewsStreams;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

//@RunWith(MockitoJUnitRunner.class)
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
    public void searchArticlesTest() throws Exception {
        // Get the stream
        Observable<FreeSubjectStructure> observableSearch = NewsStreams.SearchArticlesStream
                (0, "Mars","20181101",
                        "20181130", "Home & Garden" ) ;

        // Create a new TestObserver
        TestObserver<FreeSubjectStructure> testObserver = new TestObserver<>();
        // Launch observable
        observableSearch.subscribeWith(testObserver)
                .assertNoErrors() // Check if no errors
                .assertNoTimeout() // Check if no Timeout
                .awaitTerminalEvent(); // Await the stream terminated before continue

        // Get list of articles
        FreeSubjectStructure searchResults = testObserver.values().get(0);

        // Verify that, in Novembre 2018, there is no article about "Home & Garden" related to Mars
        assertThat("In November 2018, no articles about \"Home & Garden\" on Mars",
                searchResults.getResponse().getDocs().size() == 0);
    }
}
