package com.example.madchef.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madchef.Listeners.RecipeClickListener;
import com.example.madchef.Models.Recipe;
import com.example.madchef.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{
    Context context;
    List<Recipe> list;
    RecipeClickListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public RandomRecipeAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.insertcarrdview,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.textview_title.setText(list.get(position).title);
        holder.textview_title.setSelected(true);
        holder.textView_timer.setText(list.get(position).readyInMinutes + "MIN");
        Picasso.get().load(list.get(position).image).into(holder.imageview_food);

        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class RandomRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView random_list_container;
    ImageView imageview_food;
    TextView textview_title,textView_timer;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        random_list_container = itemView.findViewById(R.id.random_list_container);
        imageview_food = itemView.findViewById(R.id.imageview_food);
        textview_title = itemView.findViewById(R.id.textview_title);
        textView_timer = itemView.findViewById(R.id.textView_timer);

    }
}
