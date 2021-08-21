package com.example.assigment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.DeviceAdminService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText ed_user,ed_pass;
    Button btnlogin;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLogin();
        setContentView(R.layout.activity_login);
        ed_user = (EditText)findViewById(R.id.ed_user);
        ed_pass = (EditText)findViewById(R.id.ed_pass);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                LayoutInflater inflater = LoginActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.layout_dialog_thu,null);
                builder.setView(view);
                builder.show();
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = "nhphuoc";
                String pw = "279";
                if(ed_user.getText().length()!=0 && ed_pass.getText().length()!=0){
                    if(ed_user.getText().toString().equals(us) && ed_pass.getText().toString().equals(pw)){
                        if(checkBox.isChecked()) {
                            SharedPreferences.Editor editor = getSharedPreferences("login_status,", MODE_PRIVATE).edit();
                            editor.putBoolean("isloggedIn", true);
                            editor.apply();
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }else {
                    Toast.makeText(LoginActivity.this,"Vui lòng không để trống mục nào",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void checkLogin(){
        SharedPreferences editor = getSharedPreferences("login_status",MODE_PRIVATE);
        boolean login = editor.getBoolean("isloggedIn",false);
        if(login == true){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            return;
        }
    }
}