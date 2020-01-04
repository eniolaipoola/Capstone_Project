package com.eniola.capstoneproject_mynotes;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import com.eniola.capstoneproject_mynotes.models.Notes;

public class MyNotesWidgetProvider extends AppWidgetProvider {

    public static Notes createNote;

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int[] appWidgetId, Notes notes) {
        createNote = notes;
        for (int appWidget : appWidgetId) {
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_notes_widget_provider);
            views.setTextViewText(R.id.note_title_widget, createNote.getTitle());
            views.setTextViewText(R.id.note_content_widget, createNote.getContent());
            appWidgetManager.updateAppWidget(appWidget, views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
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

