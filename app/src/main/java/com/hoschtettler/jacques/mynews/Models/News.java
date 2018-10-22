package com.hoschtettler.jacques.mynews.Models;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class News {
    private String mMediaUrl ;
    private String mTitle ;
    private String mText ;
    private String mUrl ;
    private String mDate ;

    public News()
    {
    }

    public News(String mediaUrl, String title, String text, String url, String date) {
        mMediaUrl = mediaUrl;
        mTitle = title;
        mText = text;
        mUrl = url;
        mDate = date;
    }

    public String getMediaUrl() {
        return mMediaUrl;
    }

    public void setImageView(String mediaUrl) {
        mMediaUrl = mediaUrl;
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
