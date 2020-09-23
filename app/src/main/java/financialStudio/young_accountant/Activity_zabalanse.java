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

public class Activity_zabalanse extends BaseActivite {

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

        ListView listView = (ListView) findViewById(R.id.list_zabalans);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), getIntent().getStringArrayListExtra("zabalanse"));
        listView.setAdapter(sch);
    }

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    @Override
    void startBanner() {

    }
}
