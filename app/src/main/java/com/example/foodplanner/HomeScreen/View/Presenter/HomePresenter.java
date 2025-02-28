package com.example.foodplanner.HomeScreen.View.Presenter;


import com.example.foodplanner.HomeScreen.View.Model.Area;
import com.example.foodplanner.HomeScreen.View.Model.Category;
import com.example.foodplanner.HomeScreen.View.Model.HomeModel;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;

import java.util.List;

public class HomePresenter implements HomeBridge.Presenter {

    private HomeBridge.View view;
    private HomeModel homeModel;

    public HomePresenter(HomeBridge.View view) {
        this.view = view;
        this.homeModel = new HomeModel();
    }

    @Override
    public void GetMeals() {
        homeModel.GetRecipe(new HomeModel.OnHomeListener() {
            @Override
            public void onSuccessRecipe(List<Recipe> RecipeList) {
                view.onMealsRequestSuccess(RecipeList);
            }

            @Override
            public void onSuccessCategory(List<Category> RecipeList) {

            }

            @Override
            public void onSuccessArea(List<Area> RecipeList) {

            }

            @Override
            public void onFailure(String errorMessage) {
                view.onMealsRequestFailure(errorMessage);
            }
        });
    }

    @Override
    public void GetCategories() {
        homeModel.GetCategories(new HomeModel.OnHomeListener() {
            @Override
            public void onSuccessRecipe(List<Recipe> RecipeList) {
            }

            @Override
            public void onSuccessCategory(List<Category> RecipeList) {
                view.onCategoriesRequestSuccess(RecipeList);
            }

            @Override
            public void onSuccessArea(List<Area> RecipeList) {

            }

            @Override
            public void onFailure(String errorMessage) {
                view.onCategoriesRequestFailure(errorMessage);
            }
        });
    }

    @Override
    public void GetMealsByCountry() {
        homeModel.GetMealByCountry(new HomeModel.OnHomeListener() {

            @Override
            public void onSuccessRecipe(List<Recipe> RecipeList) {

            }

            @Override
            public void onSuccessCategory(List<Category> RecipeList) {

            }

            @Override
            public void onSuccessArea(List<Area> RecipeList) {
                view.onMealsByCountryRequestSuccess(RecipeList);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.onMealsByCountryRequestFailure(errorMessage);
            }
        });
    }


}
