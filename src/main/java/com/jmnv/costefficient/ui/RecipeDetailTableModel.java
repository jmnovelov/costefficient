/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.ui;

import com.jmnv.costefficient.model.Ingredient;
import com.jmnv.costefficient.model.RecipeDetail;

/**
 *
 * @author jmnov
 */
public class RecipeDetailTableModel extends BeanTableModel<RecipeDetail> {

    @Override
    public Object getValueAt(RecipeDetail bean, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return bean.getIngredient();
            case 1:
                return bean.getUnits();
            case 2:
                if (bean.getIngredient() != null)
                    return bean.getIngredient().getUnitName();
                
                return "";
            case 3:
                if (bean.getIngredient() != null)
                    return bean.getIngredient().getCostPerUnit();
                
                return "";
                
            case 4:
                if (bean.getIngredient() != null)
                    return bean.getIngredient().getCostPerUnit() * bean.getUnits();
                
                return "";
            default:
                return null;
        }
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Ingrediente";
            case 1:
                return "Unidades";
            case 2:
                return "Tipo de Unidad";
            case 3: 
                return "Costo por Unidad";
            case 4:
                return "Total";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return Ingredient.class;
            case 1:
                return Double.class;
            case 2:
                return String.class;
            case 3:
                return Double.class;
            case 4:
                return Double.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Double units = (Double) aValue;
        beans.get(rowIndex).setUnits(units);
        super.fireTableRowsUpdated(rowIndex, rowIndex);
    }
    
    public double getTotalValue() {
        double total = 0;
        
        for (RecipeDetail detail : beans)
            total += detail.getUnits() * detail.getIngredient().getCostPerUnit();
        
        return total;
    }
}
