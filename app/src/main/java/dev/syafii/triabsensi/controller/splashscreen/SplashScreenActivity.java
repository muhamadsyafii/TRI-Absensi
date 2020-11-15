package dev.syafii.triabsensi.controller.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import dev.syafii.triabsensi.R;
import dev.syafii.triabsensi.controller.home.MainActivity;
import dev.syafii.triabsensi.controller.intro.BoardingActivity;
import dev.syafii.triabsensi.controller.login.LoginActivity;
import dev.syafii.triabsensi.utils.ActivityUtils;
import dev.syafii.triabsensi.utils.PrefUtils;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        boolean isFirstInstall = PrefUtils.with(this).getIsFirstInstall();
        boolean isLogin = PrefUtils.with(this).isLogin();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (isFirstInstall) {
                    if (isLogin) {
                        ActivityUtils.openActivity(SplashScreenActivity.this, MainActivity.class);
                        finish();
                    } else {
                        ActivityUtils.openActivity(SplashScreenActivity.this, LoginActivity.class);
                        finish();
                    }
                } else {
                    ActivityUtils.openActivity(SplashScreenActivity.this, BoardingActivity.class);
                    finish();
                }
            }
        }, 2000);
    }
}