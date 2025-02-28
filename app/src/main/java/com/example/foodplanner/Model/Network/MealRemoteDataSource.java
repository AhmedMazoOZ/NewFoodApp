package com.example.foodplanner.Model.Network;

import android.util.Log;


import com.example.foodplanner.HomeScreen.View.Model.AreaResponse;
import com.example.foodplanner.HomeScreen.View.Model.CategoryResponse;
import com.example.foodplanner.HomeScreen.View.Model.RecipeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSource {

    public static final String URL = "https://www.themealdb.com/api/json/v1/1/";
    RecipeApi mealApi;
    private static MealRemoteDataSource mealRemoteDataSource = null;

    public static MealRemoteDataSource getInstance(){
        if(mealRemoteDataSource == null){
            mealRemoteDataSource = new MealRemoteDataSource();
        }
        return mealRemoteDataSource;
    }

    private MealRemoteDataSource(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mealApi = retrofit.create(RecipeApi.class);
    }


public void RandomNetworkCallBack(RandomMealNetworkCallBack randomMealNetworkCallBack){

    Call<RecipeResponse> api = mealApi.getRandomRecipe();
    api.enqueue(new Callback<RecipeResponse>() {
        @Override
        public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                randomMealNetworkCallBack.onSuccessResult(response.body().meals);
            }
        }

        @Override
        public void onFailure(Call<RecipeResponse> call, Throwable t) {
            Log.e("API_ERROR", "Error: " + t.getMessage());
            randomMealNetworkCallBack.onFailureResult(t.getMessage());

        }

    });

}
public void CategoryNetworkCallBAck(CategoryNetworkCallBack categoryNetworkCallBack){
    Call<CategoryResponse>api= mealApi.getCategories();
    api.enqueue(new Callback<CategoryResponse>() {
        @Override
        public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                categoryNetworkCallBack.onSuccessResultCategory(response.body().categories);
            }
        }

        @Override
        public void onFailure(Call<CategoryResponse> call, Throwable t) {
            Log.e("API_ERROR", "Error: " + t.getMessage());
            categoryNetworkCallBack.onFailureResultCategory(t.getMessage());
        }
    });
}
    public void AreaNetworkCallBack(AreaNetworkCallBack areaMealNetworkCallBack) {

        Call<AreaResponse> api = mealApi.getAreaes();
        api.enqueue(new Callback<AreaResponse>() {
            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    areaMealNetworkCallBack.onSuccessResultArea(response.body().meals);
                } else {
                    Log.e("API_ERROR", "Response error: " + response.errorBody());
                    areaMealNetworkCallBack.onFailureResultArea("Response error");
                }
            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                areaMealNetworkCallBack.onFailureResultArea(t.getMessage());
            }
        });
    }


}
