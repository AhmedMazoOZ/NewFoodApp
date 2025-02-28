package com.example.foodplanner.View;



import com.example.foodplanner.HomeScreen.View.Model.Recipe;

import java.util.List;

public interface MealView {
    void setRandomMeal(List<Recipe> recipePojoList);
    void setErrorMessage (String errorMessage);
}
