package com.example.madchef;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madchef.Adapters.MyPostAdapter;
import com.example.madchef.Models.PostObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CB_Favourite extends Fragment {
    TextView postcount1;
    int x=0;
    RecyclerView recyclerView;
    MyPostAdapter myPostAdapter;
    List<PostObject> postObjectList;

    public CB_Favourite() {
        // Required empty public constructor
    }

    public static CB_Favourite newInstance(String param1, String param2) {
        CB_Favourite fragment = new CB_Favourite();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c_b__favourite, container, false);

        // Inflate the layout for this fragment
        recyclerView = view.findViewById(R.id.recyclefav);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(linearLayoutManager);
        postObjectList = new ArrayList<>();
        myPostAdapter = new MyPostAdapter(getContext(),postObjectList);
        recyclerView.setAdapter(myPostAdapter);
        //myPosts();
        //postcount1.setText("t");
        //postcount.setText(myPostAdapter.getItemCount());
        return view;
    }

    private void myPosts(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                postObjectList.clear();
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    PostObject postObject = snapshot1.getValue(PostObject.class);
                    if (postObject.getPublisher().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        postObjectList.add(postObject);
                        x++;
                    }
                }
                postcount1.setText(""+x);
                Collections.reverse(postObjectList);
                myPostAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}