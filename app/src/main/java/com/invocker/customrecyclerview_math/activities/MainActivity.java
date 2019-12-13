package com.invocker.customrecyclerview_math.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.invocker.customrecyclerview_math.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goPlay(View view) {
        Intent intent =new Intent(this,PlayActivity.class);
        startActivity(intent);
    }
}
