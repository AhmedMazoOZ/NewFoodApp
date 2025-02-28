package com.example.foodplanner.Presnter;


import com.example.foodplanner.HomeScreen.View.Model.Area;
import com.example.foodplanner.Model.Network.AreaNetworkCallBack;
import com.example.foodplanner.Model.Repositry;
import com.example.foodplanner.View.AreaView;

import java.util.List;

public class AreaPresenter implements AreaNetworkCallBack {
    Repositry repositry;
    AreaView areaView;

    public AreaPresenter(Repositry repositry, AreaView areaView) {
        this.repositry = repositry;
        this.areaView = areaView;
    }
    public void getAllArea(){
        repositry.getAreaMeal(this);

    }

    @Override
    public void onSuccessResultArea(List<Area> RecipeList) {
        areaView.setArea(RecipeList);
    }

    @Override
    public void onFailureResultArea(String errorMessage) {
        areaView.setErrorMessage(errorMessage);
    }
}
