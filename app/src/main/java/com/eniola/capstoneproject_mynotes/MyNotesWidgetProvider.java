package com.eniola.capstoneproject_mynotes;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import com.eniola.capstoneproject_mynotes.ui.DashboardActivity;
import com.eniola.capstoneproject_mynotes.utilities.AppConstant;

public class MyNotesWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews remoteViews;
        Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
        int width = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        Log.d(AppConstant.DEBUG_TAG, "width is " + width);
        remoteViews = makeWidgetLaunchDefaultActivity(context);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

    }

    private static RemoteViews makeWidgetLaunchDefaultActivity(Context context){
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_notes_widget_provider);
        Intent intent = new Intent(context, DashboardActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.app_widget_image_view, pendingIntent);
        return  remoteViews;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

