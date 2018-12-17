package com.hoschtettler.jacques.mynews.Models;

import java.util.ArrayList;

public class AlreadyReadArticles {
    private ArrayList<String> mAlreadyReadArticlesList;

    // Constructor
    AlreadyReadArticles()
    {
        mAlreadyReadArticlesList = new ArrayList<>() ;
     }

    // Getters
    public String getAlreadyReadArticlesUrl(int index)
    {
        return mAlreadyReadArticlesList.get(index) ;
    }

    public ArrayList<String> getAlreadyReadArticlesList() {
        return mAlreadyReadArticlesList;
    }

    // Setters
    public void setAlreadyReadArticlesUrl(String Url)
    {
        mAlreadyReadArticlesList.add(Url) ;
    }

    public void setAlreadyReadArticlesList(ArrayList<String> alreadyReadArticlesList) {
        for (int index = 0 ; index < alreadyReadArticlesList.size() ; index++)
        {
            setAlreadyReadArticlesUrl(alreadyReadArticlesList.get(index));
        }

    }

    // Other method
    public void removeAlreadyReadArticlesUrl(String articleUrl)
    {
        mAlreadyReadArticlesList.remove(articleUrl) ;
    }
}

