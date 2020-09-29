package financialStudio.young_accountant;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    private SchetAdapter sch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transite);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        toolbar_init();
        init_transit();

        new ThreadBanner(getActivity()).start();
    }

    private void init_transit(){
        ListView listView = (ListView) findViewById(R.id.list_transit);

        sch = new SchetAdapter(getApplicationContext(), getIntent().getStringArrayListExtra("transite"));
        listView.setAdapter(sch);
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

        ImageButton imgbtn_bar = (ImageButton) findViewById(R.id.imgbtn_bar);
        imgbtn_bar.setImageResource(R.drawable.ic_search);
        imgbtn_bar.setOnClickListener(new View.OnClickListener() {
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

                Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

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

                        init_transit();
                    }
                });
            }
        });
    }

    public void search(String value){

        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();


        temp = new ArrayList<String>(getIntent().getStringArrayListExtra("transite"));


        int count = temp.size();

        if (count > 0){
            for (int i = 0; i < count; i++){
                if (temp.get(i).toLowerCase().contains(value.toLowerCase())){
                    result.add(temp.get(i));
                }
            }
        }

        ListView listView = (ListView) findViewById(R.id.list_transit);
        listView.setAdapter(null);

        sch = new SchetAdapter(getApplicationContext(), result);
        listView.setAdapter(sch);
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
