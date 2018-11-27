package com.hoschtettler.jacques.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.hoschtettler.jacques.mynews.Controllers.Fragments.SearchAndNotificationFragment;
import com.hoschtettler.jacques.mynews.Models.NewsViewModel;
import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Utils.DatePickerFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

public class SearchAndNotificationActivity extends AppCompatActivity  {

    final String EXTRA_ID_BOUTON = "id_bouton" ;

    private NewsViewModel mNewsViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_notification);

        Intent intent = getIntent() ;

        mNewsViewModel = new NewsViewModel() ;
        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class) ;
        mNewsViewModel.setSearchDisplayIndex(intent.getIntExtra(EXTRA_ID_BOUTON,-3));

        configureToolbar();

        SearchAndNotificationFragment fragment = new SearchAndNotificationFragment() ;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_search_and_notification, fragment)
                .commit() ;
    }

    public void showBeginDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        mNewsViewModel.setDateButtonIndex(0);
    }

    public void showEndDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        mNewsViewModel.setDateButtonIndex(1);
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

    public void onCheckboxClicked(View v)
    {
        switch(v.getId()) {
            case R.id.query_domain_0:
                checkedBoxesCount(v, 0);
                break;
            case R.id.query_domain_1:
                checkedBoxesCount(v, 1);
                break;
            case R.id.query_domain_2:
                checkedBoxesCount(v, 2);
                break;
            case R.id.query_domain_3:
                checkedBoxesCount(v, 3);
                break;
            case R.id.query_domain_4:
                checkedBoxesCount(v, 4);
                break;
            case R.id.query_domain_5:
                checkedBoxesCount(v, 5);
                break;
            case R.id.query_domain_6:
                checkedBoxesCount(v, 6);
                break;
            case R.id.query_domain_7:
                checkedBoxesCount(v, 7);
                break;
            default:
        }
        Log.d("MyNews", "SearcAndNotificationActivity : onClick : mBoxesChecked : "
                + mNewsViewModel.getCheckedBoxesNumber()) ;
    }

    private void checkedBoxesCount(View v, int index)
    {
        Integer checkedBoxesNumber = mNewsViewModel.getCheckedBoxesNumber() ;
        if (checkedBoxesNumber == null)
        {
            checkedBoxesNumber = 0 ;
        }
        CheckBox checkBox = (CheckBox) v ;
        if (checkBox.isChecked())
        {
            checkedBoxesNumber++;
            mNewsViewModel.setCheckedBoxes(index, true);
        }
        else
        {
            checkedBoxesNumber-- ;
            mNewsViewModel.setCheckedBoxes(index, false);
        }
        mNewsViewModel.setCheckedBoxesNumber(checkedBoxesNumber);
    }

}
