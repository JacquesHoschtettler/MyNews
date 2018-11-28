package com.hoschtettler.jacques.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    private NewsViewModel mNewsViewModel ;
    private QueryDomains mQueryDomains ;
    private int mBoxesChecked ;
    final private String QueryTerm = "EXTRA_QUERY_TERM" ;
    private String mQueryTerm ;


    @BindView(R.id.begin_date_btn) Button beginBtn ;
    @BindView(R.id.end_date_btn) Button endBtn ;
    @BindView(R.id.notification_switch) Switch notificationSwitch ;
    @BindView(R.id.search_dates) LinearLayout searchDates ;
    @BindView(R.id.search_floating_button) FloatingActionButton searchButton ;
    @BindView(R.id.search_grid_layout) GridLayout mGridLayout ;
    @BindView(R.id.query_term_input) TextInputEditText mQueryTermInput ;

    public SearchAndNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
        {
            mQueryTerm = (String) savedInstanceState.get(QueryTerm) ;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_and_notification, container, false);

        ButterKnife.bind(this, view);

        mNewsViewModel = new NewsViewModel() ;
        mNewsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);

        configureQueryDomains(view);

         // Displaying the good UI according to the selected function : searching articles or notifications.
        if (mNewsViewModel.getSearchDisplayIndex() == 0) {
            notificationSwitch.setVisibility(View.GONE);
            managingTheButton();
        } else {
            searchDates.setVisibility(View.GONE);
            searchButton.hide();
            managingTheButton();
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
                    mQueryTerm = s.toString() ;
                    managingTheButton(); ;
             }
        });

        // Managing the button
        mNewsViewModel.mCheckedBoxesNumber.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                managingTheButton();
            }
        });

        return view;
    }

// **********************
    // private methods
// **********************
    // Revering the dates order if the begin date is after the end date.
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

    // Checking if the dates are in the right order. If not it returns false.
    private boolean datesInOrder()
    {
        int beginYear = Integer.parseInt(mNewsViewModel.getBeginDate().substring(0,4)) ;
        int beginMonth = Integer.parseInt(mNewsViewModel.getBeginDate().substring(5,7)) ;
        int beginDay = Integer.parseInt(mNewsViewModel.getBeginDate().substring(8,10)) ;

        int endYear = Integer.parseInt(mNewsViewModel.getEndDate().substring(0,4)) ;
        int endMonth = Integer.parseInt(mNewsViewModel.getEndDate().substring(5,7)) ;
        int endDay = Integer.parseInt(mNewsViewModel.getEndDate().substring(8,10)) ;

        if (beginYear < endYear)
        {            return true ; }
        else {
            if (beginYear == endYear)
            {
                if (beginMonth < endMonth)
                {   return true ; }
                else
                {   if (beginMonth == endMonth)
                    {
                        if (beginDay <= endDay)
                        {   return true ; }
                        else {
                            return false ; }
                    }
                    else
                    {   return false ; }
                }
            }
            else
                {  return false; }
        }
    }

    // Setting up the check boxes.
    private void configureQueryDomains(View view)
    {
           mQueryDomains = new QueryDomains() ;
           if (mNewsViewModel.mCheckedBoxesNumber.equals(null))
           {
               mBoxesChecked = 0 ;
           }
           else
           {
               mBoxesChecked = mNewsViewModel.getCheckedBoxesNumber() ;
           }

            for(int i = 0 ; i < mQueryDomains.getQueryDomainsNumber(); i++)
            {
                CheckBox checkBox = view.findViewById(mQueryDomains.getCheckBoxId(i)) ;
                checkBox.setText(mQueryDomains.getQueryDomain(i));
                checkBox.setChecked(mNewsViewModel.getCheckedBoxes(i));
            }
    }

    // Checking if a query term is input AND if at least a checkbox is checked.
    private boolean allowingTheActionButton()
    {
        if (!mNewsViewModel.getQueryTerm().equals("") && mNewsViewModel.getCheckedBoxesNumber() > 0)
        {
            return true ;
        }
        else
        {
            return false ;
        }
    }

    // Managing the buttons
    private void managingTheButton()
    {
        boolean allowed = allowingTheActionButton() ;
        if (mNewsViewModel.getSearchDisplayIndex() == 0) {
            searchButton.setClickable(allowed);
            if (allowed)
            {
                searchButton.setCompatElevationResource(R.dimen.double_elevation);
                searchButton.setColorFilter(getResources().getColor(R.color.colorSecondary));
            }
            else
            {
                searchButton.setCompatElevationResource(R.dimen.null_elevation);
                searchButton.setColorFilter(getResources().getColor(R.color.colorPrimaryLight));
            }
        }
        else
        {
            notificationSwitch.setClickable(allowed) ;
            if (allowed)
            {
                notificationSwitch.setTextColor(getResources().getColor(R.color.colorSecondary));
            }
            else
            {
                notificationSwitch.setTextColor(getResources().getColor(R.color.colorPrimaryLight));
            }
        }
    }

    private int buttonColor(boolean colorChoice)
    {
        if (colorChoice)
        {
            return R.color.colorSecondary ;
        }
        else
        {
            return R.color.colorPrimaryLight ;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(QueryTerm, mQueryTerm);
    }

}
