package com.example.madchef;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madchef.Adapters.RandomRecipeAdapter;
import com.example.madchef.Listeners.RandomRecipeResponseListener;
import com.example.madchef.Listeners.RecipeClickListener;
import com.example.madchef.Models.RandomRecipeApiResponse;

import java.util.ArrayList;
import java.util.List;

public class SearchMenu extends AppCompatActivity {

    RecyclerView recyclerView;
    RandomRecipeAdapter randomRecipeAdapter2;
    ImageView buttonfilter;
    androidx.appcompat.widget.SearchView searchView;
    ProgressDialog dialog;
    RequestManager manager;
    List<String> tags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);

        buttonfilter = findViewById(R.id.but_filter);
        buttonfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( SearchMenu.this,filter.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.menulist);
        recyclerView.setHasFixedSize(true);

        searchView = findViewById(R.id.menuSearch);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeResponseListener3,tags);
                dialog.show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading....");

        manager = new RequestManager(this);
        //manager.getRandomRecipes(randomRecipeResponseListener3,tags);
        //dialog.show();

        /*
        searchView = findViewById(R.id.menuSearch);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeResponseListener3,tags);
                dialog.show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });*/

    }
    final RandomRecipeResponseListener randomRecipeResponseListener3 = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recyclerView = findViewById(R.id.menulist);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(SearchMenu.this, LinearLayoutManager.VERTICAL,false));
            randomRecipeAdapter2 = new RandomRecipeAdapter(SearchMenu.this, response.recipes,recipeClickListener2);
            recyclerView.setAdapter(randomRecipeAdapter2);
        }

        @Override
        public void didError(String messaage) {
            Toast.makeText(SearchMenu.this,messaage,Toast.LENGTH_SHORT);
        }
    };
    
    final RecipeClickListener recipeClickListener2 = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(SearchMenu.this, RecipeDetailActivity.class)
                    .putExtra("id",id));
        }
    };


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu_search,menu);

        MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeResponseListener3,tags);
                dialog.show();
                return true;

            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/
}
