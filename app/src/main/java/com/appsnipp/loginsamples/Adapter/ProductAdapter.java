package com.appsnipp.loginsamples.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsnipp.loginsamples.Model.Product;
import com.appsnipp.loginsamples.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {
    Context context;
    ArrayList<Product> productsList;

    public ProductAdapter(Context context, ArrayList<Product> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list, parent, false);
        ItemHolder vHolder = new ItemHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        //holder.productImage.setImageResource(productsList.get(position).getImage());
        holder.productName.setText(productsList.get(position).getName());
        holder.productPrice.setText(productsList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        //public ImageView productImage;
        public TextView productName, productPrice;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            //productImage = (ImageView) itemView.findViewById(R.id.productimageview);
            productName = (TextView) itemView.findViewById(R.id.producttextview);
            productPrice = (TextView) itemView.findViewById(R.id.productpricetextview);
        }
    }
}
