package com.example.madchef;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madchef.Adapters.IngredientsAdapter;
import com.example.madchef.Adapters.InstructionAdapter;
import com.example.madchef.Listeners.InstructionListener;
import com.example.madchef.Listeners.RecipeDetailsListener;
import com.example.madchef.Models.InstructionsResponse;
import com.example.madchef.Models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {
    int id=1;
    TextView recipename, recipetime, recipeserving, authorname, meal_summary;
    ImageView recipeimage;
    RecyclerView recycler_mealIngredientsID, recylcer_meal_instruction;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    InstructionAdapter instructionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        findViewById();
        if (getIntent().getStringExtra("id")!=null){
        id = Integer.parseInt(getIntent().getStringExtra("id"));}
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener, id);
        manager.getInstruction(instructionListener, id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        dialog.show();
    }

    private void findViewById() {
        recipename = findViewById(R.id.recipename);
        recipetime = findViewById(R.id.recipetime);
        recipeserving = findViewById(R.id.recipeserving);
        authorname = findViewById(R.id.authorname);
        meal_summary = findViewById(R.id.meal_summary);
        recipeimage = findViewById(R.id.recipeimage);
        recycler_mealIngredientsID = findViewById(R.id.recycler_mealIngredientsID);
        recylcer_meal_instruction = findViewById(R.id.recycler_meal_instruction);
    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            recipename.setText(response.title);
            recipetime.setText(response.readyInMinutes + " minutes");
            recipeserving.setText(response.servings + " persons");
            authorname.setText(response.sourceName);
            meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(recipeimage);

            recycler_mealIngredientsID.setHasFixedSize(true);
            recycler_mealIngredientsID.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
            ingredientsAdapter = new IngredientsAdapter(RecipeDetailActivity.this, response.extendedIngredients);
            recycler_mealIngredientsID.setAdapter(ingredientsAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };

    private final InstructionListener instructionListener = new InstructionListener() {
        @Override
        public void didFetch(List<InstructionsResponse> response, String message) {
            recylcer_meal_instruction.setHasFixedSize(true);
            recylcer_meal_instruction.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this, LinearLayoutManager.VERTICAL,false));
            instructionAdapter = new InstructionAdapter(RecipeDetailActivity.this, response);
            recylcer_meal_instruction.setAdapter(instructionAdapter);

        }

        @Override
        public void didError(String message) {

        }
    };
}