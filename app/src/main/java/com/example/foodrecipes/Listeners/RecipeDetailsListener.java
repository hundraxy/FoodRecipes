package com.example.foodrecipes.Listeners;

import com.example.foodrecipes.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void  didError(String message);

}
