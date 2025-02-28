package com.example.foodplanner.Model.Network;



import com.example.foodplanner.HomeScreen.View.Model.Category;

import java.util.List;

public interface CategoryNetworkCallBack {
    void onSuccessResultCategory (List<Category> RecipeList);
    void onFailureResultCategory (String errorMessage);
}
