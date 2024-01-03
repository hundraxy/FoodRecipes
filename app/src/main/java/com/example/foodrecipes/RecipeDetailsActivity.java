package com.example.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipes.Adapters.IngredientsAdapter;
import com.example.foodrecipes.Adapters.InstructionsAdapter;
import com.example.foodrecipes.Adapters.SimilarRecipeAdapter;
import com.example.foodrecipes.Listeners.InstructionsListener;
import com.example.foodrecipes.Listeners.RecipeClickListener;
import com.example.foodrecipes.Listeners.RecipeDetailsListener;
import com.example.foodrecipes.Listeners.SimilarRecipesListener;
import com.example.foodrecipes.Models.InstructionsResponse;
import com.example.foodrecipes.Models.RecipeDetailsResponse;
import com.example.foodrecipes.Models.SimilarRecipeResponse;
import com.example.foodrecipes.R;
import com.example.foodrecipes.RequestManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;
    TextView textView_meal_name, textView_meal_source,textView_meal_summary;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients,recycler_meal_similar,recycler_meal_Instructions;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    SimilarRecipeAdapter similarRecipeAdapter;

    InstructionsAdapter instructionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        findViews();


        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener,id);

        manager.getSimilarRecipes(similarRecipesListener,id);
        manager.getInstructions(instructionsListener,id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details....");
        dialog.show();

    }
    private void findViews(){
        textView_meal_name =findViewById(R.id.textView_meal_name);
        textView_meal_source =findViewById(R.id.textView_meal_source);
        textView_meal_summary =findViewById(R.id.textView_meal_summary);
        imageView_meal_image=findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients = findViewById(R.id.recycler_meal_ingredients);
        recycler_meal_similar = findViewById(R.id.recycler_meal_similar);
        recycler_meal_Instructions = findViewById(R.id.recycler_meal_Instructions);
    }
    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            textView_meal_name.setText(response.title);
            textView_meal_source.setText(response.sourceName);
            textView_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_meal_image);

            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
            ingredientsAdapter  = new IngredientsAdapter(RecipeDetailsActivity.this,response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this,message,Toast.LENGTH_SHORT).show();

        }
    };
    private  final SimilarRecipesListener similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> response, String message) {
            recycler_meal_similar.setHasFixedSize(true);
            recycler_meal_similar.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
            similarRecipeAdapter =new SimilarRecipeAdapter(RecipeDetailsActivity.this,response,recipeClickListener );
            recycler_meal_similar.setAdapter(similarRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this,message,Toast.LENGTH_SHORT).show();

        }
    };
    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(RecipeDetailsActivity.this,RecipeDetailsActivity.class)
                    .putExtra("id",id));
        }
    };

    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(List<InstructionsResponse> response, String message) {
            recycler_meal_Instructions.setHasFixedSize(true);
            recycler_meal_Instructions.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
            instructionsAdapter = new InstructionsAdapter(RecipeDetailsActivity.this,response);
            recycler_meal_Instructions.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };
}