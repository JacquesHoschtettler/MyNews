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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hoschtettler.jacques.mynews.Controllers.Activities.NewsStreams;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {"@link" TopStoriesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopStoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopStoriesFragment extends Fragment {

    private static final String KEY_URL = "Url" ;
    private Disposable mDisposable ;

    private List<TopStoriesResult> mTopStoriesResults ;
    private List<News> mNews ;
    @BindView(R.id.fragment_top_stories_recycler_view) RecyclerView mRecyclerView ;
    @BindView(R.id.text_in_top_stories_fragment) TextView mTextView ;
    @BindView(R.id.top_stories_progress_bar) ProgressBar mProgressBar ;
    private NewsAdapter mNewsAdapter ;

    public TopStoriesFragment() {
        // Required empty public constructor
    }

    public static TopStoriesFragment newInstance()
    {
        TopStoriesFragment topStoriesFragment = new TopStoriesFragment();
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
        LoadingTopStories() ;

        if (mTopStoriesResults.size() != 0) {
            Log.e("News", "TopStoriesFragemnt.OnCreateView : result :" + mTopStoriesResults.get(0).getTitle());
        }
        else {
            Log.e("News", "TopStoriesFragemnt.OnCreateView : result de taille nulle !");
        }

        RecyclerViewConfiguration() ;

        AdapterConfiguration() ;

        mNewsAdapter.notifyDataSetChanged();

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
        mTopStoriesResults = new ArrayList<TopStoriesResult>() ;

        updateUIWhenStartingHTTPRequest() ;

        this.mDisposable = NewsStreams.TopStoriesStream(0)
                .subscribeWith(new DisposableObserver<TopsStoriesStructure>() {
                    @Override
                    public void onNext(TopsStoriesStructure topsStoriesStructure) {
                        TopsStoriesStructure temp = topsStoriesStructure ;
                        mTopStoriesResults = temp.getResults() ;
                        Log.e("News", "TopStoriesFragemnt.LoadingTopStories : result :"+mTopStoriesResults.get(0).getTitle()) ;
                        RecyclerViewConfiguration();
                        }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("News","TopStoriesFragment.LoadingTopStories : Y'a un bug : " + e.getMessage()) ;
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        updateUIWhenStopingHTTPRequest();

    }

    private void updateUIWhenStartingHTTPRequest(){
        mTextView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);

    }

    private void updateUIWhenStopingHTTPRequest(){
        mTextView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);

    }


    private void RecyclerViewConfiguration()
    {

        mNews = new ArrayList<>();

        for (TopStoriesResult result : mTopStoriesResults)
        {
            News news = new News(result.getMultimedia().get(2).getUrl(),
                    result.getTitle(), result.getAbstract(), result.getUrl(),
                    result.getCreatedDate()) ;
            mNews.add(news) ;
        }

        Log.e("News", "TopStoriesFragemnt.RecylerViewConfiguration : news :"+mNews.get(0).getTitle()) ;
    }

    private void AdapterConfiguration()
    {
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
