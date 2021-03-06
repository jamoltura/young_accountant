package financialStudio.young_accountant;

import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_transite extends BaseActivite {

    private static final String TAG = "myLogs";

    private ArrayList<String> transit;
    private DisplayMetrics metrics;
    private SchetAdapter sch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transite);

        String lang = LocaleHelper.getLanguage(getApplicationContext());
        LocaleHelper.onAttach(this, lang);

        ChartOFaccounts chartOFaccounts = new ChartOFaccounts(getResources());
        transit = chartOFaccounts.getTransite();

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        toolbar_init(0);
        init_transit();

      //  new ThreadBanner(getActivity()).start();
    }

    @Override
    void toolbar_init(final int value){
        super.toolbar_init(value);
        TextView textView = (TextView) findViewById(R.id.action_bar_text);
        textView.setText(getResources().getString(R.string.transit));
    }

    @Override
    void add_custom_action_bar() {
        final LinearLayout ll = (LinearLayout) findViewById(R.id.actionBar);

        ll.removeViewAt(0);

        LinearLayout ll_search = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_action_bar, ll, false);

        ll.addView(ll_search, 0);

        toolbar_init(0);

        init_transit();
    }

    @Override
    void _search(String value){

        ArrayList<String> temp = new ArrayList<>();
        ArrayList<MyMap> result = new ArrayList<>();

        temp = new ArrayList<String>(transit);

        int count = temp.size();

        if (count > 0){
            for (int i = 0; i < count; i++){
                if (temp.get(i).toLowerCase().contains(value.toLowerCase())){
                    result.add(getMyMap(temp.get(i), value));
                }
            }
        }

        ListView listView = (ListView) findViewById(R.id.list_transit);
        listView.setAdapter(null);

        sch = new SchetAdapter(getApplicationContext(), result);
        listView.setAdapter(sch);
    }

    private void init_transit(){
        ListView listView = (ListView) findViewById(R.id.list_transit);

        int count = transit.size();

        ArrayList<MyMap> myMapArrayList = new ArrayList<>();

        for (int i = 0; i < count; i++){
            myMapArrayList.add(getMyMap(transit.get(i), ""));
        }

        sch = new SchetAdapter(getApplicationContext(), myMapArrayList);
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
        /*
        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                FragmentBanner frb = new FragmentBanner();
                FragmentTransaction rft = getSupportFragmentManager().beginTransaction();
                rft.add(R.id.frame_banner, frb);
                rft.commit();
            }
        });
         */
    }
}
