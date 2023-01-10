package com.example.madchef;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AddMeal {
    @PrimaryKey(autoGenerate = true)
    public int mNoteID;
    public String mNote;
    @NonNull
    public String mDate;
    @NonNull
    public String mMood;
    @NonNull
    public boolean mDayNight;
    public AddMeal(@NonNull String date, @NonNull String mood, @NonNull boolean
            dayNight, String note) {
        this.mDate = date;
        this.mMood = mood;
        this.mDayNight = dayNight;
        this.mNote = note;
    }
    public String getmDate(){return this.mDate.toString();}
    public String getmMood(){return this.mMood;}
    public boolean getmDayNight(){return this.mDayNight;}
    public String getmNote(){return this.mNote;}
}
