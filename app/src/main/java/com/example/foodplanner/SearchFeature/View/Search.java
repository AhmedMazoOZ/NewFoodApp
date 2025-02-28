package com.example.foodplanner.SearchFeature.View;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.HomeScreen.View.Model.Area;
import com.example.foodplanner.HomeScreen.View.Model.Category;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;
import com.example.foodplanner.HomeScreen.View.Model.RecipeResponse;
import com.example.foodplanner.HomeScreen.View.View.Adapter.AreaAdapter;
import com.example.foodplanner.HomeScreen.View.View.Adapter.CategoryAdapter;
import com.example.foodplanner.HomeScreen.View.View.Adapter.RandomAdapter;
import com.example.foodplanner.R;
import com.example.foodplanner.SearchFeature.Model.Iingrediants;
import com.example.foodplanner.SearchFeature.Presenter.SearchBridge;
import com.example.foodplanner.SearchFeature.Presenter.SearchPresenter;
import com.example.foodplanner.SearchFeature.View.Adaptor.IngrediantAdaptor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Search extends AppCompatActivity implements SearchBridge.View {

    CategoryAdapter categoryAdapter;
    AreaAdapter areaAdapter;
    IngrediantAdaptor ingrediantAdaptor;
    SearchPresenter searchPresenter;
    private EditText searchEditText;
    private Button btnCategory, btnArea, btnIngredient;
    private RecyclerView caregoriesRecyclerView, countriesRecyclerView, ingrediantsRecyclerView,
            SearchBycaregoriesRecyclerView;
    private RandomAdapter randomAdapter;
    private CompositeDisposable disposable = new CompositeDisposable();
    private String searchType = "category";
    private CompositeDisposable disposables = new CompositeDisposable();
    String character;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_actvity);

        searchEditText = findViewById(R.id.Search);
        btnCategory = findViewById(R.id.btnCategory);
        btnArea = findViewById(R.id.btnArea);
        btnIngredient = findViewById(R.id.btnIngredient);

        caregoriesRecyclerView = findViewById(R.id.caregoriesRecyclerView);
        countriesRecyclerView = findViewById(R.id.countriesRecyclerView);
        ingrediantsRecyclerView = findViewById(R.id.ingrediantsRecyclerView);
        SearchBycaregoriesRecyclerView= findViewById(R.id.SearchBycaregoriesRecyclerView);


        searchPresenter = new SearchPresenter(this);
        searchPresenter.GetCategories();
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchPresenter.GetCategories();
                character="c";
            }
        });
        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchPresenter.GetCountries();
                character="a";
            }
        });
        btnIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchPresenter.GetIngrediants();
                character="i";
            }
        });



        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchPresenter.SearchByCategory(character,searchEditText);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }



    @Override
    public void onCategoriesSuccess(List<Category> CategoriesList) {
        showCategories(CategoriesList);
    }

    @Override
    public void onCategoriesFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCountriesSuccess(List<Area> AreaList) {
        showCountries(AreaList);
    }

    @Override
    public void onCountriesFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onIngrediantsSuccess(List<Iingrediants> ingrediantsList) {
        showIngreidiants(ingrediantsList);
    }

    @Override
    public void onIngrediantsFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchByCategoriesSuccess(List<Recipe> recipeList) {
        showSearchByCategories(recipeList);
    }

    @Override
    public void onSearchByCategoriesFailure(String message) {
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showSearchByCategories(List<Recipe> recipeList) {
        Log.d("asdfdsfsdfsdf", "showSearchByCategories: " + recipeList.size());
        SearchBycaregoriesRecyclerView.setVisibility(VISIBLE);
        caregoriesRecyclerView.setVisibility(GONE);
        countriesRecyclerView.setVisibility(GONE);
        ingrediantsRecyclerView.setVisibility(GONE);
        SearchBycaregoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        randomAdapter = new RandomAdapter(recipeList, this);
        SearchBycaregoriesRecyclerView.setAdapter(randomAdapter);
    }

    private void showCategories(List<Category> categoryList) {
        caregoriesRecyclerView.setVisibility(VISIBLE);
        countriesRecyclerView.setVisibility(GONE);
        ingrediantsRecyclerView.setVisibility(GONE);
        SearchBycaregoriesRecyclerView.setVisibility(GONE);
        caregoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        categoryAdapter = new CategoryAdapter(categoryList, this);
        caregoriesRecyclerView.setAdapter(categoryAdapter);
    }

    private void showCountries(List<Area> AreaList) {
        countriesRecyclerView.setVisibility(VISIBLE);
        caregoriesRecyclerView.setVisibility(GONE);
        ingrediantsRecyclerView.setVisibility(GONE);
        SearchBycaregoriesRecyclerView.setVisibility(GONE);
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        areaAdapter = new AreaAdapter(AreaList, this);
        countriesRecyclerView.setAdapter(areaAdapter);
    }

    private void showIngreidiants(List<Iingrediants> ingrediantsList) {
        ingrediantsRecyclerView.setVisibility(VISIBLE);
        caregoriesRecyclerView.setVisibility(GONE);
        countriesRecyclerView.setVisibility(GONE);
        SearchBycaregoriesRecyclerView.setVisibility(GONE);
        ingrediantsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        ingrediantAdaptor = new IngrediantAdaptor(ingrediantsList, this);
        ingrediantsRecyclerView.setAdapter(ingrediantAdaptor);
    }
}

