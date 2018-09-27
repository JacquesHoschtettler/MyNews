package com.hoschtettler.jacques.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoschtettler.jacques.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsPage extends Fragment {

    private static final String KEY_URL = "Url" ;

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

        TextView temporary_text = (TextView) list_news.findViewById(R.id.news_page_text) ;

        String url = getArguments().getString(KEY_URL) ;
        temporary_text.setText(url);

        return list_news ;
    }

}
