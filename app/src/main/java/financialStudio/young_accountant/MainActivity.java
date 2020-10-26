package financialStudio.young_accountant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private NavController navController;
    private NavigationView navigationView;
    private SliderBuilder sliderBuilder;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String lang = LocaleHelper.getLanguage(getApplicationContext());
        LocaleHelper.onAttach(this, lang);

        FileManager fma = new FileManager(getApplicationContext());

     //   if (!fma.isTime()){
        if (false){
            setContentView(R.layout.activity_main_time);

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            SliderView sliderView = findViewById(R.id.imageSlider);

            SliderAdapterExample adapter = new SliderAdapterExample(getApplicationContext());

            sliderBuilder = new SliderBuilder(sliderView, adapter);

            sliderBuilder.setResourses(getResources());

            sliderBuilder.build();

        }else {
            setContentView(R.layout.activity_main);

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            drawer = findViewById(R.id.drawer_layout);
            navigationView = findViewById(R.id.nav_view);

            MenuItem item_share = navigationView.getMenu().findItem(R.id.nav_share);
            item_share.setOnMenuItemClickListener(click_share);

            MenuItem item_rate = navigationView.getMenu().findItem(R.id.nav_rate);
            item_rate.setOnMenuItemClickListener(click_rate);

            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_lang, R.id.nav_share, R.id.nav_rate, R.id.nav_about, R.id.nav_other)
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
    }

    MenuItem.OnMenuItemClickListener click_share = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            drawer.closeDrawers();

            final String URL_TO_SHARE = "https://play.google.com/store/apps/details?id=com.viber.voip&hl=ru&gl=US";

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);

            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, URL_TO_SHARE);
            startActivity(Intent.createChooser(intent, "Share"));

            return true;
        }
    };

    MenuItem.OnMenuItemClickListener click_rate = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            drawer.closeDrawers();
            openLink("market://details?id=com.viber.voip&hl=ru&gl=US");
            return true;
        }
    };

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

        final String VK_APP_PACKAGE_ID = "com.vkontakte.android";
        final String FACEBOOK_APP_PACKAGE_ID = "com.facebook.katana";
        final String INSTAGRAM_APP_PACKAGE_ID = "com.instagram.android";
        final String TELEGRAM_APP_PACKAGE_ID = "com.telegram.android";
        final String PLAYMARKET_APP_PACKAGE_ID = "com.android.vending";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(intent, 0);

        if (resInfo.isEmpty()) return;

        for (ResolveInfo info: resInfo) {
            if (info.activityInfo == null) continue;
            if (VK_APP_PACKAGE_ID.equals(info.activityInfo.packageName)
                    || FACEBOOK_APP_PACKAGE_ID.equals(info.activityInfo.packageName)
                    || INSTAGRAM_APP_PACKAGE_ID.equals(info.activityInfo.packageName)
                    || TELEGRAM_APP_PACKAGE_ID.equals(info.activityInfo.packageName)
                    || PLAYMARKET_APP_PACKAGE_ID.equals(info.activityInfo.packageName)
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

        FileManager fma = new FileManager(getApplicationContext());

        if (fma.isTime()){
            super.onBackPressed();
        }else {
            if (drawer.isOpen()){
                drawer.closeDrawers();
            }else {
                super.onBackPressed();
            }
        }
    }

    public void setLocaleRu (){
        LocaleHelper.onAttach(getApplicationContext(), "ru");
        LocaleHelper.onAttach(this, "ru");
        initLanguage();
    }

    public void setLocaleUz_L (){
        LocaleHelper.onAttach(getApplicationContext(), "uz");
        LocaleHelper.onAttach(this, "uz");
        initLanguage();
    }

    public void setLocaleUz_K (){
        LocaleHelper.onAttach(getApplicationContext(), "default");
        LocaleHelper.onAttach(this, "default");
        initLanguage();
    }

    private void initLanguage(){

        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.activity_main_drawer);

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
}
