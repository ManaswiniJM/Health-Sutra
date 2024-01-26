package com.example.login1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login1.products.MyCartAdapter;
import com.example.login1.products.MyCartModel;
import com.example.login1.products.placed_order;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class frag_cart extends Fragment {

    RecyclerView recyclerView;
    List<MyCartModel> cartModelList;
    MyCartAdapter cartAdapter;
    Button confirm;

    TextView overTotalAmount;

    FirebaseFirestore db;
    FirebaseAuth auth;


    public frag_cart(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        cartModelList = new ArrayList<>();

        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_frag_cart, container, false);

        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        recyclerView=root.findViewById(R.id.recycle_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        confirm=root.findViewById(R.id.confirm_now);
        overTotalAmount=root.findViewById(R.id.textView16);
      /*  LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));
*/
        cartModelList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getActivity(),cartModelList);



        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            cartModelList.clear();
                            for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){

                                String documentId=documentSnapshot.getId();

                                MyCartModel cartModel=documentSnapshot.toObject(MyCartModel.class);

                                cartModel.setDocumentId(documentId);
                                cartModelList.add(cartModel);

                            }
                            calculateTotalAmount(cartModelList);
                            cartAdapter.notifyDataSetChanged();
                        }
                    }
                });
        recyclerView.setAdapter(cartAdapter);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartModelList.isEmpty()) {
                    Toast.makeText(getActivity(), "Please add items to the cart", Toast.LENGTH_SHORT).show();
                } else {
                Intent intent=new Intent(getContext(), placed_order.class);
                intent.putExtra("itemList", (Serializable)cartModelList);
                startActivity(intent);
            }}
        });


        return root;
    }

    private void calculateTotalAmount(List<MyCartModel> cartModelList) {

        int totalAmount=0;
        for(MyCartModel myCartModel:cartModelList){
            totalAmount+=myCartModel.getTotalPrice();
            overTotalAmount.setText("Total Bill  "+totalAmount+" Rs.");

        }



    }
   /* public BroadcastReceiver mMessageReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill=intent.getIntExtra("totalAmount",00);
            overTotalAmount.setText("Total Bill  "+totalBill+" Rs.");
        }
    };*/
}