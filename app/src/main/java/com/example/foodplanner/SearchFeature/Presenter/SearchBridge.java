package com.example.foodplanner.SearchFeature.Presenter;

import android.widget.EditText;

import com.example.foodplanner.HomeScreen.View.Model.Area;
import com.example.foodplanner.HomeScreen.View.Model.Category;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;
import com.example.foodplanner.SearchFeature.Model.Iingrediants;

import java.util.List;

public interface SearchBridge {

    interface View {
        void onCategoriesSuccess(List<Category> CategoriesList);
        void onCategoriesFailure(String message);
        void onCountriesSuccess(List<Area> AreaList);
        void onCountriesFailure(String message);
        void onIngrediantsSuccess(List<Iingrediants> ingrediantsList);
        void onIngrediantsFailure(String message);
        void onSearchByCategoriesSuccess(List<Recipe> recipeList);
        void onSearchByCategoriesFailure(String message);

    }

    interface Presenter {
        void GetCategories();
        void GetCountries();
        void GetIngrediants();
        void SearchByCategory(String str,EditText searchEditText);
    }
}
