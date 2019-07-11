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
public class Ingredient {
    
    private long ingredientId;
    
    private String name;
    
    private String unitName;
    
    private double costPerUnit;

    public Ingredient() {
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }
    
    public void validate() throws Exception {
        if (this.name.trim().length() == 0)
            throw new Exception("Nombre de ingrediente no valido");
        
        if (this.unitName == null || this.unitName.length() == 0)
            throw new Exception("Tiene que seleccionar un nombre de unidad");
        
        if (this.costPerUnit <= 0)
            throw new Exception("El costo por unidad debe ser mayor que cero");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.ingredientId ^ (this.ingredientId >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ingredient other = (Ingredient) obj;
        if (this.ingredientId != other.ingredientId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
