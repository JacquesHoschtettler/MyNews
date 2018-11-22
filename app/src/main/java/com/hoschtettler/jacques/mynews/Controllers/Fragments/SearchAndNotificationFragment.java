package com.hoschtettler.jacques.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hoschtettler.jacques.mynews.Models.NewsViewModel;
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

    @BindView(R.id.begin_date_btn) Button beginBtn ;
    @BindView(R.id.end_date_btn) Button endBtn ;

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

        ButterKnife.bind(view) ;

        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class) ;

        mNewsViewModel.beginDate.observe(this,  new androidx.lifecycle.Observer<String>() {
            @Override
            public void onChanged(String s) {
                    if (mNewsViewModel.beginDate.getValue() != "")
                    {
                        beginBtn.setText(mNewsViewModel.beginDate.getValue());
                    }
            }
        } );

        mNewsViewModel.endDate.observe(getActivity(),  new androidx.lifecycle.Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (mNewsViewModel.endDate.getValue() != "")
                {
                    endBtn.setText(mNewsViewModel.endDate.getValue());
                }
            }
        } );

        return view ;
    }

}
