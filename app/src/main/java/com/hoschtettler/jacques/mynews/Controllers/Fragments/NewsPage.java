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
import com.hoschtettler.jacques.mynews.Models.MostPopular.MostPopularResult;
import com.hoschtettler.jacques.mynews.Models.MostPopular.MostPopularStructure;
import com.hoschtettler.jacques.mynews.Models.News;
import com.hoschtettler.jacques.mynews.Models.TopStories.TopStoriesResult;
import com.hoschtettler.jacques.mynews.Models.TopStories.TopsStoriesStructure;
import com.hoschtettler.jacques.mynews.Utils.NewsAdapter;
import com.hoschtettler.jacques.mynews.R;
import com.hoschtettler.jacques.mynews.Utils.NewsStreams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class NewsPage extends Fragment {

    protected abstract NewsPage newsInstance() ;
    protected abstract int getLayoutId() ;
    protected abstract void LoadingNews() ;
    protected abstract void AdapterConfiguration() ;


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
        View view = inflater.inflate(getLayoutId(), container, false) ;

        ButterKnife.bind(this,view) ;

        AdapterConfiguration() ;

        this.LoadingNews() ;

        return view ;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }


    protected String FrenchDate(String englishDate)
    {
        return englishDate.substring(8,10) + "/" +
                englishDate.substring(5,7) + "/"
                +englishDate.substring(0,4) ;
    }



    public void onButtonPressed(Uri uri)
    {
        // if (mListener != null) {
        //    mListener.onFragmentInteraction(uri);
        // }
    }

    protected void disposeWhenDestroy(Disposable disposable)
    {
        if(disposable != null && !disposable.isDisposed()) disposable.dispose();
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


