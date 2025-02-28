package com.example.foodplanner.SearchFeature.View.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Country_Area_Page.Category_Country_Page;
import com.example.foodplanner.R;
import com.example.foodplanner.SearchFeature.Model.Iingrediants;

import java.util.List;

public class IngrediantAdaptor extends RecyclerView.Adapter<IngrediantAdaptor.ViewHolder> {
    private List<Iingrediants> IngrediantList;
    private Context context;

    public IngrediantAdaptor(List<Iingrediants> IngrediantListt, Context context) {
        this.IngrediantList = IngrediantListt;
        this.context = context;
    }

    @NonNull
    @Override
    public IngrediantAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_ingrediant_adaptor, parent, false);
        return new IngrediantAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngrediantAdaptor.ViewHolder holder, int position) {
        Iingrediants iingrediants = IngrediantList.get(position);
        holder.recipeNum.setText(iingrediants.getstrIngrediants());
        holder.recipeName.setText(iingrediants.getStrIngredient());
        holder.recipeDesc.setText(iingrediants.getStrDescription());


//        Glide.with(context)
//                .load(iingrediants.getStrCategoryThumb())
//                .placeholder(R.drawable.re)
//                .into(holder.categoryImage);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Category_Country_Page.class);
            intent.putExtra("category", iingrediants.getstrIngrediants());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return IngrediantList.size();
    }

    public void setCategoryList(List<Iingrediants> IngrediantListt) {
        this.IngrediantList = IngrediantListt;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeNum , recipeName,recipeDesc;
        ImageView categoryImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeNum = itemView.findViewById(R.id.recipeNum);
            recipeName = itemView.findViewById(R.id.recipeName);
            recipeDesc= itemView.findViewById(R.id.recipeDesc);
        }




    }

}