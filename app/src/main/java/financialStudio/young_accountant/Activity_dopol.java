package financialStudio.young_accountant;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Activity_dopol extends BaseActivite{

    private static final String TAG = "myLogs";

    private DokDopol dokDopol;
    private int fl_heigth;
    private DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String lang = LocaleHelper.getLanguage(getApplicationContext());
        LocaleHelper.onAttach(this, lang);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        setContentView(R.layout.activity_dopol);

        int dopol = getIntent().getIntExtra("dopol", 0);

        if (dopol == 1){
            toolbar_init(1);
            dopol1();
        }else if (dopol == 2){
            toolbar_init(2);
            dopol2();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(click_fab_up);

       // new ThreadBanner(getActivity()).start();
    }

    View.OnClickListener click_fab_up = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PdfFragment pdfFragment = new PdfFragment();
            pdfFragment.show(getSupportFragmentManager(), "pdf_dialog");


          //  getSupportFragmentManager().beginTransaction().add(R.id.pdf_fragment, pdfFragment);
          //  getSupportFragmentManager().beginTransaction().show(pdfFragment);
          //  getSupportFragmentManager().beginTransaction().commit();
        }
    };




    @Override
    void toolbar_init(int value) {
        super.toolbar_init(value);
        TextView textView = (TextView) findViewById(R.id.action_bar_text);

        if (value == 1) {
            textView.setText(getResources().getString(R.string.zakon));
        }else {
            textView.setText(getResources().getString(R.string.nsbu));
        }
    }

    @Override
    void add_custom_action_bar(){
        setNavigateVisible(false);
        final LinearLayout ll = (LinearLayout) findViewById(R.id.actionBar);

        ll.removeViewAt(0);

        LinearLayout ll_search = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_action_bar, ll, false);

        ll.addView(ll_search, 0);

        dokDopol.reset();
        dokDopol.open();

        int value = getIntent().getIntExtra("dopol", 0);

        if (value == 1){
            toolbar_init(value);
        }else if (value == 2){
            toolbar_init(value);
        }
    }

    @Override
    void _search(String value){

        final SearchView searchView = (SearchView) findViewById(R.id.search_view);

        searchView.post(() -> {
            InputMethodManager inputMethodManager =
                    (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(
                    searchView.getApplicationWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        });

        RelativeLayout rl_progress = (RelativeLayout) findViewById(R.id.progress);
        rl_progress.setVisibility(View.VISIBLE);

        new ThreadSearch(getActivity(), dokDopol, value, StatusDopol.SearchStart).start();
    }

    public void stopSearch(DokDopol dokDopol, boolean result){
        this.dokDopol = dokDopol;

        RelativeLayout rl_progress = (RelativeLayout) findViewById(R.id.progress);
        rl_progress.setVisibility(View.INVISIBLE);

        if (result){
            this.dokDopol.reopen();
            setNavigation();
            setNavigateVisible(true);
        }else {
            String msg = getResources().getString(R.string.action_search_not);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            add_custom_action_bar();
        }
    }

    @Override
    void add_custom_search_bar() {
        super.add_custom_search_bar();

        final SearchView searchView = (SearchView) findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(onQuery);
    }

    SearchView.OnQueryTextListener onQuery = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if (query.length() > 0){
                _search(query);
                return true;
            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private void dopol1(){
        setNavigateVisible(false);
        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        dokDopol = DokDopol.getInstanceZakon(this, pdfView);
        dokDopol.open();
    }

    private void dopol2(){
        setNavigateVisible(false);
        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        dokDopol = DokDopol.getInstanceNSBU(this, pdfView);
        dokDopol.open();
    }

    @Override
    protected void onStop() {
        dokDopol.Destroy();
        super.onStop();
    }

    View.OnClickListener click_left = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RelativeLayout rl_progress = (RelativeLayout) findViewById(R.id.progress);
            rl_progress.setVisibility(View.VISIBLE);

            new ThreadSearch(getActivity(), dokDopol, StatusDopol.SearchPreed).start();
        }
    };

    View.OnClickListener click_right = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RelativeLayout rl_progress = (RelativeLayout) findViewById(R.id.progress);
            rl_progress.setVisibility(View.VISIBLE);

            new ThreadSearch(getActivity(), dokDopol, StatusDopol.SearchNext).start();
        }
    };

    public void next_and_preed_Search(DokDopol dokDopol, boolean result){
        this.dokDopol = dokDopol;

        RelativeLayout rl_progress = (RelativeLayout) findViewById(R.id.progress);
        rl_progress.setVisibility(View.INVISIBLE);

        if (result){
            this.dokDopol.reopen();
            setNavigation();
        }
    }

    public static String convertSecondsToHMmSs(long seconds) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss.SSS");
        cal.setTimeInMillis(seconds);
        return format.format(cal.getTime());
    }

    // TODO: Navigation
    private void setNavigation(){
        PdfNavigation pdfNavigation = dokDopol.getPdfNavigation();
        int index = pdfNavigation.getCountAllArrayMapChild();
        int count = pdfNavigation.getCountArrayMapChild();
        setTextNavigation(index, count);

        if (index == 1){
            ImageView img_nav_left = (ImageView) findViewById(R.id.img_nav_left);
            img_nav_left.setEnabled(false);
        }else {
            ImageView img_nav_left = (ImageView) findViewById(R.id.img_nav_left);
            img_nav_left.setEnabled(true);
        }

        if (index == count){
            ImageView img_nav_right = (ImageView) findViewById(R.id.img_nav_right);
            img_nav_right.setEnabled(false);
        }else {
            ImageView img_nav_right = (ImageView) findViewById(R.id.img_nav_right);
            img_nav_right.setEnabled(true);
        }
    }

    private void setNavigateVisible(boolean visible){
        if (visible){
            RelativeLayout rl_nav = (RelativeLayout) findViewById(R.id.navigation);
            rl_nav.setVisibility(View.VISIBLE);

            ImageView img_nav_left = (ImageView) findViewById(R.id.img_nav_left);
            img_nav_left.setOnClickListener(click_left);

            ImageView img_nav_right = (ImageView) findViewById(R.id.img_nav_right);
            img_nav_right.setOnClickListener(click_right);
        }else {
            RelativeLayout rl_nav = (RelativeLayout) findViewById(R.id.navigation);
            rl_nav.setVisibility(View.INVISIBLE);
        }
    }

    private void setTextNavigation(int index, int count){
        TextView textView = (TextView) findViewById(R.id.text_navigation);
        String value = index + "/" + count;
        textView.setText(value);
    }

    public Activity_dopol getActivity(){
        return this;
    }

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(DisplayMetrics metrics) {
        this.metrics = metrics;
    }

    @Override
    void startBanner() {
        /*
        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                FragmentBanner frb = new FragmentBanner();
                FragmentTransaction rft = getSupportFragmentManager().beginTransaction();
                rft.add(R.id.frame_banner, frb);
                rft.commit();
            }
        });
         */
    }
}
