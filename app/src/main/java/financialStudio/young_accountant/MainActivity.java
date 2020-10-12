package financialStudio.young_accountant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import financialStudio.young_accountant.ui.home.HomeFragment;

import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    private static final String VK_APP_PACKAGE_ID = "com.vkontakte.android";
    private static final String FACEBOOK_APP_PACKAGE_ID = "com.facebook.katana";
    private static final String INSTAGRAM_APP_PACKAGE_ID = "com.instagram.android";
    private static final String TELEGRAM_APP_PACKAGE_ID = "com.telegram.android";

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private NavController navController;


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

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        ImageView img_telegramm = (ImageView) drawer.findViewById(R.id.img_telegramm);
        img_telegramm.setOnClickListener(click_telegramm);

        ImageView img_facebook = (ImageView) drawer.findViewById(R.id.img_facebook);
        img_facebook.setOnClickListener(click_facebook);

        ImageView img_vk = (ImageView) drawer.findViewById(R.id.img_vk);
        img_vk.setOnClickListener(click_vk);

        ImageView img_instagramm = (ImageView) drawer.findViewById(R.id.img_instagramm);
        img_instagramm.setOnClickListener(click_instagramm);

    }

    View.OnClickListener click_telegramm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawer.closeDrawers();
            openLink("tg://resolve?domain=moliya_studiyasi");
        }
    };

    View.OnClickListener click_facebook = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawer.closeDrawers();
            openLink("fb://facewebmodal/f?href=https://www.facebook.com/moliya.studiyasi/");
        }
    };

    View.OnClickListener click_vk = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawer.closeDrawers();
         //   openLink("vk");
        }
    };

    View.OnClickListener click_instagramm = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawer.closeDrawers();
            openLink("https://www.instagram.com/moliya_studiyasi/");
        }
    };

    public Intent getOpenFacebookIntent() {

        try {
             getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=https://www.facebook.com/moliya.studiyasi/"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/moliya_studiyasi"));
        }
    }

    public void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(intent, 0);

        if (resInfo.isEmpty()) return;

        for (ResolveInfo info: resInfo) {
            if (info.activityInfo == null) continue;
            if (VK_APP_PACKAGE_ID.equals(info.activityInfo.packageName)
                    || FACEBOOK_APP_PACKAGE_ID.equals(info.activityInfo.packageName)
                    || INSTAGRAM_APP_PACKAGE_ID.equals(info.activityInfo.packageName)
                    || TELEGRAM_APP_PACKAGE_ID.equals(info.activityInfo.packageName)
            ) {
                intent.setPackage(info.activityInfo.packageName);
                break;
            }
        }
        startActivity(intent);
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

    public void setLocaleRu (){
        LocaleHelper.onAttach(getApplicationContext(), "ru");
        LocaleHelper.onAttach(this, "ru");
        initLanguage();

     //   chartOFaccounts = new ChartOFaccounts(getResources());
      //  sliderBuilder.update(getResources());
    }

    public void setLocaleUz_L (){
        LocaleHelper.onAttach(getApplicationContext(), "uz");
        LocaleHelper.onAttach(this, "uz");
        initLanguage();
      //  chartOFaccounts = new ChartOFaccounts(getResources());
     //   sliderBuilder.update(getResources());
    }

    public void setLocaleUz_K (){
        LocaleHelper.onAttach(getApplicationContext(), "default");
        LocaleHelper.onAttach(this, "default");
        initLanguage();
     //   chartOFaccounts = new ChartOFaccounts(getResources());
     //   sliderBuilder.update(getResources());
    }

    private void initLanguage(){

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(R.string.active);

        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText(R.string.passive);

        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setText(R.string.transit);

        TextView textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setText(R.string.zabalans);

        TextView textView5 = (TextView) findViewById(R.id.textView5);
        textView5.setText(R.string.zakon);

        TextView textView6 = (TextView) findViewById(R.id.textView6);
        textView6.setText(R.string.nsbu);
    }

    public NavController getNavController() {
        return navController;
    }
}
