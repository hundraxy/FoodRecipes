package com.example.foodrecipes.Listeners;



import com.example.foodrecipes.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String message);
    void  didError(String message);


}
