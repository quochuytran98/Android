package com.appsnipp.loginsamples.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.appsnipp.loginsamples.DAO.ProductDAO;
import com.appsnipp.loginsamples.Data.DatabaseManager;
import com.appsnipp.loginsamples.Model.Product;
import com.appsnipp.loginsamples.R;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    Integer id;
    TextView txtid, price,description;
    String name;
    Context context;
    private ArrayList<Product> ProductList;

    DatabaseManager db = new DatabaseManager(this);
    ProductDAO productDAO = new ProductDAO(context,db);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        try {
            Intent intent = getIntent();
            txtid = (TextView)findViewById(R.id.TxtName);
            price = (TextView)findViewById(R.id.Price);
            description = (TextView)findViewById(R.id.description);

            Bundle bundle = intent.getExtras();
            name = bundle.getString("Name");
            id = bundle.getInt("ID",0);
            //txtid.setText(String.valueOf(id));
            Cursor cursor = db.GetDetailsProduct(name);

            if(cursor.getCount()!=0){
                if(cursor.moveToFirst()){
                    String txt = "Giá: " + cursor.getString(2) + " vnđ";
                    txtid.setText(name);
                    price.setText(txt);
                    description.setText(cursor.getString(6));

                }
            }
        }catch (Exception e){
            Toast.makeText(this,"sss",Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public  String  GetSessionUser(){

        SharedPreferences sharedPreferences = this.context.getSharedPreferences("user_check", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user_email","");
        return name;
    }
}