package com.example.engineerexercise001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.btn_create_account);
        button.setOnClickListener(v -> {
            // your handler code here
            Intent moveToActivity = new Intent();
            moveToActivity.setClass(MainActivity.this,
                    LoginActivity.class);
            startActivity(moveToActivity);
        });
    }



}