package com.example.foodplanner.Presnter;

import com.example.foodplanner.HomeScreen.View.Model.Recipe;
import com.example.foodplanner.Model.Network.RandomMealNetworkCallBack;
import com.example.foodplanner.Model.Repositry;

import com.example.foodplanner.View.MealView;

import java.util.List;

public class HomePresenter implements RandomMealNetworkCallBack {
    Repositry repositry;
    private MealView mealView;


    public HomePresenter (MealView allMealView , Repositry repositry){
        this.mealView = allMealView;
        this.repositry = repositry;
    }

    public void getRandomMeal (){
        repositry.getRandomMeal(this);
    }




    @Override
    public void onSuccessResult(List<Recipe> RecipeList) {
        mealView.setRandomMeal(RecipeList);

    }

    @Override
    public void onFailureResult(String errorMessage) {
        mealView.setErrorMessage(errorMessage);
    }

}
