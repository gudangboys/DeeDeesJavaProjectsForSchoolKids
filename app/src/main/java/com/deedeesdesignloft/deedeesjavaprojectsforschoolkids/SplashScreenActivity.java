package com.deedeesdesignloft.deedeesjavaprojectsforschoolkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class SplashScreenActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setContentView to be after Full Screen request
        setContentView(R.layout.activity_splash_screen);

        webView = new WebView(this);
        webView.loadUrl("file:///android_asset/school_project_animation_yellow.gif");
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        setContentView(webView);


        //Hide actionbar - these steps will display only your logo without the actionbar
        getSupportActionBar().hide();

        SplashLauncher splashLauncher = new SplashLauncher();
        splashLauncher.start();

    }


    private class SplashLauncher extends Thread {
        private int SLEEP_TIMER = 5;

        public void run() {
            try {
                sleep(1000 * SLEEP_TIMER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent mainActivityIntent = new Intent(SplashScreenActivity.this, IntroActivity.class);
            startActivity(mainActivityIntent);

            SplashScreenActivity.this.finish();
        }
    }

}
