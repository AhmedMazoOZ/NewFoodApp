package com.example.foodplanner.FavoriteScrren;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.foodplanner.Calander.DateTypeConverter;
import com.example.foodplanner.Calander.RecipeTypeConverter;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;

import java.io.Serializable;
import java.util.Date;



    @Entity(tableName = "calendar_recipes")
    public class Reciepe_calendar implements Serializable {

        @PrimaryKey(autoGenerate = true)
        @NonNull

        public int idMeal;
        @TypeConverters(RecipeTypeConverter.class)
        public Recipe recipe;
//        @TypeConverters(DateTypeConverter.class)
        public String date;

        public Reciepe_calendar(Recipe recipe, String date) {
            this.recipe = recipe;
            this.date = date;
        }

        @NonNull
        public int getIdMeal() {
            return idMeal;
        }

        public void setIdMeal(@NonNull int idMeal) {
            this.idMeal = idMeal;
        }

        public Recipe getRecipe() {
            return recipe;
        }

        public void setRecipe(Recipe recipe) {
            this.recipe = recipe;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
