package financialStudio.young_accountant;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;

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

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        int dopol = getIntent().getIntExtra("dopol", 0);

        if (dopol == 1){
            dopol1();
        }else if (dopol == 2){
            dopol2();
        }
    }

    private void dopol1(){
        TextView textView = (TextView) findViewById(R.id.action_bar_text);
        textView.setText(getResources().getString(R.string.dopol));

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

        ImageView btnBack = (ImageView) findViewById(R.id.imageback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    private void dopol2(){
        TextView textView = (TextView) findViewById(R.id.action_bar_text);
        textView.setText(getResources().getString(R.string.dopol));

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

        ImageView btnBack = (ImageView) findViewById(R.id.imageback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // init();
            }
        });
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

    @Override
    void startBanner() {

    }
}
