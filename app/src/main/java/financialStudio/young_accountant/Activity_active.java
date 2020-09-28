package financialStudio.young_accountant;

import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Activity_active extends BaseActivite {

    private static final String TAG = "myLogs";
    private static final String KEY_PAGE = "PAGE";
    private static final String KEY_STATUSBANNER = "STATUSBANNER";
    private static final String KEY_WIDTH = "WIDTH";

    private DisplayMetrics metrics;
    private int page;
    private int status_banner;
    private int btn_Width;

    private Animation downAnimation, upAnimation;
    private RelativeLayout rl_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        toolbar_init();
        display_active_init();

        if (savedInstanceState != null){
            setPage(savedInstanceState.getInt(KEY_PAGE, 0));
            setStatus_banner(savedInstanceState.getInt(KEY_STATUSBANNER, 0));
            setBtn_Width(savedInstanceState.getInt(KEY_WIDTH, 0));
        }else{
            setPage(0);
            setStatus_banner(0);
        }

        if (getPage() != 2){
            init_active1();
        }else {
            init_active2();
        }

        if (getStatus_banner() == 0) {
            new ThreadBanner(getActivity()).start();
            setStatus_banner(1);
        }
    }

    private void toolbar_init(){
        ImageButton imageView = (ImageButton) findViewById(R.id.imageback);
        imageView.setImageResource(R.drawable.ic_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView textView = (TextView) findViewById(R.id.action_bar_text);
        textView.setVisibility(View.VISIBLE);
        textView.setText(getResources().getString(R.string.active));

        ImageButton imgbtn_lang = (ImageButton) findViewById(R.id.imgbtn_bar);
        imgbtn_lang.setImageResource(R.drawable.ic_search);
        imgbtn_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void display_active_init(){

        final Button btn_a1 = (Button) findViewById(R.id.btn_a1);
        final Button btn_a2 = (Button) findViewById(R.id.btn_a2);

        btn_a1.setBackgroundResource(R.drawable.btnsimple);
        btn_a1.setTextColor(getResources().getColor(R.color.colorfon, getTheme()));

        btn_a2.setBackgroundResource(R.drawable.btnsimple);
        btn_a2.setTextColor(getResources().getColor(R.color.colorfon, getTheme()));

        btn_a1.setOnClickListener(chip_a1Click);
        btn_a2.setOnClickListener(chip_a2Click);

        btn_a1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                btn_a1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setBtn_Width(btn_a1.getWidth());
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ViewGroup.LayoutParams params = btn_a1.getLayoutParams();
                    params.width = getBtn_Width();
                    btn_a1.setLayoutParams(params);
                }
            }
        });

        btn_a2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                btn_a2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setBtn_Width(btn_a2.getWidth());
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ViewGroup.LayoutParams params = btn_a2.getLayoutParams();
                    params.width = getBtn_Width();
                    btn_a2.setLayoutParams(params);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PAGE, getPage());
        outState.putInt(KEY_STATUSBANNER, getStatus_banner());
        outState.putInt(KEY_WIDTH, getBtn_Width());

    }

    View.OnClickListener chip_a1Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            init_active1();
        }
    };

    View.OnClickListener chip_a2Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            init_active2();
        }
    };

    private void init_active1(){
        setPage(1);

        Button btn_a1 = (Button) findViewById(R.id.btn_a1);
        Button btn_a2 = (Button) findViewById(R.id.btn_a2);

        btn_a1.setBackgroundResource(R.drawable.btnsimple_select);
        btn_a1.setTextColor(getResources().getColor(R.color.color_white, getTheme()));

        btn_a2.setBackgroundResource(R.drawable.btnsimple);
        btn_a2.setTextColor(getResources().getColor(R.color.colorfon, getTheme()));

        ListView listView = (ListView) findViewById(R.id.list_activ);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), getIntent().getStringArrayListExtra("active_1"));
        listView.setAdapter(sch);
    }

    private void init_active2(){
        setPage(2);

        Button btn_a1 = (Button) findViewById(R.id.btn_a1);
        Button btn_a2 = (Button) findViewById(R.id.btn_a2);

        btn_a1.setBackgroundResource(R.drawable.btnsimple);
        btn_a1.setTextColor(getResources().getColor(R.color.colorfon, getTheme()));

        btn_a2.setBackgroundResource(R.drawable.btnsimple_select);
        btn_a2.setTextColor(getResources().getColor(R.color.color_white, getTheme()));

        ListView listView = (ListView) findViewById(R.id.list_activ);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), getIntent().getStringArrayListExtra("active_2"));
        listView.setAdapter(sch);
    }

    private Activity_active getActivity(){
        return this;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStatus_banner() {
        return status_banner;
    }

    public void setStatus_banner(int status_banner) {
        this.status_banner = status_banner;
    }

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    public int getBtn_Width() {
        return btn_Width;
    }

    public void setBtn_Width(int btn_Width) {
        this.btn_Width = btn_Width;
    }

    @Override
    void startBanner() {
        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

                FragmentTransaction rft = getSupportFragmentManager().beginTransaction();

                FragmentBanner frb = new FragmentBanner();
                rft.add(R.id.frame_banner, frb);
                rft.commit();
            }
        });
    }


}
