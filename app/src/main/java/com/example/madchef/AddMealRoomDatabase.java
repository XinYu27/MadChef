package com.example.madchef;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 3. Create a Room Database - a database abstraction layer on top of sqlite
@Database(entities = {AddMeal.class}, version = 1, exportSchema = false)
public abstract class AddMealRoomDatabase extends RoomDatabase {
    public abstract AddMealDao noteDao();
    private static volatile AddMealRoomDatabase INSTANCE;
    // We've created an ExecutorService with a fixed thread pool that you will use to run database
    // operations asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    // getDatabase returns the singleton.
    // It'll create the database the first time it's accessed, using Room's database builder to
    // create a RoomDatabase object in the application context from the NoteRoomDatabase class
    // and names it "note_database".
    static AddMealRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AddMealRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                            AddMealRoomDatabase.class, "note_database")
                                    .addCallback(sRoomDatabaseCallback)
                                    .build();
                }
            }
        }
        return INSTANCE;
    }
    private static Callback sRoomDatabaseCallback = new
            Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    // If you want to keep data through app restarts,
                    // comment out the following block
                    databaseWriteExecutor.execute(() -> {
                        // Populate the database in the background.
                        // If you want to start with more notes, just add them.
                        AddMealDao dao = INSTANCE.noteDao();
                        dao.deleteAll();
                        // insert a default instance

                        AddMeal note = new AddMeal("Wed, Jan 5th", "android.resource://com.example.addmeal/drawable/img_1", false, "Fried Chicken");
                        dao.insert(note);
                    });
                }
            };
}
