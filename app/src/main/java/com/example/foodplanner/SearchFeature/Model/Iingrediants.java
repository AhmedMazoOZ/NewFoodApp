package com.example.foodplanner.SearchFeature.Model;

public class Iingrediants {
    public String idIngredient,strIngredient,strDescription;

    public Iingrediants(String stringrediants,String strIngredientt,String strDescriptionn) {
        this.idIngredient = stringrediants;
        this.strIngredient = strIngredientt;
        this.strDescription = strDescriptionn;
    }

    public String getstrIngrediants() {
        return idIngredient;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrArea(String stringrediantsArea) {
        this.idIngredient = stringrediantsArea;
    }
}
