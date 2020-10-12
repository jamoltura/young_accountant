package financialStudio.young_accountant;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import financialStudio.young_accountant.ui.home.HomeFragment;

import java.util.Iterator;

public class DialogLang extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "myLogs";

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
            btn_lang_ru.setTextColor(getActivity().getResources().getColor(R.color.color_white, getActivity().getTheme()));
        }else if (lang.equalsIgnoreCase("uz")){
            btn_lang_uz_l.setBackgroundResource(R.drawable.btnsimple_select);
            btn_lang_uz_l.setTextColor(getActivity().getResources().getColor(R.color.color_white, getActivity().getTheme()));
        }else if (lang.equalsIgnoreCase("default")){
            btn_lang_uz_k.setBackgroundResource(R.drawable.btnsimple_select);
            btn_lang_uz_k.setTextColor(getActivity().getResources().getColor(R.color.color_white, getActivity().getTheme()));
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
}
