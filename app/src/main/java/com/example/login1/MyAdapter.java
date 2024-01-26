package com.example.login1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;


//To collect the user details in admin side
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<UserData> list;

    public MyAdapter(Context context, ArrayList<UserData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
         return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        UserData user=list.get(position);
        holder.name.setText(user.getUsername());
        holder.email.setText(user.getUemail());
        holder.address.setText(user.getUaddress());
        holder.phno.setText(user.getUphone());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView name,email,address,phno;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        name=itemView.findViewById(R.id.username);
        email=itemView.findViewById(R.id.useremail);
        address=itemView.findViewById(R.id.useradd);
        phno=itemView.findViewById(R.id.userphone);

    }
}

}
