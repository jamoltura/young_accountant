package financialStudio.young_accountant;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class Activity_passive extends AppCompatActivity {

    private static final String TAG = "myLogs";

    private DisplayMetrics metrics;
    private int page;
    private static final String KEY_PAGE = "PAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passive);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        display_passive_init();

        Chip chip_p1 = (Chip) findViewById(R.id.chip_p1);
        Chip chip_p2 = (Chip) findViewById(R.id.chip_p2);

        chip_p1.setOnClickListener(chip_p1Click);
        chip_p2.setOnClickListener(chip_p2Click);

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
        }else{
            setPage(0);
        }

        if (getPage() == 1){
            init_passive1();
        }else if (getPage() == 2){
            init_passive2();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PAGE, getPage());
    }

    private void display_passive_init(){

        final ChipGroup chipgroup_passive = (ChipGroup) findViewById(R.id.chipgroup_passive);

        chipgroup_passive.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                float _density = getMetrics().density;

                float chipgroup_passive_Width = (chipgroup_passive.getWidth() / _density);

                chipgroup_passive.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int width = (int)((chipgroup_passive_Width / 2));

                Chip chip_p1 = (Chip) findViewById(R.id.chip_p1);
                Chip chip_p2 = (Chip) findViewById(R.id.chip_p2);

                chip_p1.setWidth((int)(width * _density));
                chip_p2.setWidth((int)(width * _density));
            }
        });

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

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }
}
