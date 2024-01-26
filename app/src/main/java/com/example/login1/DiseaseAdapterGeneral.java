package com.example.login1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DiseaseAdapterGeneral extends RecyclerView.Adapter<DiseaseAdapterGeneral.ViewHolder> {

    private Context context;
    private List<DiseaseAdmin>diseaseList;

    public DiseaseAdapterGeneral(Context context, List<DiseaseAdmin> diseaseList) {
        this.context = context;
        this.diseaseList = diseaseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseAdapterGeneral.ViewHolder holder, int position) {
        DiseaseAdmin disease = diseaseList.get(position);
        holder.name.setText(disease.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, display_general_admin.class);
                intent.putExtra("detail", disease);
                intent.putExtra("position", position); // Pass the position of the clicked item
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
            name=itemView.findViewById(R.id.listitemstextAdm);
        }
    }
}



