package financialStudio.young_accountant;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.*;

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

    private void toolbar_init(int value){
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

        ImageButton imgbtn_lang = (ImageButton) findViewById(R.id.imgbtn_bar);
        imgbtn_lang.setImageResource(R.drawable.ic_search);
        imgbtn_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_LONG).show();
            }
        });
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
