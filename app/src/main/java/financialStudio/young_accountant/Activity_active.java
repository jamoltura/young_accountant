package financialStudio.young_accountant;

import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Activity_active extends BaseActivite {

    private static final String TAG = "myLogs";
    private static final String KEY_PAGE = "PAGE";
    private static final String KEY_STATUSBANNER = "STATUSBANNER";
    private static final String KEY_WIDTH = "WIDTH";

    private ArrayList<String> activ_1;
    private ArrayList<String> activ_2;
    private DisplayMetrics metrics;
    private int page;
    private int status_banner;
    private int btn_Width;

    private SchetAdapter sch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);

        String lang = LocaleHelper.getLanguage(getApplicationContext());
        LocaleHelper.onAttach(this, lang);

        ChartOFaccounts chartOFaccounts = new ChartOFaccounts(getResources());
        activ_1 = chartOFaccounts.getActive_1();
        activ_2 = chartOFaccounts.getActive_2();

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        toolbar_init(0);
        display_active_init();

        if (savedInstanceState != null){
            setPage(savedInstanceState.getInt(KEY_PAGE, 0));
            setStatus_banner(savedInstanceState.getInt(KEY_STATUSBANNER, 0));
            setBtn_Width(savedInstanceState.getInt(KEY_WIDTH, 0));
        }else{
            setPage(0);
            setStatus_banner(0);
        }

        if (getPage() != 2){
            init_active1();
        }else {
            init_active2();
        }

        /*
        if (getStatus_banner() == 0) {
            new ThreadBanner(getActivity()).start();
            setStatus_banner(1);
        }
         */
    }

    @Override
    void toolbar_init(int value) {
        super.toolbar_init(value);
        TextView textView = (TextView) findViewById(R.id.action_bar_text);
        textView.setText(getResources().getString(R.string.active));
    }

    @Override
    void add_custom_action_bar() {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.actionBar);

        ll.removeViewAt(0);

        LinearLayout ll_search = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_action_bar, ll, false);

        ll.addView(ll_search, 0);

        toolbar_init(0);

        if (getPage() != 2){
            init_active1();
        }else {
            init_active2();
        }
    }

    @Override
    void _search(String value){

        ArrayList<String> temp = new ArrayList<>();
        ArrayList<MyMap> result = new ArrayList<>();

        if (getPage() == 1) {
            temp = new ArrayList<String>(activ_1);
        }else {
            temp = new ArrayList<String>(activ_2);
        }

        int count = temp.size();

        if (count > 0){
            for (int i = 0; i < count; i++){
                if (temp.get(i).toLowerCase().contains(value.toLowerCase())){
                    result.add(getMyMap(temp.get(i), value));
                }
            }
        }

        ListView listView = (ListView) findViewById(R.id.list_activ);
        listView.setAdapter(null);

        sch = new SchetAdapter(getApplicationContext(), result);
        listView.setAdapter(sch);
    }

    private MyMap getMyMap(String sourse, String value){

        MyMap myMap = new MyMap(sourse, new Point(-1,-1), false);

        if (value.length() > 0) {

            String _sourse = sourse.toLowerCase();
            String _value = value.toLowerCase();

            if (_sourse.contains(_value)) {

                int start_index = _sourse.indexOf(_value);
                int end_index = start_index + _value.length();

                Point p = new Point(start_index, end_index);

                myMap.setText(sourse);
                myMap.setP(p);
                myMap.setBool(true);
            }
        }
        return myMap;
    }

    private void display_active_init(){

        final Button btn_a1 = (Button) findViewById(R.id.btn_a1);
        final Button btn_a2 = (Button) findViewById(R.id.btn_a2);

        btn_a1.setBackgroundResource(R.drawable.btnsimple);
        btn_a1.setTextColor(getColorPrimaryDark());

        btn_a2.setBackgroundResource(R.drawable.btnsimple);
        btn_a2.setTextColor(getColorPrimaryDark());

        btn_a1.setOnClickListener(chip_a1Click);
        btn_a2.setOnClickListener(chip_a2Click);

        btn_a1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                btn_a1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setBtn_Width(btn_a1.getWidth());
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ViewGroup.LayoutParams params = btn_a1.getLayoutParams();
                    params.width = getBtn_Width();
                    btn_a1.setLayoutParams(params);
                }
            }
        });

        btn_a2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                btn_a2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setBtn_Width(btn_a2.getWidth());
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ViewGroup.LayoutParams params = btn_a2.getLayoutParams();
                    params.width = getBtn_Width();
                    btn_a2.setLayoutParams(params);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PAGE, getPage());
        outState.putInt(KEY_STATUSBANNER, getStatus_banner());
        outState.putInt(KEY_WIDTH, getBtn_Width());
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

        Button btn_a1 = (Button) findViewById(R.id.btn_a1);
        Button btn_a2 = (Button) findViewById(R.id.btn_a2);

        btn_a1.setBackgroundResource(R.drawable.btnsimple_select);
        btn_a1.setTextColor(getColor_white());

        btn_a2.setBackgroundResource(R.drawable.btnsimple);
        btn_a2.setTextColor(getColorPrimaryDark());

        ListView listView = (ListView) findViewById(R.id.list_activ);

      //  listView.

        int count = activ_1.size();

        ArrayList<MyMap> myMapArrayList = new ArrayList<>();

        for (int i = 0; i < count; i++){
            myMapArrayList.add(getMyMap(activ_1.get(i), ""));
        }

        sch = new SchetAdapter(getApplicationContext(), myMapArrayList);
        listView.setAdapter(sch);
    }

    private void init_active2(){
        setPage(2);

        Button btn_a1 = (Button) findViewById(R.id.btn_a1);
        Button btn_a2 = (Button) findViewById(R.id.btn_a2);

        btn_a1.setBackgroundResource(R.drawable.btnsimple);
        btn_a1.setTextColor(getColorPrimaryDark());

        btn_a2.setBackgroundResource(R.drawable.btnsimple_select);
        btn_a2.setTextColor(getColor_white());

        ListView listView = (ListView) findViewById(R.id.list_activ);

        int count = activ_2.size();

        ArrayList<MyMap> myMapArrayList = new ArrayList<>();

        for (int i = 0; i < count; i++){
            myMapArrayList.add(getMyMap(activ_2.get(i), ""));
        }

        sch = new SchetAdapter(getApplicationContext(), myMapArrayList);
        listView.setAdapter(sch);
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

    public int getBtn_Width() {
        return btn_Width;
    }

    public void setBtn_Width(int btn_Width) {
        this.btn_Width = btn_Width;
    }

    public SchetAdapter getSch() {
        return sch;
    }

    public void setSch(SchetAdapter sch) {
        this.sch = sch;
    }

    @Override
    void startBanner() {
        /*
        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

                FragmentTransaction rft = getSupportFragmentManager().beginTransaction();

                FragmentBanner frb = new FragmentBanner();
                rft.add(R.id.frame_banner, frb);
                rft.commit();
            }
        });
         */
    }


}
