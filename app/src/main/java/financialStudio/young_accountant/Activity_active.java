package financialStudio.young_accountant;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
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

    private DisplayMetrics metrics;
    private int page;
    private int status_banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        display_active_init();

        TextView textView = (TextView) findViewById(R.id.action_bar_text);
        textView.setText(getResources().getString(R.string.active));

        ImageView btnBack = (ImageView) findViewById(R.id.imageback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (savedInstanceState != null){
            setPage(savedInstanceState.getInt(KEY_PAGE, 0));
            setStatus_banner(savedInstanceState.getInt(KEY_STATUSBANNER, 0));
        }else{
            setPage(0);
            setStatus_banner(0);
        }

        if (getPage() == 1){
            init_active1();
        }else if (getPage() == 2){
            init_active2();
        }

        if (getStatus_banner() == 0) {
            new ThreadBanner(getActivity()).start();
            setStatus_banner(1);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PAGE, getPage());
        outState.putInt(KEY_STATUSBANNER, getStatus_banner());
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

        ListView listView = (ListView) findViewById(R.id.list_activ);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), getIntent().getStringArrayListExtra("active_1"));
        listView.setAdapter(sch);
    }

    private void init_active2(){
        setPage(2);

        ListView listView = (ListView) findViewById(R.id.list_activ);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), getIntent().getStringArrayListExtra("active_2"));
        listView.setAdapter(sch);
    }

    private void display_active_init(){

        ChipGroup chipGroup = (ChipGroup) findViewById(R.id.chipgroup_active);
        chipGroup.setChipSpacingHorizontal(0);


        final Chip chip_a1 = (Chip) findViewById(R.id.chip_a1);
        final Chip chip_a2 = (Chip) findViewById(R.id.chip_a2);

        chip_a1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = metrics.widthPixels / 2;
                chip_a1.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                ViewGroup.LayoutParams params = chip_a1.getLayoutParams();
                params.width = width;
                chip_a1.setLayoutParams(params);
            }
        });

        chip_a2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = metrics.widthPixels / 2;
                chip_a2.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                ViewGroup.LayoutParams params = chip_a2.getLayoutParams();
                params.width = width;
                chip_a2.setLayoutParams(params);
            }
        });

        chip_a1.setOnClickListener(chip_a1Click);
        chip_a2.setOnClickListener(chip_a2Click);
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
