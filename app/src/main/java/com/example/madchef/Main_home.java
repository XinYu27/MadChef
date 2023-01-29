package com.example.madchef;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madchef.Adapters.RandomRecipeAdapter;
import com.example.madchef.Listeners.RandomRecipeResponseListener;
import com.example.madchef.Listeners.RecipeClickListener;
import com.example.madchef.Models.RandomRecipeApiResponse;
import com.example.madchef.utils.MyRecyclerScroll;

import java.util.ArrayList;
import java.util.List;

public class Main_home extends Fragment {

    Spinner spinner;
    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;
    CardView TopView;

    List<String> tags = new ArrayList<>();



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Main_home() {
        // Required empty public constructor

    }


    public static Main_home newInstance(String param1, String param2) {
        Main_home fragment = new Main_home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        dialog = new ProgressDialog(getActivity());
        manager = new RequestManager(getActivity());
        TopView = view.findViewById(R.id.TopBar);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.simple_grow);
        TopView.startAnimation(animation);
        spinner = view.findViewById(R.id.foodTypeSelection);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.tags, R.layout.spinner_text);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);

        recyclerView = view.findViewById(R.id.recycler_random);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setAdapter(randomRecipeAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tags.clear();
                tags.add(adapterView.getSelectedItem().toString());
                manager.getRandomRecipes(new RandomRecipeResponseListener() {
                    @Override
                    public void didFetch(RandomRecipeApiResponse response, String message) {
                        dialog.dismiss();
                        randomRecipeAdapter = new RandomRecipeAdapter(getActivity(), response.recipes,listener);

                    }

                    @Override
                    public void didError(String messaage) {
                        Toast.makeText(getActivity(), messaage, Toast.LENGTH_SHORT);
                    }
                }, tags);
                dialog.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        final int[] state = new int[1];
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                state[0]=newState;
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0&&(state[0]==0||state[0]==2)){
                    TopView.setVisibility(View.GONE);
                }else if(dy<-10){
                    TopView.setVisibility(View.VISIBLE);

                }
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner.setSelection(0);
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();

            randomRecipeAdapter = new RandomRecipeAdapter(getActivity(), response.recipes,listener);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String messaage) {
            Toast.makeText(getActivity(),messaage,Toast.LENGTH_SHORT);
        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.getRandomRecipes(randomRecipeResponseListener,tags);
            dialog.show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    RecipeClickListener listener = new RecipeClickListener(){
        @Override
        public void onRecipeClicked(String id){
            Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
            intent.putExtra("recipe_id", id);
            startActivity(intent);
        }
    };

}