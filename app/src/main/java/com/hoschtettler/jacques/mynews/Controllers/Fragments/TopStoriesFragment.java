package com.hoschtettler.jacques.mynews.Controllers.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import com.hoschtettler.jacques.mynews.Models.News;
import com.hoschtettler.jacques.mynews.Models.NewsAdapter;
import com.hoschtettler.jacques.mynews.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private List<News> mNews ;
    @BindView(R.id.fragment_top_stories_recycler_view) RecyclerView mRecyclerView ;
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
        RecyclerViewConfiguration() ;
        AdapterConfiguration() ;

        mNewsAdapter.notifyDataSetChanged();

        return view ;
    }

    private void RecyclerViewConfiguration()
    {
        mNews = new ArrayList<>();
        for (int i = 0 ; i< 8 ; i++)
        {
            News news = new News(getResources().getDrawable(R.mipmap.ic_launcher_round),
                    "New "+i, "Tout va bien, Madame la Marquise.", "adresse url", i+"/10/2018") ;
            mNews.add(news) ;
        }
     }

    private void AdapterConfiguration()
    {
        mNewsAdapter = new NewsAdapter(mNews) ;
        this.mRecyclerView.setAdapter(mNewsAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void onButtonPressed(Uri uri)
    {
       // if (mListener != null) {
        //    mListener.onFragmentInteraction(uri);
       // }
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
