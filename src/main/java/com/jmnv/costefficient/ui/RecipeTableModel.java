/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.ui;

import com.jmnv.costefficient.model.Recipe;

/**
 *
 * @author jmnov
 */
public class RecipeTableModel extends BeanTableModel<Recipe> {

    @Override
    public Object getValueAt(Recipe recipe, int columnIndex) {
        
        switch(columnIndex) {
            case 0:
                return recipe.getName();
            case 1:
                return recipe.getPrice();
            case 2:
                return recipe.getCost();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Double.class;
            case 2:
                return Double.class;
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Receta";
            case 1:
                return "Precio";
            case 2:
                return "Costo";
            default:
                return "";
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    
}
