/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.model;

/**
 *
 * @author jmnov
 */
public class RecipeDetail {
    
    private Ingredient ingredient;
    
    private double units;

    public RecipeDetail() {
    }

    public RecipeDetail(Ingredient ingredient, double units) {
        this.ingredient = ingredient;
        this.units = units;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }
}
