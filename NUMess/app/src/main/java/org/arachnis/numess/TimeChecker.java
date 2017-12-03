package org.arachnis.numess;

import java.util.Calendar;

/**
 * A utility class which returns different values for different times of the day.
 * Used for default tabs loading functionality.
 */
public class TimeChecker {

    public static int tabnum() {
        Calendar c = Calendar.getInstance();
        int hr = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        if(hr <= 9)
            return 0;
        else if(hr <= 14)
            return 1;
        else if(hr <= 18)
            return 2;
        else if(hr <= 22)
            return 3;
        else
            return 3;
    }
}
