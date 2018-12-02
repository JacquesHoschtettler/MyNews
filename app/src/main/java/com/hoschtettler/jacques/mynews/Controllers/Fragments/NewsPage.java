package com.hoschtettler.jacques.mynews.Controllers.Fragments;


import android.content.Context;
import android.net.Uri;
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

    protected NewsViewModel mNewsUrl ;
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
       // View view = inflater.inflate(getLayoutId(), container, false) ;
        View view = inflater.inflate(R.layout.fragment_news_page, container, false) ;

        ButterKnife.bind(this,view) ;

        AdapterConfiguration() ;

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
                        mNewsUrl.setChosendUrl(mNewsAdapter.getUrl(position));
                    }
                });
    }


    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
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

        mNewsUrl = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);
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


