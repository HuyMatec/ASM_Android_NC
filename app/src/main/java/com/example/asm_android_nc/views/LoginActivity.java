package com.example.asm_android_nc.views;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.asm_android_nc.R;
import com.example.asm_android_nc.models.AppUser;
import com.example.asm_android_nc.services.UserService;

public class LoginActivity extends AppCompatActivity {
    EditText edtLoginEmail, edtLoginPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // kiểm tra trạng thái login
        readLogin();
        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtLoginEmail.getText().toString();
                String password = edtLoginPassword.getText().toString();
                Intent intent = new Intent(LoginActivity.this, UserService.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.setAction(UserService.ACTION_LOGIN);
                startService(intent);

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter loginFilter = new IntentFilter(UserService.USER_SERVICE_EVENT);
        LocalBroadcastManager.getInstance(this).
                registerReceiver(loginReceiver, loginFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).
                unregisterReceiver(loginReceiver);
    }

    private BroadcastReceiver loginReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            AppUser appUser = (AppUser) intent.getSerializableExtra("appUser");
            if (appUser != null) {
                // lưu trạng thái đăng nhập vào bộ nhớ
                writeLogin(appUser);
                Toast.makeText(context, "Đăng nhập thành công",
                        Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(LoginActivity.this, LoadingActivity.class);
                startActivity(intent1);
                finish();
            }
            else {
                Toast.makeText(context, "Đăng nhập không thành công",
                        Toast.LENGTH_LONG).show();
            }
        }
    };

    //nhớ người dùng
    private void writeLogin (AppUser appUser){
        SharedPreferences preferences = getSharedPreferences("LOGIN_STATUS",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn",true);
        editor.putInt("id",appUser.getId());
        editor.putInt("role",appUser.getRole());
        editor.putString("email",appUser.getEmail());
        editor.commit();
    }

    private void readLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences("LOGIN_STATUS",MODE_PRIVATE);
        Boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false);
        if(isLoggedIn){
            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        }
    }
}