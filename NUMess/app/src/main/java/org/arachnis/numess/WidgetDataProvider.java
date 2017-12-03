package org.arachnis.numess;

import android.app.LauncherActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Binder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

/**
 * Widget Data Provider. [NOT IN USE]
 */

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    private Context context = null;
    private ArrayList<String> ItemList = new ArrayList<String>();

    WidgetDataProvider(Context context, Intent intent) {
        this.context = context;
        populateItems();
    }

    private void populateItems() {
        for(int i=0;i<6;i++) {
            ItemList.add("Item #" + i);
        }
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ItemList.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {

        RemoteViews view = new RemoteViews(context.getPackageName(),
                R.layout.widget_list_item);
        view.setTextViewText(R.id.menu_item_tv, ItemList.get(i));
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
