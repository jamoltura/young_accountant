package financialStudio.young_accountant;

import android.content.Context;
import android.util.Log;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.kernel.pdf.canvas.parser.listener.RegexBasedLocationExtractionStrategy;
import com.itextpdf.layout.Canvas;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class DokDopol {

    private static final String TAG = "myLogs";

    private StatusDopol status;

    private Context context;
    private PDFView pdfView;
    private InputStream inPDF;
    private File nowFile;
    private ArrayList<PdfOnlyPage> pdfOnlyPages;

    private ExecutorService pool;
    private int countPdfPage;

    private PdfNavigation pdfNavigation;

    public static DokDopol getInstanceNSBU(Context context, PDFView pdfView) {
        InputStream in = context.getResources().openRawResource(R.raw.nsbu);
        return new DokDopol(context, pdfView, in);
    }

    public static DokDopol getInstanceZakon(Context context, PDFView pdfView) {
        InputStream in = context.getResources().openRawResource(R.raw.zakon);
        return new DokDopol(context, pdfView, in);
    }

    private DokDopol(Context context, PDFView pdfView, InputStream in) {
        this.context = context;
        this.pdfView = pdfView;
        inPDF = in;
        pdfNavigation = new PdfNavigation();
        initNowFile();
        status = StatusDopol.None;
    }

    private void initNowFile(){
        nowFile = new File(context.getExternalFilesDir(null), "tempNow.pdf");
        try {
            PdfReader reader = new PdfReader(inPDF);
            PdfWriter writer = new PdfWriter(nowFile);
            PdfDocument newPdf = new PdfDocument(reader, writer);
            newPdf.close();
            writer.close();
            reader.close();
            reset();
        }catch (IOException e){
            Log.d(TAG, e.getMessage());
        }
    }

    public void Destroy() {
        stop();
        try {
            inPDF.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfNavigation.Destroy();

        if (countPdfPage > 0){
            for (PdfOnlyPage p : pdfOnlyPages) {
                p.Destroy();
            }
            pdfOnlyPages.clear();
            nowFile.delete();
        }
    }

    public void stop() {
        if (status == StatusDopol.SearchStart) {
            if (!pool.isShutdown()) {
                pool.shutdownNow();
                status = StatusDopol.SearchStop;
            }
        }
    }

    public void reset() {
        try {
            this.inPDF.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() {
        pdfView.fromStream(inPDF).scrollHandle(new DefaultScrollHandle(context)).load();
    }

    public void reopen() {
        int page = pdfNavigation.getPosition().getIndexArrayMap();
        pdfView.fromFile(nowFile).scrollHandle(new DefaultScrollHandle(context)).defaultPage(page).load();
    }

    private void initialization() throws IOException {
        status = StatusDopol.Init;
        reset();
        PdfReader reader = new PdfReader(inPDF);
        PdfDocument pdfDocument = new PdfDocument(reader);

        countPdfPage = pdfDocument.getNumberOfPages();

        pdfOnlyPages = new ArrayList<>();

        for (int i = 0; i < countPdfPage; i++) {
            pdfOnlyPages.add(new PdfOnlyPage(context, pdfDocument, i));
        }

        pdfDocument.close();

        reset();
        status = StatusDopol.None;
    }

    private PdfDocument getPdfDocument(int index) throws IOException {
        PdfReader reader = new PdfReader(pdfOnlyPages.get(index).getReader_Document());
        PdfWriter writer = new PdfWriter(pdfOnlyPages.get(index).getWriter_Document());
        return new PdfDocument(reader, writer);
    }

    public boolean search(String value) {

        try {
            initialization();

            status = StatusDopol.SearchStart;

            pool = Executors.newFixedThreadPool(10);

            for (int i = 0; i < countPdfPage; i++) {
                pool.submit(getRunnable(getPdfDocument(i), value, i));
            }

            pool.shutdown();

            pool.awaitTermination(1, TimeUnit.DAYS);

        }catch (InterruptedException e){
            Log.d(TAG, e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }

        return firstCollection();
    }

    private Runnable getRunnable(final PdfDocument pdfDocument, final String value, int index) {
        return () -> {

            RegexBasedLocationExtractionStrategy strategy = new RegexBasedLocationExtractionStrategy(value);

            PdfCanvasProcessor proc = new PdfCanvasProcessor(strategy);

            PdfPage page = pdfDocument.getFirstPage();

            proc.processPageContent(page);

            proc.reset();

            Collection<IPdfTextLocation> resultCollection = strategy.getResultantLocations();

            ArrayList<IPdfTextLocation> array = new ArrayList<>(resultCollection);

            array = getSortArray(array);

            pdfNavigation.add(index, array);

            pdfDocument.close();

        };
    }

    private ArrayList<IPdfTextLocation> getSortArray(ArrayList<IPdfTextLocation> list){

        int count = list.size();
        ArrayList<IPdfTextLocation> result = new ArrayList<>();
        ArrayList<ArrayList<IPdfTextLocation>> temp = new ArrayList<>();
        ArrayList<IPdfTextLocation> tempChild = new ArrayList<>();

        if (count > 0) {

            float y = list.get(0).getRectangle().getY();

            for (int i = 0; i < count; i++) {

                if (list.get(i).getRectangle().getY() == y) {

                    tempChild.add(list.get(i));

                } else {

                    temp.add(tempChild);
                    y = list.get(i).getRectangle().getY();
                    tempChild = new ArrayList<>();
                    tempChild.add(list.get(i));

                }

                if (i == count - 1){
                    temp.add(tempChild);
                }
            }

            Collections.reverse(temp);
            count = temp.size();


            for (int i = 0; i < count; i++){
                tempChild = temp.get(i);
                int countChild = tempChild.size();
                for (int j = 0; j < countChild; j++){
                    result.add(tempChild.get(j));
                }
            }
        }

        return result;
    }

    private void setCollection() {
        try {
            pool = Executors.newFixedThreadPool(10);

            for (int i = 0; i < countPdfPage; i++) {
                pool.submit(getRunnable_nav(getPdfDocument(i), i));
            }

            pool.shutdown();

            try{
                pool.awaitTermination(1, TimeUnit.DAYS);
            }catch (InterruptedException e){

            }

            compressPdfFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setCollectionNext(int index) {
        try {
            pool = Executors.newFixedThreadPool(10);

            pool.submit(getRunnable_nav(getPdfDocument(index), index));

            if (index > 0){
                pool.submit(getRunnable_nav(getPdfDocument(index - 1), index - 1));
            }

            pool.shutdown();

            try{
                pool.awaitTermination(1, TimeUnit.DAYS);
            }catch (InterruptedException e){

            }

            compressPdfFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setCollectionPreed(int index) {
        try {
            pool = Executors.newFixedThreadPool(10);

            pool.submit(getRunnable_nav(getPdfDocument(index), index));

            if (index < countPdfPage - 1){
                pool.submit(getRunnable_nav(getPdfDocument(index + 1), index + 1));
            }

            pool.shutdown();

            try{
                pool.awaitTermination(1, TimeUnit.DAYS);
            }catch (InterruptedException e){

            }

            compressPdfFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void compressPdfFile() {

        if (status != StatusDopol.SearchStop) {

            int count = pdfOnlyPages.size();

            try {
                PdfWriter writer = new PdfWriter(nowFile);
                PdfDocument newPdf = new PdfDocument(writer);

                for (int i = 0; i < count; i++) {

                    PdfReader read = new PdfReader(pdfOnlyPages.get(i).getWriter_Document());
                    PdfDocument pdfDoc = new PdfDocument(read);

                    pdfDoc.copyPagesTo(1, 1, newPdf, i + 1);
                }

                newPdf.close();
                writer.close();

            } catch (IOException e) {
                Log.d(TAG, e.getMessage());
            }
        }
    }

    private Runnable getRunnable_nav(final PdfDocument pdfDocument, final int index) {
        return () -> {

            PdfPage page = pdfDocument.getFirstPage();

            ArrayList<IPdfTextLocation> array = pdfNavigation.getArrayElement(index);
            IPdfTextLocation result = pdfNavigation.getElement();

            paintRectangle(pdfDocument, page, array, result);

            pdfDocument.close();
        };
    }

    private void paintRectangle(PdfDocument pdfDocument, PdfPage page, ArrayList<IPdfTextLocation> arrayList, IPdfTextLocation result){

        int count = arrayList.size();
        PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDocument);
        DeviceRgb rgb = null;

        if (count > 0) {
            for (int i = 0; i < count; i++) {

                if (result == arrayList.get(i)) {
                    rgb = new DeviceRgb(12, 148, 99);
                }else {
                    rgb = new DeviceRgb(235, 200, 75);
                }

                Rectangle rectangle = arrayList.get(i).getRectangle();

                pdfCanvas.setFillColor(rgb).setStrokeColor(rgb).rectangle(rectangle).fillStroke();

                Canvas canvas = new Canvas(pdfCanvas, rectangle);

                canvas.close();
            }
        }
        pdfCanvas.release();
    }

    private boolean firstCollection(){
        status = StatusDopol.SearchFirst;

        boolean result = false;

        if (pdfNavigation.first()) {
            setCollection();
            result = true;
        }

        status = StatusDopol.SearchEnd;
        return result;
    }

    public boolean lastCollection(){

        return false;
    }

    public boolean nextCollection(){
        status = StatusDopol.SearchNext;

        boolean result = false;

        if (pdfNavigation.next()) {
            setCollectionNext(pdfNavigation.getPosition().getIndexArrayMap());
            result = true;
        }

        status = StatusDopol.SearchEnd;
        return result;
    }

    public boolean preedCollection(){
        status = StatusDopol.SearchPreed;
        boolean result = false;

        if (pdfNavigation.preed()) {
            setCollectionPreed(pdfNavigation.getPosition().getIndexArrayMap());
            result = true;
        }

        status = StatusDopol.SearchEnd;
        return result;
    }

    public PdfNavigation getPdfNavigation() {
        return pdfNavigation;
    }

    public static String convertSecondsToHMmSs(long seconds) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss.SSS");
        cal.setTimeInMillis(seconds);
        return format.format(cal.getTime());
    }

    private void copy(File sourse, File target) throws IOException {

        InputStream in = new FileInputStream(sourse);
        OutputStream out = new FileOutputStream(target);


        byte[] buf = new byte[1024];
        int len;

        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.flush();
        out.close();
    }
}
