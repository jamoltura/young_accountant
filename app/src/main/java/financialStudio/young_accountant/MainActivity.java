package financialStudio.young_accountant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.moeidbannerlibrary.banner.BannerLayout;
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.chip.Chip;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    private SharedPreferences sp;
    private DisplayMetrics metrics;

    private ChartOFaccounts chartOFaccounts;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().hide();

        String lang = LocaleHelper.getLanguage(this);

        LocaleHelper.onAttach(this, lang);

        init();
        displayInit();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    getMainActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //startBanner();
                        }
                    });


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        chartOFaccounts = new ChartOFaccounts(getResources());

    }

    private void startBanner(){

        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                FragmentBanner frb = new FragmentBanner();
                FragmentTransaction rft = getSupportFragmentManager().beginTransaction();
                rft.add(R.id.frame_banner, frb);
                rft.commit();
            }
        });
    }

    private void init() {
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        setContentView(R.layout.activity_main);

        List<String> urls = new ArrayList<>();

        try{
            InputStream in = getResources().openRawResource(R.raw.logo1);

            File file = new File(getExternalFilesDir(null), "logo1.jpg");

            copy(in, file);

            String logo_1 = file.getAbsolutePath();

            in = getResources().openRawResource(R.raw.logo2);

            file = new File(getExternalFilesDir(null), "logo2.jpg");

            copy(in, file);

            String logo_2 = file.getAbsolutePath();

            in = getResources().openRawResource(R.raw.logo3);

            file = new File(getExternalFilesDir(null), "logo3.jpg");

            copy(in, file);

            String logo_3 = file.getAbsolutePath();

            urls.add(logo_1);
            urls.add(logo_2);
            urls.add(logo_3);

            BaseBannerAdapter webBannerAdapter = new BaseBannerAdapter(this, urls);

            BannerLayout banner = (BannerLayout) findViewById(R.id.Banner);

            banner.setAdapter(webBannerAdapter);

            webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {


                }
            });
        }catch (IOException e){
            Log.d(TAG, e.getMessage());
        }

        ImageButton btn1 = (ImageButton) findViewById(R.id.imgbtn1);
        btn1.setOnClickListener(btn1Click);

        ImageButton btn2 = (ImageButton) findViewById(R.id.imgbtn2);
        btn2.setOnClickListener(btn2Click);

        ImageButton btn3 = (ImageButton) findViewById(R.id.imgbtn3);
        btn3.setOnClickListener(btn3Click);

        ImageButton btn4 = (ImageButton) findViewById(R.id.imgbtn4);
        btn4.setOnClickListener(btn4Click);

        ImageButton btn5 = (ImageButton) findViewById(R.id.imgbtn5);
        btn5.setOnClickListener(btn5Click);

        ImageButton btn6 = (ImageButton) findViewById(R.id.imgbtn6);
        btn6.setOnClickListener(btn6Click);

        Chip ch_ru = (Chip) findViewById(R.id.chip_ru);
        ch_ru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocaleRu();
                ((Chip) v).setText(R.string.lang_ru);
            }
        });

        Chip ch_uz_l = (Chip) findViewById(R.id.chip_uz_latin);
        ch_uz_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocaleUz_L();
                ((Chip) v).setText(R.string.lang_uz_l);
            }
        });

        Chip ch_uz_k = (Chip) findViewById(R.id.chip_uz_kiril);
        ch_uz_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocaleUz_K();
                ((Chip) v).setText(R.string.lang_uz_k);
            }
        });

        String lang = LocaleHelper.getLanguage(this);

        if (lang.equalsIgnoreCase("ru")){
            ch_ru.setChecked(true);
        }else if (lang.equalsIgnoreCase("uz")){
            ch_uz_l.setChecked(true);
        }else if (lang.equalsIgnoreCase("default")){
            ch_uz_k.setChecked(true);
        }

    }

    private void initLanguage(){

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(R.string.active);

        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText(R.string.passive);

        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setText(R.string.transit);

        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setText(R.string.zabalans);

        TextView textView5 = (TextView) findViewById(R.id.textView5);
        textView5.setText(R.string.dopol);

        TextView textView6 = (TextView) findViewById(R.id.textView6);
        textView6.setText(R.string.dopol2);

        Chip ch_ru = (Chip) findViewById(R.id.chip_ru);

        Chip ch_uz_l = (Chip) findViewById(R.id.chip_uz_latin);

        Chip ch_uz_k = (Chip) findViewById(R.id.chip_uz_kiril);

        String lang = LocaleHelper.getLanguage(this);

        if (lang.equalsIgnoreCase("ru")){
            ch_ru.setChecked(true);
        }else if (lang.equalsIgnoreCase("uz")){
            ch_uz_l.setChecked(true);
        }else if (lang.equalsIgnoreCase("default")){
            ch_uz_k.setChecked(true);
        }
    }

    private void copy(InputStream in, File target) throws IOException {

        OutputStream out = new FileOutputStream(target);

        byte[] buf = new byte[1024];
        int len;

        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.flush();
        out.close();
    }

    private void displayInit(){

        final LinearLayout fr_1 = (LinearLayout) findViewById(R.id.ll_1);
        final LinearLayout fr_2 = (LinearLayout) findViewById(R.id.ll_2);
        final LinearLayout fr_3 = (LinearLayout) findViewById(R.id.ll_3);
        final LinearLayout fr_4 = (LinearLayout) findViewById(R.id.ll_4);
        final LinearLayout fr_5 = (LinearLayout) findViewById(R.id.ll_5);
        final LinearLayout fr_6 = (LinearLayout) findViewById(R.id.ll_6);

        fr_1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                int _Width = fr_1.getWidth();
                int _Height = fr_1.getHeight();

                fr_1.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int superfluous = _Width - _Height;

                int rigth_margin= (int) superfluous / 3;
                int left_margin  = superfluous - rigth_margin;


                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fr_1.getLayoutParams();
                params.setMargins(left_margin,params.topMargin, rigth_margin,params.bottomMargin);
                fr_1.setLayoutParams(params);
            }
        });

        fr_2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int _Width = fr_2.getWidth();
                int _Height = fr_2.getHeight();

                fr_2.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int superfluous = _Width - _Height;

                int left_margin = (int) superfluous / 3;
                int rigth_margin = superfluous - left_margin;


                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fr_2.getLayoutParams();
                params.setMargins(left_margin,params.topMargin, rigth_margin,params.bottomMargin);
                fr_2.setLayoutParams(params);
            }
        });

        fr_3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int _Width = fr_3.getWidth();
                int _Height = fr_3.getHeight();

                fr_3.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int superfluous = _Width - _Height;

                int rigth_margin= (int) superfluous / 3;
                int left_margin  = superfluous - rigth_margin;


                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fr_3.getLayoutParams();
                params.setMargins(left_margin,params.topMargin, rigth_margin,params.bottomMargin);
                fr_3.setLayoutParams(params);
            }
        });

        fr_4.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int _Width = fr_4.getWidth();
                int _Height = fr_4.getHeight();

                fr_4.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int superfluous = _Width - _Height;

                int left_margin = (int) superfluous / 3;
                int rigth_margin = superfluous - left_margin;


                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fr_4.getLayoutParams();
                params.setMargins(left_margin,params.topMargin, rigth_margin,params.bottomMargin);
                fr_4.setLayoutParams(params);
            }
        });

        fr_5.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int _Width = fr_5.getWidth();
                int _Height = fr_5.getHeight();

                fr_5.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int superfluous = _Width - _Height;

                int rigth_margin= (int) superfluous / 3;
                int left_margin  = superfluous - rigth_margin;


                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fr_5.getLayoutParams();
                params.setMargins(left_margin,params.topMargin, rigth_margin,params.bottomMargin);
                fr_5.setLayoutParams(params);
            }
        });

        fr_6.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int _Width = fr_6.getWidth();
                int _Height = fr_6.getHeight();

                fr_6.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int superfluous = _Width - _Height;

                int left_margin = (int) superfluous / 3;
                int rigth_margin = superfluous - left_margin;


                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fr_6.getLayoutParams();
                params.setMargins(left_margin,params.topMargin, rigth_margin,params.bottomMargin);
                fr_6.setLayoutParams(params);
            }
        });
    }

    View.OnClickListener btn1Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), Activity_active.class);

            intent.putExtra("active_1", getChartOFaccounts().getActive_1());
            intent.putExtra("active_2", getChartOFaccounts().getActive_2());

            startActivity(intent);
        }
    };

    View.OnClickListener btn2Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), Activity_passive.class);

            intent.putExtra("passive_1", getChartOFaccounts().getPasssive_1());
            intent.putExtra("passive_2", getChartOFaccounts().getPasssive_2());

            startActivity(intent);
        }
    };

    View.OnClickListener btn3Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), Activity_transite.class);

            intent.putExtra("transite", getChartOFaccounts().getTransite());

            startActivity(intent);
        }
    };

    View.OnClickListener btn4Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), Activity_zabalanse.class);

            intent.putExtra("zabalanse", getChartOFaccounts().getZabalans());

            startActivity(intent);
        }
    };

    View.OnClickListener btn5Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), Activity_dopol.class);
            intent.putExtra("dopol", 1);

            startActivity(intent);
        }
    };

    View.OnClickListener btn6Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), Activity_dopol.class);
            intent.putExtra("dopol", 2);

            startActivity(intent);
        }
    };

    public void setLocaleRu (){
        LocaleHelper.onAttach(this, "ru");
        initLanguage();
    }

    public void setLocaleUz_L (){
        LocaleHelper.onAttach(this, "uz");
        initLanguage();
    }

    public void setLocaleUz_K (){
        LocaleHelper.onAttach(this, "default");
        initLanguage();
    }

    private MainActivity getMainActivity(){
        return this;
    }

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    public ChartOFaccounts getChartOFaccounts() {
        return chartOFaccounts;
    }
}
