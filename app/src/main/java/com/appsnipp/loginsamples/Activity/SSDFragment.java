package com.appsnipp.loginsamples.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appsnipp.loginsamples.Adapter.RecyclerViewAdapter;
import com.appsnipp.loginsamples.DAO.CategoryDAO;
import com.appsnipp.loginsamples.DAO.UsersDAO;
import com.appsnipp.loginsamples.Data.DatabaseManager;
import com.appsnipp.loginsamples.Model.Category;
import com.appsnipp.loginsamples.Model.Product;
import com.appsnipp.loginsamples.Model.Users;
import com.appsnipp.loginsamples.R;

import java.util.ArrayList;
import java.util.List;

public class SSDFragment extends Fragment {
    private RecyclerView myRecyclerView;
    Context context;
    private ArrayList<Product> ProductList;

    DatabaseManager  db = new DatabaseManager(context);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_sdd, container, false);
        context = container.getContext();

        myRecyclerView =  view.findViewById(R.id.ssd_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context,ProductList);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProductList = new ArrayList<>();

        Cursor cursor = new DatabaseManager(getContext()).GetSSD();

        if (cursor != null & cursor.moveToFirst()){
            do {
                Product product = new Product(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getString(6));

                ProductList.add(product);
            }
            while (cursor.moveToNext());
        }
        cursor.close();



    }



}
