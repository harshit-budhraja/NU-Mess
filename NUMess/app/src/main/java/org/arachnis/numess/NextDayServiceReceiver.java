package org.arachnis.numess;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Implementation of Next Day functionality. [NOT IN USE]
 */
public class NextDayServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // For our recurring task, we'll just display a message
        Log.v("NU MESS", "NextDayServiceReceiver");
    }
}
