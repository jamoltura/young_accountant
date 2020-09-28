package financialStudio.young_accountant;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class Activity_transite extends BaseActivite {

    private static final String TAG = "myLogs";

    private DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transite);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        toolbar_init();

        ListView listView = (ListView) findViewById(R.id.list_transit);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), getIntent().getStringArrayListExtra("transite"));
        listView.setAdapter(sch);

        new ThreadBanner(getActivity()).start();
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
        textView.setText(getResources().getString(R.string.transit));

        ImageButton imgbtn_lang = (ImageButton) findViewById(R.id.imgbtn_bar);
        imgbtn_lang.setImageResource(R.drawable.ic_search);
        imgbtn_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_LONG).show();
            }
        });
    }

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    private Activity_transite getActivity(){
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
