/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.repository;

import com.jmnv.costefficient.model.Ingredient;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jmnov
 */
public interface IngredientRepo {
    
    List<Ingredient> getIngredients() throws SQLException;
    
    void save(Ingredient ingredient) throws SQLException;
    
    void delete(long id) throws SQLException;
}
