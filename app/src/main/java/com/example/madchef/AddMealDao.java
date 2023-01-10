package com.example.madchef;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AddMealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AddMeal note);
    @Query("DELETE FROM AddMeal")
    void deleteAll();
    @Query("SELECT * FROM AddMeal ORDER BY mDate ASC")
    LiveData<List<AddMeal>> getAscendingNote();
    @Delete
    void delete(AddMeal note);
}
