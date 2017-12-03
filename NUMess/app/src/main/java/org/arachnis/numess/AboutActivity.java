package org.arachnis.numess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Implementation of App Info Screen.
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        TextView versionname = (TextView) findViewById(R.id.versionname);
        versionname.setText("v" + BuildConfig.VERSION_NAME);
    }

    public void viewchangelog(View view) {
        ChangeLog cl = new ChangeLog(this);
        cl.getFullLogDialog().show();
    }
}
