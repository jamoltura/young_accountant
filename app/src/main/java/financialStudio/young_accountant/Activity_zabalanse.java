package financialStudio.young_accountant;

import android.graphics.Point;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class Activity_zabalanse extends BaseActivite {

    private static final String TAG = "myLogs";

    private DisplayMetrics metrics;
    private SchetAdapter sch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zabalanse);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        toolbar_init();
        init_zabalans();

        new ThreadBanner(getActivity()).start();
    }

    private void init_zabalans(){
        ListView listView = (ListView) findViewById(R.id.list_zabalans);

        ArrayList<String> list = getIntent().getStringArrayListExtra("zabalanse");

        int count = list.size();

        ArrayList<MyMap> myMapArrayList = new ArrayList<>();

        for (int i = 0; i < count; i++){
            myMapArrayList.add(getMyMap(list.get(i), ""));
        }

        sch = new SchetAdapter(getApplicationContext(), myMapArrayList);
        listView.setAdapter(sch);
    }

    private void toolbar_init(){
        ImageButton imageView = (ImageButton) findViewById(R.id.img_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView textView = (TextView) findViewById(R.id.action_bar_text);
        textView.setText(getResources().getString(R.string.zabalans));

        ImageButton imgbtn_bar = (ImageButton) findViewById(R.id.img_search);
        imgbtn_bar.setOnClickListener(clickSearch);
    }

    View.OnClickListener clickSearch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final LinearLayout ll = (LinearLayout) findViewById(R.id.actionBar);

            ll.removeViewAt(0);

            LinearLayout ll_search = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_search_bar, ll, false);

            ll.addView(ll_search, 0);

            final EditText editText = (EditText) findViewById(R.id.editText_search);
            editText.post(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager inputMethodManager =
                            (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.toggleSoftInputFromWindow(
                            editText.getApplicationWindowToken(),InputMethodManager.SHOW_IMPLICIT, 0);
                    editText.requestFocus();
                }
            });

            editText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode==KeyEvent.KEYCODE_ENTER){
                        return true;
                    }
                    return false;
                }
            });

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    search(s.toString());
                }
            });

            Button btn_cancel = (Button) findViewById(R.id.btn_search_and_cancel);
            btn_cancel.setText(R.string.action_cancel);
            btn_cancel.setOnClickListener(clickCancel);
        }
    };

    View.OnClickListener clickCancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final LinearLayout ll = (LinearLayout) findViewById(R.id.actionBar);
            final EditText editText = (EditText) findViewById(R.id.editText_search);
            editText.post(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager inputMethodManager =
                            (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.toggleSoftInputFromWindow(
                            editText.getApplicationWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }
            });

            ll.removeViewAt(0);

            LinearLayout ll_search = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_action_bar, ll, false);

            ll.addView(ll_search, 0);
            toolbar_init();

            init_zabalans();
        }
    };

    public void search(String value){

        ArrayList<String> temp = new ArrayList<>();
        ArrayList<MyMap> result = new ArrayList<>();

        temp = new ArrayList<String>(getIntent().getStringArrayListExtra("zabalanse"));

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

    private MyMap getMyMap(String sourse, String value){

        MyMap myMap = new MyMap(sourse, new Point(-1,-1), false);

        if (value.length() > 0) {

            String[] arr = sourse.split(" ");

            int childCount = arr.length;

            for (int j = 0; j < childCount; j++) {
                if (arr[0].toLowerCase().contains(value.toLowerCase())) {
                    String _start = String.valueOf(value.charAt(0));
                    String _end = String.valueOf(value.charAt(value.length() - 1));

                    int start_index = value.indexOf(_start);
                    int end_index = value.indexOf(_end);

                    Point p = new Point(start_index, end_index);

                    myMap.setText(sourse);
                    myMap.setP(p);
                    myMap.setBool(true);
                }
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

    private Activity_zabalanse getActivity(){
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
