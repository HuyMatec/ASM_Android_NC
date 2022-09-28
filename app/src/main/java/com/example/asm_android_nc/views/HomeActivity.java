package com.example.asm_android_nc.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.asm_android_nc.R;

public class HomeActivity extends AppCompatActivity {
    TextView tvLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvLogout = findViewById(R.id.tvLogout);

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("LOGIN_STATUS",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("isLoggedIn");
                editor.remove("id");
                editor.remove("role");
                editor.remove("email");
                editor.commit();
                Intent homeIntent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}