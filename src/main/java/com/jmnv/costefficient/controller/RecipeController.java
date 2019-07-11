/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.controller;

import com.jmnv.costefficient.model.Recipe;
import com.jmnv.costefficient.model.RecipeDetail;
import com.jmnv.costefficient.repository.ConnectionSingleton;
import com.jmnv.costefficient.repository.RecipeRepo;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jmnov
 */
public class RecipeController implements RecipeGateway {

    final private RecipeRepo repository;

    public RecipeController(RecipeRepo repository) {
        this.repository = repository;
    }
    
    @Override
    public List<Recipe> getRecipes() throws Exception {
        return repository.getAll();
    }

    @Override
    public List<RecipeDetail> getDetails(long id) throws Exception {
        return repository.getDetails(id);
    }

    @Override
    public long saveRecipe(Recipe recipe, List<RecipeDetail> details) throws Exception {
        recipe.validate();
        
        if (recipe.getId() == 0) {
            long id = new Date().getTime();
            recipe.setId(id);
        }
        
        Connection conn = ConnectionSingleton.getConnection();
        
        try {
            conn.setAutoCommit(false);
            List<Long> ingredientIds = new ArrayList<>();
            details.forEach((detail) -> ingredientIds.add(detail.getIngredient().getIngredientId()));
            repository.saveRecipe(recipe);
            repository.deleteDetails(recipe.getId(), ingredientIds);
            
            for (RecipeDetail detail : details)
                repository.saveDetail(recipe.getId(), detail);
            
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
            
        double cost = 0;

        for (RecipeDetail detail : details)
            cost += detail.getUnits() * detail.getIngredient().getCostPerUnit();

        recipe.setCost(cost);
        return recipe.getId();
    }

    @Override
    public void deleteRecipe(long id) throws Exception {
        repository.deleteRecipe(id);
    }
}
