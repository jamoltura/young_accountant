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
        ArrayList<String> list = new ArrayList<>();
        list.add(getResources().getString(R.string.passive_6000));
        list.add(getResources().getString(R.string.passive_6010));
        list.add(getResources().getString(R.string.passive_6020));
        list.add(getResources().getString(R.string.passive_6100));
        list.add(getResources().getString(R.string.passive_6110));
        list.add(getResources().getString(R.string.passive_6120));
        list.add(getResources().getString(R.string.passive_6200));
        list.add(getResources().getString(R.string.passive_6210));
        list.add(getResources().getString(R.string.passive_6220));
        list.add(getResources().getString(R.string.passive_6230));
        list.add(getResources().getString(R.string.passive_6240));
        list.add(getResources().getString(R.string.passive_6250));
        list.add(getResources().getString(R.string.passive_6290));
        list.add(getResources().getString(R.string.passive_6300));
        list.add(getResources().getString(R.string.passive_6310));
        list.add(getResources().getString(R.string.passive_6320));
        list.add(getResources().getString(R.string.passive_6390));
        list.add(getResources().getString(R.string.passive_6400));
        list.add(getResources().getString(R.string.passive_6410));
        list.add(getResources().getString(R.string.passive_6500));
        list.add(getResources().getString(R.string.passive_6510));
        list.add(getResources().getString(R.string.passive_6520));
        list.add(getResources().getString(R.string.passive_6600));
        list.add(getResources().getString(R.string.passive_6610));
        list.add(getResources().getString(R.string.passive_6620));
        list.add(getResources().getString(R.string.passive_6630));
        list.add(getResources().getString(R.string.passive_6700));
        list.add(getResources().getString(R.string.passive_6710));
        list.add(getResources().getString(R.string.passive_6720));
        list.add(getResources().getString(R.string.passive_6800));
        list.add(getResources().getString(R.string.passive_6810));
        list.add(getResources().getString(R.string.passive_6820));
        list.add(getResources().getString(R.string.passive_6830));
        list.add(getResources().getString(R.string.passive_6840));
        list.add(getResources().getString(R.string.passive_6900));
        list.add(getResources().getString(R.string.passive_6910));
        list.add(getResources().getString(R.string.passive_6920));
        list.add(getResources().getString(R.string.passive_6930));
        list.add(getResources().getString(R.string.passive_6930));
        list.add(getResources().getString(R.string.passive_6940));
        list.add(getResources().getString(R.string.passive_6950));
        list.add(getResources().getString(R.string.passive_6960));
        list.add(getResources().getString(R.string.passive_6970));
        list.add(getResources().getString(R.string.passive_6990));
        list.add(getResources().getString(R.string.passive_7000));
        list.add(getResources().getString(R.string.passive_7010));
        list.add(getResources().getString(R.string.passive_7020));
        list.add(getResources().getString(R.string.passive_7100));
        list.add(getResources().getString(R.string.passive_7110));
        list.add(getResources().getString(R.string.passive_7120));
        list.add(getResources().getString(R.string.passive_7200));
        list.add(getResources().getString(R.string.passive_7210));
        list.add(getResources().getString(R.string.passive_7220));
        list.add(getResources().getString(R.string.passive_7230));
        list.add(getResources().getString(R.string.passive_7240));
        list.add(getResources().getString(R.string.passive_7250));
        list.add(getResources().getString(R.string.passive_7290));
        list.add(getResources().getString(R.string.passive_7300));
        list.add(getResources().getString(R.string.passive_7310));
        list.add(getResources().getString(R.string.passive_7400));
        list.add(getResources().getString(R.string.passive_7500));
        list.add(getResources().getString(R.string.passive_7600));
        list.add(getResources().getString(R.string.passive_7700));
        list.add(getResources().getString(R.string.passive_7800));
        list.add(getResources().getString(R.string.passive_7810));
        list.add(getResources().getString(R.string.passive_7820));
        list.add(getResources().getString(R.string.passive_7830));
        list.add(getResources().getString(R.string.passive_7840));
        list.add(getResources().getString(R.string.passive_7900));
        list.add(getResources().getString(R.string.passive_7910));
        list.add(getResources().getString(R.string.passive_7920));


        ListView listView = (ListView) findViewById(R.id.list_passiv);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), list);
        listView.setAdapter(sch);
    }

    private void init_passive2(){
        setPage(2);
        ArrayList<String> list = new ArrayList<>();
        list.add(getResources().getString(R.string.passive_8000));
        list.add(getResources().getString(R.string.passive_8100));
        list.add(getResources().getString(R.string.passive_8200));
        list.add(getResources().getString(R.string.passive_8300));
        list.add(getResources().getString(R.string.passive_8310));
        list.add(getResources().getString(R.string.passive_8320));
        list.add(getResources().getString(R.string.passive_8330));
        list.add(getResources().getString(R.string.passive_8400));
        list.add(getResources().getString(R.string.passive_8410));
        list.add(getResources().getString(R.string.passive_8420));
        list.add(getResources().getString(R.string.passive_8500));
        list.add(getResources().getString(R.string.passive_8510));
        list.add(getResources().getString(R.string.passive_8520));
        list.add(getResources().getString(R.string.passive_8530));
        list.add(getResources().getString(R.string.passive_8600));
        list.add(getResources().getString(R.string.passive_8610));
        list.add(getResources().getString(R.string.passive_8620));
        list.add(getResources().getString(R.string.passive_8700));
        list.add(getResources().getString(R.string.passive_8710));
        list.add(getResources().getString(R.string.passive_8720));
        list.add(getResources().getString(R.string.passive_8800));
        list.add(getResources().getString(R.string.passive_8810));
        list.add(getResources().getString(R.string.passive_8820));
        list.add(getResources().getString(R.string.passive_8830));
        list.add(getResources().getString(R.string.passive_8840));
        list.add(getResources().getString(R.string.passive_8890));

        ListView listView = (ListView) findViewById(R.id.list_passiv);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), list);
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
