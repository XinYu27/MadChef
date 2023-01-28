package com.example.madchef.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madchef.Models.ExtendedIngredient;
import com.example.madchef.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder>{

    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_ingradient_cardview, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.ingredientname.setText(list.get(position).name);
        holder.ingredientname.setSelected(true);
        holder.ingredientquantity.setText(list.get(position).original);
        holder.ingredientquantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/" + list.get(position).image).into(holder.ingredientimage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class IngredientsViewHolder extends RecyclerView.ViewHolder{

    TextView ingredientquantity, ingredientname;
    ImageView ingredientimage;

    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        ingredientname = itemView.findViewById(R.id.ingredientname);
        ingredientquantity = itemView.findViewById(R.id.ingredientquatity);
        ingredientimage = itemView.findViewById(R.id.ingredientimage);
    }
}
