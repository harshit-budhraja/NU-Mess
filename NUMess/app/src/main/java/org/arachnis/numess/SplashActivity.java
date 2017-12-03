package org.arachnis.numess;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.kobakei.ratethisapp.RateThisApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

/**
 * Implementation of Splash Screen.
 * This is the first screen when the app is launched.
 */
public class SplashActivity extends Activity {

    public JSONObject menuobj;
    public Boolean fetched=false;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 5000;
    private PendingIntent pendingIntent;
    OnlineActivity o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        PreferenceManager.setDefaultValues(this, R.xml.prefs, false);

        // Monitor launch times and interval from installation
        RateThisApp.onCreate(this);
        // Custom condition: 7 days and 10 launches
        RateThisApp.Config config = new RateThisApp.Config(7, 10);
        /*config.setTitle(R.string.my_own_title);
        config.setMessage(R.string.my_own_message);
        config.setYesButtonText(R.string.my_own_rate);
        config.setNoButtonText(R.string.my_own_thanks);
        config.setCancelButtonText(R.string.my_own_cancel);*/
        RateThisApp.init(config);
        o = new OnlineActivity();
        o.execute("https://arachnis.xyz/data/DH.json");
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if(!fetched) {  //Only executes if the menu is not yet fetched
                    o.cancel(true);
                    SharedPreferences sp = getSharedPreferences("MYPREFERENCES", 0);
                    JSONObject j = null;
                    try {
                        j = new JSONObject(sp.getString("menu", null));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Intent i = new Intent(SplashActivity.this, MenuActivity.class);
                    if (j != null) {
                        i.putExtra("json", j.toString());
                        Toast.makeText(getApplicationContext(), "Could not refresh the menu from server due to unstable internet connection", Toast.LENGTH_LONG).show();
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Could not retrieve a menu due to internal server error", Toast.LENGTH_LONG).show();
                        startActivity(i);
                    }
                }
            }
        }, SPLASH_TIME_OUT);
    }


    private class OnlineActivity extends AsyncTask<String,String,JSONObject> {

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        SharedPreferences sharedpreferences;
        private boolean running = true;

        @Override
        protected void onCancelled() {
            running = false;
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            if (isConnected()) {
                try {
                    if(running) {
                        URL url = null;
                        try {
                            url = new URL(strings[0]);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        connection = (HttpURLConnection) url.openConnection();
                        connection.connect();
                    }

                    if(running) {
                        InputStream stream = connection.getInputStream();

                        reader = new BufferedReader(new InputStreamReader(stream));

                        StringBuffer buffer = new StringBuffer();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            buffer.append(line);
                            Log.v("JSON Menu", line);
                        }
                        menuobj = new JSONObject(buffer.toString());
                        sharedpreferences= getSharedPreferences("MYPREFERENCES", 0);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("menu", menuobj.toString());
                        editor.apply();
                        return menuobj;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else
            {
                SharedPreferences sp = getSharedPreferences("MYPREFERENCES",0);
                try {
                    return new JSONObject(sp.getString("menu",null));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject response)
        {
            fetched = true;
            if(running) {
                Intent i = new Intent(SplashActivity.this, MenuActivity.class);
                if (response != null) {
                    i.putExtra("json", response.toString());
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Could not retrieve a menu", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
            }
        }
    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }


}
