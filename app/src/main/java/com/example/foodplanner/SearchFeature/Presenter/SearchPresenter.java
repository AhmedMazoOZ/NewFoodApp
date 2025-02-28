package com.example.foodplanner.SearchFeature.Presenter;


import android.widget.EditText;

import com.example.foodplanner.HomeScreen.View.Model.Area;
import com.example.foodplanner.HomeScreen.View.Model.Category;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;
import com.example.foodplanner.SearchFeature.Model.Iingrediants;
import com.example.foodplanner.SearchFeature.Model.SearchModel;

import java.util.List;

public class SearchPresenter implements SearchBridge.Presenter {

    private SearchBridge.View view;
    private SearchModel searchModel;

    public SearchPresenter(SearchBridge.View view) {
        this.view = view;
        this.searchModel = new SearchModel();
    }

    @Override
    public void GetCategories() {
        searchModel.GetCategories(new SearchModel.OnHomeListener() {
            @Override
            public void onSuccessCategory(List<Category> CategoriesList) {
                view.onCategoriesSuccess(CategoriesList);
            }

            @Override
            public void onSuccessCountries(List<Area> AreaList) {

            }

            @Override
            public void onSuccessIngrediants(List<Iingrediants> RecipeList) {

            }

            @Override
            public void onSuccessSearchByCategory(List<Recipe> RecipeList) {

            }

            @Override
            public void onFailure(String errorMessage) {
                view.onCategoriesFailure(errorMessage);
            }
        });

    }

    @Override
    public void GetCountries() {
        searchModel.GetCountries(new SearchModel.OnHomeListener() {
            @Override
            public void onSuccessCategory(List<Category> CategoriesList) {

            }

            @Override
            public void onSuccessCountries(List<Area> AreaList) {
                view.onCountriesSuccess(AreaList);
            }

            @Override
            public void onSuccessIngrediants(List<Iingrediants> RecipeList) {

            }

            @Override
            public void onSuccessSearchByCategory(List<Recipe> RecipeList) {

            }

            @Override
            public void onFailure(String errorMessage) {
                view.onCountriesFailure(errorMessage);
            }
        });
    }

    @Override
    public void GetIngrediants() {
        searchModel.GetIngrediants(new SearchModel.OnHomeListener() {
            @Override
            public void onSuccessCategory(List<Category> CategoriesList) {

            }

            @Override
            public void onSuccessCountries(List<Area> AreaList) {
                view.onCountriesSuccess(AreaList);
            }

            @Override
            public void onSuccessIngrediants(List<Iingrediants> RecipeList) {
                view.onIngrediantsSuccess(RecipeList);
            }

            @Override
            public void onSuccessSearchByCategory(List<Recipe> RecipeList) {

            }

            @Override
            public void onFailure(String errorMessage) {
                view.onIngrediantsFailure(errorMessage);
            }
        });
    }

    @Override
    public void SearchByCategory(String str,EditText searchEditText) {
        searchModel.SearchByCategory(str,searchEditText, new SearchModel.OnHomeListener() {
            @Override
            public void onSuccessCategory(List<Category> CategoriesList) {

            }

            @Override
            public void onSuccessCountries(List<Area> AreaList) {

            }

            @Override
            public void onSuccessIngrediants(List<Iingrediants> ingrediantsList) {

            }

            @Override
            public void onSuccessSearchByCategory(List<Recipe> RecipeList) {
                view.onSearchByCategoriesSuccess(RecipeList);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.onSearchByCategoriesFailure(errorMessage);
            }
        });
    }
}
