package com.example.foodplanner.HomeScreen.View.Model;

import android.util.Log;

import com.example.foodplanner.NetworkLayer.ApiClient;
import com.example.foodplanner.NetworkLayer.ApiInterface;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeModel {


    public void GetRecipe(OnHomeListener onhomeListener){
        ApiInterface services = ApiClient.getClient().create(ApiInterface.class);
        Call<RecipeResponse> api = services.getRandomRecipe();
        api.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onhomeListener.onSuccessRecipe(response.body().meals);
                }
            }
            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                onhomeListener.onFailure(t.getMessage());

            }

        });

    }
    public void GetCategories( OnHomeListener onhomeListener){
        ApiInterface services = ApiClient.getClient().create(ApiInterface.class);
        Call<CategoryResponse>api= services.getCategories();
        api.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onhomeListener.onSuccessCategory(response.body().categories);
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                onhomeListener.onFailure(t.getMessage());
            }
        });
    }
    public void GetMealByCountry(OnHomeListener onhomeListener) {
        ApiInterface services = ApiClient.getClient().create(ApiInterface.class);
        Call<AreaResponse> api = services.getAreaes();
        api.enqueue(new Callback<AreaResponse>() {
            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    onhomeListener.onSuccessArea(response.body().meals);
                } else {
                    Log.e("API_ERROR", "Response error: " + response.errorBody());
                    onhomeListener.onFailure("Response error");
                }
            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                onhomeListener.onFailure(t.getMessage());
            }
        });
    }

    public interface OnHomeListener {
        void onSuccessRecipe (List<Recipe> RecipeList);
        void onSuccessCategory(List<Category> CategoriesList);
        void onSuccessArea(List<Area> AreaList);
        void onFailure(String errorMessage);
    }

}
