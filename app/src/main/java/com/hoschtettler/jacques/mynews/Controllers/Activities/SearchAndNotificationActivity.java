package com.hoschtettler.jacques.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.hoschtettler.jacques.mynews.Controllers.Fragments.ArticleFragment;
import com.hoschtettler.jacques.mynews.Controllers.Fragments.SearchAndNotificationFragment;
import com.hoschtettler.jacques.mynews.Controllers.Fragments.SearchArticlesFragment;
import com.hoschtettler.jacques.mynews.Models.NewsViewModel;
import com.hoschtettler.jacques.mynews.Models.QueryDomains;
import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Utils.DatePickerFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
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


        mNewsViewModel = ViewModelProviders.of(this)
                .get(NewsViewModel.class) ;

        mNewsViewModel.mNewsUrl.observe(this,  new Observer<String>() {
            @Override
            public void onChanged(@Nullable String newsUrl) {
                if (!mNewsViewModel.getChosendUrl().equals("")) {
                    ArticleFragment newSiteView = new ArticleFragment();
                    newSiteView.setArticleUrl(newsUrl);

                    Bundle args = new Bundle();
                    args.putString(ArticleFragment.ARG_URL, newsUrl);
                    newSiteView.setArguments(args);

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout_search_and_notification, newSiteView);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    mNewsViewModel.setChosendUrl("");
                }
            }
        });

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

    public void searchingArticles(View v)
    {
        String formattedBeginDate ;
        String formattedEndDate ;
        String formattedQueryDomains ;
        QueryDomains domains = new QueryDomains() ;

        // Transformation of the datas to the endpoint format.
        formattedBeginDate = formatingDate(mNewsViewModel.getBeginDate());
        formattedEndDate = formatingDate(mNewsViewModel.getEndDate()) ;

        formattedQueryDomains = "(\"";
        int CheckedBoxesCounter = 0 ;
        for (int i = 0 ; i < mNewsViewModel.getNumberOfBoxes(); i++)
        {
            if (mNewsViewModel.getCheckedBoxes(i))
            {
                if (CheckedBoxesCounter > 0)
                {
                    formattedQueryDomains += " \"";
                }
                formattedQueryDomains += domains.getQueryDomain(i) ;
                formattedQueryDomains += "\"" ;
                ++CheckedBoxesCounter ;
            }
        }
        formattedQueryDomains += ")";

        // Passage of the stream parameters to the SearchArticlesFragment
        mNewsViewModel.setFormattedBeginDate(formattedBeginDate);
        mNewsViewModel.setFormattedEndDate(formattedEndDate);
        mNewsViewModel.setFormattedQueryDomains(formattedQueryDomains);

        // Calling the SearchFragment
        SearchArticlesFragment fragment = new SearchArticlesFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout_search_and_notification, fragment)
                .addToBackStack(null)
                .commit() ;
    }

    private String formatingDate(String date)
    {
        if (date != "") {
            String formattedDate;
            formattedDate = date.substring(0, 4) + date.substring(5, 7)
                    + date.substring(8);
            return formattedDate;
        }
            {
                return "" ;
            }
    }
}
