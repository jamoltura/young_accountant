package financialStudio.young_accountant;

import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;
import com.github.barteksc.pdfviewer.PDFView;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;


public class DokDopol {

    private static final String TAG = "myLogs";

    private Context context;
    private PDFView pdfView;
    private InputStream inPDF;
    private ArrayMap<Integer, Collection<IPdfTextLocation>> collection;
    private SearchState searchState;
    private ExecutorService pool;
    private int countPdfPage;
    private ArrayList<PdfOnlyPage> list;

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

        try {
            initialization();
            inPDF.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSearchState(SearchState.None);
    }

    public void Destroy() throws IOException {
        stop();
        list.clear();
        inPDF.close();
    }

    public void stop() {

        if (getSearchState() == SearchState.SearchStart) {
            pool.shutdownNow();
        }
        setSearchState(SearchState.SearchCanceled);
        collection.clear();
    }

    public void reset() throws IOException {
        this.inPDF.reset();
    }

    public void open() {
        pdfView.fromStream(inPDF).load();
    }

    public void search(String value) throws IOException {

        setSearchState(SearchState.SearchStart);

        pool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < countPdfPage; i++) {
            PdfOnlyPage pdfOnlyPage = list.get(i);
            pool.submit(getRunnable(pdfOnlyPage, value, i));
        }

        pool.shutdown();

        try{
            pool.awaitTermination(1, TimeUnit.DAYS);
        }catch (InterruptedException e){

        }

        if (getSearchState() != SearchState.SearchCanceled) {
            File nowPdfFile = PdfOnlyPage.getEndFile(context, list);
            pdfView.fromFile(nowPdfFile).load();
            inPDF.reset();
        }

        /*
        for (int i = 0; i < countPdfPage; i++) {

            Collection<IPdfTextLocation> resultCollection = collection.valueAt(i);

            for (IPdfTextLocation result : resultCollection) {

                Log.d(TAG, "страница : " + String.valueOf(i));
                Log.d(TAG, "text : " + result.getText());

            }
        }
         */

        setSearchState(SearchState.SearchEnd);
    }

    private void initialization() throws IOException {

        PdfDocument pdfDocument = new PdfDocument(new PdfReader(inPDF));

        countPdfPage = pdfDocument.getNumberOfPages();

        collection = new ArrayMap<>();

        list = new ArrayList<>();

        for (int i = 0; i < countPdfPage; i++) {
            list.add(new PdfOnlyPage(context, pdfDocument, i));
        }

        pdfDocument.close();

    }

    private Runnable getRunnable(final PdfOnlyPage pdfOnlyPage, final String value, final int index) {
        return new Runnable() {
            @Override
            public void run() {

                File filePdf = pdfOnlyPage.getPdfDocument();
                File file = pdfOnlyPage.getFile();

                PdfDocument pdfDocument = null;
                try {
                    pdfDocument = new PdfDocument(new PdfReader(filePdf), new PdfWriter(file));

                    RegexBasedLocationExtractionStrategy strategy = new RegexBasedLocationExtractionStrategy(value);

                    PdfCanvasProcessor proc = new PdfCanvasProcessor(strategy);

                    PdfPage page = pdfDocument.getFirstPage();

                    proc.processPageContent(page);

                    Collection<IPdfTextLocation> resultCollection = strategy.getResultantLocations();

                    collection.put(index, resultCollection);

                    for (IPdfTextLocation result : resultCollection) {

                        PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDocument);

                        Rectangle rectangle = result.getRectangle();

                        DeviceRgb rgb = new DeviceRgb(235, 200, 75);

                        pdfCanvas.setFillColor(rgb).setStrokeColor(rgb).rectangle(rectangle).fillStroke();

                        Canvas canvas = new Canvas(pdfCanvas, pdfDocument, rectangle);

                        canvas.close();
                    }

                    pdfDocument.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private static class PdfOnlyPage{
        private File pdfDocument;
        private File file;
        private Context context;

        private PdfOnlyPage(Context context, PdfDocument pdfDocument, int index) throws FileNotFoundException {
            this.context = context;
            this.pdfDocument = getNowPdfFile(pdfDocument, index);
            this.file = getNowFile(index);
        }

        private File getNowPdfFile(PdfDocument pdfDocument, int index) throws FileNotFoundException {
            File file = new File(context.getExternalFilesDir(null), "tempNow" + index + ".pdf");
            PdfDocument newPdf = new PdfDocument(new PdfWriter(file));
            pdfDocument.copyPagesTo(index + 1,index + 1, newPdf);
            newPdf.close();
            return file;
        }

        private File getNowFile(int index){
            return new File(context.getExternalFilesDir(null), "temp" + index + ".pdf");
        }

        public File getPdfDocument() {
            return pdfDocument;
        }

        public File getFile() {
            return file;
        }

        public static File getEndFile(Context context, ArrayList<PdfOnlyPage> list) throws IOException {
            int count = list.size();
            File file = new File(context.getExternalFilesDir(null), "tempNow.pdf");
            PdfDocument newPdf = new PdfDocument(new PdfWriter(file));

            for (int i = 0; i < count; i++){
                PdfDocument pdfDocument = new PdfDocument(new PdfReader(list.get(i).getFile()));
                pdfDocument.copyPagesTo(1, 1, newPdf, i + 1);
                pdfDocument.close();
            }
            newPdf.close();
            return file;
        }
    }

    public SearchState getSearchState() {
        return searchState;
    }

    public void setSearchState(SearchState searchState) {
        this.searchState = searchState;
    }
}
