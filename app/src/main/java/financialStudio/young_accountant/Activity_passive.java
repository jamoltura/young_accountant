package financialStudio.young_accountant;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class Activity_passive extends BaseActivite {

    private static final String TAG = "myLogs";

    private DisplayMetrics metrics;
    private int page;
    private int status_banner;
    private static final String KEY_PAGE = "PAGE";
    private static final String KEY_STATUSBANNER = "STATUSBANNER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passive);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        display_passive_init();

        TextView textView = (TextView) findViewById(R.id.action_bar_text);
        textView.setText(getResources().getString(R.string.passive));

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
            init_passive1();
        }else if (getPage() == 2){
            init_passive2();
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

    private void display_passive_init(){

        ChipGroup chipGroup2 = (ChipGroup) findViewById(R.id.chipgroup_passive);
        chipGroup2.setChipSpacingHorizontal(0);

        final Chip chip_p1 = (Chip) findViewById(R.id.chip_p1);
        final Chip chip_p2 = (Chip) findViewById(R.id.chip_p2);

        chip_p1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = metrics.widthPixels / 2;
                chip_p1.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                ViewGroup.LayoutParams params = chip_p1.getLayoutParams();
                params.width = width;
                chip_p1.setLayoutParams(params);
            }
        });

        chip_p2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = metrics.widthPixels / 2;
                chip_p2.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                ViewGroup.LayoutParams params = chip_p2.getLayoutParams();
                params.width = width;
                chip_p2.setLayoutParams(params);
            }
        });

        chip_p1.setOnClickListener(chip_p1Click);
        chip_p2.setOnClickListener(chip_p2Click);
    }

    View.OnClickListener chip_p1Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            init_passive1();
        }
    };

    View.OnClickListener chip_p2Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            init_passive2();
        }
    };

    private void init_passive1(){
        setPage(1);

        ListView listView = (ListView) findViewById(R.id.list_passiv);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), getIntent().getStringArrayListExtra("passive_1"));
        listView.setAdapter(sch);
    }

    private void init_passive2(){
        setPage(2);

        ListView listView = (ListView) findViewById(R.id.list_passiv);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), getIntent().getStringArrayListExtra("passive_2"));
        listView.setAdapter(sch);
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

    private Activity_passive getActivity(){
        return this;
    }

    @Override
    void startBanner() {
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
}
