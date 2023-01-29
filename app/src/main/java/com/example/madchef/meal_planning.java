package com.example.madchef;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class meal_planning extends AppCompatActivity implements AddMealListAdapter.itemclicked{
    public static final int NEW_NOTE_ACTIVITY_REQUEST_CODE = 1;
    public AddMealViewModel mAddMealModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_meal_planning);

        RecyclerView recyclerView = findViewById(R.id.RVAddMeal);
        final AddMealListAdapter adapter = new AddMealListAdapter(new
                AddMealListAdapter.NoteDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mAddMealModel = new
                ViewModelProvider(this).get(AddMealViewModel.class);
        mAddMealModel.getAllNotes().observe(this, notes -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(notes);
        });
        Button fab = findViewById(R.id.button4);
        fab.setOnClickListener( view -> {
            //AddMeal n = new AddMeal("Thu, Jan 12th","",true,"dgfg");
            //delete(n);
            Toast.makeText(this, "nice", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(meal_planning.this, AddMealActivity.class);
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE);
        });
        ImageButton clear = findViewById(R.id.imageButton2);
        clear.setOnClickListener(view -> {
            delete();
        });

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode ==
                RESULT_OK) {
            int mood =0;
            // Integer.parseInt(data.getStringExtra(AddMealActivity.ExtraMood));
            boolean daynight = true;
            //Boolean.parseBoolean(data.getStringExtra(AddMealActivity.ExtraDayNight));
            AddMeal note = new
                    AddMeal(data.getStringExtra(AddMealActivity.ExtraDate), data.getStringExtra(AddMealActivity.ExtraUri),
                    daynight, data.getStringExtra(AddMealActivity.ExtraNote));
            mAddMealModel.insert(note);

            /*ImageView imageView = findViewById(R.id.imageView);

            Uri uri = Uri.parse(data.getStringExtra(AddMealActivity.ExtraUri));
            imageView.setImageURI(uri);
            Glide.with(this)
                    .load(new File(uri.getPath()))
                    .into(imageView);*/
            //TextView image = findViewById(R.id.textView14);
            //image.setText(data.getStringExtra(AddMealActivity.ExtraUri));

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Meal not saved",
                    Toast.LENGTH_LONG).show();
        }
    }
    public void delete(){
        Toast.makeText(this,"Clear All",Toast.LENGTH_SHORT).show();
        mAddMealModel.deleteAll();
    }

    @Override
    public void deleteclicked() {
        Toast.makeText(this,"done",Toast.LENGTH_LONG).show();
        //mAddMealModel.insert(note);
    }
}