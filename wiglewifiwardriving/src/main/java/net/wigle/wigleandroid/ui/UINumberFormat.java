package net.wigle.wigleandroid.ui;

import android.content.Context;
import android.content.SharedPreferences;

import net.wigle.wigleandroid.R;
import net.wigle.wigleandroid.util.PreferenceKeys;

import java.text.NumberFormat;

public class UINumberFormat {
    /**
     * format the topbar counters
     */
    public static String counterFormat(long input) {
        if (input > 9999999999L) {
            //any android device on the market today would explode
            return (input / 1000000000L) + "G";
        } else if (input >  9999999L) {
            return (input / 1000000L) + "M";
        } else if (input > 9999L) {
            //stay specific until we pass 5 digits
            return (input / 1000L) + "K";
        } else {
            return input+"";
        }
    }

    public static String metersToString(final SharedPreferences prefs, final NumberFormat numberFormat, final Context context, final float meters,
                                        final boolean useShort ) {
        final boolean metric = prefs.getBoolean( PreferenceKeys.PREF_METRIC, false );
        String retval;
        if ( meters > 3000f ) {
            if ( metric ) {
                retval = numberFormat.format( meters / 1000f ) + " " + context.getString(R.string.km_short);
            } else {
                retval = numberFormat.format( meters / 1609.344f ) + " " +
                        (useShort ? context.getString(R.string.mi_short) : context.getString(R.string.miles));
            }
        } else if (metric) {
            retval = numberFormat.format( meters ) + " " + (useShort ? context.getString(R.string.m_short) : context.getString(R.string.meters));
        } else {
            retval = numberFormat.format( meters * 3.2808399f  ) + " " +
                (useShort ? context.getString(R.string.ft_short) : context.getString(R.string.feet));
        }
        return retval;
    }

    public static String metersToShortString(final SharedPreferences prefs, final NumberFormat numberFormat, final Context context, final float meters) {
        final boolean metric = prefs.getBoolean( PreferenceKeys.PREF_METRIC, false );
        String result;
        if (metric) {
            if (meters >= 1000000) {
                result = numberFormat.format( meters / 1000000 ) + context.getString(R.string.Mm_short);
            } else if (meters >= 1000) {
                result = numberFormat.format( meters / 1000f ) + context.getString(R.string.km_short);
            } else {
                result = numberFormat.format(meters) + context.getString(R.string.m_short);
            }
        } else {
            if (meters >= 1609340) {
                result =  numberFormat.format( meters / 1609.344f / 1000f ) + "k " +  context.getString(R.string.mi_short);
            } else if (meters >= 1609.34) {
                result =  numberFormat.format( meters / 1609.344f ) +  context.getString(R.string.mi_short);
            } else if (meters >= 304.8) {
                result = numberFormat.format( meters * 3.2808399f / 1000f ) + "k " + context.getString(R.string.ft_short);
            } else {
                result = numberFormat.format( meters * 3.2808399f  ) +
                        context.getString(R.string.ft_short);
            }
        }
        return result;
    }
}
