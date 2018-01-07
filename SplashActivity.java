package com.example.kang.playground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by kang on 2018. 1. 4..
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(1500);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
