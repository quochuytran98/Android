package com.appsnipp.loginsamples.Activity;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appsnipp.loginsamples.DAO.UsersDAO;
import com.appsnipp.loginsamples.Data.DatabaseManager;
import com.appsnipp.loginsamples.R;
import com.appsnipp.loginsamples.Model.Users;

public class RegisterAcitivity extends AppCompatActivity {

    private Button btnRegister;
    private TextView login;
    private  EditText Remail;
    private  EditText Rname;
    private  EditText Rpassword;

    DatabaseManager db = new DatabaseManager(RegisterAcitivity.this);
    private UsersDAO user = new UsersDAO(db);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        login = (TextView)findViewById(R.id.login);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        Remail = (EditText)findViewById(R.id.email);
        Rpassword = (EditText)findViewById(R.id.password);
        Rname = (EditText)findViewById(R.id.name);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Rname.getText().toString().equals("")){
                    Toast.makeText(RegisterAcitivity.this, "Vui lòng nhập tên của bạn", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Remail.getText().toString().equals("")){
                    Toast.makeText(RegisterAcitivity.this, "Vui lòng nhập Email của bạn", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Rpassword.getText().toString().equals("")){
                    Toast.makeText(RegisterAcitivity.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean checkemail = db.checkmail(Remail.getText().toString());
                if(checkemail == true){
                    Users users = CreateUser();
                    user.addUsers(users);
                    Toast.makeText(RegisterAcitivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(RegisterAcitivity.this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });
    }
    public void openActivity1(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private Users CreateUser(){
        String name = Rname.getText().toString();
        String password = Rpassword.getText().toString();
        String email = Remail.getText().toString();
        Users users = new Users(name,email,password);
        return users;
    }
}
