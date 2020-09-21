package financialStudio.young_accountant;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SchetAdapter extends BaseAdapter {

    private static final String TAG = "myLogs";

    private ArrayList<String> list;
    private final LayoutInflater lInflater;
    private final Context context;

    public SchetAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;

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

        final TextView textView = (TextView) view.findViewById(R.id.textschet);
        final RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.rel);

        String s = list.get(position);

        textView.setText(s);

        if (s.codePointAt(2) == 48 && s.codePointAt(3) == 48){
            rl.setBackgroundColor(context.getResources().getColor(R.color.colorlisthead));
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
        }else{
            rl.setBackgroundColor(context.getResources().getColor(R.color.colorlist));
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            textView.setGravity(Gravity.LEFT);
        }


        return view;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }
}
