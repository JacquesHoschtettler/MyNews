package com.hoschtettler.jacques.mynews.Controllers.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.hoschtettler.jacques.mynews.Controllers.Fragments.ArticleFragment;
import com.hoschtettler.jacques.mynews.Controllers.Fragments.TopStoriesFragment;
import com.hoschtettler.jacques.mynews.Models.NewsViewModel;
import com.hoschtettler.jacques.mynews.Models.PagesUrl;
import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Utils.PageAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private PagesUrl mPagesUrl;
    private NewsViewModel mNewsViewModel ;

    final String EXTRA_ID_BOUTON = "id_bouton" ;

    final String EXTRA_ALREADY_READ_ARTICLES_URL =
            "Already_read_articles_url" ;
    final String EXTRA_ALREADY_READ_ARTICLES_NUMBER_FOR_WINDOW =
            "Already_read_articles_number_for_window" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsViewModel = ViewModelProviders.of(this)
                .get(NewsViewModel.class) ;

        remindingAlreadyArticles() ;

        this.configureToolbar() ;
        this.configureViewPager() ;

        TopStoriesFragment fragment = new TopStoriesFragment() ;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_news2, fragment)
                .commit() ;

          mNewsViewModel.mNewsUrl.observe(this,  new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String newsUrl)
            {
                if(!mNewsViewModel.getChosendUrl().equals("")) {
                    ArticleFragment newSiteView = new ArticleFragment();
                    newSiteView.setArticleUrl(newsUrl);

                    Bundle args = new Bundle();
                    args.putString(ArticleFragment.ARG_URL, newsUrl);
                    newSiteView.setArguments(args);

                   FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout_news, newSiteView);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    mNewsViewModel.setChosendUrl("");
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.main_activity_menu_search :
               mNewsViewModel.setSearchDisplayIndex(0);
                launchingActivity(0) ;
                return true ;
            case R.id.main_activity_menu_parameters :
               mNewsViewModel.setSearchDisplayIndex(1);
                launchingActivity(1);
                return true ;
            case R.id.main_activity_about :
                // Display the message 3 seconds
                for (int i = 0 ; i<1 ; i++) {
                    Toast.makeText(this, R.string.about_message, Toast.LENGTH_LONG)
                            .show();
                }
            default :
                mNewsViewModel.setSearchDisplayIndex(-2);
                return super.onOptionsItemSelected(item) ;
        }
    }

    public void launchingActivity(int index)
    {
        Intent searchAndNotificationActivity = new Intent(MainActivity.this,
                SearchAndNotificationActivity.class) ;
        searchAndNotificationActivity.putExtra(EXTRA_ID_BOUTON, index) ;
        startActivity(searchAndNotificationActivity);
    }

    private void remindingAlreadyArticles()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        if (!preferences.equals(null)) {
            for (int windowIssue = 0; windowIssue < mNewsViewModel.getNumberOfWindows();
                 windowIssue++) {
                String key0 = EXTRA_ALREADY_READ_ARTICLES_NUMBER_FOR_WINDOW+ windowIssue ;
                int alreadyReadArticlesNumber = preferences.getInt(key0, 0) ;

                    for (int index = 0 ; index < alreadyReadArticlesNumber ; index++)
                    {
                        String key1 = EXTRA_ALREADY_READ_ARTICLES_URL + windowIssue + index ;
                        mNewsViewModel.setAlreadyReadArticleUrl(preferences.getString( key1, ""),
                                windowIssue );
                    }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true ;
    }

    private void configureToolbar()
    {
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);
    }

    private void configureViewPager()
    {
        mPagesUrl = new PagesUrl() ;
        ViewPager pager = findViewById(R.id.main_display_view_pager) ;
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), mPagesUrl)
        { });
        TabLayout tabs = findViewById(R.id.main_activity_tabs) ;
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        savingAlreadyReadArticles() ;
    }

    private void savingAlreadyReadArticles()
    {

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        for (int windowIssue = 0 ; windowIssue < mNewsViewModel.getNumberOfWindows() ; windowIssue++)
        {
            String key0 = EXTRA_ALREADY_READ_ARTICLES_NUMBER_FOR_WINDOW+ windowIssue ;
            int alreadyReadArticlesNumber = mNewsViewModel.getAlreadyReadArticlesList(windowIssue)
                    .size() ;
            editor.putInt( key0, alreadyReadArticlesNumber) ;

            for(int index = 0 ; index<alreadyReadArticlesNumber ; index++)
            {
                String key1 = EXTRA_ALREADY_READ_ARTICLES_URL+ windowIssue + index ;
                editor.putString(key1, mNewsViewModel.getAlreadyReadArticleUrl(windowIssue, index) ) ;
            }
        }
        editor.commit();
    }
}
