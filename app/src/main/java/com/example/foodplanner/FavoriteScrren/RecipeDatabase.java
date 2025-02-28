package com.example.foodplanner.FavoriteScrren;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.foodplanner.Calander.DateTypeConverter;
import com.example.foodplanner.Calander.RecipeTypeConverter;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;


@Database(entities = {Recipe.class,Reciepe_calendar.class}, version = 4, exportSchema = false)
@TypeConverters({RecipeTypeConverter.class,DateTypeConverter.class})
public abstract class RecipeDatabase extends RoomDatabase {
public static String dataBaseName="recipe_database";
    private static volatile RecipeDatabase INSTANCE;

    public abstract RecipeDao recipeDao();

    public static RecipeDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RecipeDatabase.class, dataBaseName)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void deleteDatabase(Context context){
        if(INSTANCE!=null){
            INSTANCE.close();
            INSTANCE=null;
        }
        context.deleteDatabase(dataBaseName);

    }
}

