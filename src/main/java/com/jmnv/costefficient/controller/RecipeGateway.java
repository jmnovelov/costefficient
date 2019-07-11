/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.controller;

import com.jmnv.costefficient.model.Recipe;
import com.jmnv.costefficient.model.RecipeDetail;
import java.util.List;

/**
 *
 * @author jmnov
 */
public interface RecipeGateway {
    
    List<Recipe> getRecipes() throws Exception;
    
    List<RecipeDetail> getDetails(long id) throws Exception;
    
    long saveRecipe(Recipe recipe, List<RecipeDetail> details) throws Exception;
    
    void deleteRecipe(long id) throws Exception;
}
