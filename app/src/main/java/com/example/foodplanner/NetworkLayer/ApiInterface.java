package com.example.foodplanner.NetworkLayer;


import com.example.foodplanner.HomeScreen.View.Model.AreaResponse;
import com.example.foodplanner.HomeScreen.View.Model.CategoryResponse;
import com.example.foodplanner.HomeScreen.View.Model.RecipeResponse;
import com.example.foodplanner.SearchFeature.Model.IngrediantResponce;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("random.php")
    Call<RecipeResponse> getRandomRecipe();

    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    @GET("list.php?a=list")
    Call<AreaResponse>getAreaes();

    @GET("list.php?i=list")
    Call<IngrediantResponce>getIngrediant();

    @GET("filter.php")
    Observable<RecipeResponse> getRecipesByCategory(@Query("c") String category);

    @GET("filter.php")
    Observable<RecipeResponse> getRecipesByArea(@Query("a") String area);

    @GET("filter.php")
    Observable<RecipeResponse> getRecipesByingrediant(@Query("i") String ingrediant);

    @GET("filter.php")
    Call<RecipeResponse> getMealsByArea(@Query("a") String area);
    @GET("lookup.php")
    Call<RecipeResponse> getMealById(@Query("i") String mealId);
}
