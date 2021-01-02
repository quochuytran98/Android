package com.appsnipp.loginsamples.Animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.appsnipp.loginsamples.Activity.MainActivity;
import com.appsnipp.loginsamples.R;

public class LoadAppAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_app_acitivity);
        Thread bamgio=new Thread(){
            public void run()
            {
                try {
                    sleep(5000);
                }catch (Exception e) {

                }
                finally
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);;
                    startActivity(intent);
                }
            }
        };
        bamgio.start();
    }
    protected void onPause(){
        super.onPause();
        finish();
    }
}