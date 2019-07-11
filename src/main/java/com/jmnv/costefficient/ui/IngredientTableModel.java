/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.ui;

import com.jmnv.costefficient.model.Ingredient;

/**
 *
 * @author jmnov
 */
public class IngredientTableModel extends BeanTableModel<Ingredient> {

    private static final Class[] columnTypes = new Class[] {
        String.class, Double.class, String.class
    };
    
    private static final String[] columnNames = new String[] {
        "Nombre", "Costo por Unidad", "Tipo de Unidad"
    };
    
    @Override
    public Object getValueAt(Ingredient bean, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return bean.getName();
            case 1:
                return bean.getCostPerUnit();
            case 2:
                return bean.getUnitName();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }
    

    @Override
    public int getColumnCount() {
        return columnTypes.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
