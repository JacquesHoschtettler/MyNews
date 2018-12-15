package com.hoschtettler.jacques.mynews.Models;

import java.util.ArrayList;

public class AlreadyReadArticles {
    private ArrayList<String> mAlreadyReadArticlesList;

    AlreadyReadArticles()
    {
        mAlreadyReadArticlesList = new ArrayList<>() ;
     }

    public ArrayList<String> getAlreadyReadArticlesList() {
        return mAlreadyReadArticlesList;
    }

    public void setAlreadyReadArticlesList(ArrayList<String> alreadyReadArticlesList) {
        mAlreadyReadArticlesList = alreadyReadArticlesList;
    }

    public String getAlreadyReadArticlesUrl(int index)
    {
        return mAlreadyReadArticlesList.get(index) ;
    }

    public void setAlreadyReadArticlesUrl(String Url)
    {
        mAlreadyReadArticlesList.add(Url) ;
    }

    public void removeAlreadyReadArticlesUrl(String articleUrl)
    {
        mAlreadyReadArticlesList.remove(articleUrl) ;
    }
}

