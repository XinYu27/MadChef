package com.example.madchef;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class AddMealViewHolder extends RecyclerView.ViewHolder {
    private final TextView noteDate;
    private final TextView noteContent;
    private final ImageView noteMood;
    public final ImageButton set;

    // private final ImageView noteDayNight;
    private AddMealViewHolder(View itemView) {
        super(itemView);
        noteDate = itemView.findViewById(R.id.textView11);
        noteContent = itemView.findViewById(R.id.textView15);
        noteMood = itemView.findViewById(R.id.imageView7);
        set = itemView.findViewById(R.id.imageButton);
        //noteDayNight = itemView.findViewById(R.id.IVDayNight);
    }
    public void bind(String date, String mood, boolean daynight, String note) {
        noteDate.setText(date);
        noteContent.setText(note);
        Uri uri = Uri.parse(mood);
        noteMood.setImageURI(uri);
        /*if (mood == 1)

            noteMood.setImageResource(R.drawable.ic_baseline_sentiment_very_satisfied_24);
        else if (mood == 2)

            noteMood.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_24);
        else

            noteMood.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
        if (daynight == true)
            noteDayNight.setImageResource(R.drawable.ic_baseline_wb_sunny_24);
        else
            noteDayNight.setImageResource(R.drawable.ic_baseline_shield_moon_24);*/
    }
    static AddMealViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.individual_item_view, parent, false);
        return new AddMealViewHolder(view);
    }
}
