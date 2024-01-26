package com.example.login1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login1.products.ProductAdapter;
import com.example.login1.products.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class frag_home extends Fragment {

    //recycler view
      RecyclerView productRec,productrec1;

    FirebaseFirestore db;
    //product list

      List<ProductModel> productModelList;
      ProductAdapter productAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.products, container, false);

        db=FirebaseFirestore.getInstance();

        //connect to recycler view in xml
        productRec=root.findViewById(R.id.pro_rec);

        //pro itesm
        //set recycler view 1 move horizontally
        productRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        productModelList=new ArrayList<ProductModel>();
        productAdapter=new ProductAdapter(getActivity(),productModelList);
        productRec.setAdapter(productAdapter);

        //will contain same items as recycler 1:
        //to contain different things copy from productRec.setLayoutAddapter till end of db.collection  And in firebase firestore there should be collection for that category
        //productrec1.setAdapter(productAdapter);


        db.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){

                                ProductModel productModel=document.toObject(ProductModel.class);
                                productModelList.add(productModel);
                                productAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        return root;
    }
}