package financialStudio.young_accountant;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SchetAdapter extends BaseAdapter {

    private static final String TAG = "myLogs";

    private ArrayList<MyMap> list;
    private ArrayList<MyMap> listBase;

    private final LayoutInflater lInflater;
    private final Context context;

    public SchetAdapter(Context context, ArrayList<MyMap> list) {
        this.context = context;
        this.list = new ArrayList<MyMap>(list);
        this.listBase = new ArrayList<MyMap>(list);

        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null){
            view = lInflater.inflate(R.layout.item, parent, false);
        }

        TextView textSchet = (TextView) view.findViewById(R.id.textschet);
        TextView textSchet_value = (TextView) view.findViewById(R.id.textschet_value);
        RelativeLayout rl_schet = (RelativeLayout) view.findViewById(R.id.rl_schet);
        RelativeLayout rl_schet_value = (RelativeLayout) view.findViewById(R.id.rl_schet_value);
        LinearLayout ll_a = (LinearLayout) view.findViewById(R.id.ll_a);

        MyMap myMap = (MyMap) getItem(position);

        String s = myMap.getText();

        Log.d(TAG, myMap.getP().x + " " + myMap.getP().y);

        if (s.codePointAt(2) == 48 && s.codePointAt(3) == 48){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            rl_schet.setLayoutParams(params);

            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 0);
            rl_schet_value.setLayoutParams(params1);

            ll_a.setBackgroundColor(context.getResources().getColor(R.color.colorlisthead, context.getTheme()));

            textSchet.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            textSchet.setGravity(Gravity.CENTER_HORIZONTAL);

            SpannableStringBuilder text = getColorText(myMap);

            textSchet.setText(text);
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            textSchet.setLayoutParams(params2);
        }else{
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

            rl_schet.setLayoutParams(params);

            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            rl_schet_value.setLayoutParams(params2);

            ll_a.setBackgroundColor(context.getResources().getColor(R.color.colorfonchild, context.getTheme()));

            SpannableStringBuilder Schet = getColorText_1(myMap);
            SpannableStringBuilder Schet_value = getColorText_2(myMap);

            textSchet.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            textSchet.setGravity(Gravity.LEFT);
            textSchet.setText(Schet);
            RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            textSchet.setLayoutParams(params3);

            textSchet_value.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            textSchet_value.setGravity(Gravity.LEFT);
            textSchet_value.setText(Schet_value);
        }

        return view;
    }

    private SpannableStringBuilder getColorText(MyMap myMap){

        String sourse = myMap.getText();

        Point p = myMap.getP();
        SpannableStringBuilder text = new SpannableStringBuilder(sourse);

        if (myMap.isBool()) {
            ForegroundColorSpan style = new ForegroundColorSpan(Color.rgb(255, 0, 0));
            text.setSpan(style, p.x, p.y, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return text;
    }

    private SpannableStringBuilder getColorText_1(MyMap myMap){

        String[] sourse = myMap.getText().split(" ", 2);

        Point p = myMap.getP();
        SpannableStringBuilder text = new SpannableStringBuilder(sourse[0]);

        if (myMap.isBool() && p.x < 4) {
            ForegroundColorSpan style = new ForegroundColorSpan(Color.rgb(255, 0, 0));

            // 0123 _4
            int y = 0;
            if (p.y > 4){
                y = 3;
            }else {
                y = p.y;
            }

            text.setSpan(style, p.x, y, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return text;
    }

    private SpannableStringBuilder getColorText_2(MyMap myMap){

        String[] sourse = myMap.getText().split(" ", 2);

        Point p = myMap.getP();
        SpannableStringBuilder text = new SpannableStringBuilder(sourse[1]);

        if (myMap.isBool() && p.x > 4) {
            ForegroundColorSpan style = new ForegroundColorSpan(Color.rgb(255, 0, 0));
            text.setSpan(style, p.x - 5, p.y - 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return text;
    }

    public void cancel_search(){
        setList(getListBase());
    }

    public ArrayList<MyMap> getList() {
        return list;
    }

    public void setList(ArrayList<MyMap> list) {
        this.list = list;
    }

    public ArrayList<MyMap> getListBase() {
        return listBase;
    }

    public void setListBase(ArrayList<MyMap> listBase) {
        this.listBase = listBase;
    }
}
