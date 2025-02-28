package com.example.foodplanner.FavoriteScrren;

import android.content.Context;

import androidx.lifecycle.LiveData;



import java.util.List;


import android.content.Context;
import androidx.lifecycle.LiveData;

import com.example.foodplanner.HomeScreen.View.Model.Recipe;

import java.util.List;
import java.util.concurrent.Executors;

public class FavoriteRepository {
    private RecipeDao recipeDao;

    public FavoriteRepository(Context context) {
        RecipeDatabase db = RecipeDatabase.getInstance(context);
        recipeDao = db.recipeDao();
    }


    public void addToFavorites(Recipe recipe) {
        new Thread(()->recipeDao.insertFavorite(recipe)).start();
    }

    public LiveData<List<Recipe>> getAllFavorites() {
        return recipeDao.getAllFavorites();
    }

    public LiveData<Integer> isFavorite(String id) {
        return recipeDao.isFavorite(id);
    }

    public void removeFromFavorites(String id) {
        new Thread(()->recipeDao.deleteById(id)).start();
    }
}

