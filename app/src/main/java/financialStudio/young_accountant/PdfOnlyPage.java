package financialStudio.young_accountant;

import android.content.Context;
import android.util.Log;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.File;
import java.io.IOException;

public class PdfOnlyPage {

    private static final String TAG = "myLogs";

    private File reader_Document;
    private File writer_Document;
    private Context context;



    public PdfOnlyPage(Context context, PdfDocument pdfDocument,int index) {
        this.context = context;
        try {
            reader_Document_File(pdfDocument, index);
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
        writer_Document_File(index);
    }

    public void Destroy(){
        reader_Document.delete();
        writer_Document.delete();
    }

    private void reader_Document_File(PdfDocument pdfDocument, int index) throws IOException {
        reader_Document = new File(context.getExternalFilesDir(null), "read" + index + ".pdf");
        PdfWriter writer = new PdfWriter(reader_Document);
        PdfDocument newPdf = new PdfDocument(writer);
        pdfDocument.copyPagesTo(index+1 ,index+1, newPdf);
        newPdf.close();
        writer.close();
    }

    private void writer_Document_File(int index){
        writer_Document = new File(context.getExternalFilesDir(null), "write" + index + ".pdf");
    }

    public File getReader_Document() {
        return reader_Document;
    }

    public File getWriter_Document() {
        return writer_Document;
    }

}
