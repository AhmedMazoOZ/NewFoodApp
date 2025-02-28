package com.example.foodplanner.DetailsScreen.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private Context context;
    private List<String> ingredients;
    private List<String> measures;

    public IngredientAdapter(Context context, List<String> ingredients, List<String> measures) {
        this.context = context;
        this.ingredients = ingredients;
        this.measures = measures;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_ingerdents, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ingredient = ingredients.get(position);
        String measure = measures.get(position);

        holder.ingredientName.setText(ingredient);
        holder.measureName.setText(measure);


        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient + "-Small.png";
        Glide.with(context).load(imageUrl).into(holder.ingredientImage);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName, measureName;
        ImageView ingredientImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.IngreName);
            measureName = itemView.findViewById(R.id.MeasureName);
            ingredientImage = itemView.findViewById(R.id.cardImage);
        }
    }
}
