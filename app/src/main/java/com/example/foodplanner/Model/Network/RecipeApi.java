package com.example.foodplanner.Model.Network;



import com.example.foodplanner.HomeScreen.View.Model.AreaResponse;
import com.example.foodplanner.HomeScreen.View.Model.CategoryResponse;
import com.example.foodplanner.HomeScreen.View.Model.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public  interface RecipeApi {

    @GET("random.php")
    Call<RecipeResponse> getRandomRecipe();

    @GET("categories.php")
    Call<CategoryResponse> getCategories();
    @GET("list.php?a=list")
    Call<AreaResponse>getAreaes();
    @GET("filter.php")
    Call<RecipeResponse> getRecipesByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<RecipeResponse> getMealsByArea(@Query("a") String area);
    @GET("lookup.php")
    Call<RecipeResponse> getMealById(@Query("i") String mealId);









}
