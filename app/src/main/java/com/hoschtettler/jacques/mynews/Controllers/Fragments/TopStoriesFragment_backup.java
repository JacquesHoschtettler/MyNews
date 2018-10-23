package com.hoschtettler.jacques.mynews.Controllers.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.hoschtettler.jacques.mynews.Utils.NewsStreams;
import com.hoschtettler.jacques.mynews.Models.News;
import com.hoschtettler.jacques.mynews.Models.TopStories.TopStoriesResult;
import com.hoschtettler.jacques.mynews.Models.TopStories.TopsStoriesStructure;
import com.hoschtettler.jacques.mynews.Utils.NewsAdapter;
import com.hoschtettler.jacques.mynews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


public class TopStoriesFragment_backup extends Fragment {

    private Disposable mDisposable ;

    private List<TopStoriesResult> mTopStoriesResults ;
    private ArrayList<News> mNews ;

    @BindView(R.id.fragment_top_stories_recycler_view) RecyclerView mRecyclerView ;

    private NewsAdapter mNewsAdapter ;

    public TopStoriesFragment_backup() {
        // Required empty public constructor
    }

    public static TopStoriesFragment_backup newInstance()
    {
        TopStoriesFragment_backup topStoriesFragment = new TopStoriesFragment_backup();
        return topStoriesFragment ;
    }


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
        View view = inflater.inflate(R.layout.fragment_top_stories, container, false) ;

        ButterKnife.bind(this,view) ;

        AdapterConfiguration() ;

        LoadingTopStories() ;

        return view ;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        this.disposeWhenDestroy() ;
    }

    private void LoadingTopStories()
    {
        this.mDisposable = NewsStreams.TopStoriesStream(0)
                .subscribeWith(new DisposableObserver<TopsStoriesStructure>() {
                    @Override
                    public void onNext(TopsStoriesStructure topsStoriesStructure) {
                        mTopStoriesResults = topsStoriesStructure.getResults() ;
                        UpdateRecyclerView();
                        mNewsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("News","TopStoriesFragment_backup.LoadingTopStories : Error : " + e.getMessage()) ;
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    private void UpdateRecyclerView ()
    {
         for (TopStoriesResult result : mTopStoriesResults) {
            News news = new News();
            if (result.getMultimedia().size() != 0) {
                news.setImageView(result.getMultimedia().get(0).getUrl());
            } else {
                news.setImageView("");
            }

            news.setTitle(result.getSection() + "/" + result.getSubsection());
            news.setText(result.getTitle());
            news.setUrl(result.getUrl());
            news.setDate(FrenchDate(result.getCreatedDate()));
            mNews.add(news);
        }

    }

    private String FrenchDate(String englishDate)
    {
        return englishDate.substring(8,10) + "/" +
                englishDate.substring(5,7) + "/"
                +englishDate.substring(0,4) ;
    }

    private void AdapterConfiguration()
    {
        mNews = new ArrayList<>();
        mTopStoriesResults = new ArrayList<>() ;

        mNewsAdapter = new NewsAdapter(mNews, Glide.with(this)) ;
        this.mRecyclerView.setAdapter(mNewsAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void onButtonPressed(Uri uri)
    {
       // if (mListener != null) {
        //    mListener.onFragmentInteraction(uri);
       // }
    }

    private void disposeWhenDestroy()
    {
            if(this.mDisposable != null && !this.mDisposable.isDisposed()) this.mDisposable.dispose();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
       // mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
  /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }*/
}
