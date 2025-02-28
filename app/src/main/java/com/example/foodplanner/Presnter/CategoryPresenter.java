package com.example.foodplanner.Presnter;


import com.example.foodplanner.HomeScreen.View.Model.Category;
import com.example.foodplanner.Model.Network.CategoryNetworkCallBack;
import com.example.foodplanner.Model.Repositry;
import com.example.foodplanner.View.CategoryView;

import java.util.List;

public class CategoryPresenter implements CategoryNetworkCallBack {
    Repositry repositry;
    CategoryView categoryView;

    public CategoryPresenter(Repositry repositry, CategoryView categoryView) {
        this.repositry = repositry;
        this.categoryView = categoryView;
    }
   public void getAllCategory(){
       repositry.getCategoryMeal(this);

   }

    @Override

    public void onSuccessResultCategory(List<Category> RecipeList) {
        categoryView.setCategory(RecipeList);

    }

    @Override
    public void onFailureResultCategory(String errorMessage) {
        categoryView.setErrorMessage(errorMessage);
    }
}
