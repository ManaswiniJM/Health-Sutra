package com.example.login1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.login1.R;
import com.example.login1.products.ProductAdapter;
import com.example.login1.products.ProductModel;
import com.example.login1.products.displayProduct;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;

import java.util.List;


public class ProductAdapterAdmin extends RecyclerView.Adapter<ProductAdapterAdmin.ViewHolder> {

    private Context context;
    private List<ProductModelAdmin> productModelList;

    public ProductAdapterAdmin(Context context, List<ProductModelAdmin> productModelList) {
        this.context = context;
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_admin,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapterAdmin.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(productModelList.get(position).getImg_url()).into(holder.admprodImg);
        holder.admname.setText(productModelList.get(position).getName());
        holder.admdescription.setText(productModelList.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, displayProductAdmin.class);
                intent.putExtra("detail",productModelList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView admprodImg;
        TextView admname, admdescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            admprodImg = itemView.findViewById(R.id.prod_img_adm);
            admname = itemView.findViewById(R.id.pro_name_adm);
            admdescription = itemView.findViewById(R.id.pro_des_adm);
            //rating=itemView.findViewById(R.id.pro_ratings);
            //price=itemView.findViewById(R.id.price);
        }
    }
    public void updateProductList(List<ProductModelAdmin> productList) {
        productModelList = productList;
        notifyDataSetChanged();
    }

}