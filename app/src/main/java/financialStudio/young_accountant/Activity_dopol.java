package financialStudio.young_accountant;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.fragment.app.FragmentTransaction;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.*;

public class Activity_dopol extends BaseActivite{

    private static final String TAG = "myLogs";

    private DisplayMetrics metrics;
    private DokDopol dokDopol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dopol);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int dopol = getIntent().getIntExtra("dopol", 0);

        if (dopol == 1){
            toolbar_init(1);
            dopol1();
        }else if (dopol == 2){
            toolbar_init(2);
            dopol2();
        }

        new ThreadBanner(getActivity()).start();
    }

    private void toolbar_init(final int value){

        ImageButton imageView = (ImageButton) findViewById(R.id.img_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView textView = (TextView) findViewById(R.id.action_bar_text);

        if (value == 1) {
            textView.setText(getResources().getString(R.string.zakon));
        }else {
            textView.setText(getResources().getString(R.string.nsbu));
        }

        ImageButton imgbtn_bar = (ImageButton) findViewById(R.id.img_search);
        imgbtn_bar.setOnClickListener(clickSearchImg);
    }

    View.OnClickListener clickSearchImg = new View.OnClickListener() {
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

                    // TODO : отмена ранный запушенный поиск

                    if (dokDopol.getSearchState() == SearchState.SearchStart) {
                        dokDopol.stop();
                    }

                    Button btn_search_and_cancel = (Button) findViewById(R.id.btn_search_and_cancel);
                    btn_search_and_cancel.setText(R.string.action_search);
                    btn_search_and_cancel.setOnClickListener(clickSearch);
                }
            });

            Button btn_search_and_cancel = (Button) findViewById(R.id.btn_search_and_cancel);
            btn_search_and_cancel.setOnClickListener(clickSearch);
        }
    };

    View.OnClickListener clickSearch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.setEnabled(false);

            // TODO : запускат новый поиск
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

            try {
                dokDopol.search(editText.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            ((Button) v).setText(R.string.action_cancel);
            v.setOnClickListener(clickCancel);
            v.setEnabled(true);
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

            int value = getIntent().getIntExtra("dopol", 0);

            if (value == 1){
                toolbar_init(value);
            }else if (value == 2){
                toolbar_init(value);
            }
        }
    };

    private void dopol1(){
        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        dokDopol = DokDopol.getInstanceNSBU(getApplicationContext(), pdfView);
        dokDopol.open();
    }

    private void dopol2(){
        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        dokDopol = DokDopol.getInstanceZakon(getApplicationContext(), pdfView);
        dokDopol.open();
    }

    @Override
    protected void onStop() {
        try {
            dokDopol.Destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    public Activity_dopol getActivity(){
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
