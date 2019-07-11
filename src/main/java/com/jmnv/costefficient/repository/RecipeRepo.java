/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.repository;

import com.jmnv.costefficient.model.Recipe;
import com.jmnv.costefficient.model.RecipeDetail;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jmnov
 */
public interface RecipeRepo {
    
    void saveRecipe(Recipe recipe) throws SQLException;
    
    void saveDetail(long id, RecipeDetail detail) throws SQLException;
    
    void deleteDetails(long recipeId, List<Long> ingredients) throws SQLException;
    
    void deleteRecipe(long id) throws SQLException;
    
    List<Recipe> getAll() throws SQLException;
    
    List<RecipeDetail> getDetails(long id) throws SQLException;
}
