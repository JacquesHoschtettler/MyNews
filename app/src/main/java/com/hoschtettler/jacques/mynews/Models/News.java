package com.hoschtettler.jacques.mynews.Models;

import com.hoschtettler.jacques.mynews.R;

public class News {
    private String mMediaUrl ;
    private String mTitle ;
    private String mText ;
    private String mUrl ;
    private String mDate ;
    private Integer mBackground ;
    private boolean mAlreadyRead ;


   // ***************
    //Constructors
    //***************
    public News()
    {
        mMediaUrl = "";
        mTitle = "";
        mText = "";
        mUrl = "";
        mDate = "";
        mBackground = R.color.Translucent ;
        mAlreadyRead = false ;
    }

    public News(String mediaUrl, String title, String text, String url, String date, Integer background) {
        mMediaUrl = mediaUrl;
        mTitle = title;
        mText = text;
        mUrl = url;
        mDate = date;
        mBackground = background ;
    }


    // ***************
    // Getters
    //***************

    public String getMediaUrl() {
        return mMediaUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getText() {
        return mText;
    }

     public String getUrl() {
        return mUrl;
    }

    public String getDate() {
        return mDate;
    }

    public Integer getBackground() {
        return mBackground;
    }

    public boolean getAlreadyRead() {
        return mAlreadyRead ;
    }

    // ***************
    // Setters
    //***************
    public void setImageView(String mediaUrl) {
        mMediaUrl = mediaUrl;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setText(String text) {
        mText = text;
    }

    public void setUrl(String url) {
        mUrl = url;
    }


    public void setDate(String date) {
        mDate = date;
    }

    public void setBackground(Integer background) {
        mBackground = background;
    }

    public void setAlreadyRead(boolean read)
    {
        mAlreadyRead = read ;
    }

}
