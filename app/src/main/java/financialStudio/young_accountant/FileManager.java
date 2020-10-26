package financialStudio.young_accountant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class FileManager {

    private static final String TAG = "myLogs";

 //   private final long TIME_OFF = 86400000;
    private final long TIME_OFF = 3600000;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_START_DATE_TIME = "StartDateTime";

    private Context context;

    // TODO construktor FileManager
    public FileManager(Context context) {
        this.context = context;
        init();
    }

    // проверяем время работа тестовой режим
    public boolean isTime(){

        boolean result = false;

        try {
            result = timeIsKorrect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    // читаем значения из файла "setting"
    public Boolean timeIsKorrect() throws IOException {

        SharedPreferences mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        String time_setup = mSettings.getString(APP_START_DATE_TIME, "");

        return getDifferenceTime(time_setup);
    }

    // инитциализация
    private void init(){

        if (!getKeyInit()) {
            SharedPreferences mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor ed = mSettings.edit();

            ed.putString(APP_START_DATE_TIME, getCurrentTime());

            ed.apply();
        }
    }

    private boolean getKeyInit(){
        return context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).contains(APP_START_DATE_TIME);
    }

    public String getCurrentTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyMMddhh");
        return format.format(Calendar.getInstance().getTime());
    }

    public boolean getDifferenceTime(String value) {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyMMddhh");

        try {

            Calendar cal_s = Calendar.getInstance();
            cal_s.setTime(Objects.requireNonNull(format.parse(value)));

            Calendar cal_c = Calendar.getInstance();
            cal_c.setTime(Objects.requireNonNull(format.parse(getCurrentTime())));

            long timeDiff = cal_c.getTimeInMillis() - cal_s.getTimeInMillis();

            if (timeDiff > TIME_OFF){
                return true;
            }

        } catch (ParseException e) {
            Log.d(TAG, e.getMessage());
        }

        return false;
    }
}
