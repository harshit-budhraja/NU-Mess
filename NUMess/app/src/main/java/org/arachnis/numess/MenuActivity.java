package org.arachnis.numess;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.kobakei.ratethisapp.RateThisApp;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Activity to host menu fragments for breakfast, lunch, hi-tea and dinner.
 */
public class MenuActivity extends AppCompatActivity {

    JSONObject json;
    public static ArrayList<String> menu;
    public ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    FloatingActionButton fab;

    public void stoprate() {
        RateThisApp.stopRateDialog(this);
    }

    /*public void addItemsToDB() {
        Search_DatabaseHandler db = new Search_DatabaseHandler(this);
        db.dropandRefreshTable();
        Log.v("DB INSERT","Inserting items...");

        try {
            int count = 0;
            for (String i : MenuParser.Mon) {
                if (count <= 5)
                    db.addItem(new Search_MenuClass(i, "Mon", "B"));
                else if (count <= 12)
                    db.addItem(new Search_MenuClass(i, "Mon", "L"));
                else if (count <= 14)
                    db.addItem(new Search_MenuClass(i, "Mon", "H"));
                else
                    db.addItem(new Search_MenuClass(i, "Mon", "D"));
                count++;
            }
            Log.v("DB INSERT", count + " items inserted");

            count = 0;
            for (String i : MenuParser.Tue) {
                if (count <= 5)
                    db.addItem(new Search_MenuClass(i, "Tue", "B"));
                else if (count <= 12)
                    db.addItem(new Search_MenuClass(i, "Tue", "L"));
                else if (count <= 14)
                    db.addItem(new Search_MenuClass(i, "Tue", "H"));
                else
                    db.addItem(new Search_MenuClass(i, "Tue", "D"));
                count++;
            }
            Log.v("DB INSERT", count + " items inserted");

            count = 0;
            for (String i : MenuParser.Wed) {
                if (count <= 5)
                    db.addItem(new Search_MenuClass(i, "Wed", "B"));
                else if (count <= 12)
                    db.addItem(new Search_MenuClass(i, "Wed", "L"));
                else if (count <= 14)
                    db.addItem(new Search_MenuClass(i, "Wed", "H"));
                else
                    db.addItem(new Search_MenuClass(i, "Wed", "D"));
                count++;
            }
            Log.v("DB INSERT", count + " items inserted");

            count = 0;
            for (String i : MenuParser.Thu) {
                if (count <= 5)
                    db.addItem(new Search_MenuClass(i, "Thu", "B"));
                else if (count <= 12)
                    db.addItem(new Search_MenuClass(i, "Thu", "L"));
                else if (count <= 14)
                    db.addItem(new Search_MenuClass(i, "Thu", "H"));
                else
                    db.addItem(new Search_MenuClass(i, "Thu", "D"));
                count++;
            }
            Log.v("DB INSERT", count + " items inserted");

            count = 0;
            for (String i : MenuParser.Fri) {
                if (count <= 5)
                    db.addItem(new Search_MenuClass(i, "Fri", "B"));
                else if (count <= 12)
                    db.addItem(new Search_MenuClass(i, "Fri", "L"));
                else if (count <= 14)
                    db.addItem(new Search_MenuClass(i, "Fri", "H"));
                else
                    db.addItem(new Search_MenuClass(i, "Fri", "D"));
                count++;
            }
            Log.v("DB INSERT", count + " items inserted");

            count = 0;
            for (String i : MenuParser.Sat) {
                if (count <= 5)
                    db.addItem(new Search_MenuClass(i, "Sat", "B"));
                else if (count <= 12)
                    db.addItem(new Search_MenuClass(i, "Sat", "L"));
                else if (count <= 14)
                    db.addItem(new Search_MenuClass(i, "Sat", "H"));
                else
                    db.addItem(new Search_MenuClass(i, "Sat", "D"));
                count++;
            }
            Log.v("DB INSERT", count + " items inserted");

            count = 0;
            for (String i : MenuParser.Sun) {
                if (count <= 5)
                    db.addItem(new Search_MenuClass(i, "Sun", "B"));
                else if (count <= 12)
                    db.addItem(new Search_MenuClass(i, "Sun", "L"));
                else if (count <= 14)
                    db.addItem(new Search_MenuClass(i, "Sun", "H"));
                else
                    db.addItem(new Search_MenuClass(i, "Sun", "D"));
                count++;
            }
            Log.v("DB INSERT", count + " items inserted");
        }
        catch (java.util.ConcurrentModificationException e)
        {
            e.printStackTrace();
        }

        Log.v("DB READ","Reading items...");
        List<Search_MenuClass> items = db.searchItem("Chapp");
        for(Search_MenuClass s : items) {
            Log.v("SEARCH RESULT",s.getItem_name() + " - " + s.getDay() + " - " + s.getMeal_type());
        }
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // If the condition is satisfied, "Rate this app" dialog will be shown
        RateThisApp.showRateDialogIfNeeded(this);
        RateThisApp.setCallback(new RateThisApp.Callback() {
            @Override
            public void onYesClicked() {
                stoprate();
            }

            @Override
            public void onNoClicked() {

            }

            @Override
            public void onCancelClicked() {

            }
        });

        int default_tab = TimeChecker.tabnum();

        ChangeLog cl = new ChangeLog(this);
        if (cl.firstRun())
            cl.getLogDialog().show();

        SharedPreferences sp = getSharedPreferences("MYPREFERENCES",0);
        try {
            json = new JSONObject(sp.getString("menu",null));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(json != null)
            {
                menu = new MenuParser().parse(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);
        viewPager.setCurrentItem(default_tab);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            toolbar.setBackgroundColor(getResources().getColor(R.color.breakfast));
                            tabLayout.setBackgroundColor(getResources().getColor(R.color.breakfast));
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(getResources().getColor(R.color.breakfastdark));
                            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.breakfastdark)));
                        }
                        break;
                    case 1:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            toolbar.setBackgroundColor(getResources().getColor(R.color.lunch));
                            tabLayout.setBackgroundColor(getResources().getColor(R.color.lunch));
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(getResources().getColor(R.color.lunchdark));
                            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.lunchdark)));
                        }
                        break;
                    case 2:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            toolbar.setBackgroundColor(getResources().getColor(R.color.hitea));
                            tabLayout.setBackgroundColor(getResources().getColor(R.color.hitea));
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(getResources().getColor(R.color.hiteadark));
                            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.hiteadark)));
                        }
                        break;
                    case 3:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            toolbar.setBackgroundColor(getResources().getColor(R.color.dinner));
                            tabLayout.setBackgroundColor(getResources().getColor(R.color.dinner));
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(getResources().getColor(R.color.dinnerdark));
                            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dinnerdark)));
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "For error reports and suggestions, write to harshit.budhraja@st.niituniversity.in", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent send = new Intent(Intent.ACTION_SENDTO);
                String uriText = "mailto:" + Uri.encode("harshit.budhraja@st.niituniversity.in") +
                        "?subject=" + Uri.encode("Feedback and Suggestions | NU Mess App") +
                        "&body=" + Uri.encode("");
                Uri uri = Uri.parse(uriText);

                send.setData(uri);
                startActivity(Intent.createChooser(send, "Give a Feedback..."));
            }
        });

        switch (default_tab) {
            case 0:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.breakfast));
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.breakfast));
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.breakfastdark));
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.breakfastdark)));
                }
                break;
            case 1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.lunch));
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.lunch));
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.lunchdark));
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.lunchdark)));
                }
                break;
            case 2:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.hitea));
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.hitea));
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.hiteadark));
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.hiteadark)));
                }
                break;
            case 3:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.dinner));
                    tabLayout.setBackgroundColor(getResources().getColor(R.color.dinner));
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.dinnerdark));
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dinnerdark)));
                }
                break;
            default:
                break;
        }
        //new Thread(new DBSearch()).start();

    }

    /*private class DBSearch implements Runnable {

        @Override
        public void run() {
            addItemsToDB();
        }
    }
    */

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle bundleBreakfast = new Bundle();
        Bundle bundleLunch = new Bundle();
        Bundle bundleHitea = new Bundle();
        Bundle bundleDinner = new Bundle();

        ArrayList<String> breakfast= new ArrayList<String>();
        ArrayList<String> lunch= new ArrayList<String>();
        ArrayList<String> hitea= new ArrayList<String>();
        ArrayList<String> dinner= new ArrayList<String>();

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        // 3 letter name form of the day
        String day = new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime());

        try {
            int i = 0;
            System.out.println("breakfast");
            if ("Sun".equals(day)) {
                breakfast.add("9:00AM to 10:00AM");
            }
            else
            {
                breakfast.add("7:30AM to 9:30AM");
            }
            for (i = 0; i <= 5; i++) {
                System.out.println(menu.get(i));
                breakfast.add(menu.get(i));
            }

            System.out.println("lunch");
            lunch.add("12:30PM to 2:30PM");
            for (; i <= 12; i++) {
                System.out.println(menu.get(i));
                lunch.add(menu.get(i));
            }

            System.out.println("hi-tea");
            hitea.add("5:00PM to 6:30PM");
            for (; i <= 14; i++) {
                System.out.println(menu.get(i));
                hitea.add(menu.get(i));
            }

            System.out.println("dinner");
            dinner.add("8:30PM to 9:45PM");
            for (; i <= 20; i++) {
                System.out.println(menu.get(i));
                dinner.add(menu.get(i));
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }


        bundleBreakfast.putStringArrayList("items", breakfast);
        bundleLunch.putStringArrayList("items", lunch);
        bundleHitea.putStringArrayList("items", hitea);
        bundleDinner.putStringArrayList("items", dinner);

        PlaceholderFragment mfBreakfast = new PlaceholderFragment();
        PlaceholderFragment mfLunch = new PlaceholderFragment();
        PlaceholderFragment mfHitea = new PlaceholderFragment();
        PlaceholderFragment mfDinner = new PlaceholderFragment();

        adapter.addFragment(mfBreakfast, "BREAKFAST");
        mfBreakfast.setArguments(bundleBreakfast);

        adapter.addFragment(mfLunch, "LUNCH");
        mfLunch.setArguments(bundleLunch);

        adapter.addFragment(mfHitea, "HI-TEA");
        mfHitea.setArguments(bundleHitea);

        adapter.addFragment(mfDinner, "DINNER");
        mfDinner.setArguments(bundleDinner);

        SharedPreferences sp = getSharedPreferences("MYPREFERENCES",0);
        SharedPreferences.Editor e = sp.edit();
        e.putString("widget0",bundleBreakfast.toString());
        e.putString("widget1",bundleLunch.toString());
        e.putString("widget2",bundleHitea.toString());
        e.putString("widget3",bundleDinner.toString());
        e.apply();

        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        /*final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            Intent share_intent = new Intent("android.intent.action.SEND");
            share_intent.setType("text/plain");
            share_intent.putExtra("android.intent.extra.SUBJECT", "Hey! Check this out");
            share_intent.putExtra("android.intent.extra.TEXT", "Hey! Checking NU Mess Menu has never been so easy for me." +
                    " I'm sure you would find it useful and easy too. Please do not forget to rate it. Check out on Google Play:- https://play.google.com/store/apps/details?id=org.arachnis.numess");
            startActivity(Intent.createChooser(share_intent, "Complete action using"));
            return true;
        }
        if (id == R.id.action_tomorrow) {
            Intent i = new Intent(MenuActivity.this,Tomorrow.class);
            i.putExtra("json",json.toString());
            startActivity(i);
            return true;
        }
        if (id == R.id.action_prefs) {
            Intent i = new Intent(MenuActivity.this,PrefsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



        class ViewPagerAdapter extends FragmentPagerAdapter {
            private final List<Fragment> mFragmentList = new ArrayList<>();
            private final List<String> mFragmentTitleList = new ArrayList<>();

            public ViewPagerAdapter(FragmentManager manager) {
                super(manager);
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            public void addFragment(Fragment fragment, String title) {
                mFragmentList.add(fragment);
                mFragmentTitleList.add(title);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentTitleList.get(position);
            }
        }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
}
