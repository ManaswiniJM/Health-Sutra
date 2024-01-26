package com.example.login1;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Data_Adapter_herb extends RecyclerView.Adapter<Data_Adapter_herb.View_holder> {

    private Data_Model_herb[] data_list;

    public  Data_Adapter_herb(Data_Model_herb[] data_list) {
        this.data_list = data_list;
    }

    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());

        View list_items=layoutInflater.inflate(R.layout.herb_list_items,null);

        View_holder view_holder=new View_holder(list_items);

        return view_holder;
    }
    @Override
    public void onBindViewHolder(@NonNull View_holder holder, int position) {

        Data_Model_herb data_model_herb=data_list[position];
        holder.my_img.setImageResource(data_model_herb.getImage());
        holder.txt_pname.setText(data_model_herb.getPlant_Name());
        holder.txt_des.setText(data_model_herb.getP_Des());

        holder.main_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),herb2.class);
                intent.putExtra("Plant_Name",data_model_herb.getPlant_Name());
                intent.putExtra("P_des",data_model_herb.getP_Des());
                intent.putExtra("Image",data_model_herb.getImage());

                view.getContext().startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return data_list.length;
    }

    public class View_holder extends RecyclerView.ViewHolder {

        ImageView my_img;
        TextView txt_pname, txt_des;
        CardView main_card;

        public View_holder(@NonNull View itemView) {
            super(itemView);
            txt_pname = itemView.findViewById(R.id.pname);
            my_img = itemView.findViewById(R.id.img);
            txt_des = itemView.findViewById(R.id.des);
            main_card = itemView.findViewById(R.id.card);

        }
    }



}
