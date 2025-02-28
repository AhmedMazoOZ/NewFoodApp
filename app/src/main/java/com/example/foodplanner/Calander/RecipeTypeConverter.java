package com.example.foodplanner.Calander;

import androidx.room.TypeConverter;

import com.example.foodplanner.HomeScreen.View.Model.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class RecipeTypeConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromRecipe(Recipe recipe) {
        if (recipe == null) {
            return null;
        }
        return gson.toJson(recipe);
    }

    @TypeConverter
    public static Recipe toRecipe(String json) {
        if (json == null) {
            return null;
        }
        Type type = new TypeToken<Recipe>() {}.getType();
        return gson.fromJson(json, type);
    }
}
