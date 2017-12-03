package org.arachnis.numess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of search functionality. [NOT IN USE]
 */

public class Search_DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "dhmenu";

    // Items table name
    private static final String TABLE_ITEMS = "items";

    // Items Table Columns names
    private static final String KEY_ID = "item_id";
    private static final String KEY_ITEM_NAME = "item_name";
    private static final String KEY_TYPE = "meal_type";
    private static final String KEY_DAY = "day";

    // Default constructor
    public Search_DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ITEM_NAME + " TEXT,"
                + KEY_TYPE + " TEXT, " + KEY_DAY + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);

        // Create tables again
        onCreate(db);
    }

    public void dropandRefreshTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);

        // Create tables again
        onCreate(db);
    }

    public void addItem(Search_MenuClass item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_NAME, item.getItem_name());
        values.put(KEY_DAY, item.getDay());
        values.put(KEY_TYPE, item.getMeal_type());

        // Inserting Row
        db.insert(TABLE_ITEMS, null, values);
        db.close(); // Closing database connection
    }

    public List<Search_MenuClass> searchItem(String name) {
        List<Search_MenuClass> itemslist = new ArrayList<Search_MenuClass>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS + " WHERE " + KEY_ITEM_NAME + " LIKE '%" + name + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Search_MenuClass m = new Search_MenuClass();
                m.setItem_name(cursor.getString(1));
                m.setMeal_type(cursor.getString(2));
                m.setDay(cursor.getString(3));
                // Adding contact to list
                itemslist.add(m);
            } while (cursor.moveToNext());
        }

        return itemslist;
    }
}
