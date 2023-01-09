package com.example.madchef.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madchef.Models.Recipe;
import com.example.madchef.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{
    Context context;
    List<Recipe> list;

    //constructor
    public RandomRecipeAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.RecipePostTitle.setText(list.get(position).title);
        holder.RecipePostTitle.setSelected(true);
        //holder.RecipeFavourite.setText(list.get(position).aggregateLikes+" Favourite");
        //holder.RecipeServing.setText(list.get(position).servings+ " Servings");
        holder.RecipeDuration.setText(list.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.RecipeImage);
    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}



class RandomRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView random_list_container;
    TextView RecipePostTitle;
    ImageView RecipeImage;
    TextView RecipeDuration, RecipeServing, RecipeFavourite;


    //constructor
    public RandomRecipeViewHolder(@NonNull View itemView) {

        super(itemView);
        random_list_container = itemView.findViewById(R.id.random_list_container);
        RecipePostTitle = itemView.findViewById(R.id.RecipePostTitle);
        RecipeImage = itemView.findViewById(R.id.RecipeImage);
        RecipeDuration = itemView.findViewById(R.id.RecipeDuration);
        //RecipeServing = itemView.findViewById(R.id.RecipeServing);
        //RecipeFavourite = itemView.findViewById(R.id.RecipeFavourite);

    }
}
