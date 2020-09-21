package financialStudio.young_accountant;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class Activity_active extends AppCompatActivity {

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
        ArrayList<String> list = new ArrayList<>();
        list.add(getResources().getString(R.string.active_0100));
        list.add(getResources().getString(R.string.active_0110));
        list.add(getResources().getString(R.string.active_0111));
        list.add(getResources().getString(R.string.active_0112));
        list.add(getResources().getString(R.string.active_0120));
        list.add(getResources().getString(R.string.active_0130));
        list.add(getResources().getString(R.string.active_0140));
        list.add(getResources().getString(R.string.active_0150));
        list.add(getResources().getString(R.string.active_0160));
        list.add(getResources().getString(R.string.active_0170));
        list.add(getResources().getString(R.string.active_0180));
        list.add(getResources().getString(R.string.active_0190));
        list.add(getResources().getString(R.string.active_0199));
        list.add(getResources().getString(R.string.active_0200));
        list.add(getResources().getString(R.string.active_0211));
        list.add(getResources().getString(R.string.active_0212));
        list.add(getResources().getString(R.string.active_0220));
        list.add(getResources().getString(R.string.active_0230));
        list.add(getResources().getString(R.string.active_0240));
        list.add(getResources().getString(R.string.active_0250));
        list.add(getResources().getString(R.string.active_0260));
        list.add(getResources().getString(R.string.active_0270));
        list.add(getResources().getString(R.string.active_0280));
        list.add(getResources().getString(R.string.active_0290));
        list.add(getResources().getString(R.string.active_0299));
        list.add(getResources().getString(R.string.active_0300));
        list.add(getResources().getString(R.string.active_0310));
        list.add(getResources().getString(R.string.active_0400));
        list.add(getResources().getString(R.string.active_0410));
        list.add(getResources().getString(R.string.active_0420));
        list.add(getResources().getString(R.string.active_0430));
        list.add(getResources().getString(R.string.active_0440));
        list.add(getResources().getString(R.string.active_0460));
        list.add(getResources().getString(R.string.active_0470));
        list.add(getResources().getString(R.string.active_0480));
        list.add(getResources().getString(R.string.active_0490));
        list.add(getResources().getString(R.string.active_0500));
        list.add(getResources().getString(R.string.active_0510));
        list.add(getResources().getString(R.string.active_0520));
        list.add(getResources().getString(R.string.active_0530));
        list.add(getResources().getString(R.string.active_0540));
        list.add(getResources().getString(R.string.active_0560));
        list.add(getResources().getString(R.string.active_0570));
        list.add(getResources().getString(R.string.active_0590));
        list.add(getResources().getString(R.string.active_0600));
        list.add(getResources().getString(R.string.active_0610));
        list.add(getResources().getString(R.string.active_0620));
        list.add(getResources().getString(R.string.active_0630));
        list.add(getResources().getString(R.string.active_0640));
        list.add(getResources().getString(R.string.active_0690));
        list.add(getResources().getString(R.string.active_0700));
        list.add(getResources().getString(R.string.active_0710));
        list.add(getResources().getString(R.string.active_0720));
        list.add(getResources().getString(R.string.active_0800));
        list.add(getResources().getString(R.string.active_0810));
        list.add(getResources().getString(R.string.active_0820));
        list.add(getResources().getString(R.string.active_0830));
        list.add(getResources().getString(R.string.active_0840));
        list.add(getResources().getString(R.string.active_0850));
        list.add(getResources().getString(R.string.active_0860));
        list.add(getResources().getString(R.string.active_0890));
        list.add(getResources().getString(R.string.active_0900));
        list.add(getResources().getString(R.string.active_0910));
        list.add(getResources().getString(R.string.active_0920));
        list.add(getResources().getString(R.string.active_0930));
        list.add(getResources().getString(R.string.active_0940));
        list.add(getResources().getString(R.string.active_0950));
        list.add(getResources().getString(R.string.active_0960));
        list.add(getResources().getString(R.string.active_0990));


        ListView listView = (ListView) findViewById(R.id.list_activ);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), list);
        listView.setAdapter(sch);
    }

    private void init_active2(){
        setPage(2);
        ArrayList<String> list = new ArrayList<>();
        list.add(getResources().getString(R.string.active_1000));
        list.add(getResources().getString(R.string.active_1010));
        list.add(getResources().getString(R.string.active_1020));
        list.add(getResources().getString(R.string.active_1030));
        list.add(getResources().getString(R.string.active_1040));
        list.add(getResources().getString(R.string.active_1050));
        list.add(getResources().getString(R.string.active_1060));
        list.add(getResources().getString(R.string.active_1070));
        list.add(getResources().getString(R.string.active_1080));
        list.add(getResources().getString(R.string.active_1090));
        list.add(getResources().getString(R.string.active_1100));
        list.add(getResources().getString(R.string.active_1110));
        list.add(getResources().getString(R.string.active_1120));
        list.add(getResources().getString(R.string.active_1200));
        list.add(getResources().getString(R.string.active_1300));
        list.add(getResources().getString(R.string.active_1400));
        list.add(getResources().getString(R.string.active_1500));
        list.add(getResources().getString(R.string.active_1510));
        list.add(getResources().getString(R.string.active_1600));
        list.add(getResources().getString(R.string.active_1610));
        list.add(getResources().getString(R.string.active_1700));
        list.add(getResources().getString(R.string.active_1800));
        list.add(getResources().getString(R.string.active_1900));
        list.add(getResources().getString(R.string.active_2000));
        list.add(getResources().getString(R.string.active_2010));
        list.add(getResources().getString(R.string.active_2100));
        list.add(getResources().getString(R.string.active_2110));
        list.add(getResources().getString(R.string.active_2200));
        list.add(getResources().getString(R.string.active_2300));
        list.add(getResources().getString(R.string.active_2310));
        list.add(getResources().getString(R.string.active_2310));
        list.add(getResources().getString(R.string.active_2400));
        list.add(getResources().getString(R.string.active_2310));
        list.add(getResources().getString(R.string.active_2400));
        list.add(getResources().getString(R.string.active_2500));
        list.add(getResources().getString(R.string.active_2510));
        list.add(getResources().getString(R.string.active_2600));
        list.add(getResources().getString(R.string.active_2610));
        list.add(getResources().getString(R.string.active_2700));
        list.add(getResources().getString(R.string.active_2710));
        list.add(getResources().getString(R.string.active_2800));
        list.add(getResources().getString(R.string.active_2810));
        list.add(getResources().getString(R.string.active_2820));
        list.add(getResources().getString(R.string.active_2830));
        list.add(getResources().getString(R.string.active_2900));
        list.add(getResources().getString(R.string.active_2910));
        list.add(getResources().getString(R.string.active_2920));
        list.add(getResources().getString(R.string.active_2930));
        list.add(getResources().getString(R.string.active_2940));
        list.add(getResources().getString(R.string.active_2950));
        list.add(getResources().getString(R.string.active_2960));
        list.add(getResources().getString(R.string.active_2970));
        list.add(getResources().getString(R.string.active_2980));
        list.add(getResources().getString(R.string.active_2990));
        list.add(getResources().getString(R.string.active_3000));
        list.add(getResources().getString(R.string.active_3100));
        list.add(getResources().getString(R.string.active_3110));
        list.add(getResources().getString(R.string.active_3120));
        list.add(getResources().getString(R.string.active_3190));
        list.add(getResources().getString(R.string.active_3200));
        list.add(getResources().getString(R.string.active_3210));
        list.add(getResources().getString(R.string.active_3220));
        list.add(getResources().getString(R.string.active_3290));
        list.add(getResources().getString(R.string.active_3300));
        list.add(getResources().getString(R.string.active_3400));
        list.add(getResources().getString(R.string.active_3500));
        list.add(getResources().getString(R.string.active_3600));
        list.add(getResources().getString(R.string.active_3700));
        list.add(getResources().getString(R.string.active_3800));
        list.add(getResources().getString(R.string.active_3900));
        list.add(getResources().getString(R.string.active_4000));
        list.add(getResources().getString(R.string.active_4010));
        list.add(getResources().getString(R.string.active_4020));
        list.add(getResources().getString(R.string.active_4100));
        list.add(getResources().getString(R.string.active_4110));
        list.add(getResources().getString(R.string.active_4120));
        list.add(getResources().getString(R.string.active_4200));
        list.add(getResources().getString(R.string.active_4210));
        list.add(getResources().getString(R.string.active_4220));
        list.add(getResources().getString(R.string.active_4230));
        list.add(getResources().getString(R.string.active_4290));
        list.add(getResources().getString(R.string.active_4300));
        list.add(getResources().getString(R.string.active_4310));
        list.add(getResources().getString(R.string.active_4320));
        list.add(getResources().getString(R.string.active_4330));
        list.add(getResources().getString(R.string.active_4400));
        list.add(getResources().getString(R.string.active_4410));
        list.add(getResources().getString(R.string.active_4500));
        list.add(getResources().getString(R.string.active_4510));
        list.add(getResources().getString(R.string.active_4600));
        list.add(getResources().getString(R.string.active_4610));
        list.add(getResources().getString(R.string.active_4700));
        list.add(getResources().getString(R.string.active_4710));
        list.add(getResources().getString(R.string.active_4720));
        list.add(getResources().getString(R.string.active_4730));
        list.add(getResources().getString(R.string.active_4790));
        list.add(getResources().getString(R.string.active_4800));
        list.add(getResources().getString(R.string.active_4810));
        list.add(getResources().getString(R.string.active_4820));
        list.add(getResources().getString(R.string.active_4830));
        list.add(getResources().getString(R.string.active_4840));
        list.add(getResources().getString(R.string.active_4850));
        list.add(getResources().getString(R.string.active_4860));
        list.add(getResources().getString(R.string.active_4890));
        list.add(getResources().getString(R.string.active_4900));
        list.add(getResources().getString(R.string.active_4910));
        list.add(getResources().getString(R.string.active_5000));
        list.add(getResources().getString(R.string.active_5010));
        list.add(getResources().getString(R.string.active_5020));
        list.add(getResources().getString(R.string.active_5100));
        list.add(getResources().getString(R.string.active_5110));
        list.add(getResources().getString(R.string.active_5200));
        list.add(getResources().getString(R.string.active_5210));
        list.add(getResources().getString(R.string.active_5220));
        list.add(getResources().getString(R.string.active_5300));
        list.add(getResources().getString(R.string.active_5400));
        list.add(getResources().getString(R.string.active_5500));
        list.add(getResources().getString(R.string.active_5510));
        list.add(getResources().getString(R.string.active_5520));
        list.add(getResources().getString(R.string.active_5530));
        list.add(getResources().getString(R.string.active_5600));
        list.add(getResources().getString(R.string.active_5610));
        list.add(getResources().getString(R.string.active_5700));
        list.add(getResources().getString(R.string.active_5710));
        list.add(getResources().getString(R.string.active_5800));
        list.add(getResources().getString(R.string.active_5810));
        list.add(getResources().getString(R.string.active_5830));
        list.add(getResources().getString(R.string.active_5890));
        list.add(getResources().getString(R.string.active_5900));
        list.add(getResources().getString(R.string.active_5910));
        list.add(getResources().getString(R.string.active_5920));

        ListView listView = (ListView) findViewById(R.id.list_activ);

        SchetAdapter sch = new SchetAdapter(getApplicationContext(), list);
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
