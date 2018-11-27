package com.hoschtettler.jacques.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.hoschtettler.jacques.mynews.Models.NewsViewModel;
import com.hoschtettler.jacques.mynews.Models.QueryDomains;
import com.hoschtettler.jacques.mynews.R;

import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchAndNotificationFragment extends Fragment {

    private NewsViewModel mNewsViewModel = new NewsViewModel() ;
    private QueryDomains mQueryDomains ;
    private int mBoxesChecked = 0 ;

    @BindView(R.id.begin_date_btn) Button beginBtn ;
    @BindView(R.id.end_date_btn) Button endBtn ;
    @BindView(R.id.notification_switch) Switch notificationSwitch ;
    @BindView(R.id.search_dates) LinearLayout searchDates ;
    @BindView(R.id.search_floating_button) FloatingActionButton searchButton ;
    @BindView(R.id.search_grid_layout) GridLayout mGridLayout ;
    @BindView(R.id.query_domain_0) CheckBox mCheckBox0 ;
    @BindView(R.id.query_domain_1) CheckBox mCheckBox1 ;
    @BindView(R.id.query_term_input) TextInputEditText mQueryTermInput ;

    public SearchAndNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_and_notification, container, false);

        ButterKnife.bind(this, view);

        configureQueryDomains(view);

        mNewsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);

        if (mNewsViewModel.getSearchDisplayIndex() == 0) {
            notificationSwitch.setVisibility(View.INVISIBLE);
            if (mBoxesChecked < 1) {
                searchButton.setClickable(false);
            }
        } else {
            searchDates.setVisibility(View.GONE);
            searchButton.hide();
        }

        // Managing the begin and end dates
        mNewsViewModel.beginDate.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!mNewsViewModel.getBeginDate().equals("")) {
                    beginBeforeEnd();
                    beginBtn.setText(mNewsViewModel.getBeginDate());
                }
            }
        });

        mNewsViewModel.endDate.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!mNewsViewModel.getEndDate().equals("")) {
                    beginBeforeEnd();
                    endBtn.setText(mNewsViewModel.getEndDate());
                }
            }
        });



        // Managing the query term
        mQueryTermInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {  }

            @Override
            public void afterTextChanged(Editable s) {
                    mNewsViewModel.setQueryTerm(s.toString());
                    Log.d("MyNews", "SearchAndNotificationFragment : mmQueryTermInput.addTextChangedListener : query term = " +s.toString() ) ;
                    allowingTheActionButton(s.toString()) ;
             }
        });


        return view;
    }


    private void beginBeforeEnd()
    {
        if (!mNewsViewModel.getEndDate().equals("") && !mNewsViewModel.getBeginDate().equals("")) {
            if (!datesInOrder()) {
                String temp = mNewsViewModel.getBeginDate() ;
                mNewsViewModel.setBeginDate(mNewsViewModel.getEndDate());
                mNewsViewModel.setEndDate(temp) ;
            }
        }
    }

    private boolean datesInOrder()
    {
        int beginYear = Integer.parseInt(mNewsViewModel.getBeginDate().substring(0,4)) ;
        int beginMonth = Integer.parseInt(mNewsViewModel.getBeginDate().substring(5,7)) ;
        int beginDay = Integer.parseInt(mNewsViewModel.getBeginDate().substring(8,10)) ;

        int endYear = Integer.parseInt(mNewsViewModel.getEndDate().substring(0,4)) ;
        int endMonth = Integer.parseInt(mNewsViewModel.getEndDate().substring(5,7)) ;
        int endDay = Integer.parseInt(mNewsViewModel.getEndDate().substring(8,10)) ;

        if (beginYear < endYear)
        {
            return true ;
        }
        else
        {
            if (beginYear == endYear)
            {
                if (beginMonth < endMonth)
                {
                    return true ;
                }
                else
                {
                    if (beginMonth == endMonth)
                    {
                        if (beginDay <= endDay)
                        {
                            return true ;
                        }
                        else
                        {
                            return false ;
                        }
                    }
                    else
                    {
                        return false ;
                    }
                }
            }
            else {
                return false;
            }
        }
    }
    private void configureQueryDomains(View view)
    {
           mQueryDomains = new QueryDomains() ;
           mBoxesChecked = 0 ;
            for(int i = 0 ; i < mQueryDomains.getQueryDomainsNumber(); i++)
            {
                CheckBox checkBox = view.findViewById(mQueryDomains.getCheckBoxId(i)) ;
                if (checkBox.isChecked())
                {
                    mBoxesChecked++ ;
                }
                checkBox.setText(mQueryDomains.getQueryDomain(i));
            }
            mNewsViewModel.setCheckedBoxesNumber(mBoxesChecked) ;
            Log.d("MyNews", "SearcAndNotificationFragment : configureQueryDomains : "
                    +"mBoxesChecked : " + mBoxesChecked) ;
    }

    private void allowingTheActionButton(String term)
    {
        if (!term.equals("") && mNewsViewModel.getCheckedBoxesNumber() > 0)
        {
            Log.d("MyNews" ,"SearchAndNotificationFragment : allowingTheActionButton : Allowed") ;
        }
        else
        {
            Log.d("MyNews" ,"SearchAndNotificationFragment : allowingTheActionButton : −−> Not Allowed") ;
        }

    }
}
