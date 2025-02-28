package com.example.foodplanner.FavoriteScrren;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Calander.Calander;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;
import com.example.foodplanner.HomeScreen.View.View.Adapter.RandomAdapter;
import com.example.foodplanner.HomeScreen.View.View.Home_Activity;
import com.example.foodplanner.Login.View.LogIn;
import com.example.foodplanner.R;
import com.example.foodplanner.SearchScreen.Search;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class FavoriteScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RandomAdapter adapter;
    private FavoriteRepository favoriteRepository;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorite_screen);

        recyclerView = findViewById(R.id.allrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bottomNavigationView=findViewById(R.id.bottom_navigation);

        favoriteRepository = new FavoriteRepository(this);
        adapter = new RandomAdapter(List.of(), this);
        recyclerView.setAdapter(adapter);

        favoriteRepository.getAllFavorites().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                adapter.setData(recipes);
                adapter.notifyDataSetChanged();
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.favotrite) {
                Intent intent=new Intent(FavoriteScreen.this, FavoriteScreen.class);
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
                Intent intent=new Intent(FavoriteScreen.this, Home_Activity.class);
                startActivity(intent);

            }
            else if (itemId == R.id.calander) {
                Intent intent=new Intent(FavoriteScreen.this, Calander.class);
                startActivity(intent);

            }
            else if (itemId == R.id.search) {
                Intent intent=new Intent(FavoriteScreen.this, Search.class);
                startActivity(intent);

            }
            return true;
        });
    }
}
