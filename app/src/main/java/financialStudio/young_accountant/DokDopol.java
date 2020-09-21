package financialStudio.young_accountant;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.*;


public class DokDopol {

    private static final String TAG = "myLogs";

    final private Context context;
    final Resources resources;
    private PDFView pdfView;


    public DokDopol(Context context, Resources resources) {
        this.resources = resources;
        this.context = context;
        this.pdfView = ((MainActivity) context).findViewById(R.id.pdfView);
    }

    public void openNSBU() throws IOException{

        Log.d(TAG, "log");

        InputStream in = getResources().openRawResource(R.raw.nsbu);
        File file = new File(context.getExternalFilesDir(null), "nsbu.pdf");

        copy(in, file);

        pdfView.fromFile(file).pages().enableSwipe(true).swipeHorizontal(false).enableDoubletap(true).defaultPage(0);

        Log.d(TAG, "log");

    }

    public void openZakon() throws IOException{

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

    public Resources getResources() {
        return resources;
    }
}
