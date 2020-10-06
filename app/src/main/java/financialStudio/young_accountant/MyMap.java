package financialStudio.young_accountant;

import android.graphics.Point;

public class MyMap {

  private String text;
  private Point p;
  private boolean isBool;


    public MyMap(String text, Point p, boolean isBool) {
        this.text = text;
        this.p = p;
        this.isBool = isBool;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    public boolean isBool() {
        return isBool;
    }

    public void setBool(boolean bool) {
        isBool = bool;
    }
}
