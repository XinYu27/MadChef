package com.example.madchef.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madchef.Models.PostObject;
import com.example.madchef.R;

import java.util.List;

public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.ViewHolder>{

    private Context context;
    private List<PostObject> mPosts;

    private TextView TVPosts;

    public MyPostAdapter(Context context, List<PostObject> mPosts) {
        this.context = context;
        this.mPosts = mPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.individual_post,parent,false);
        return new MyPostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostObject postObject = mPosts.get(position);
        Glide.with(context).load(postObject.getPostimage()).into(holder.post_image);
        TVPosts.setText(postObject.getCaption());

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView post_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TVPosts = itemView.findViewById(R.id.TVPosts);
            post_image = itemView.findViewById(R.id.IVPosts);
        }
    }
}
