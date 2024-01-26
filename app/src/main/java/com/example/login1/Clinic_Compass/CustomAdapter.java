package com.example.login1.Clinic_Compass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login1.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<ListItem> items;
    private ItemClickListener itemClickListener;

    public CustomAdapter(ArrayList<ListItem> items, ItemClickListener itemClickListener) {
        this.items = items;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CustomAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        ListItem item = (ListItem)this.items.get(position);
        holder.imageView.setImageResource(item.getImageResId());
        holder.textView.setText(item.getText());
    }

    public int getItemCount() {
        return this.items.size();
    }

    public void setItems(ArrayList<ListItem> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView)itemView.findViewById(R.id.imageclinic);
            this.textView = (TextView)itemView.findViewById(R.id.textView_clinic);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            int position = this.getAdapterPosition();
            if (position != -1) {
                ListItem item = (ListItem)CustomAdapter.this.items.get(position);
                CustomAdapter.this.itemClickListener.onItemClick(item);
            }

        }
    }
}

