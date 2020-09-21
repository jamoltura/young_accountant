package financialStudio.young_accountant;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class PdfAdapter extends BaseAdapter {

    private static final String TAG = "myLogs";

    private final ArrayList<Bitmap> list = new ArrayList<>();
    private final LayoutInflater lInflater;
    private final PdfRenderer pdf;
    private int count;
    private int displayWidth;
    private int displayHeight;
    private Context context;
    private float zoom = 1;


    public PdfAdapter(Context context, PdfRenderer pdf) {
        this.context = context;
        this.pdf = pdf;

        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        count = pdf.getPageCount();

        displayWidth = context.getResources().getDisplayMetrics().widthPixels;
        displayHeight = context.getResources().getDisplayMetrics().heightPixels;
    }

 //   @Override
 //   public boolean isEnabled(int position) {
 //       return false;
 //   }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {

        PdfRenderer.Page curPage = pdf.openPage(position);

        int newWidth = 0;
        int newHeight = 0;

        int raz_Width = displayWidth - curPage.getWidth();
        float proc = raz_Width / curPage.getWidth() * 100;
        float raz_proc = curPage.getHeight() * proc / 100;

        newWidth = displayWidth;
        newHeight = (int) (curPage.getHeight() + raz_proc);

        Bitmap bitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        curPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

        curPage.close();

        Matrix matrix = new Matrix();
        matrix.reset();

        matrix.setScale(getZoom(), getZoom());

        return Bitmap.createBitmap(bitmap, 0,0,newWidth, newHeight, matrix, false);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null){
            view = lInflater.inflate(R.layout.item_pdf, parent, false);
        }

        final ImageView imageView = (ImageView) view.findViewById(R.id.image_pdf);

        imageView.setImageBitmap((Bitmap) getItem(position));

        return view;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
}
