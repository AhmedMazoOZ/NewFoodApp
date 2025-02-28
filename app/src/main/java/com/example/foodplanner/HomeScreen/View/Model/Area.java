package com.example.foodplanner.HomeScreen.View.Model;

import java.io.Serializable;

public class Area implements Serializable {
    public String strArea;

    public Area(String strArea) {
        this.strArea = strArea;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
}
