package financialStudio.young_accountant.ui.home;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smarteist.autoimageslider.SliderView;
import financialStudio.young_accountant.*;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private View root;

    private SliderBuilder sliderBuilder;
    private ChartOFaccounts chartOFaccounts;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_fragment, container, false);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        init();
        displayInit();
        chartOFaccounts = new ChartOFaccounts(getResources());
        // TODO: Use the ViewModel
    }

    private void init() {

        //  toolbar_init();

        SliderView sliderView = root.findViewById(R.id.imageSlider);

        SliderAdapterExample adapter = new SliderAdapterExample(getContext());

        sliderBuilder = new SliderBuilder(sliderView, adapter);

        sliderBuilder.setResourses(getResources());

        sliderBuilder.build();


        ImageButton btn1 = (ImageButton) root.findViewById(R.id.imgbtn1);
        btn1.setOnClickListener(btn1Click);

        ImageButton btn2 = (ImageButton) root.findViewById(R.id.imgbtn2);
        btn2.setOnClickListener(btn2Click);

        ImageButton btn3 = (ImageButton) root.findViewById(R.id.imgbtn3);
        btn3.setOnClickListener(btn3Click);

        ImageButton btn4 = (ImageButton) root.findViewById(R.id.imgbtn4);
        btn4.setOnClickListener(btn4Click);

        ImageButton btn5 = (ImageButton) root.findViewById(R.id.imgbtn5);
        btn5.setOnClickListener(btn5Click);

        ImageButton btn6 = (ImageButton) root.findViewById(R.id.imgbtn6);
        btn6.setOnClickListener(btn6Click);
    }

    private void toolbar_init(){
        ImageButton imageView = (ImageButton) root.findViewById(R.id.img_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView textView = (TextView) root.findViewById(R.id.action_bar_text);
        textView.setVisibility(View.INVISIBLE);

        final DialogLang dialogLang = new DialogLang();

        ImageButton imgbtn_lang = (ImageButton) root.findViewById(R.id.img_search);
        imgbtn_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  dialogLang.show(getSupportFragmentManager(), "dlg_lang");
            }
        });
    }

    private void initLanguage(){

        TextView textView = (TextView) root.findViewById(R.id.textView);
        textView.setText(R.string.active);

        TextView textView2 = (TextView) root.findViewById(R.id.textView2);
        textView2.setText(R.string.passive);

        TextView textView3 = (TextView) root.findViewById(R.id.textView3);
        textView3.setText(R.string.transit);

        TextView textView4 = (TextView) root.findViewById(R.id.textView4);
        textView4.setText(R.string.zabalans);

        TextView textView5 = (TextView) root.findViewById(R.id.textView5);
        textView5.setText(R.string.zakon);

        TextView textView6 = (TextView) root.findViewById(R.id.textView6);
        textView6.setText(R.string.nsbu);
    }

    private void displayInit(){

        final LinearLayout fr_1 = (LinearLayout) root.findViewById(R.id.ll_1);
        final LinearLayout fr_2 = (LinearLayout) root.findViewById(R.id.ll_2);
        final LinearLayout fr_3 = (LinearLayout) root.findViewById(R.id.ll_3);
        final LinearLayout fr_4 = (LinearLayout) root.findViewById(R.id.ll_4);
        final LinearLayout fr_5 = (LinearLayout) root.findViewById(R.id.ll_5);
        final LinearLayout fr_6 = (LinearLayout) root.findViewById(R.id.ll_6);

        fr_1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                int _Width = fr_1.getWidth();
                int _Height = fr_1.getHeight();

                fr_1.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int superfluous = _Width - _Height;

                int rigth_margin= (int) superfluous / 3;
                int left_margin  = superfluous - rigth_margin;


                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fr_1.getLayoutParams();
                params.setMargins(left_margin,params.topMargin, rigth_margin,params.bottomMargin);
                fr_1.setLayoutParams(params);
            }
        });

        fr_2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int _Width = fr_2.getWidth();
                int _Height = fr_2.getHeight();

                fr_2.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int superfluous = _Width - _Height;

                int left_margin = (int) superfluous / 3;
                int rigth_margin = superfluous - left_margin;


                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fr_2.getLayoutParams();
                params.setMargins(left_margin,params.topMargin, rigth_margin,params.bottomMargin);
                fr_2.setLayoutParams(params);
            }
        });

        fr_3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int _Width = fr_3.getWidth();
                int _Height = fr_3.getHeight();

                fr_3.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int superfluous = _Width - _Height;

                int rigth_margin= (int) superfluous / 3;
                int left_margin  = superfluous - rigth_margin;


                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fr_3.getLayoutParams();
                params.setMargins(left_margin,params.topMargin, rigth_margin,params.bottomMargin);
                fr_3.setLayoutParams(params);
            }
        });

        fr_4.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int _Width = fr_4.getWidth();
                int _Height = fr_4.getHeight();

                fr_4.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int superfluous = _Width - _Height;

                int left_margin = (int) superfluous / 3;
                int rigth_margin = superfluous - left_margin;


                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fr_4.getLayoutParams();
                params.setMargins(left_margin,params.topMargin, rigth_margin,params.bottomMargin);
                fr_4.setLayoutParams(params);
            }
        });

        fr_5.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int _Width = fr_5.getWidth();
                int _Height = fr_5.getHeight();

                fr_5.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int superfluous = _Width - _Height;

                int rigth_margin= (int) superfluous / 3;
                int left_margin  = superfluous - rigth_margin;


                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fr_5.getLayoutParams();
                params.setMargins(left_margin,params.topMargin, rigth_margin,params.bottomMargin);
                fr_5.setLayoutParams(params);
            }
        });

        fr_6.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int _Width = fr_6.getWidth();
                int _Height = fr_6.getHeight();

                fr_6.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int superfluous = _Width - _Height;

                int left_margin = (int) superfluous / 3;
                int rigth_margin = superfluous - left_margin;


                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fr_6.getLayoutParams();
                params.setMargins(left_margin,params.topMargin, rigth_margin,params.bottomMargin);
                fr_6.setLayoutParams(params);
            }
        });
    }

    View.OnClickListener btn1Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext().getApplicationContext(), Activity_active.class);

            intent.putExtra("active_1", getChartOFaccounts().getActive_1());
            intent.putExtra("active_2", getChartOFaccounts().getActive_2());

            startActivity(intent);
        }
    };

    View.OnClickListener btn2Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext().getApplicationContext(), Activity_passive.class);

            intent.putExtra("passive_1", getChartOFaccounts().getPasssive_1());
            intent.putExtra("passive_2", getChartOFaccounts().getPasssive_2());

            startActivity(intent);
        }
    };

    View.OnClickListener btn3Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext().getApplicationContext(), Activity_transite.class);

            intent.putExtra("transite", getChartOFaccounts().getTransite());

            startActivity(intent);
        }
    };

    View.OnClickListener btn4Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext().getApplicationContext(), Activity_zabalanse.class);

            intent.putExtra("zabalanse", getChartOFaccounts().getZabalans());

            startActivity(intent);
        }
    };

    View.OnClickListener btn5Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext().getApplicationContext(), Activity_dopol.class);
            intent.putExtra("dopol", 1);

            startActivity(intent);
        }
    };

    View.OnClickListener btn6Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext().getApplicationContext(), Activity_dopol.class);
            intent.putExtra("dopol", 2);

            startActivity(intent);
        }
    };

    public void setLocaleRu (){
        LocaleHelper.onAttach(getContext().getApplicationContext(), "ru");
        initLanguage();
        chartOFaccounts = new ChartOFaccounts(getResources());
        sliderBuilder.update(getResources());
    }

    public void setLocaleUz_L (){
        LocaleHelper.onAttach(getContext().getApplicationContext(), "uz");
        initLanguage();
        chartOFaccounts = new ChartOFaccounts(getResources());
        sliderBuilder.update(getResources());
    }

    public void setLocaleUz_K (){
        LocaleHelper.onAttach(getContext().getApplicationContext(), "default");
        initLanguage();
        chartOFaccounts = new ChartOFaccounts(getResources());
        sliderBuilder.update(getResources());
    }

    public ChartOFaccounts getChartOFaccounts() {
        return chartOFaccounts;
    }

}
