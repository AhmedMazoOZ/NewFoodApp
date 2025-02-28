package com.example.foodplanner.HomeScreen.View.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodplanner.Calander.Calander;
import com.example.foodplanner.Country_Area_Page.Category_Country_Page;
import com.example.foodplanner.FavoriteScrren.FavoriteScreen;
import com.example.foodplanner.FavoriteScrren.RecipeDatabase;
import com.example.foodplanner.HomeScreen.View.Model.Area;
import com.example.foodplanner.HomeScreen.View.Model.Category;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;
import com.example.foodplanner.HomeScreen.View.Presenter.HomeBridge;
import com.example.foodplanner.HomeScreen.View.Presenter.HomePresenter;
import com.example.foodplanner.HomeScreen.View.View.Adapter.AreaAdapter;
import com.example.foodplanner.HomeScreen.View.View.Adapter.CategoryAdapter;
import com.example.foodplanner.HomeScreen.View.View.Adapter.RandomAdapter;
import com.example.foodplanner.Login.View.LogIn;
import com.example.foodplanner.R;
import com.example.foodplanner.SearchScreen.Search;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home_Activity extends AppCompatActivity implements HomeBridge.View {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private RecyclerView recyclerViewRecipes, recyclerViewCategories, recyclerViewArreaes;
    private RandomAdapter recipeAdapter;
    private CategoryAdapter categoryAdapter;
    private AreaAdapter areaAdapter;
    BottomNavigationView bottomNavigationView;


    private List<Recipe> recipeList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();
    private List<Area> areaList = new ArrayList<>();

    HomePresenter homePresenter;
//    MealsPresenter mealsPresenter;
//    CategoriesPresenter categoriesPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.random_recycler_view);
        homePresenter=new HomePresenter(this);
        recyclerViewRecipes = findViewById(R.id.rrv);
        recyclerViewCategories = findViewById(R.id.crv);
        recyclerViewArreaes = findViewById(R.id.arv);
        bottomNavigationView=findViewById(R.id.bottom_navigation);


        homePresenter.GetMeals();
        homePresenter.GetCategories();
        homePresenter.GetMealsByCountry();
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.favotrite) {
                Intent intent=new Intent(Home_Activity.this, FavoriteScreen.class);
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
            }


            else if (itemId ==R.id.home) {
                Intent intent=new Intent(Home_Activity.this, Home_Activity.class);
                startActivity(intent);

            }
            else if (itemId == R.id.calander) {
                Intent intent=new Intent(Home_Activity.this, Calander.class);
                startActivity(intent);

            }
            else if (itemId == R.id.search) {
                Intent intent=new Intent(Home_Activity.this, Search.class);
                startActivity(intent);

            }
            return true;
        });

    }

   @Override
    public void onMealsRequestSuccess(List<Recipe> recipeList) {
        showMeals(recipeList);
    }

    private void showMeals(List<Recipe> recipeList) {
        recyclerViewRecipes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
        recipeAdapter = new RandomAdapter(recipeList, this);
        recyclerViewRecipes.setAdapter(recipeAdapter);
    }

    @Override
    public void onMealsRequestFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCategoriesRequestSuccess(List<Category> categoryList) {
        showCategories(categoryList);
    }

    private void showCategories(List<Category> categoryList) {
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
        categoryAdapter = new CategoryAdapter(categoryList, this);
        recyclerViewCategories.setAdapter(categoryAdapter);
    }

    @Override
    public void onCategoriesRequestFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealsByCountryRequestSuccess(List<Area> AreaList) {
        showCountries(AreaList);
    }
    private void showCountries(List<Area> AreaList) {
        recyclerViewArreaes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
        areaAdapter = new AreaAdapter(AreaList,this);
        recyclerViewArreaes.setAdapter(areaAdapter);
    }
    @Override
    public void onMealsByCountryRequestFailure(String message) {

    }
}