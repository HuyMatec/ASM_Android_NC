package com.example.asm_android_nc.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asm_android_nc.R;

public class MainActivity extends AppCompatActivity {
    ImageView ivHome, gif_view, gif_android;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivHome = findViewById(R.id.ivHome);
        gif_view = findViewById(R.id.gif_view);
        gif_android = findViewById(R.id.gif_android);

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Glide.with(this).load(R.drawable.android_tutorial).into(gif_view);
        Glide.with(this).load(R.drawable.gif_android).into(gif_android);
    }
}