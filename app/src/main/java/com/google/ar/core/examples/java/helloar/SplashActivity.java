package com.google.ar.core.examples.java.helloar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 3000);

    }

    private class splashhandler implements Runnable {
        public void run(){
            // get email from shared preferences
            SharedPreferences prefs = getSharedPreferences("application", MODE_PRIVATE);
            String email = prefs.getString("email", "");
            Intent intent;
            if (email.equals("")) {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }
            else {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            }
            startActivity(intent);
            SplashActivity.this.finish();
        }
    }

    @Override
    public void onBackPressed() {
    }

}