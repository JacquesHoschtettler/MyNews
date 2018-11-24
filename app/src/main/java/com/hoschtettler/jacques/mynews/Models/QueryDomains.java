package com.hoschtettler.jacques.mynews.Models;

public class QueryDomains {
    private String[] mQueryDomains = new String[8] ;

    public QueryDomains()
    {
        mQueryDomains[0] = "Books" ;
        mQueryDomains[1] = "Business" ;
        mQueryDomains[2] = " Health & Fitness" ;
        mQueryDomains[3] = "Society" ;
        mQueryDomains[4] = " Home & Garden" ;
        mQueryDomains[5] = "Environment" ;
        mQueryDomains[6] = "Sports" ;
        mQueryDomains[7] = "Travel" ;
   }

   public int getNumbenOfDomains()
   {
       return mQueryDomains.length ;
   }

    public String getQueryDomainName(int index) {
        return mQueryDomains[index];
    }
}
