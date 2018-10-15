package com.hoschtettler.jacques.mynews.Models;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class News {
    private Drawable mDrawable ;
    private String mTitle ;
    private String mText ;
    private String mUrl ;
    private String mDate ;

    public News()
    {
    }

    public News(Drawable imageView, String title, String text, String url, String date) {
        mDrawable = imageView;
        mTitle = title;
        mText = text;
        mUrl = url;
        mDate = date;
    }

    public Drawable getImageView() {
        return mDrawable;
    }

    public void setImageView(Drawable imageView) {
        mDrawable = imageView;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

}
