package org.pk.cas.newsappwithfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.pk.cas.fragements.IslamicNews;
import org.pk.cas.fragements.PakNews;
import org.pk.cas.fragements.ProfileSetting;
import org.pk.cas.fragements.SportsNews;
import org.pk.cas.fragements.WorldNews;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    WebView webView23;
    BottomNavigationView bottomNavigation;

    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Remove tool Bar*/
        Objects.requireNonNull(getSupportActionBar()).hide();
        webView23 = findViewById(R.id.webView23);
        bottomNavigation = findViewById(R.id.bottom_Navigation);
        webView23.setWebViewClient(new WebViewClient());
        webView23.loadUrl("https://google.com");
        /*ya wala fragment sb sa phly load ho ga ku ka agr hm asy onCreate ka andr nhi lakta
         * to hmy blank screen show krya ga q ka hmy to run time ka bd hi ak fragment load
         * chaya asye laya hm na ya fragment onCreate ka andr load krwya ha  */
        getSupportFragmentManager().beginTransaction().replace(R.id.container_Layout, new PakNews()).commit();


        /*Bottom Navigation ka icon pr jb click ho ga to tb hi hmy different fragments
         * view kr paya ga.*/
        bottomNavigation.setOnItemSelectedListener(item -> {

            int data = item.getItemId();

            switch (data) {
                case R.id.pak_news:
                    Toast.makeText(MainActivity.this, "Pak News", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_Layout, new PakNews()).commit();
                    break;
                case R.id.world_news:
                    Toast.makeText(MainActivity.this, "World News", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_Layout, new WorldNews()).commit();
                    break;
                case R.id.sports_news:
                    Toast.makeText(MainActivity.this, "Sports News", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_Layout, new SportsNews()).commit();
                    break;
                case R.id.Islam:
                    Toast.makeText(MainActivity.this, "Islamic News", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_Layout, new IslamicNews()).commit();
                    break;
                default:
                    Toast.makeText(MainActivity.this, "Setting", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_Layout, new ProfileSetting()).commit();

            }
            return true;
        });

    }
    @Override
    public void onBackPressed() {
        if (webView23.canGoBack()){
            webView23.goBack();
        }else{
            super.onBackPressed();
        }
    }
}