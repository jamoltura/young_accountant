package financialStudio.young_accountant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class FileManager {

    private static final String TAG = "myLogs";

    private final long TIME_OFF = 86400000;

    private Context context;

    // Поный пут к приложении ////////////
    private String pathApp;

    // Поный пут к папкам ////////////////
    private String SettingPath;
    // Поный пут к файл ////////////////
    private String SettingFile;

    public FileManager(Context context) {
        this.context = context;
        init();
    }

    public boolean isTime(){

        boolean result = false;

        try {
            result = timeIsKorrect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Boolean timeIsKorrect() throws IOException {

        String tempPath = getSettingFile();
        File file = new File(tempPath);

        InputStream in = new FileInputStream(file);

        byte[] buf = new byte[(int) file.length()];

        in.read(buf, 0, (int) file.length());

        in.close();

        String time_setup = new String(buf);

        return getDifferenceTime(time_setup);
    }

    private void init(){
        pathApp = context.getFilesDir().getAbsolutePath();

        String TempPath = getPathApp() + "/Setting";

        File theDir = new File(TempPath);

        if (!theDir.exists()) {
            try {
                theDir.mkdir();
            } catch (SecurityException se) {
                //handle it
            }
        }

        if (theDir.exists()) {
            this.SettingPath = TempPath;
        }

        File theFile = new File(getSettingPath(), "SettingFile");

        if (!theFile.exists()) {
            try {
                boolean newFile = theFile.createNewFile();

                if (newFile) {
                    initSettingFile(theFile);
                }
            } catch (SecurityException | IOException se) {
                //handle it
            }
        }

        if (theFile.exists()) {
            this.SettingFile = theFile.getAbsolutePath();
        }
    }

    private void initSettingFile(File file) throws IOException {

        OutputStream out = new FileOutputStream(file);

        byte[] buf = new byte[8];

        String str = getCurrentTime();

        buf = str.getBytes();

        out.write(buf, 0, buf.length);

        out.flush();
        out.close();
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

            long timeDiff = cal_s.getTimeInMillis() - cal_c.getTimeInMillis();

            if (timeDiff > TIME_OFF){
                return true;
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }


    public String getPathApp() {
        return pathApp;
    }

    public void setPathApp(String pathApp) {
        this.pathApp = pathApp;
    }

    public String getSettingPath() {
        return SettingPath;
    }

    public void setSettingPath(String settingPath) {
        SettingPath = settingPath;
    }

    public String getSettingFile() {
        return SettingFile;
    }

    public void setSettingFile(String settingFile) {
        SettingFile = settingFile;
    }
}
