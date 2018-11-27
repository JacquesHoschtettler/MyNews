package com.hoschtettler.jacques.mynews.Models;

import com.hoschtettler.jacques.mynews.R;

public class QueryDomains {

    private int mQueryDomainsNumber = 8 ;
    private String[] mQueryDomains = new String[mQueryDomainsNumber] ;
    private int[] mCheckBoxesId = new int[mQueryDomainsNumber] ;

    public QueryDomains()
    {
        mQueryDomains[0] = "Books" ;
        mQueryDomains[1] = "Business" ;
        mQueryDomains[2] = "Health & Fitness" ;
        mQueryDomains[3] = "Society" ;
        mQueryDomains[4] = "Home & Garden" ;
        mQueryDomains[5] = "Environment" ;
        mQueryDomains[6] = "World" ;
        mQueryDomains[7] = "Politics" ;

        mCheckBoxesId[0] = R.id.query_domain_0 ;
        mCheckBoxesId[1] = R.id.query_domain_1 ;
        mCheckBoxesId[2] = R.id.query_domain_2 ;
        mCheckBoxesId[3] = R.id.query_domain_3 ;
        mCheckBoxesId[4] = R.id.query_domain_4 ;
        mCheckBoxesId[5] = R.id.query_domain_5 ;
        mCheckBoxesId[6] = R.id.query_domain_6 ;
        mCheckBoxesId[7] = R.id.query_domain_7 ;
   }

    public void setQueryDomains(int index, String domain)
    {
        mQueryDomains[index] = domain ;
    }

    public String getQueryDomain(int index) {
        return mQueryDomains[index];
    }

    public int getCheckBoxId(int index)
    {
        return mCheckBoxesId[index] ;
    }

    public void setCheckBoxesId(int index, int id)
    {
        mCheckBoxesId[index] = id ;
    }

    public void setQueryDomainsNumber(int queryDomainsNumber) {
        mQueryDomainsNumber = queryDomainsNumber;
    }

    public int getQueryDomainsNumber()
    {
        return mQueryDomainsNumber ;
    }
}
