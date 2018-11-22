package com.hoschtettler.jacques.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hoschtettler.jacques.mynews.Controllers.Fragments.SearchAndNotificationFragment;
import com.hoschtettler.jacques.mynews.Models.NewsViewModel;
import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Utils.DatePickerFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

public class SearchAndNotificationActivity extends AppCompatActivity {

    final String EXTRA_ID_BOUTON = "id_bouton" ;

    private NewsViewModel mNewsViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_notification);

        Intent intent = getIntent() ;

        mNewsViewModel = ViewModelProviders.of(this)
                .get(NewsViewModel.class) ;
        mNewsViewModel.setSearchDisplayIndex(intent.getIntExtra(EXTRA_ID_BOUTON,-3));

        configureToolbar();

        SearchAndNotificationFragment fragment = new SearchAndNotificationFragment() ;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_search_and_notification, fragment)
                .commit() ;
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void configureToolbar()
    {
       androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar) ;
       switch (mNewsViewModel.getSearchDisplayIndex())
       {
           case 0 : toolbar.setTitle(R.string.search_articles);
           break;
           case 1 : toolbar.setTitle(R.string.notifications);
           break;
           default: toolbar.setTitle(R.string.an_error_has_occured);
       }
        setSupportActionBar(toolbar);
    }

}
