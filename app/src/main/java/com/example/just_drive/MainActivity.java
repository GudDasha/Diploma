package com.example.just_drive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread welcome = new Thread(){
            @Override
            public void run(){
                try{
                    super.run();
                    sleep(4000);
                } catch (Exception e){}
                finally {
                    Intent i = new Intent(MainActivity.this, Authorization.class);
                    startActivity(i);
                    finish();


                }

            }

        };
        welcome.start();
    }
}