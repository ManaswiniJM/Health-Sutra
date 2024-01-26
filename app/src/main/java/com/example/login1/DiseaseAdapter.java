package com.example.login1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login1.products.ProductAdapter;
import com.example.login1.products.ProductModel;
import com.example.login1.products.displayProduct;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.ViewHolder> {

    private Context context;
    private List<Disease>diseaseList;

    public DiseaseAdapter(Context context, List<Disease> diseaseList) {
        this.context = context;
        this.diseaseList = diseaseList;
    }

    @NonNull
    @Override
    public DiseaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseAdapter.ViewHolder holder, int position) {
        holder.name.setText(diseaseList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, display_diag.class);
                intent.putExtra("detail",diseaseList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return diseaseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.listitemstext);
        }
    }
}



