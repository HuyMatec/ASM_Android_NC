package com.example.asm_android_nc.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_android_nc.R;
import com.example.asm_android_nc.models.AppUser;
import com.example.asm_android_nc.services.UserService;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtRegisterName, edtRegisterEmail, edtRegisterPassword;
    private Button btnReset, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtRegisterName = findViewById(R.id.edtRegisterName);
        edtRegisterEmail = findViewById(R.id.edtRegisterEmail);
        edtRegisterPassword = findViewById(R.id.edtRegisterPassword);
        btnReset = findViewById(R.id.btnReset);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtRegisterName.getText().toString();
                String email = edtRegisterEmail.getText().toString();
                String password = edtRegisterPassword.getText().toString();
                int role = 1; //spinner

                Intent intent = new Intent(RegisterActivity.this, UserService.class);
                intent.setAction(UserService.ACTION_REGISTER);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("password", password);
                intent.putExtra("role", role);
                startService(intent);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtRegisterName.setText("");
                edtRegisterEmail.setText("");
                edtRegisterPassword.setText("");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(UserService.USER_SERVICE_EVENT);
        LocalBroadcastManager.getInstance(this).
                registerReceiver(registerReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).
                unregisterReceiver(registerReceiver);
    }

    private BroadcastReceiver registerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Boolean result = intent.getBooleanExtra("result",false);
            if (result){
                Toast.makeText(RegisterActivity.this, "Đăng ký thành công",
                        Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent1);
                finish();
            }
            else {
                Toast.makeText(RegisterActivity.this, "Đăng ký không thành công",
                        Toast.LENGTH_LONG).show();
            }
        }
    };
}