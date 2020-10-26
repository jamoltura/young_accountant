package financialStudio.young_accountant;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public abstract class BaseActivite extends AppCompatActivity {

    private boolean isSearchStatus;

    void toolbar_init(final int value){
        isSearchStatus = false;
        ImageButton imageView = (ImageButton) findViewById(R.id.img_back);
        imageView.setOnClickListener(v -> finish());

        ImageButton imgbtn_bar = (ImageButton) findViewById(R.id.img_search);
        imgbtn_bar.setOnClickListener(clickSearchImg);
    }

    View.OnClickListener clickSearchImg = v -> add_custom_search_bar();

    abstract void add_custom_action_bar();

    void add_custom_search_bar(){
        isSearchStatus = true;

        final LinearLayout ll = (LinearLayout) findViewById(R.id.actionBar);

        ll.removeViewAt(0);

        LinearLayout ll_search = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_search_bar, ll, false);

        ll.addView(ll_search, 0);

        final SearchView searchView = (SearchView) findViewById(R.id.search_view);

        searchView.setOnSearchClickListener(clickSearch);

        searchView.setOnQueryTextListener(onQuery);

        searchView.post(() -> {
            InputMethodManager inputMethodManager =
                    (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(
                    searchView.getApplicationWindowToken(),InputMethodManager.SHOW_IMPLICIT, 0);
            searchView.requestFocus();
        });
    }

    // TODO : click button
    View.OnClickListener clickSearch = v -> {
        final SearchView searchView = (SearchView) findViewById(R.id.search_view);
        String value = searchView.getQuery().toString();
        _search(value);
    };

    SearchView.OnQueryTextListener onQuery = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            _search(newText);
            return true;
        }
    };

    // TODO : поиск
    abstract void _search(String value);

    @Override
    public void onBackPressed() {
        if (isSearchStatus){
            isSearchStatus = false;
            add_custom_action_bar();
        }else {
            super.onBackPressed();
        }
    }

    protected int getColorPrimaryDark(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           return getResources().getColor(R.color.colorPrimaryDark, getTheme());
        }else {
            return getResources().getColor(R.color.colorPrimaryDark);
        }
    }

    protected int getColor_white(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(R.color.color_white, getTheme());
        }else {
            return getResources().getColor(R.color.color_white);
        }
    }

    abstract void startBanner();

    private boolean isSearchStatus() {
        return isSearchStatus;
    }

    private void setSearchStatus(boolean searchStatus) {
        isSearchStatus = searchStatus;
    }
}
