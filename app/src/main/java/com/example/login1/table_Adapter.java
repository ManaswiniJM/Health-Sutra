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

public class table_Adapter extends RecyclerView.Adapter<table_Adapter.View_holder>{

    private table_model[] table_list;
    public  table_Adapter(table_model[] table_list) {
        this.table_list = table_list;
    }


    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());

        View list_items=layoutInflater.inflate(R.layout.herb_list_items,null);

        table_Adapter.View_holder view_holder= new View_holder(list_items);

        return view_holder;
    }

    @Override
    public void onBindViewHolder( View_holder holder, int position) {
        table_model tab_model=table_list[position];
       holder.my_img.setImageResource(tab_model.getImage());
        holder.txt_pname.setText(tab_model.getPlant_Name());
        holder.txt_des.setText(tab_model.getP_Des());
        holder.txt_sans.setText(tab_model.getP_sans());
        holder.txt_ver.setText(tab_model.getP_ver());
        holder.txt_bot.setText(tab_model.getP_bot());
        holder.txt_fam.setText(tab_model.getP_fam());
        holder.txt_part.setText(tab_model.getP_part());

        //P_sans,P_ver,P_bot,P_fam,P_part

        holder.main_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),table2.class);
                intent.putExtra("Image",tab_model.getImage());
                intent.putExtra("Plant_Name",tab_model.getPlant_Name());
                intent.putExtra("P_des",tab_model.getP_Des());
                intent.putExtra("P_sans",tab_model.getP_sans());
                intent.putExtra("P_ver",tab_model.getP_ver());
                intent.putExtra("P_bot",tab_model.getP_bot());
                intent.putExtra("P_fam",tab_model.getP_fam());
                intent.putExtra("P_part",tab_model.getP_part());

                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return table_list.length;
    }

    public class View_holder extends RecyclerView.ViewHolder {
        ImageView my_img;
        TextView txt_pname, txt_des,txt_sans,txt_ver,txt_bot,txt_fam,txt_part;
        CardView main_card;

        public View_holder(@NonNull View itemView) {
            super(itemView);
            txt_pname = itemView.findViewById(R.id.plname);
            my_img = itemView.findViewById(R.id.img1);
            txt_des = itemView.findViewById(R.id.tabdes);
            txt_sans = itemView.findViewById(R.id.sansname);
            txt_ver = itemView.findViewById(R.id.vername);
            txt_bot = itemView.findViewById(R.id.botname);
            txt_fam = itemView.findViewById(R.id.famname);
            txt_part = itemView.findViewById(R.id.part);

            main_card = itemView.findViewById(R.id.card);

        }
    }
}
