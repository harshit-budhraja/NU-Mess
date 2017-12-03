package org.arachnis.numess;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view for each meal.
 */
public class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        Boolean nightmode = sharedPref.getBoolean("nightmode", false);
        if(nightmode)
        {
            rootView = inflater.inflate(R.layout.fragment_menu_dark, container, false);
        }
        else
        {
            rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        }

        Bundle b = getArguments();
        TextView tvTimings = (TextView) rootView.findViewById(R.id.info_text_timings);
        TextView tvResult = (TextView) rootView.findViewById(R.id.info_text);

        ArrayList<String> items = b.getStringArrayList("items");

        String result = "";
        String timing="Usual Meal Timing";
        try{
            timing = items.get(0);
        }
        catch(IndexOutOfBoundsException iobe)
        {
            iobe.printStackTrace();
        }

        if (items != null) {
            for(int i=1; i<items.size();i++)   result += (items.get(i) + "\n");
        }

        try {
            tvTimings.setText(timing);
            tvResult.setText(result);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }
}