package com.example.foodplanner.DetailsScreen;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Calander.Calander;
import com.example.foodplanner.DetailsScreen.View.IngredientAdapter;
import com.example.foodplanner.FavoriteScrren.FavoriteRepository;
import com.example.foodplanner.FavoriteScrren.FavoriteScreen;
import com.example.foodplanner.FavoriteScrren.Reciepe_calendar;
import com.example.foodplanner.FavoriteScrren.RecipeDatabase;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;
import com.example.foodplanner.HomeScreen.View.Model.RecipeResponse;
import com.example.foodplanner.HomeScreen.View.View.Home_Activity;
import com.example.foodplanner.Login.View.LogIn;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.Model.Network.RecipeApi;
import com.example.foodplanner.R;
import com.example.foodplanner.SearchFeature.View.Search;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealActivity extends AppCompatActivity {

    FavoriteRepository favoriteRepository;
    BottomNavigationView bottomNavigationView;
    Recipe recipe;
    //commit
    private TextView nameTextView, categoryTextView, areaTextView, instructionsTextView;
    private ImageView imageView;
    private RecyclerView ingredientsRecyclerView;
    private WebView myWeb;
    private ImageView favoriteIcon, calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal);

        nameTextView = findViewById(R.id.recipeName);
        categoryTextView = findViewById(R.id.DishName);
        areaTextView = findViewById(R.id.Description);
        instructionsTextView = findViewById(R.id.instruction);
        imageView = findViewById(R.id.mealImage);
        ingredientsRecyclerView = findViewById(R.id.ingerdents);
        myWeb = findViewById(R.id.webView);
        favoriteIcon = findViewById(R.id.love);
        calendar = findViewById(R.id.calendar);
        String recipeId = getIntent().getStringExtra("recipeId");
        favoriteRepository = new FavoriteRepository(this);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.favotrite) {
                Intent intent = new Intent(MealActivity.this, FavoriteScreen.class);
                startActivity(intent);
            } else if (itemId == R.id.Logout) {
                RecipeDatabase.deleteDatabase(this);

                SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(this, LogIn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                finish();
            } else if (itemId == R.id.home) {
                Intent intent = new Intent(MealActivity.this, Home_Activity.class);
                startActivity(intent);

            } else if (itemId == R.id.calander) {
                Intent intent = new Intent(MealActivity.this, Calander.class);
                startActivity(intent);

            } else if (itemId == R.id.search) {
                Intent intent = new Intent(MealActivity.this, Search.class);
                startActivity(intent);

            }
            return true;
        });
        calendar.setOnClickListener(view -> {
            openDatePicker();
        });
        favoriteIcon.setOnClickListener(view -> {

            favoriteRepository.isFavorite(recipeId).observe(this, isFav -> {
                if (isFav != null && isFav > 0) {
                    //favoriteRepository.removeFromFavorites(recipe.idMeal);
                } else {
                    favoriteRepository.addToFavorites(recipe);
                }
            });
            Intent intent = new Intent(this, FavoriteScreen.class);
            startActivity(intent);

        });


        if (recipeId != null) {
            fetchMealDetails(recipeId);
        }
    }
    public void addreciepe(String date){
        Reciepe_calendar recipe = new Reciepe_calendar(this.recipe, date);
        Log.d("Calendar", "Selected date: " + new Date().toString());
        recipe.setRecipe(this.recipe);
        recipe.setDate(date);
        favoriteRepository.addToCalendar(recipe);
        Intent intent = new Intent(this, Calander.class);
        startActivity(intent);
    }
    private void openDatePicker() {
        // Get current date to set as default in the DatePickerDialog
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Note: Month is 0-based
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create and show the DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Handle the selected date
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        Toast.makeText(MealActivity.this, "Selected Date: " +
                                selectedDate, Toast.LENGTH_SHORT).show();
                        addreciepe(selectedDate);
                    }
                },
                year, month, day
        );

        datePickerDialog.show();

}
    private void fetchMealDetails(String recipeId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipeApi recipeApi = retrofit.create(RecipeApi.class);
        Call<RecipeResponse> call = recipeApi.getMealById(recipeId);

        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Recipe> meals = response.body().getMeals();
                    if (meals != null && !meals.isEmpty()) {
                        displayMealDetails(meals.get(0));
                    }
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Toast.makeText(MealActivity.this, "Failed to load meal details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayMealDetails(Recipe recipe) {
        nameTextView.setText(recipe.getStrMeal());
        categoryTextView.setText("Category: " + recipe.getStrCategory());
        areaTextView.setText("Area: " + recipe.getStrArea());
        instructionsTextView.setText(recipe.getStrInstructions());
        Glide.with(this).load(recipe.getStrMealThumb()).into(imageView);
        this.recipe = recipe;
        List<String> ingredients = new ArrayList<>();
        List<String> measures = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            try {
                String ingredient = (String) Recipe.class.getMethod("getStrIngredient" + i).invoke(recipe);
                String measure = (String) Recipe.class.getMethod("getStrMeasure" + i).invoke(recipe);

                if (ingredient != null && !ingredient.trim().isEmpty()) {
                    ingredients.add(ingredient);
                    measures.add(measure);
                }
            } catch (Exception e) {

            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        ingredientsRecyclerView.setLayoutManager(layoutManager);
        IngredientAdapter adapter = new IngredientAdapter(this, ingredients, measures);
        ingredientsRecyclerView.setAdapter(adapter);

        myWeb.getSettings().setJavaScriptEnabled(true);
        myWeb.setWebViewClient(new WebViewClient());
        myWeb.loadUrl(recipe.getStrYoutube());
    }
}
