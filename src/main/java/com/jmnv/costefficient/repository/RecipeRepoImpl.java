/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.repository;

import com.jmnv.costefficient.model.Ingredient;
import com.jmnv.costefficient.model.Recipe;
import com.jmnv.costefficient.model.RecipeDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jmnov
 */
public class RecipeRepoImpl implements RecipeRepo {

    private static final String INSERT = "INSERT INTO recipes (id, name, price, instructions) VALUES (?, ?, ?, ?)";
    
    private static final String UPDATE = "UPDATE recipes SET name = ?, price = ?, instructions = ? WHERE id = ?";
    
    private static final String DELETE = "DELETE FROM recipes WHERE id = ?";
    
    private static final String INSERT_DETAIL = "INSERT INTO recipe_details (recipe_id, ingredient_id, units) VALUES (?, ?, ?)";
    
    private static final String UPDATE_DETAIL = "UPDATE recipe_details SET units = ? WHERE recipe_id = ? AND ingredient_id = ?";
    
    private static final String DELETE_DETAILS = "DELETE FROM recipe_details WHERE recipe_id = ? AND ingredient_id NOT IN (%s)";
    
    private static final String GET_ALL = 
            "SELECT r.id, r.name, r.price, r.instructions, sum(rd.units * i.cost_per_unit) as cost " +
            "FROM recipes r " +
            "JOIN recipe_details rd ON r.id = rd.recipe_id " +
            "JOIN ingredients i ON rd.ingredient_id = i.id " +
            "GROUP BY r.id";
    
    private static final String GET_DETAILS = 
            "SELECT i.id, i.name, i.unit_name, i.cost_per_unit, rd.units " +
            "FROM ingredients i " +
            "JOIN recipe_details rd ON rd.ingredient_id = i.id " +
            "WHERE rd.recipe_id = ?";
    
    @Override
    public void saveRecipe(Recipe recipe) throws SQLException {
        Connection conn = ConnectionSingleton.getConnection();
        
        try (PreparedStatement stmt = conn.prepareStatement(INSERT)) {
            stmt.setLong(1, recipe.getId());
            stmt.setString(2, recipe.getName());
            stmt.setDouble(3, recipe.getPrice());
            stmt.setString(4, recipe.getInstructions());
            stmt.execute();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 19) {
                //#define SQLITE_CONSTRAINT  19   /* Abort due to constraint violation */
                try (PreparedStatement stmt = conn.prepareStatement(UPDATE)) {
                    stmt.setString(1, recipe.getName());
                    stmt.setDouble(2, recipe.getPrice());
                    stmt.setString(3, recipe.getInstructions());
                    stmt.setLong(4, recipe.getId());
                    stmt.execute();
                }
            } else {
                throw ex;
            }
        }
    }

    @Override
    public void saveDetail(long id, RecipeDetail detail) throws SQLException{
        Connection conn = ConnectionSingleton.getConnection();
        
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_DETAIL)) {
            stmt.setLong(1, id);
            stmt.setLong(2, detail.getIngredient().getIngredientId());
            stmt.setDouble(3, detail.getUnits());
            stmt.execute(); 
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 19) {
                //#define SQLITE_CONSTRAINT  19   /* Abort due to constraint violation */
                try (PreparedStatement stmt = conn.prepareStatement(UPDATE_DETAIL)) {
                    stmt.setDouble(1, detail.getUnits());
                    stmt.setLong(2, id);
                    stmt.setLong(3, detail.getIngredient().getIngredientId());
                    stmt.execute();
                }
            } else {
                throw ex;
            }
        }
    }

    @Override
    public void deleteDetails(long recipeId, List<Long> ingredients) throws SQLException {
        final StringBuilder builder = new StringBuilder();
        ingredients.forEach((ingredientId) -> builder.append(ingredientId).append(","));
        String ingredientIds = builder.substring(0, builder.length() - 1);
        Connection conn = ConnectionSingleton.getConnection();
        
        try (PreparedStatement stmt = conn.prepareStatement(String.format(DELETE_DETAILS, ingredientIds))) {
            stmt.setLong(1, recipeId);
            stmt.execute();
        }
    }

    @Override
    public List<Recipe> getAll() throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        Connection conn = ConnectionSingleton.getConnection();
        
        try (Statement stmt = conn.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(GET_ALL)) {
                while (rs.next()) {
                    Recipe recipe = new Recipe();
                    recipe.setId(rs.getLong("id"));
                    recipe.setName(rs.getString("name"));
                    recipe.setPrice(rs.getDouble("price"));
                    recipe.setInstructions(rs.getString("instructions"));
                    recipe.setCost(rs.getDouble("cost"));
                    recipes.add(recipe);
                }
            }
        }
        
        return recipes;
    }
    
    public List<RecipeDetail> getDetails(long id) throws SQLException {
        List<RecipeDetail> details = new ArrayList<>();
        Connection conn = ConnectionSingleton.getConnection();
        
        try (PreparedStatement stmt = conn.prepareStatement(GET_DETAILS)) {
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setIngredientId(rs.getLong("id"));
                    ingredient.setName(rs.getString("name"));
                    ingredient.setUnitName(rs.getString("unit_name"));
                    ingredient.setCostPerUnit(rs.getDouble("cost_per_unit"));
                    RecipeDetail detail = new RecipeDetail();
                    detail.setIngredient(ingredient);
                    detail.setUnits(rs.getDouble("units"));
                    details.add(detail);
                }
            }
        }
        
        return details;
    }

    @Override
    public void deleteRecipe(long id) throws SQLException {
        Connection conn = ConnectionSingleton.getConnection();
        
        try (PreparedStatement stmt = conn.prepareStatement(DELETE)) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }
}
