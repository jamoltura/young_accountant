package financialStudio.young_accountant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.SliderView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String lang = LocaleHelper.getLanguage(this);

        LocaleHelper.onAttach(this, lang);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_lang, R.id.nav_share, R.id.nav_about, R.id.nav_about_app, R.id.nav_rate, R.id.nav_other)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        ImageView img_telegramm = (ImageView) drawer.findViewById(R.id.img_telegramm);
        img_telegramm.setOnClickListener(click_telegramm);

        ImageView img_facebook = (ImageView) drawer.findViewById(R.id.img_facebook);
        img_telegramm.setOnClickListener(click_facebook);

        ImageView img_vk = (ImageView) drawer.findViewById(R.id.img_vk);
        img_telegramm.setOnClickListener(click_vk);

        ImageView img_instagramm = (ImageView) drawer.findViewById(R.id.img_instagramm);
        img_telegramm.setOnClickListener(click_instagramm);

    }

    View.OnClickListener click_telegramm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=moliya_studiyasi"));
            startActivity(intent);
        }
    };

    View.OnClickListener click_facebook = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getOpenFacebookIntent();
            startActivity(intent);
        }
    };

    View.OnClickListener click_vk = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=moliya_studiyasi"));
            startActivity(intent);
        }
    };

    View.OnClickListener click_instagramm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=moliya_studiyasi"));
            startActivity(intent);
        }
    };

    public Intent getOpenFacebookIntent() {

        try {
             getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/moliya_studiyasi"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/moliya_studiyasi"));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

        if (drawer.isOpen()){
            drawer.closeDrawers();
        }else {
            super.onBackPressed();
        }
    }
}
