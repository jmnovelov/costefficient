/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jmnov
 */
public abstract class BeanTableModel<T> extends AbstractTableModel {
    
    protected List<T> beans = new ArrayList<>();

    public abstract Object getValueAt(T bean, int columnIndex);
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getValueAt(beans.get(rowIndex), columnIndex);
    }
    
    @Override
    public int getRowCount() {
        return beans.size();
    }
    
    public void clear() {
        int size = beans.size();
        
        if (size > 0) {
            beans.clear();
            super.fireTableRowsDeleted(0, size - 1);
        }
    }
    
    public void add(T object) {
        beans.add(object);
        super.fireTableRowsInserted(beans.size() - 1, beans.size() - 1);
    }
    
    public void addAll(Collection<T> list) {
        if (list.size() > 0) {
            int size = beans.size();
            beans.addAll(list);
            super.fireTableRowsInserted(size, beans.size() - 1);
        }
    }
    
    public void updateRow(int row) {
        super.fireTableRowsUpdated(row, row);
    }
    
    public T get(int row) {
        return beans.get(row);
    }
    
    public void set(int row, T bean) {
        beans.set(row, bean);
        super.fireTableRowsUpdated(row, row);
    }
    
    public void deleteRow(int row) {
        beans.remove(row);
        super.fireTableRowsDeleted(row, row);
    }
    
    public List<T> getAll() {
        return this.beans;
    }
}
