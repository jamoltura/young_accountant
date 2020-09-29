package financialStudio.young_accountant;

import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class Activity_passive extends BaseActivite {

    private static final String TAG = "myLogs";

    private static final String KEY_PAGE = "PAGE";
    private static final String KEY_STATUSBANNER = "STATUSBANNER";
    private static final String KEY_WIDTH = "WIDTH";

    private DisplayMetrics metrics;
    private int page;
    private int status_banner;
    private int btn_Width;
    private SchetAdapter sch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passive);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        toolbar_init();
        display_passive_init();

        if (savedInstanceState != null){
            setPage(savedInstanceState.getInt(KEY_PAGE, 0));
            setStatus_banner(savedInstanceState.getInt(KEY_STATUSBANNER, 0));
            setBtn_Width(savedInstanceState.getInt(KEY_WIDTH, 0));
        }else{
            setPage(0);
            setStatus_banner(0);
        }

        if (getPage() != 2){
            init_passive1();
        }else {
            init_passive2();
        }

        if (getStatus_banner() == 0) {
            new ThreadBanner(getActivity()).start();
            setStatus_banner(1);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PAGE, getPage());
        outState.putInt(KEY_STATUSBANNER, getStatus_banner());
        outState.putInt(KEY_WIDTH, getBtn_Width());
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
        textView.setText(getResources().getString(R.string.passive));

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

                        if (getPage() != 2){
                            init_passive1();
                        }else {
                            init_passive2();
                        }
                    }
                });
            }
        });
    }

    public void search(String value){

        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        if (getPage() == 1) {
            temp = new ArrayList<String>(getIntent().getStringArrayListExtra("passive_1"));
        }else {
            temp = new ArrayList<String>(getIntent().getStringArrayListExtra("passive_2"));
        }

        int count = temp.size();

        if (count > 0){
            for (int i = 0; i < count; i++){
                if (temp.get(i).toLowerCase().contains(value.toLowerCase())){
                    result.add(temp.get(i));
                }
            }
        }

        ListView listView = (ListView) findViewById(R.id.list_passiv);
        listView.setAdapter(null);

        sch = new SchetAdapter(getApplicationContext(), result);
        listView.setAdapter(sch);

    }

    private void display_passive_init(){

        final Button btn_p1 = (Button) findViewById(R.id.btn_p1);
        final Button btn_p2 = (Button) findViewById(R.id.btn_p2);

        btn_p1.setBackgroundResource(R.drawable.btnsimple);
        btn_p1.setTextColor(getResources().getColor(R.color.colorfon, getTheme()));

        btn_p2.setBackgroundResource(R.drawable.btnsimple);
        btn_p2.setTextColor(getResources().getColor(R.color.colorfon, getTheme()));

        btn_p1.setOnClickListener(chip_p1Click);
        btn_p2.setOnClickListener(chip_p2Click);

        btn_p1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                btn_p1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setBtn_Width(btn_p1.getWidth());
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ViewGroup.LayoutParams params = btn_p1.getLayoutParams();
                    params.width = getBtn_Width();
                    btn_p1.setLayoutParams(params);
                }
            }
        });

        btn_p2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                btn_p2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setBtn_Width(btn_p2.getWidth());
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ViewGroup.LayoutParams params = btn_p2.getLayoutParams();
                    params.width = getBtn_Width();
                    btn_p2.setLayoutParams(params);
                }
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

        Button btn_p1 = (Button) findViewById(R.id.btn_p1);
        Button btn_p2 = (Button) findViewById(R.id.btn_p2);

        btn_p1.setBackgroundResource(R.drawable.btnsimple_select);
        btn_p1.setTextColor(getResources().getColor(R.color.color_white, getTheme()));

        btn_p2.setBackgroundResource(R.drawable.btnsimple);
        btn_p2.setTextColor(getResources().getColor(R.color.colorfon, getTheme()));

        ListView listView = (ListView) findViewById(R.id.list_passiv);

        sch = new SchetAdapter(getApplicationContext(), getIntent().getStringArrayListExtra("passive_1"));
        listView.setAdapter(sch);
    }

    private void init_passive2(){
        setPage(2);

        Button btn_p1 = (Button) findViewById(R.id.btn_p1);
        Button btn_p2 = (Button) findViewById(R.id.btn_p2);

        btn_p1.setBackgroundResource(R.drawable.btnsimple);
        btn_p1.setTextColor(getResources().getColor(R.color.colorfon, getTheme()));

        btn_p2.setBackgroundResource(R.drawable.btnsimple_select);
        btn_p2.setTextColor(getResources().getColor(R.color.color_white, getTheme()));

        ListView listView = (ListView) findViewById(R.id.list_passiv);

        sch = new SchetAdapter(getApplicationContext(), getIntent().getStringArrayListExtra("passive_2"));
        listView.setAdapter(sch);
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

    private Activity_passive getActivity(){
        return this;
    }

    public int getBtn_Width() {
        return btn_Width;
    }

    public void setBtn_Width(int btn_Width) {
        this.btn_Width = btn_Width;
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
