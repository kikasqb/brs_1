package com.example.pccuong.appbook.View.WelcomeScreen;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.example.pccuong.appbook.R;
import com.example.pccuong.appbook.View.HomePage.HomePageActivity;

/**
 * Created by PCCuong on 11/29/2016.
 */

public class WelcomeScreenActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chao_layout);
         Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                Thread.sleep(3000);
                }catch (Exception e){

                }finally {
                    Intent iHomePage = new Intent(WelcomeScreenActivity.this, HomePageActivity.class);
                    startActivity(iHomePage);
                }
            }
        });
         thread.start();
    }
}
