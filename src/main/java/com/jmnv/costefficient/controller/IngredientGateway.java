/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.controller;

import com.jmnv.costefficient.model.Ingredient;
import java.util.List;

/**
 *
 * @author jmnov
 */
public interface IngredientGateway {
    
    List<Ingredient> getIngredients() throws Exception;
    
    long saveIngredient(Ingredient ingredient) throws Exception;
    
    void deleteIngredient(long id) throws Exception;
}
