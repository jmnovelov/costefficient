/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jmnv.costefficient.ui;

import com.jmnv.costefficient.controller.IngredientController;
import com.jmnv.costefficient.controller.IngredientGateway;
import com.jmnv.costefficient.controller.RecipeController;
import com.jmnv.costefficient.controller.RecipeGateway;
import com.jmnv.costefficient.model.Recipe;
import com.jmnv.costefficient.repository.IngredientRepoImpl;
import com.jmnv.costefficient.repository.RecipeRepoImpl;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jmnov
 */
public class FrmMain extends javax.swing.JFrame {

    private RecipeGateway recipeGateway;
    
    private IngredientGateway ingredientGateway;
    
    private TableRowSorter<RecipeTableModel> sorter;
    
    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
        initComponents();
        setLocationRelativeTo(null);
        recipeGateway = new RecipeController(new RecipeRepoImpl());
        ingredientGateway = new IngredientController(new IngredientRepoImpl());
        setControlsEnabled(false);
        sorter = new TableRowSorter<>(recipeTableModel);
        tblRecipes.setRowSorter(sorter);
        initialLoad();
        tblRecipes.setDefaultRenderer(Double.class, new DefaultTableCellRenderer() {
            
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                int model = table.convertRowIndexToModel(row);
                Recipe recipe = recipeTableModel.get(model);
                
                if (!isSelected) {
                    setForeground(recipe.getPrice() < recipe.getCost()? Color.RED : Color.BLACK);
                } else {
                    setForeground(recipe.getPrice() < recipe.getCost()? Color.PINK : Color.WHITE);
                }
                    
                if (value != null)
                    setText(String.format("$%.2f", (Double) value));
                else
                    setText("");
                
                return this;
            }
        });
    }
    
    private void initialLoad() {
        new SwingWorker<List<Recipe>, Void>() {
            @Override
            protected List<Recipe> doInBackground() throws Exception {
                return recipeGateway.getRecipes();
            }

            @Override
            protected void done() {
                try {
                    recipeTableModel.clear();
                    recipeTableModel.addAll(get());
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    setControlsEnabled(true);
                }
            }
        }.execute();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        recipeTableModel = new com.jmnv.costefficient.ui.RecipeTableModel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRecipes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtSearchRecipe = new javax.swing.JTextField();
        btnRemoveRecipe = new javax.swing.JButton();
        btnAddRecipe = new javax.swing.JButton();
        btnIngredients = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Costos vs Precios");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cupcake.jpg"))); // NOI18N

        tblRecipes.setModel(recipeTableModel);
        tblRecipes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRecipesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRecipes);

        jLabel2.setText("Buscar Receta:");

        txtSearchRecipe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchRecipeKeyReleased(evt);
            }
        });

        btnRemoveRecipe.setText("Eliminar Receta");
        btnRemoveRecipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveRecipeActionPerformed(evt);
            }
        });

        btnAddRecipe.setText("Agregar Receta");
        btnAddRecipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRecipeActionPerformed(evt);
            }
        });

        btnIngredients.setText("Administrar Ingredientes");
        btnIngredients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngredientsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnIngredients)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddRecipe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveRecipe))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchRecipe, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtSearchRecipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemoveRecipe)
                    .addComponent(btnAddRecipe)
                    .addComponent(btnIngredients))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddRecipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRecipeActionPerformed
        DlgRecipe dialog = new DlgRecipe(this, recipeGateway, ingredientGateway, null, (r) -> {
            recipeTableModel.add(r);
        });
        dialog.setVisible(true);
    }//GEN-LAST:event_btnAddRecipeActionPerformed

    private void tblRecipesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRecipesMouseClicked
        if (evt.getClickCount() >= 2) {
            int viewRow = tblRecipes.getSelectedRow();
            int modelRow = tblRecipes.convertRowIndexToModel(viewRow);
            Recipe recipe = recipeTableModel.get(modelRow);
            DlgRecipe dialog = new DlgRecipe(this, recipeGateway, ingredientGateway, recipe, (r) -> {
                recipeTableModel.set(viewRow, r);
            });
            
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_tblRecipesMouseClicked

    private void txtSearchRecipeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchRecipeKeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_txtSearchRecipeKeyReleased

    private void btnRemoveRecipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveRecipeActionPerformed
        // TODO add your handling code here:
        final int row = tblRecipes.getSelectedRow();
        final int modelRow = tblRecipes.convertRowIndexToModel(row);
        
        if (modelRow >= 0) {
            final Recipe recipe = recipeTableModel.get(modelRow);
            int choice = JOptionPane.showConfirmDialog(this, "Esta seguro que quiere eliminar la receta: " + recipe.getName());

            if (choice == JOptionPane.YES_OPTION) {
                setControlsEnabled(false);
                
                new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        recipeGateway.deleteRecipe(recipe.getId());
                        return null;
                    }

                    @Override
                    protected void done() {
                        try {
                            get();
                            recipeTableModel.deleteRow(modelRow);
                            JOptionPane.showMessageDialog(FrmMain.this,
                                    "Receta eliminada exitosamente",
                                    "Resultado",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(FrmMain.this, 
                                    "Hubo un problema al eliminar la receta: "
                                            + ex.getMessage(), 
                                    "Advertencia", 
                                    JOptionPane.WARNING_MESSAGE);
                        } finally {
                            setControlsEnabled(true);
                        }
                    }
                }.execute();
            }
        }
    }//GEN-LAST:event_btnRemoveRecipeActionPerformed

    private void btnIngredientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngredientsActionPerformed
        DlgIngredient dialog = new DlgIngredient(this, ingredientGateway, (i) -> {});
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                initialLoad();
            }
        });
        dialog.setVisible(true);
    }//GEN-LAST:event_btnIngredientsActionPerformed

    private void search() {
        String search = txtSearchRecipe.getText().trim();
        
        if (search.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + search));
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMain().setVisible(true);
            }
        });
    }
    
    public void setControlsEnabled(boolean enabled) {
        txtSearchRecipe.setEnabled(enabled);
        tblRecipes.setEnabled(enabled);
        btnAddRecipe.setEnabled(enabled);
        btnRemoveRecipe.setEnabled(enabled);
        btnIngredients.setEnabled(enabled);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddRecipe;
    private javax.swing.JButton btnIngredients;
    private javax.swing.JButton btnRemoveRecipe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.jmnv.costefficient.ui.RecipeTableModel recipeTableModel;
    private javax.swing.JTable tblRecipes;
    private javax.swing.JTextField txtSearchRecipe;
    // End of variables declaration//GEN-END:variables
}
