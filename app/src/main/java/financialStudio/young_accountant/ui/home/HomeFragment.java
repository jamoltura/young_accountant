package financialStudio.young_accountant.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    private static final String TAG = "myLogs";

    private HomeViewModel mViewModel;
    private View root;
    private DisplayMetrics metrics;

    private SliderBuilder sliderBuilder;

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

        metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        init();
        displayInit();

        // TODO: Use the ViewModel
    }

    private void init() {

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

    View.OnClickListener btn1Click = v -> {
        Intent intent = new Intent(getContext().getApplicationContext(), Activity_active.class);
        startActivity(intent);
    };

    View.OnClickListener btn2Click = v -> {
        Intent intent = new Intent(getContext().getApplicationContext(), Activity_passive.class);
        startActivity(intent);
    };

    View.OnClickListener btn3Click = v -> {
        Intent intent = new Intent(getContext().getApplicationContext(), Activity_transite.class);
        startActivity(intent);
    };

    View.OnClickListener btn4Click = v -> {
        Intent intent = new Intent(getContext().getApplicationContext(), Activity_zabalanse.class);
        startActivity(intent);
    };

    View.OnClickListener btn5Click = v -> {
        Intent intent = new Intent(getContext().getApplicationContext(), Activity_dopol.class);
        intent.putExtra("dopol", 1);

        startActivity(intent);
    };

    View.OnClickListener btn6Click = v -> {
        Intent intent = new Intent(getContext().getApplicationContext(), Activity_dopol.class);
        intent.putExtra("dopol", 2);

        startActivity(intent);
    };

    private DisplayMetrics getMetrics() {
        return metrics;
    }

    private void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }
}
