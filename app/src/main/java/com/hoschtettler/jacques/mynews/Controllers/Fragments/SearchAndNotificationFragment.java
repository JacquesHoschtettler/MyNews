package com.hoschtettler.jacques.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoschtettler.jacques.mynews.Models.NewsViewModel;
import com.hoschtettler.jacques.mynews.Models.QueryDomains;
import com.hoschtettler.jacques.mynews.R;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchAndNotificationFragment extends Fragment {

    private NewsViewModel mNewsViewModel ;
    private QueryDomains mQueryDomains ;

    @BindView(R.id.begin_date_btn) Button beginBtn ;
    @BindView(R.id.end_date_btn) Button endBtn ;
    @BindView(R.id.notification_switch) Switch notificationSwitch ;
    @BindView(R.id.search_dates) LinearLayout searchDates ;
    @BindView(R.id.search_floating_button) FloatingActionButton searchButton ;
    @BindView(R.id.search_grid_layout) GridLayout mGridLayout ;

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
        View view =  inflater.inflate(R.layout.fragment_search_and_notification, container, false);

        ButterKnife.bind(this, view) ;

        configureQueryDomains() ;

        mNewsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class) ;

        if (mNewsViewModel.getSearchDisplayIndex() == 0)
        {
            notificationSwitch.setVisibility(View.INVISIBLE);
        }
        else
        {
            searchDates.setVisibility(View.INVISIBLE);
            searchButton.hide();
        }

        mNewsViewModel.beginDate.observe(getActivity(),  new androidx.lifecycle.Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!mNewsViewModel.beginDate.getValue().equals(""))
                {
                    beginBtn.setText(mNewsViewModel.beginDate.getValue());
                }
            }
        } );

        mNewsViewModel.endDate.observe(getActivity(),  new androidx.lifecycle.Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!mNewsViewModel.endDate.getValue().equals(""))
                {
                    endBtn.setText(mNewsViewModel.endDate.getValue());
                }
            }
        } );

        return view ;
    }

    private void configureQueryDomains()
    {
            mQueryDomains = new QueryDomains() ;
        /*
            mGridLayout.
                    setAdapter(new PageAdapter(getSupportFragmentManager(), mQueryDomains)
            { });

        */

    }

}
