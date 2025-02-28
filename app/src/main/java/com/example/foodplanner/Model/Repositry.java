package com.example.foodplanner.Model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Network.AreaNetworkCallBack;
import com.example.foodplanner.Model.Network.CategoryNetworkCallBack;
import com.example.foodplanner.Model.Network.MealRemoteDataSource;
import com.example.foodplanner.Model.Network.RandomMealNetworkCallBack;

import java.util.List;

public class Repositry {
    private MealRemoteDataSource mealRemoteDataSource;
    private static Repositry instance = null;

    private Repositry( MealRemoteDataSource mealRemoteDataSource) {

        this.mealRemoteDataSource = mealRemoteDataSource;
    }

    public static Repositry getInstance ( MealRemoteDataSource mealRemoteDataSource){
        if(instance == null){
            instance = new Repositry(mealRemoteDataSource);
        }
        return  instance;
    }

    public void getRandomMeal(RandomMealNetworkCallBack randomMealNetworkCallBack){
        mealRemoteDataSource.RandomNetworkCallBack(randomMealNetworkCallBack);
    }
    public void getCategoryMeal(CategoryNetworkCallBack categoryNetworkCallBack){
        mealRemoteDataSource.CategoryNetworkCallBAck(categoryNetworkCallBack);
    }
    public void getAreaMeal(AreaNetworkCallBack areaNetworkCallBack){
        mealRemoteDataSource.AreaNetworkCallBack(areaNetworkCallBack);
    }


}
