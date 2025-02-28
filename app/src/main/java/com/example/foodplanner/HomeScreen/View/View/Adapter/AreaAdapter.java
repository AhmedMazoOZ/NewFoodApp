package com.example.foodplanner.HomeScreen.View.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Country_Area_Page.Category_Country_Page;

import com.example.foodplanner.HomeScreen.View.Model.Area;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {
    private List<Area> areaList=new ArrayList<>();

    private Context context;
    public AreaAdapter(List<Area> AreaList, Context context) {
        areaList=AreaList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.area_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Area area = areaList.get(position);
        holder.areaName.setText(area.getStrArea());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Category_Country_Page.class);
            intent.putExtra("country", area.getStrArea());
            context.startActivity(intent);
        });




    }

    @Override
    public int getItemCount() {
        return areaList.size();
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView areaName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);





            areaName = itemView.findViewById(R.id.AreaName);

        }
    }
}
