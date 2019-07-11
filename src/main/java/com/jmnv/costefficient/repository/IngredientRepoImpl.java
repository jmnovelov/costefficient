/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.repository;

import com.jmnv.costefficient.model.Ingredient;
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
public class IngredientRepoImpl implements IngredientRepo {
    
    private static final String SELECT_ALL = "SELECT * FROM ingredients";
    
    private static final String INSERT = "INSERT INTO ingredients (id, name, unit_name, cost_per_unit) VALUES (?, ?, ?, ?)";
    
    private static final String UPDATE = "UPDATE ingredients SET name = ?, unit_name = ?, cost_per_unit = ? WHERE id = ?";
    
    private static final String DELETE = "DELETE FROM ingredients WHERE id = ?";
    
    @Override
    public List<Ingredient> getIngredients() throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        
        Connection conn = ConnectionSingleton.getConnection();
        
        try (Statement stmt = conn.createStatement()) {
            try (ResultSet result = stmt.executeQuery(SELECT_ALL)) {
                while (result.next()) {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setIngredientId(result.getLong("id"));
                    ingredient.setName(result.getString("name"));
                    ingredient.setUnitName(result.getString("unit_name"));
                    ingredient.setCostPerUnit(result.getDouble("cost_per_unit"));
                    ingredients.add(ingredient);
                }
            }
        }
        
        return ingredients;
    }

    @Override
    public void save(Ingredient ingredient) throws SQLException {
        Connection conn = ConnectionSingleton.getConnection();
        
        try (PreparedStatement stmt = conn.prepareStatement(INSERT)) {
            stmt.setLong(1, ingredient.getIngredientId());
            stmt.setString(2, ingredient.getName());
            stmt.setString(3, ingredient.getUnitName());
            stmt.setDouble(4, ingredient.getCostPerUnit());
            stmt.execute();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 19) {
                //#define SQLITE_CONSTRAINT  19   /* Abort due to constraint violation */
                try (PreparedStatement stmt = conn.prepareStatement(UPDATE)) {
                    stmt.setString(1, ingredient.getName());
                    stmt.setString(2, ingredient.getUnitName());
                    stmt.setDouble(3, ingredient.getCostPerUnit());
                    stmt.setLong(4, ingredient.getIngredientId());
                    stmt.execute();
                }
            } else {
                throw ex;
            }
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        Connection conn = ConnectionSingleton.getConnection();
        
        try (PreparedStatement stmt = conn.prepareStatement(DELETE)) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }
}
