/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.controller;

import com.jmnv.costefficient.model.Ingredient;
import com.jmnv.costefficient.repository.IngredientRepo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jmnov
 */
public class IngredientController implements IngredientGateway {

    private Map<Long, Ingredient> cache = new HashMap<Long, Ingredient>();
    
    private IngredientRepo repository;

    public IngredientController(IngredientRepo repository) {
        this.repository = repository;
    }
    
    @Override
    public List<Ingredient> getIngredients() throws Exception {
        if (cache.isEmpty()) {
            List<Ingredient> ingredients = repository.getIngredients();
            ingredients.forEach((i) -> cache.put(i.getIngredientId(), i));
        }
        
        return new ArrayList<>(cache.values());
    }

    @Override
    public long saveIngredient(Ingredient ingredient) throws Exception {
        ingredient.validate();
        
        if (ingredient.getIngredientId() == 0) {
            ingredient.setIngredientId(new Date().getTime());
        }
        
        repository.save(ingredient);
        cache.put(ingredient.getIngredientId(), ingredient);
        return ingredient.getIngredientId();
    }

    @Override
    public void deleteIngredient(long id) throws Exception {
        repository.delete(id);
        cache.remove(id);
    }
}
