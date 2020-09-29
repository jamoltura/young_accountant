package financialStudio.young_accountant;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.*;
import java.util.ArrayList;

public class Activity_dopol extends BaseActivite{

    private static final String TAG = "myLogs";

    private DisplayMetrics metrics;
    private PdfRenderer pdf;

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

        if (value == 1) {
            textView.setText(getResources().getString(R.string.zakon));
        }else {
            textView.setText(getResources().getString(R.string.nsbu));
        }

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

                        if (value == 1){
                            toolbar_init(value);
                            dopol1();
                        }else if (value == 2){
                            toolbar_init(value);
                            dopol2();
                        }
                    }
                });
            }
        });
    }

    public void search(String value){


    }

    private void dopol1(){
        try {
            InputStream inZakon = getResources().openRawResource(R.raw.zakon);

            File fileZakon = new File(getExternalFilesDir(null), "zalon.pdf");
            copy(inZakon, fileZakon);

            PDFView pdfView = (PDFView) findViewById(R.id.pdfView);

            final DokDopol dokDopol = new DokDopol(pdfView);
            dokDopol.openZakon(fileZakon);

        }catch (IOException e){
            Log.d(TAG, e.getMessage());
        }
    }

    private void dopol2(){
        try {
            InputStream inNSBU = getResources().openRawResource(R.raw.nsbu);

            File fileNsbu = new File(getExternalFilesDir(null), "nsbu.pdf");
            copy(inNSBU, fileNsbu);

            PDFView pdfView = (PDFView) findViewById(R.id.pdfView);

            DokDopol dokDopol = new DokDopol(pdfView);
            dokDopol.openNSBU(fileNsbu);

        }catch (IOException e){
            Log.d(TAG, e.getMessage());
        }
    }

    private void copy(InputStream in, File target) throws IOException {

        OutputStream out = new FileOutputStream(target);

        byte[] buf = new byte[1024];
        int len;

        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.flush();
        out.close();
    }

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    private Activity_dopol getActivity(){
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
