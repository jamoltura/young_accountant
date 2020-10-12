package financialStudio.young_accountant;

import android.content.Context;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.File;
import java.io.IOException;

public class PdfOnlyPage {

    private static final String TAG = "myLogs";

    private File copy_pdf_Document;
    private File draw_pdf_Document;
    private Context context;



    public PdfOnlyPage(Context context, PdfDocument pdfDocument, int index) throws IOException {
        this.context = context;
        getNowPdfFile(pdfDocument, index);
        getNowFile(index);
    }

    private void getNowPdfFile(PdfDocument pdfDocument, int index) throws IOException {
        File nowPdfFile = new File(context.getExternalFilesDir(null), "tempNow" + index + ".pdf");
        PdfWriter writer = new PdfWriter(nowPdfFile);
        PdfDocument newPdf = new PdfDocument(writer);
        pdfDocument.copyPagesTo(index + 1,index + 1, newPdf);
        newPdf.close();
        writer.close();
        setCopy_pdf_Document(nowPdfFile);
    }

    private void getNowFile(int index){
        File drawfileFile = new File(context.getExternalFilesDir(null), "temp" + index + ".pdf");
        setDraw_pdf_Document(drawfileFile);
    }

    public File getCopyPdfDocument() {
        return copy_pdf_Document;
    }

    public File getDrawPdfDocument() {
        return draw_pdf_Document;
    }

    public void setCopy_pdf_Document(File copy_pdf_Document) {
        this.copy_pdf_Document = copy_pdf_Document;
    }

    public void setDraw_pdf_Document(File draw_pdf_Document) {
        this.draw_pdf_Document = draw_pdf_Document;
    }

}
