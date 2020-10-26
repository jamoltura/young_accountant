package financialStudio.young_accountant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


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

    private void close(){
        FragmentTransaction rft = getActivity().getSupportFragmentManager().beginTransaction();
        rft.remove(this).commit();
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

        final View v = inflater.inflate(R.layout.fragment_banner, container, false);

        /*
        final AdView mAdView = (AdView) v.findViewById(R.id.adView);

        ImageButton imgbtn_close = (ImageButton) v.findViewById(R.id.imgbtn_close);
        imgbtn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();

        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.d(TAG, loadAdError.getMessage());
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                close();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

                ImageButton imgbtn_close = (ImageButton) v.findViewById(R.id.imgbtn_close);
                imgbtn_close.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }
        });
         */
        return v;
    }
}
