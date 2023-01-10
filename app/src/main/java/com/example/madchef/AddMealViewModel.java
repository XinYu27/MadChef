package com.example.madchef;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

// 5. Created a class called MoodNoteViewModel that gets the Application as a parameter and
// extends AndroidViewModel.
public class AddMealViewModel extends AndroidViewModel {
    //Added a private member variable to hold a reference to the repository.
    private AddMealRepository mRepository;
    private final LiveData<List<AddMeal>> mAllNotes;
    //Implemented a constructor that creates the MoodNoteRepository.
    //In the constructor, initialized the allNotes LiveData using the repository.
    public AddMealViewModel(Application application) {
        super(application);
        mRepository = new AddMealRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }
    //Added a getAllNotes() method to return a cached list of words.
    LiveData<List<AddMeal>> getAllNotes() { return mAllNotes; }
    // Created a wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is encapsulated from the UI.
    public void insert(AddMeal note) { mRepository.insert(note); }
    public void deleteAll() { mRepository.deleteAll(); }
    public void delete(AddMeal note) { mRepository.deletemeal(note);}
}
