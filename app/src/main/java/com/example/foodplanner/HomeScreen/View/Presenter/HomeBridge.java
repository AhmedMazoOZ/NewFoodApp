package com.example.foodplanner.HomeScreen.View.Presenter;



import com.example.foodplanner.HomeScreen.View.Model.Area;
import com.example.foodplanner.HomeScreen.View.Model.Category;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;

import java.util.List;

public interface HomeBridge {
    interface View {
        void onMealsRequestSuccess(List<Recipe> RecipeList);
        void onMealsRequestFailure(String message);
        void onCategoriesRequestSuccess(List<Category> RecipeList);
        void onCategoriesRequestFailure(String message);
        void onMealsByCountryRequestSuccess(List<Area> AreaList);
        void onMealsByCountryRequestFailure(String message);
    }

    interface Presenter {
        void GetMeals();
        void GetCategories();
        void GetMealsByCountry();
    }
}
