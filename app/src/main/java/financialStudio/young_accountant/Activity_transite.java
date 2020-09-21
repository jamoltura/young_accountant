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

public class Activity_transite extends AppCompatActivity {

    private static final String TAG = "myLogs";

    private DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transite);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        TextView textView = (TextView) findViewById(R.id.action_bar_text);
        textView.setText(getResources().getString(R.string.transit));

        ImageView btnBack = (ImageView) findViewById(R.id.imageback);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayList<String> list = new ArrayList<>();
        list.add(getResources().getString(R.string.transit_9000));
        list.add(getResources().getString(R.string.transit_9010));
        list.add(getResources().getString(R.string.transit_9020));
        list.add(getResources().getString(R.string.transit_9030));
        list.add(getResources().getString(R.string.transit_9040));
        list.add(getResources().getString(R.string.transit_9050));
        list.add(getResources().getString(R.string.transit_9100));
        list.add(getResources().getString(R.string.transit_9110));
        list.add(getResources().getString(R.string.transit_9120));
        list.add(getResources().getString(R.string.transit_9130));
        list.add(getResources().getString(R.string.transit_9140));
        list.add(getResources().getString(R.string.transit_9150));
        list.add(getResources().getString(R.string.transit_9200));
        list.add(getResources().getString(R.string.transit_9210));
        list.add(getResources().getString(R.string.transit_9220));
        list.add(getResources().getString(R.string.transit_9300));
        list.add(getResources().getString(R.string.transit_9310));
        list.add(getResources().getString(R.string.transit_9320));
        list.add(getResources().getString(R.string.transit_9330));
        list.add(getResources().getString(R.string.transit_9340));
        list.add(getResources().getString(R.string.transit_9350));
        list.add(getResources().getString(R.string.transit_9360));
        list.add(getResources().getString(R.string.transit_9370));
        list.add(getResources().getString(R.string.transit_9380));
        list.add(getResources().getString(R.string.transit_9390));
        list.add(getResources().getString(R.string.transit_9400));
        list.add(getResources().getString(R.string.transit_9410));
        list.add(getResources().getString(R.string.transit_9420));
        list.add(getResources().getString(R.string.transit_9430));
        list.add(getResources().getString(R.string.transit_9500));
        list.add(getResources().getString(R.string.transit_9510));
        list.add(getResources().getString(R.string.transit_9520));
        list.add(getResources().getString(R.string.transit_9530));
        list.add(getResources().getString(R.string.transit_9540));
        list.add(getResources().getString(R.string.transit_9550));
        list.add(getResources().getString(R.string.transit_9560));
        list.add(getResources().getString(R.string.transit_9590));
        list.add(getResources().getString(R.string.transit_9600));
        list.add(getResources().getString(R.string.transit_9610));
        list.add(getResources().getString(R.string.transit_9620));
        list.add(getResources().getString(R.string.transit_9630));
        list.add(getResources().getString(R.string.transit_9690));
        list.add(getResources().getString(R.string.transit_9700));
        list.add(getResources().getString(R.string.transit_9710));
        list.add(getResources().getString(R.string.transit_9720));
        list.add(getResources().getString(R.string.transit_9800));
        list.add(getResources().getString(R.string.transit_9810));
        list.add(getResources().getString(R.string.transit_9820));
        list.add(getResources().getString(R.string.transit_9900));
        list.add(getResources().getString(R.string.transit_9910));

        ListView listView = (ListView) findViewById(R.id.list_transit);

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
