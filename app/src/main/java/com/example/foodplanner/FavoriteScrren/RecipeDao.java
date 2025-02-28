package com.example.foodplanner.FavoriteScrren;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;

import java.util.Date;
import java.util.List;
@Dao
public interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(Recipe recipe);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserttoCalendar(Reciepe_calendar reciepeCalendar);

    @Query("SELECT * FROM calendar_recipes WHERE date = :date")
    LiveData<List<Reciepe_calendar>> getRecipesForDate(String date);

    @Query("SELECT * FROM calendar_recipes")
    LiveData<List<Reciepe_calendar>> getAllcalendar_recipes();

    @Query("SELECT * FROM favorite_recipes")
    LiveData<List<Recipe>> getAllFavorites();

    @Query("SELECT COUNT(*) FROM favorite_recipes WHERE idMeal = :id")
    LiveData<Integer> isFavorite(String id);

    @Query("DELETE FROM favorite_recipes WHERE idMeal = :id")
    void deleteById(String id);
}
