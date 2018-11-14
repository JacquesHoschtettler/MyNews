package com.hoschtettler.jacques.mynews.Controllers.Activities;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hoschtettler.jacques.mynews.Controllers.Fragments.ArticleFragment;
import com.hoschtettler.jacques.mynews.Controllers.Fragments.TopStoriesFragment;
import com.hoschtettler.jacques.mynews.Models.NewsViewModel;
import com.hoschtettler.jacques.mynews.Models.PagesUrl;
import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Utils.PageAdapter;

public class MainActivity extends AppCompatActivity
                    implements NavigationView.OnNavigationItemSelectedListener {

    private PagesUrl mPagesUrl;
    private Toolbar mToolbar ;
    private DrawerLayout mDrawerLayout ;
    private NavigationView mNavigationView ;


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

        final NewsViewModel mNewsViewModel = ViewModelProviders.of(this)
                .get(NewsViewModel.class) ;

        mNewsViewModel.mNewsUrl.observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String newsUrl)
            {
                if(mNewsViewModel.getChoisedUrl()!= "") {
                    ArticleFragment newSiteView = new ArticleFragment();
                    newSiteView.setArticleUrl(newsUrl);

                    Log.e("News", "\t\t Url modifié :" + newsUrl) ;

                    Bundle args = new Bundle();
                    args.putString(ArticleFragment.ARG_URL, newsUrl);
                    newSiteView.setArguments(args);

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout_news, newSiteView);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    mNewsViewModel.setChoisedUrl("");
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
                Toast.makeText(this, "En attente de recherche", Toast.LENGTH_LONG)
                        .show();
                return true ;
            case R.id.main_activity_menu_parameters :
                Toast.makeText(this, "En attente de paramètres", Toast.LENGTH_LONG)
                        .show();
                return true ;
            default :
                return super.onOptionsItemSelected(item) ;
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
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);
    }

    private void configureViewPager()
    {
        mPagesUrl = new PagesUrl() ;
        ViewPager pager = findViewById(R.id.main_display_view_pager) ;
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), mPagesUrl)
        { });
        TabLayout tabs = (TabLayout)findViewById(R.id.main_activity_tabs) ;
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

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
