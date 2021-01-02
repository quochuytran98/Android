package com.appsnipp.loginsamples.Activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.loginsamples.Adapter.HDDRecycleViewAdapter;

import com.appsnipp.loginsamples.Adapter.RecyclerViewAdapter;
import com.appsnipp.loginsamples.Data.DatabaseManager;
import com.appsnipp.loginsamples.Model.Product;
import com.appsnipp.loginsamples.R;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class HDDFragment extends Fragment {

    private RecyclerView myRecyclerView;
    Context context;
    private ArrayList<Product> ProductList;
    DatabaseManager  db = new DatabaseManager(context);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_hdd, container, false);
        context = container.getContext();

        ImageSlider imageSlider = view.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.galaxy1));
        slideModels.add(new SlideModel(R.drawable.galaxy2));
        slideModels.add(new SlideModel(R.drawable.galaxy3));
        slideModels.add(new SlideModel(R.drawable.galaxy4));
        imageSlider.setImageList(slideModels, true);

        myRecyclerView =  view.findViewById(R.id.hdd_recyclerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context,ProductList);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProductList = new ArrayList<>();

        Cursor cursor = new DatabaseManager(getContext()).GetHDD();

        if (cursor != null & cursor.moveToFirst()){
            do {
                Product product = new Product(cursor.getString(1), cursor.getString(2));

                ProductList.add(product);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
    }


}
