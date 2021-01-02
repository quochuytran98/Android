package com.appsnipp.loginsamples.Activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.loginsamples.Adapter.ProductAdapter;
import com.appsnipp.loginsamples.Data.DatabaseManager;
import com.appsnipp.loginsamples.Model.Product;
import com.appsnipp.loginsamples.R;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    RecyclerView prodItemRecycler;
    Context context;
    ArrayList<Product> productsList;
    DatabaseManager db = new DatabaseManager(context);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        context = container.getContext();

        ImageSlider imageSlider = view.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.galaxy1));
        slideModels.add(new SlideModel(R.drawable.galaxy2));
        slideModels.add(new SlideModel(R.drawable.galaxy3));
        slideModels.add(new SlideModel(R.drawable.galaxy4));
        imageSlider.setImageList(slideModels, true);

        prodItemRecycler = (RecyclerView) view.findViewById(R.id.product_recycler);
        ProductAdapter productAdapter = new ProductAdapter(context, productsList);
        prodItemRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        prodItemRecycler.setAdapter(productAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productsList = new ArrayList<>();
        Cursor cursor = new DatabaseManager(getContext()).getAllProducts();
        if (cursor != null && cursor.moveToFirst()) {
            do{
                Product product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getString(6));
                productsList.add(product);
            }while(cursor.moveToNext());
        }
        cursor.close();

    }
}
