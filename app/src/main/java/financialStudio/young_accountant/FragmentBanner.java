package financialStudio.young_accountant;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBanner#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBanner extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "myLogs";


    private String mParam1;
    private String mParam2;

    public FragmentBanner() {
        // Required empty public constructor
    }







    public static FragmentBanner newInstance(String param1, String param2) {
        FragmentBanner fragment = new FragmentBanner();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_banner, container, false);

        final AdView mAdView = (AdView) v.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();

        if (adRequest.isTestDevice(v.getContext())){
          //  Log.d(TAG, "true            ggg");
        }else{
          //  Log.d(TAG, "false             tggg");
        }

        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.d(TAG, loadAdError.getMessage());
            }
        });

        return v;
    }
}
