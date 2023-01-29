package com.example.madchef;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutUser extends Fragment {
    RecyclerView recyclerView;
    MyPostAdapter myPostAdapter;
    List<PostObject> postObjectList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutUser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutUser.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutUser newInstance(String param1, String param2) {
        AboutUser fragment = new AboutUser();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_user, container, false);
        TextView postcount = view.findViewById(R.id.postcount);
        // Inflate the layout for this fragment
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(linearLayoutManager);
        postObjectList = new ArrayList<>();
        myPostAdapter = new MyPostAdapter(getContext(),postObjectList);
        recyclerView.setAdapter(myPostAdapter);
        myPosts();
        //postcount.setText("myPostAdapter.getItemCount()");
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
                    }
                }
                Collections.reverse(postObjectList);
                myPostAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}