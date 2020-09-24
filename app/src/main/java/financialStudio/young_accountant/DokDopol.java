package financialStudio.young_accountant;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.*;


public class DokDopol {

    private static final String TAG = "myLogs";

    private File fileNSBU;
    private File fileZakon;
    final private PDFView pdfView;


    public DokDopol(PDFView pdfView) {
        this.pdfView = pdfView;
    }

    public void openNSBU(File fileNSBU) {
        this.fileNSBU = fileNSBU;
        pdfView.fromFile(fileNSBU).load();
    }

    public void openZakon(File fileZakon){
        this.fileZakon = fileZakon;
        pdfView.fromFile(fileZakon).load();
    }

    public void search(){

    }
}
