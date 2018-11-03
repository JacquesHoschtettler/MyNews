package com.hoschtettler.jacques.mynews.Controllers.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.hoschtettler.jacques.mynews.R;

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
        Log.e("News", "ArticleFragment.onStart : "+mArticleUrl) ;
        updateUI();
    }


    public void setArticleUrl(String articleUrl)
    {
        mArticleUrl = articleUrl ;
        Log.e("News", "ArticleFragment.setArticleUrl : "+mArticleUrl) ;
    }


    public void updateUI()
    {
        Log.e("News" , "ArticleFragment : Url choisie " + mArticleUrl) ;
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
