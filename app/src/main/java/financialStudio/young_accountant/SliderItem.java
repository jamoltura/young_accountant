package financialStudio.young_accountant;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SliderItem {

    final private Context context;
    private int index;

    public SliderItem(Context context, int value) {
        this.context = context;
        setIndex(value);
    }

    public String getDescription(){
        return "jamol";
    }

    public Bitmap getImageUrl(){
        switch (getIndex()){
            case 0: return BitmapFactory.decodeResource(context.getResources(), R.drawable.logo1);
            case 1: return BitmapFactory.decodeResource(context.getResources(), R.drawable.logo2);
            case 2: return BitmapFactory.decodeResource(context.getResources(), R.drawable.logo3);
        }
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.logo1);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
