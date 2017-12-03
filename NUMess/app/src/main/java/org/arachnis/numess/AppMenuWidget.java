package org.arachnis.numess;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.sql.Time;
import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class AppMenuWidget extends AppWidgetProvider {

    public static void onUpdate(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_menu_widget);
        int n = TimeChecker.tabnum();
        switch(n) {
            case 0: views.setTextViewText(R.id.what,"TODAY's BREAKFAST");
                    break;
            case 1: views.setTextViewText(R.id.what,"TODAY's LUNCH");
                    break;
            case 2: views.setTextViewText(R.id.what,"TODAY's HI-TEA");
                    break;
            case 3: views.setTextViewText(R.id.what,"TONIGHT's DINNER");
                    break;
            default:    views.setTextViewText(R.id.what,"TODAY's MENU");
        }

        Intent intent = new Intent(context, MenuActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.widget_title_bar, pendingIntent);
        views.setRemoteAdapter(R.id.contest_list_widget, new Intent(context, WidgetService.class));
        views.setEmptyView(R.id.contest_list_widget, R.id.contest_list_widget_empty);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            onUpdate(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent != null) {
            if (intent.getAction().equals("org.arachnis.numess.BROADCAST_SYNC_PERFORMED")) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, getClass()));
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.contest_list_widget);
            }
        }
    }
}

