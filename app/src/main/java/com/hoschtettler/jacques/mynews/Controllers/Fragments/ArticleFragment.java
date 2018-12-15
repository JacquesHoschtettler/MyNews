package com.hoschtettler.jacques.mynews.Controllers.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.hoschtettler.jacques.mynews.R;

import androidx.fragment.app.Fragment;

public class ArticleFragment extends Fragment {


    public final static String ARG_URL = "url" ;

    private String mArticleUrl = "";

    public ArticleFragment() {
        // Required empty public constructor
    }

    public static ArticleFragment newInstance() {
        ArticleFragment fragment = new ArticleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        return view ;

    }

    @Override
    public void onStart()
    {
        super.onStart();
        updateUI();
    }


    public void setArticleUrl(String articleUrl)
    {
        mArticleUrl = articleUrl ;
    }


    public void updateUI()
    {
         if (mArticleUrl !="") {
            WebView newsView = (WebView) getActivity().findViewById(R.id.article_view);
            newsView.loadUrl(mArticleUrl);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public  void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_URL, mArticleUrl);
    }
}
