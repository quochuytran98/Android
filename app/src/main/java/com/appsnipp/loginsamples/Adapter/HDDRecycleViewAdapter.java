package com.appsnipp.loginsamples.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.loginsamples.Activity.DetailsActivity;
import com.appsnipp.loginsamples.Model.Product;
import com.appsnipp.loginsamples.R;

import java.util.ArrayList;

public class HDDRecycleViewAdapter extends RecyclerView.Adapter<HDDRecycleViewAdapter.myViewHolder>{

    Context mContex;
    ArrayList<Product> products;

    public HDDRecycleViewAdapter(Context mContex, ArrayList<Product> products) {
        this.mContex = mContex;
        this.products = products;
    }

    @NonNull
    @Override
    public HDDRecycleViewAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View v;
        v = LayoutInflater.from(mContex).inflate(R.layout.item_view,parent,false);
        return new HDDRecycleViewAdapter.myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HDDRecycleViewAdapter.myViewHolder holder, final int position) {
        String price = "Giá: " +products.get(position).getPrice();
        holder.name.setText(products.get(position).getName());
        holder.price.setText(price);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(mContex, products.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContex, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", products.get(position).getId());
                bundle.putString("Name",products.get(position).getName());
                intent.putExtras(bundle);

                // intent.putExtra("Name",products.get(position).getName());
                mContex.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private  TextView price;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.txtTenSanPham);
            price = (TextView)itemView.findViewById(R.id.txtGiaSanPham);
        }
    }
}
