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

    private DisplayMetrics metrics;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        display_active_init();

        Chip chip_a1 = (Chip) findViewById(R.id.chip_a1);
        Chip chip_a2 = (Chip) findViewById(R.id.chip_a2);

        chip_a1.setOnClickListener(chip_a1Click);
        chip_a2.setOnClickListener(chip_a2Click);

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
        }else{
            setPage(0);
        }

        if (getPage() == 1){
            init_active1();
        }else if (getPage() == 2){
            init_active2();
        }

        new ThreadBanner(getActivity()).start();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PAGE, page);
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

        new ThreadBanner(getActivity()).start();
    }

    private void init_active2(){
        setPage(2);

        ListView listView = (ListView) findViewById(R.id.list_activ);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), getIntent().getStringArrayListExtra("active_2"));
        listView.setAdapter(sch);
    }

    private void display_active_init(){

        final ChipGroup chipgroup_active = (ChipGroup) findViewById(R.id.chipgroup_active);

        chipgroup_active.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                float _density = getMetrics().density;

                float chipgroup_active_Width = (chipgroup_active.getWidth() / _density);


                chipgroup_active.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int width = (int)((chipgroup_active_Width / 2));

                Chip chip_a1 = (Chip) findViewById(R.id.chip_a1);
                Chip chip_a2 = (Chip) findViewById(R.id.chip_a2);

                chip_a1.setWidth((int)(width * _density));
                chip_a2.setWidth((int)(width * _density));
            }
        });
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
