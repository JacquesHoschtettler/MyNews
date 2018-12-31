package com.hoschtettler.jacques.mynews.Utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import com.hoschtettler.jacques.mynews.Models.FreeSubject.FreeSubjectStructure;
import com.hoschtettler.jacques.mynews.Models.FreeSubject.Response;
import com.hoschtettler.jacques.mynews.Models.NewsViewModel;
import com.hoschtettler.jacques.mynews.R;

import java.util.Calendar;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class NotificationReceiver extends BroadcastReceiver {
    private Disposable mDisposable ;
    private Response mFreeSubjectResults;
    private NewsViewModel mNewsViewModel ;
    private int mNumberOfArticles ;
    private String mDate ;
    private String mQueryTerm ;
    private String mFormattedQueryDomains ;
    final private String EXTRA_QUERY = "EXTRA_QUERY" ;
    final private String EXTRA_FILTERS = "EXTRA_FILTERS" ;

    @Override
    public void onReceive(final Context context, Intent intent) {
        mNewsViewModel = new NewsViewModel() ;

        Bundle extras = intent.getExtras() ;
        mQueryTerm = (String) extras.get(EXTRA_QUERY);
        mFormattedQueryDomains =(String) extras.get(EXTRA_FILTERS) ;
        mDate = formattedActualDate() ;

        mDisposable = NewsStreams.SearchArticlesStream(0, mQueryTerm, mDate,
                mDate, mFormattedQueryDomains)
                .subscribeWith(new DisposableObserver<FreeSubjectStructure>() {
                    @Override
                    public void onNext(FreeSubjectStructure freeSubjectStructure) {
                        mFreeSubjectResults = freeSubjectStructure.getResponse();
                        mNumberOfArticles = mFreeSubjectResults.getDocs().size() ;
                        pullTheAlreadyReadArticles() ;
                        messageResultOfNotification(mNumberOfArticles, context);
                                                }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private String formattedActualDate()
    {
        String date = "" +  Calendar.getInstance().get(Calendar.YEAR) ;

        int month = Calendar.getInstance().get(Calendar.MONTH) + 1 ;
        if (month <=9)
        {
            date += "0" ;
        }
        date += month ;

        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) ;
        if (day <= 9)
        {
            date+= "0" ;
        }
        date += day ;
        return date ;
    }

    // Pull the already read articles off the list of articles found in the notification
    private void pullTheAlreadyReadArticles()
    {
        if (mNumberOfArticles != 0) {
            for (int pointer = 0 ; pointer < mNumberOfArticles ; pointer++) {
                String urlToTest = mFreeSubjectResults.getDocs().get(pointer).getWebUrl() ;
                for (int windowNumber = 0; windowNumber < mNewsViewModel.getNumberOfWindows(); windowNumber++) {
                    for (int index = 0; index < mNewsViewModel.getAlreadyReadArticlesList(windowNumber).size(); index++) {
                        String urlAlreadyRead = mNewsViewModel.getAlreadyReadArticleUrl(windowNumber, index  );
                        if (urlToTest.equals(urlAlreadyRead))
                        {
                            mNumberOfArticles-- ;
                        }
                    }
                }
            }
        }
    }

    // Build and display the result message for the notification
    private void messageResultOfNotification(int numberOfArticles, Context context)
    {
        Resources res = context.getResources() ;
        String message = res.getString(R.string.notification_date_label) ;
        message +=  " " + human_date() ;
        message += "\n" ;
        switch (numberOfArticles)
        {
            case 0 :
                message += res.getString(R.string.none_articles_found) ;
                break;
            case 1 :
                message += res.getString(R.string.one_article_found) ;
                break ;
            default:
                message += res.getString(R.string.more_articles_found_begin) ;
                message += " " + numberOfArticles +" ";
                message += res.getString(R.string.more_articles_found_end) ;
        }
        message += " " + mQueryTerm ;
        message += res.getString(R.string.article_query_filters) ;

        String queryDomains = mFormattedQueryDomains.substring(1, mFormattedQueryDomains.length()-1) ;
        message += " " + queryDomains ;

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new
                    NotificationChannel("0",
                    "messages channe",
                    NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription("messages channel");
            NotificationManager notificationManager =
                    context.getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context, "0")
                            .setSmallIcon(R.mipmap.poweredby_nytimes_30b)
                            .setContentTitle(res.getString(R.string.notification_result))
                            .setAutoCancel(true)
                            .setContentText(message)
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText(message))
                    ;

            notificationManager.notify(0, mBuilder.build());
        }
        else
        {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context, "0")
                            .setSmallIcon(R.mipmap.poweredby_nytimes_30b)
                            .setContentTitle(res.getString(R.string.notification_result))
                            .setAutoCancel(true)
                            .setContentText(message)
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText(message))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);


            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

            notificationManager.notify(0, mBuilder.build());
        }
    }

    // Translate the searching date in a human readable format (French)
    private String human_date()
    {
        String human_date = "" +mDate.substring(6,8) + "/" + mDate.substring(4,6) + "/"
                            + mDate.substring(0,4) ;
        return human_date ;
    }
}
