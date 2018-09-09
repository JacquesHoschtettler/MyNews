package com.hoschtettler.jacques.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoschtettler.jacques.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class News_page extends Fragment {


    public News_page() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_page, container, false);
    }

}
