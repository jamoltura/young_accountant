package financialStudio.young_accountant;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Activity_zabalanse extends AppCompatActivity {

    private static final String TAG = "myLogs";

    private DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zabalanse);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        TextView textView = (TextView) findViewById(R.id.action_bar_text);
        textView.setText(getResources().getString(R.string.zabalans));

        ImageView btnBack = (ImageView) findViewById(R.id.imageback);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayList<String> list = new ArrayList<>();
        list.add(getResources().getString(R.string.zabalans_001));
        list.add(getResources().getString(R.string.zabalans_002));
        list.add(getResources().getString(R.string.zabalans_003));
        list.add(getResources().getString(R.string.zabalans_004));
        list.add(getResources().getString(R.string.zabalans_005));
        list.add(getResources().getString(R.string.zabalans_006));
        list.add(getResources().getString(R.string.zabalans_007));
        list.add(getResources().getString(R.string.zabalans_008));
        list.add(getResources().getString(R.string.zabalans_009));
        list.add(getResources().getString(R.string.zabalans_010));
        list.add(getResources().getString(R.string.zabalans_011));
        list.add(getResources().getString(R.string.zabalans_012));
        list.add(getResources().getString(R.string.zabalans_013));
        list.add(getResources().getString(R.string.zabalans_014));
        list.add(getResources().getString(R.string.zabalans_015));
        list.add(getResources().getString(R.string.zabalans_016));

        ListView listView = (ListView) findViewById(R.id.list_zabalans);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), list);
        listView.setAdapter(sch);
    }

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }
}
