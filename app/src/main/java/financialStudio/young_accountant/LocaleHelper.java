package financialStudio.young_accountant;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

public class LocaleHelper {
    private static final String TAG = "myLogs";
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    public static void onAttach(Context context) {
     //   String lang = getPersistedData(context, Locale.getDefault().getLanguage());
        setLocale(context, Locale.getDefault().getLanguage());
    }

    public static void onAttach(Context context, String defaultLanguage) {
       // String lang = getPersistedData(context, defaultLanguage);
        setLocale(context, defaultLanguage);
    }

    public static String getLanguage(Context context) {
        return getPersistedData(context, Locale.getDefault().getLanguage());
    }

    public static void setLocale(Context context, String language) {
        persist(context, language);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
           updateResources(context, language);
        }

        updateResourcesLegacy(context, language);
    }

    private static String getPersistedData(Context context, String defaultLanguage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage);
    }

    private static void persist(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static void updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();

        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(locale);
        conf.setLayoutDirection(locale);

        context.createConfigurationContext(conf);
    }

    @SuppressWarnings("deprecation")
    private static void updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = locale;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLayoutDirection(locale);
        }

        res.updateConfiguration(conf, dm);
    }
}