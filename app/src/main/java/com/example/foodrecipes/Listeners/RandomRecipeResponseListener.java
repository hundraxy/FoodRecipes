package com.example.foodrecipes.Listeners;

import com.example.foodrecipes.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse response,String message);
    void didError(String message);
}
