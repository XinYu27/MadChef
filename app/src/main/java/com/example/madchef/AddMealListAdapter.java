package com.example.madchef;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class AddMealListAdapter extends ListAdapter<AddMeal,
        AddMealViewHolder> {
    private MainActivity MA;
    private itemclicked itemclicked;
    private AddMealViewModel mAddMealModel;

    public AddMealListAdapter(@NonNull DiffUtil.ItemCallback<AddMeal>diffCallback) {
        super(diffCallback);
    }
    @Override
    public AddMealViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return AddMealViewHolder.create(parent);
    }
    @Override
    public void onBindViewHolder(AddMealViewHolder holder, int position) {
        AddMeal current = getItem(position);
        int id = position;
        holder.bind(current.getmDate(), current.getmMood(),
                current.getmDayNight(), current.getmNote());
        holder.set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "test"+id ,Toast.LENGTH_LONG).show();
            //itemclicked.deleteclicked(new AddMeal("22","",true,"sd"));
                //MA.mAddMealModel.deleteAll();

            }
        });
    }

    public interface itemclicked{
        void deleteclicked();
    }

    static class NoteDiff extends DiffUtil.ItemCallback<AddMeal> {
        @Override
        public boolean areItemsTheSame(@NonNull AddMeal oldItem, @NonNull
                AddMeal newItem) {
            return oldItem == newItem;
        }
        @Override
        public boolean areContentsTheSame(@NonNull AddMeal oldItem, @NonNull
                AddMeal newItem) {
            return oldItem.getmNote().equals(newItem.getmNote());
        }
    }
}
