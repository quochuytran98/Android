package com.appsnipp.loginsamples.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appsnipp.loginsamples.DAO.UsersDAO;
import com.appsnipp.loginsamples.Data.DatabaseManager;
import com.appsnipp.loginsamples.R;

public class LoginActivity extends AppCompatActivity {
    private EditText email ;
    private EditText password ;

    DatabaseManager db = new DatabaseManager(LoginActivity.this);
    private UsersDAO user = new UsersDAO(db);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        password = findViewById(R.id.editTextPassword);
        email = findViewById(R.id.editTextEmail);
        Button btnlogin = findViewById(R.id.cirLoginButton);
        TextView dangkyuser = findViewById(R.id.dangkyuser);



        dangkyuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    openActivity2();

            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = email.getText().toString();
                String pass = password.getText().toString();
                boolean check = user.login(name,pass);
                if(check == true) {
                    SharedPreferences sharedPreferences = getSharedPreferences("user_check", Context.MODE_PRIVATE);
                    SharedPreferences.Editor  editor =sharedPreferences.edit();
                    editor.putString("user_email", name);
                    editor.apply();
                    Login();
                }
                else
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();



            }
        });
    }
    public void openActivity2(){
        Intent intent = new Intent(this, RegisterAcitivity.class);
        startActivity(intent);
    }
    public void Login(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    protected void onPause(){
        super.onPause();
        finish();
    }
}
