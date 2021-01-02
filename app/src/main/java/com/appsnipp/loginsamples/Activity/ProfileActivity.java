package com.appsnipp.loginsamples.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.appsnipp.loginsamples.Data.DatabaseManager;
import com.appsnipp.loginsamples.R;

public class ProfileActivity extends Fragment {
    DatabaseManager db  ;
    Context thiscontext;
    TextView email,name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        thiscontext = container.getContext();
        email = view.findViewById(R.id.user_email);
        name = view.findViewById(R.id.user_namme);
        db = new DatabaseManager(thiscontext);

        String names = GetSessionUser();


        Cursor cursor = db.Profile_User(names);
        if(cursor.getCount()!=0){
            if(cursor.moveToFirst()){
                name.setText(cursor.getString(1));
                email.setText(cursor.getString(2));
            }
        }
        return view;
    }
    public  String  GetSessionUser(){

        SharedPreferences sharedPreferences = this.thiscontext.getSharedPreferences("user_check", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user_email","");
        return name;
    }


}

