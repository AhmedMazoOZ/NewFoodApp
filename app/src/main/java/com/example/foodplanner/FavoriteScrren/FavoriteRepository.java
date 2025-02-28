package com.example.foodplanner.FavoriteScrren;

import android.content.Context;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;


public class FavoriteRepository {
    private RecipeDao recipeDao;

    public FavoriteRepository(Context context) {
        RecipeDatabase db = RecipeDatabase.getInstance(context);
        recipeDao = db.recipeDao();
    }
    public void addToFavorites(Recipe recipe) {
        new Thread(()->recipeDao.insertFavorite(recipe)).start();
    }
    public void addToCalendar(Reciepe_calendar recipe) {
        new Thread(()->recipeDao.inserttoCalendar(recipe)).start();
    }

    public LiveData<List<Reciepe_calendar>> getRecipesForDate(String date) {
        return recipeDao.getRecipesForDate(date);
    }

    public LiveData<List<Reciepe_calendar>> getAllReciepe_calendar() {
        return recipeDao.getAllcalendar_recipes();
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

