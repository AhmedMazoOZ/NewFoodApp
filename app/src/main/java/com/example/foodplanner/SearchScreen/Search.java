package com.example.foodplanner.SearchScreen;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodplanner.HomeScreen.View.View.Adapter.RandomAdapter;
import com.example.foodplanner.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Search extends AppCompatActivity {

    private EditText searchEditText;
    private Button btnCategory, btnArea, btnIngredient;
    private RecyclerView searchRecyclerView;
    private RandomAdapter adapter;

    private CompositeDisposable disposable = new CompositeDisposable();
    private String searchType = "category";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_actvity);

        searchEditText = findViewById(R.id.Search);
        btnCategory = findViewById(R.id.btnCategory);
        btnArea = findViewById(R.id.btnArea);
        btnIngredient = findViewById(R.id.btnIngredient);
        searchRecyclerView = findViewById(R.id.searchRecyclerView);

        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RandomAdapter(List.of(), this);
        searchRecyclerView.setAdapter(adapter);


    }


}

