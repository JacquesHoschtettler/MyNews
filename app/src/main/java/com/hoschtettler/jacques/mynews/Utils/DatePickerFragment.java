package com.hoschtettler.jacques.mynews.Utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import com.hoschtettler.jacques.mynews.Models.NewsViewModel;
import com.hoschtettler.jacques.mynews.R;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment  implements DatePickerDialog.OnDateSetListener {


    public NewsViewModel mNewsViewModel ;

    public DatePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        mNewsViewModel = ViewModelProviders.of(this)
                .get(NewsViewModel.class) ;

        Dialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        ButterKnife.bind(this,dialog) ;

        // Create a new instance of DatePickerDialog and return it
        return dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        String dateString = reformatDate(year, month, day) ;

        Log.d("MyNews", "DatePickerFragment.onDateSet : date = " + dateString) ;

        switch (view.getId()) {
            case R.id.begin_date_btn:
                mNewsViewModel.setBeginDate(dateString);
                Log.d("MyNews", "DatePickerFragment.onDateSet : begin date = " + dateString);
                break;
            case R.id.end_date_btn:
                mNewsViewModel.setEndDate(dateString);
                Log.d("MyNews", "DatePickerFragment.onDateSet : end date = " + dateString);
                break;
            default:
                Log.d("MyNews", "DatePickerFragment.onDateSet : view Id = "+view.getId()) ;
                break;
        }
    }

    private String reformatDate(int year, int month, int day)
    {
        String date ;
        ++month;
        date = year + "/";
        if (month < 10) {
            date += "0";
        }
        date += month;
        date += "/";
        if (day < 10) {
            date += "0";
        }
        date += day;
        return date ;
    }
}