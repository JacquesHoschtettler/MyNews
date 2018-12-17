package com.hoschtettler.jacques.mynews.Controllers.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoschtettler.jacques.mynews.Models.NewsViewModel;
import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Utils.ItemClickSupport;
import com.hoschtettler.jacques.mynews.Views.NewsAdapter;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class NewsPage extends Fragment {

    protected abstract void LoadingNews() ;
    protected abstract void AdapterConfiguration() ;
    protected abstract int GetWindowNumber() ;
    protected abstract void UpDateAlreadyArticlesList() ;

    protected NewsViewModel mNewsViewModel ;
    protected NewsAdapter mNewsAdapter ;


    @BindView(R.id.fragment_news_page_recycler_view) RecyclerView mRecyclerView ;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_page, container, false) ;

        ButterKnife.bind(this,view) ;

        AdapterConfiguration() ;

        mNewsViewModel = new NewsViewModel() ;
        mNewsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);

        this.LoadingNews() ;

        configureOnClickRecyclerView();

        return view ;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }


    public String FrenchDate(String englishDate)
    {
        return englishDate.substring(8,10) + "/" +
                englishDate.substring(5,7) + "/"
                +englishDate.substring(0,4) ;
    }

    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(getRecyclerView(), R.layout.news_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        mNewsViewModel.setAlreadyReadArticleUrl(mNewsAdapter.getUrl(position), GetWindowNumber()) ;
                        v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight)) ;
                        mNewsViewModel.setChosendUrl(mNewsAdapter.getUrl(position));
                    }
                });
    }

    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public boolean isArticleAlreadyRead(String url) {
        boolean changingColor = false ;
        for (String urlToVerify : mNewsViewModel.getAlreadyReadArticlesList(GetWindowNumber())) {
            if (urlToVerify.equals(url)) {
                changingColor = true ;
            }
        }
        return changingColor ;
    }


    protected void disposeWhenDestroy(Disposable disposable)
    {
        if(disposable != null && !disposable.isDisposed()) disposable.dispose();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        mNewsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

}


