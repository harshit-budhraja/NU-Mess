package org.arachnis.numess;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Implementation of Search Callback functionality. [NOT IN USE]
 */
public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    public void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query =
                    intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    private void doSearch(String queryStr) {
        Search_DatabaseHandler sh = new Search_DatabaseHandler(this);
        List<Search_MenuClass> results = sh.searchItem(queryStr);
        String textres = "";
        for(Search_MenuClass s : results) {
            textres += s.getItem_name() + " - " + s.getDay() + " - " + s.getMeal_type() + "\n";
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(textres)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
