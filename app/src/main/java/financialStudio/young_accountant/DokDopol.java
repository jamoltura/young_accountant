package financialStudio.young_accountant;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;


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
