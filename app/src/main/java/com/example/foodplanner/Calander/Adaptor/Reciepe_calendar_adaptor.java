package com.example.foodplanner.Calander.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.DetailsScreen.MealActivity;
import com.example.foodplanner.FavoriteScrren.Reciepe_calendar;
import com.example.foodplanner.HomeScreen.View.Model.Recipe;
import com.example.foodplanner.R;

import java.util.List;

public class Reciepe_calendar_adaptor extends RecyclerView.Adapter<Reciepe_calendar_adaptor.ViewHolder> {
    private List<Reciepe_calendar> recipeList;
    private Context context;

    public Reciepe_calendar_adaptor(List<Reciepe_calendar> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
    }

    @NonNull
    @Override
    public Reciepe_calendar_adaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_reciepe_calendar_adaptor, parent, false);
        return new Reciepe_calendar_adaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Reciepe_calendar_adaptor.ViewHolder holder, int position) {
        Reciepe_calendar recipe = recipeList.get(position);
        holder.recipeName.setText(recipe.recipe.strMeal);


        Glide.with(context)
                .load(recipe.recipe.strMealThumb)
                .placeholder(R.drawable.re)
                .into(holder.recipeImage);


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MealActivity.class);
            intent.putExtra("recipeId", recipe.getIdMeal());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void setData(List<Reciepe_calendar> list) {
        this.recipeList = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        ImageView recipeImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeName);
            recipeImage = itemView.findViewById(R.id.cardImage);
        }
    }
}
