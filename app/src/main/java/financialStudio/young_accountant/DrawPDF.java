package financialStudio.young_accountant;

import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class DrawPDF implements SurfaceHolder.Callback{

    private static final String TAG = "myLogs";

    private Bitmap bitmap;
    private float X;
    private float Y;
    private float maxY;
    private int Height;

    private DrawThread drawThread;
    private SurfaceView surfaceView;

    public DrawPDF(SurfaceView surfaceView, Bitmap bitmap, int Height) {
        this.bitmap = bitmap;
        this.Height = Height;
        this.surfaceView = surfaceView;
        this.maxY = bitmap.getHeight() * -1;
        Log.d(TAG, "DrawPDF");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated");
        drawThread = new DrawThread(holder, bitmap);
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public float getMaxY() {
        return maxY;
    }

    public int getHeight() {
        return Height;
    }

    public void procrutUp(float value){

        float coorY = getY();

        float nowCoorY = coorY - value;

        Log.d(TAG, "up " + String.valueOf(nowCoorY) + " " + (getMaxY() + getHeight()));



        if (nowCoorY > getMaxY() + getHeight()){
            //  nowCoorY = getMaxY() + getHeight();

            setY(nowCoorY);
        }

        Log.d(TAG, "up 1" + String.valueOf(nowCoorY) + " " + (getMaxY() + getHeight()));


    }

    public void procrutDown(float value){

        float coorY = getY();

        float nowCoorY = coorY + value;
        Log.d(TAG, "down " + String.valueOf(nowCoorY));

        if (nowCoorY < 0){
            // nowCoorY = 0;

            setY(nowCoorY);
        }


    }

    private class DrawThread extends Thread {

        private final Bitmap bitmap;
        private final SurfaceHolder surfaceHolder;

        public DrawThread(SurfaceHolder surfaceHolder, Bitmap bitmap) {
            this.surfaceHolder = surfaceHolder;
            this.bitmap = bitmap;
        }


        @Override
        public void run() {

            Canvas canvas;
            boolean running = true;
            do {
                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas();
                    if (canvas == null) continue;
                    drawCanvas(canvas);
                } finally {
                    if (canvas != null) {

                        surfaceHolder.unlockCanvasAndPost(canvas);
                        //   running = false;
                    }
                }
            } while (running);
        }
    }

    private void drawCanvas(Canvas canvas){

        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();

        canvas.drawBitmap(bitmap, getX(), getY(), paint);

    }
}
