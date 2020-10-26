package financialStudio.young_accountant;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogLang extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "myLogs";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dialog_language, null);

        ImageButton imgbtn_close = (ImageButton) v.findViewById(R.id.imgbtnclose);
        Button btn_lang_uz_l = (Button) v.findViewById(R.id.btn_uz_l);
        Button btn_lang_ru = (Button) v.findViewById(R.id.btn_ru);
        Button btn_lang_uz_k = (Button) v.findViewById(R.id.btn_uz_k);

        imgbtn_close.setOnClickListener(this);
        btn_lang_uz_l.setOnClickListener(this);
        btn_lang_ru.setOnClickListener(this);
        btn_lang_uz_k.setOnClickListener(this);

        String lang = LocaleHelper.getLanguage(getContext());

        if (lang.equalsIgnoreCase("ru")){
            btn_lang_ru.setBackgroundResource(R.drawable.btnsimple_select);
            btn_lang_ru.setTextColor(getColor_white());
        }else if (lang.equalsIgnoreCase("uz")){
            btn_lang_uz_l.setBackgroundResource(R.drawable.btnsimple_select);
            btn_lang_uz_l.setTextColor(getColor_white());
        }else {
            btn_lang_uz_k.setBackgroundResource(R.drawable.btnsimple_select);
            btn_lang_uz_k.setTextColor(getColor_white());
        }

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtnclose: {
                getActivity().onBackPressed();
            }
            break;
            case R.id.btn_uz_l:{
                ((MainActivity) getActivity()).setLocaleUz_L();
                getActivity().onBackPressed();
            }
            break;
            case R.id.btn_ru:{
                ((MainActivity) getActivity()).setLocaleRu();
                getActivity().onBackPressed();
            }
            break;
            case R.id.btn_uz_k:{
                ((MainActivity) getActivity()).setLocaleUz_K();
                getActivity().onBackPressed();
            }
        }
    }

    protected int getColorPrimaryDark(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.colorPrimaryDark, getActivity().getTheme());
        }else {
            return getResources().getColor(R.color.colorPrimaryDark);
        }
    }

    protected int getColor_white(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.color_white, getActivity().getTheme());
        }else {
            return getResources().getColor(R.color.color_white);
        }
    }

    protected int getColor_colorlisthead(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.colorlisthead, getActivity().getTheme());
        }else {
            return getResources().getColor(R.color.colorlisthead);
        }
    }

    protected int getColor_colorfonchild(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.colorfonchild, getActivity().getTheme());
        }else {
            return getResources().getColor(R.color.colorfonchild);
        }
    }
}
