package financialStudio.young_accountant;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SliderItem {

    final private Resources res;
    private int index;

    public SliderItem(Resources res, int value) {
        this.res = res;
        setIndex(value);
    }

    public String getDescription(){
        switch (getIndex()){
            case 0: return getRes().getString(R.string.banner_1);
            case 1: return getRes().getString(R.string.banner_2);
            case 2: return getRes().getString(R.string.banner_3);
        }
        return getRes().getString(R.string.banner_1);
    }

    public Bitmap getImageUrl(){
        switch (getIndex()){
            case 0: return BitmapFactory.decodeResource(getRes(), R.drawable.logo1);
            case 1: return BitmapFactory.decodeResource(getRes(), R.drawable.logo2);
            case 2: return BitmapFactory.decodeResource(getRes(), R.drawable.logo3);
        }
        return BitmapFactory.decodeResource(getRes(), R.drawable.logo1);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Resources getRes() {
        return res;
    }
}
