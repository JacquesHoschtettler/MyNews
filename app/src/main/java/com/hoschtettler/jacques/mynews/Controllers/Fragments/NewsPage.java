package com.hoschtettler.jacques.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoschtettler.jacques.mynews.Models.News;
import com.hoschtettler.jacques.mynews.Models.NewsAdapter;
import com.hoschtettler.jacques.mynews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsPage extends Fragment {

    private static final String KEY_URL = "Url" ;
    @BindView(R.id.fragment_recycler_view) RecyclerView mRecyclerView ;
    private List<News> mNews ;
    private NewsAdapter mNewsAdapter ;

    public NewsPage() {
        // Required empty public constructor
    }

    public static NewsPage newInstance (String url)
    {
        NewsPage news_page = new NewsPage();

        Bundle args = new Bundle() ;
        args.putString(KEY_URL, url);
        news_page.setArguments(args);

        return news_page ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View list_news = inflater.inflate(R.layout.fragment_news_page, container, false);

        RecyclerViewConfiguration() ;

        String url = getArguments().getString(KEY_URL) ;


        return list_news ;
    }

    private void RecyclerViewConfiguration()
    {
        mNews = new ArrayList<News>();
    }

}
