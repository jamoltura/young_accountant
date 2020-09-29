package financialStudio.young_accountant;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
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

    private ArrayList<String> list;
    private ArrayList<String> listBase;

    private final LayoutInflater lInflater;
    private final Context context;

    public SchetAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = new ArrayList<String>(list);
        this.listBase = new ArrayList<String>(list);

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

        String s = (String) getItem(position);

        if (s.codePointAt(2) == 48 && s.codePointAt(3) == 48){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            rl_schet.setLayoutParams(params);

            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 0);
            rl_schet_value.setLayoutParams(params1);


            ll_a.setBackgroundColor(context.getResources().getColor(R.color.colorlisthead, context.getTheme()));

            textSchet.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            textSchet.setGravity(Gravity.CENTER_HORIZONTAL);
            textSchet.setText(s);
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            textSchet.setLayoutParams(params2);
        }else{
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

            rl_schet.setLayoutParams(params);

            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            rl_schet_value.setLayoutParams(params2);

            ll_a.setBackgroundColor(context.getResources().getColor(R.color.colorfonchild, context.getTheme()));

            String[] arr = s.split(" ", 2);

            String Schet = arr[0];
            String Schet_value = arr[1];

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

    public void search(String str){

        ArrayList<String> temp = new ArrayList<String>(getListBase());

        int count = temp.size();

        getList().clear();

        for (int i = 0; i < count; i++){

            Log.d(TAG, temp.get(i));

            if (temp.get(i).startsWith(str)){
                list.add(temp.get(i));
            } else if ((count - 1 - i >= str.length()) && (temp.get(i).startsWith(str, i))){
                Log.d(TAG, str + " = " + temp.get(i));
                list.add(temp.get(i));
            }

        }

    }

    public void cancel_search(){
        setList(getListBase());
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public ArrayList<String> getListBase() {
        return listBase;
    }

    public void setListBase(ArrayList<String> listBase) {
        this.listBase = listBase;
    }
}
