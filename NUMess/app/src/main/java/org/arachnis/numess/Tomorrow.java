package org.arachnis.numess;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Implementation of Tomorrow's Menu.
 */
public class Tomorrow extends AppCompatActivity {
    public static ArrayList<String> menu;
    JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean nightmode = sharedPref.getBoolean("nightmode", false);
        if(nightmode)
        {
            setContentView(R.layout.activity_tomorrow_dark);
        }
        else
        {
            setContentView(R.layout.activity_tomorrow);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try {
            json = new JSONObject(getIntent().getStringExtra("json"));
            menu = new MenuParser().tomorrow(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> breakfast= new ArrayList<String>();
        ArrayList<String> lunch= new ArrayList<String>();
        ArrayList<String> hitea= new ArrayList<String>();
        ArrayList<String> dinner= new ArrayList<String>();

        try {
            int i = 0;
            for (i = 0; i <= 5; i++) {
                breakfast.add(menu.get(i));
            }

            for (; i <= 12; i++) {
                lunch.add(menu.get(i));
            }

            for (; i <= 14; i++) {
                hitea.add(menu.get(i));
            }

            for (; i <= 20; i++) {
                dinner.add(menu.get(i));
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        String result="";

        result += "BREAKFAST\n\n";
        if (breakfast != null) {
            for(String item: breakfast)   result += (item + "\n");
        }
        result += "\n\nLUNCH\n\n";
        if (lunch != null) {
            for(String item: lunch)   result += (item + "\n");
        }
        result += "\n\nHI-TEA\n\n";
        if (hitea != null) {
            for(String item: hitea)   result += (item + "\n");
        }
        result += "\n\nDINNER\n\n";
        if (dinner != null) {
            for(String item: dinner)   result += (item + "\n");
        }

        TextView tv = (TextView) findViewById(R.id.info_text_tomorrow);
        tv.setText(result);
    }

}
