package org.arachnis.numess;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A class to parse the JSON menu into readable format.
 */
public class MenuParser {

    public static ArrayList<String> Mon = new ArrayList<String>();
    public static ArrayList<String> Tue = new ArrayList<String>();
    public static ArrayList<String> Wed = new ArrayList<String>();
    public static ArrayList<String> Thu = new ArrayList<String>();
    public static ArrayList<String> Fri = new ArrayList<String>();
    public static ArrayList<String> Sat = new ArrayList<String>();
    public static ArrayList<String> Sun = new ArrayList<String>();

    public ArrayList<String> parse(JSONObject j) {
        try {
            JSONArray sheet1 = j.getJSONArray("Sheet1");

            for (int i = 0; i < sheet1.length(); i++) {
                JSONObject object3 = sheet1.getJSONObject(i);
                if (object3.has("Monday"))
                    Mon.add(object3.getString("Monday"));

                if (object3.has("Tuesday"))
                    Tue.add(object3.getString("Tuesday"));

                if (object3.has("Wednesday"))
                    Wed.add(object3.getString("Wednesday"));

                if (object3.has("Thursday"))
                    Thu.add(object3.getString("Thursday"));

                if (object3.has("Friday"))
                    Fri.add(object3.getString("Friday"));

                if (object3.has("Saturday"))
                    Sat.add(object3.getString("Saturday"));

                if (object3.has("Sunday"))
                    Sun.add(object3.getString("Sunday"));
            }

            //Log.v("MONDAY MENU",Mon.toString());
            //Log.v("TUESDAY MENU",Tue.toString());
            //Log.v("WEDNESDAY MENU",Wed.toString());
            //Log.v("THURSDAY MENU",Thu.toString());
            //Log.v("FRIDAY MENU",Fri.toString());
            //Log.v("SATURDAY MENU",Sat.toString());
            //Log.v("SUNDAY MENU",Sun.toString());

            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            // 3 letter name form of the day
            String day = new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime());
            if ("Mon".equals(day)) {
                Log.v("MENU", Mon.toString());
                return Mon;
            }
            if ("Tue".equals(day)) {
                Log.v("MENU", Tue.toString());
                return Tue;
            }
            if ("Wed".equals(day)) {
                Log.v("MENU", Wed.toString());
                return Wed;
            }
            if ("Thu".equals(day)) {
                Log.v("MENU", Thu.toString());
                return Thu;
            }
            if ("Fri".equals(day)) {
                Log.v("MENU", Fri.toString());
                return Fri;
            }
            if ("Sat".equals(day)) {
                Log.v("MENU", Sat.toString());
                return Sat;
            }
            if ("Sun".equals(day)) {
                Log.v("MENU", Sun.toString());
                return Sun;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> tomorrow(JSONObject j) {

        try {
            JSONArray sheet1 = j.getJSONArray("Sheet1");

            for (int i = 0; i < sheet1.length(); i++) {
                JSONObject object3 = sheet1.getJSONObject(i);
                if (object3.has("Monday"))
                    Mon.add(object3.getString("Monday"));

                if (object3.has("Tuesday"))
                    Tue.add(object3.getString("Tuesday"));

                if (object3.has("Wednesday"))
                    Wed.add(object3.getString("Wednesday"));

                if (object3.has("Thursday"))
                    Thu.add(object3.getString("Thursday"));

                if (object3.has("Friday"))
                    Fri.add(object3.getString("Friday"));

                if (object3.has("Saturday"))
                    Sat.add(object3.getString("Saturday"));

                if (object3.has("Sunday"))
                    Sun.add(object3.getString("Sunday"));
            }
            //Log.v("MONDAY MENU",Mon.toString());
            //Log.v("TUESDAY MENU",Tue.toString());
            //Log.v("WEDNESDAY MENU",Wed.toString());
            //Log.v("THURSDAY MENU",Thu.toString());
            //Log.v("FRIDAY MENU",Fri.toString());
            //Log.v("SATURDAY MENU",Sat.toString());
            //Log.v("SUNDAY MENU",Sun.toString());

            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            // 3 letter name form of the day
            String day = new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime());
            if ("Mon".equals(day)) {
                return Tue;
            }
            if ("Tue".equals(day)) {
                return Wed;
            }
            if ("Wed".equals(day)) {
                return Thu;
            }
            if ("Thu".equals(day)) {
                return Fri;
            }
            if ("Fri".equals(day)) {
                return Sat;
            }
            if ("Sat".equals(day)) {
                return Sun;
            }
            if ("Sun".equals(day)) {
                return Mon;
            }
            return Mon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
