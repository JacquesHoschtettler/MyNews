package com.hoschtettler.jacques.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.hoschtettler.jacques.mynews.Controllers.Fragments.ArticleFragment;
import com.hoschtettler.jacques.mynews.Controllers.Fragments.TopStoriesFragment;
import com.hoschtettler.jacques.mynews.Models.NewsViewModel;
import com.hoschtettler.jacques.mynews.Models.PagesUrl;
import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Utils.PageAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity
                    implements NavigationView.OnNavigationItemSelectedListener {

    private PagesUrl mPagesUrl;
    private Toolbar mToolbar ;
    private DrawerLayout mDrawerLayout ;
    private NavigationView mNavigationView ;
    private NewsViewModel mNewsViewModel ;

    final String EXTRA_ID_BOUTON = "id_bouton" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureToolbar() ;
        this.configureViewPager() ;
        //this.configureDrawerLayout() ;

        TopStoriesFragment fragment = new TopStoriesFragment() ;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_news2, fragment)
                .commit() ;

        mNewsViewModel = ViewModelProviders.of(this)
                .get(NewsViewModel.class) ;

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
                Toast.makeText(this, R.string.about_message, Toast.LENGTH_LONG)
                        .show();
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

  /*
    private void configureDrawerLayout()
    {
        this.mToolbar = (Toolbar)findViewById(R.id.toolbar) ;
        setSupportActionBar(mToolbar) ;

        this.mDrawerLayout = (DrawerLayout)findViewById(R.id.main_navigation_drawer_layout) ;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) ;
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        this.mNavigationView = (NavigationView)findViewById(R.id.navigation_view_layout) ;
        mNavigationView.setNavigationItemSelectedListener(this);
    }
    */

/*
    private void configureOnClickRecyclerView(){

        ItemClickSupport.addTo(mRecyclerView, R.layout.top_stories)

                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {

                    @Override

                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        ArticleFragment fragment = new ArticleFragment() ;
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.frame_layout_news, fragment)
                                .commit() ;

                    }

                });

    }
    */


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        //this.mNavigationView.getMenu().getItem(item).setChecked(true) ;
        return true;
    }

/*
    @Override
    public void onBackPressed()
    {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
    */
}
