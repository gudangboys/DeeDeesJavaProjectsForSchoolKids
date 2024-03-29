package com.deedeesdesignloft.deedeesjavaprojectsandquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Objects;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setContentView to be after Full Screen request
        setContentView(R.layout.activity_splash_screen);

        WebView webView = new WebView(this);
        webView.loadUrl("file:///android_asset/school_project_animation_yellow.gif");
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        setContentView(webView);


        //Hide actionbar - these steps will display only your logo without the actionbar
        Objects.requireNonNull(getSupportActionBar()).hide();

        SplashLauncher splashLauncher = new SplashLauncher();
        splashLauncher.start();

    }


    private class SplashLauncher extends Thread {

        public void run() {
            try {
                int SLEEP_TIMER = 5;
                sleep(1000 * SLEEP_TIMER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent mainActivityIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(mainActivityIntent);

            SplashScreenActivity.this.finish();
        }
    }

}
