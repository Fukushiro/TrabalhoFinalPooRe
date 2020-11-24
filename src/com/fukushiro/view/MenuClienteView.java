/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fukushiro.view;

import com.fukushiro.controller.MenuClienteController;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author jpflc
 */
public class MenuClienteView extends javax.swing.JFrame {

    private MenuClienteController controller;
    /**
     * Creates new form MenuClienteView
     */
    public MenuClienteView() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.controller = new MenuClienteController(this);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        Dinheiro = new javax.swing.JMenu();
        menuItemDinheiroDepositar = new javax.swing.JMenuItem();
        MenuLoja = new javax.swing.JMenu();
        menuItemLojaComprar = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuItemComprasVizualizar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 696, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        Dinheiro.setText("Dinheiro");

        menuItemDinheiroDepositar.setText("Depositar");
        menuItemDinheiroDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemDinheiroDepositarActionPerformed(evt);
            }
        });
        Dinheiro.add(menuItemDinheiroDepositar);

        jMenuBar1.add(Dinheiro);

        MenuLoja.setText("Loja");

        menuItemLojaComprar.setText("comprar");
        menuItemLojaComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemLojaComprarActionPerformed(evt);
            }
        });
        MenuLoja.add(menuItemLojaComprar);

        jMenuBar1.add(MenuLoja);

        jMenu1.setText("Carrinho");

        jMenuItem1.setText("Gerenciar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Compras");

        menuItemComprasVizualizar.setText("Vizualizar");
        menuItemComprasVizualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemComprasVizualizarActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemComprasVizualizar);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktop)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemDinheiroDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemDinheiroDepositarActionPerformed
        this.controller.abrirDepositar();
    }//GEN-LAST:event_menuItemDinheiroDepositarActionPerformed

    private void menuItemLojaComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLojaComprarActionPerformed
        this.controller.abrirLojaComprar();
    }//GEN-LAST:event_menuItemLojaComprarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.controller.abrirGerenciarCarrinho();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuItemComprasVizualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemComprasVizualizarActionPerformed
        this.controller.abrirGerenciarCompras();
    }//GEN-LAST:event_menuItemComprasVizualizarActionPerformed

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
            java.util.logging.Logger.getLogger(MenuClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuClienteView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Dinheiro;
    private javax.swing.JMenu MenuLoja;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem menuItemComprasVizualizar;
    private javax.swing.JMenuItem menuItemDinheiroDepositar;
    private javax.swing.JMenuItem menuItemLojaComprar;
    // End of variables declaration//GEN-END:variables

    public MenuClienteController getController() {
        return controller;
    }

    public void setController(MenuClienteController controller) {
        this.controller = controller;
    }

    public JMenu getDinheiro() {
        return Dinheiro;
    }

    public void setDinheiro(JMenu Dinheiro) {
        this.Dinheiro = Dinheiro;
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    public void setDesktop(JDesktopPane desktop) {
        this.desktop = desktop;
    }

    public JMenuBar getjMenuBar1() {
        return jMenuBar1;
    }

    public void setjMenuBar1(JMenuBar jMenuBar1) {
        this.jMenuBar1 = jMenuBar1;
    }

    public JMenuItem getMenuItemDinheiroDepositar() {
        return menuItemDinheiroDepositar;
    }

    public void setMenuItemDinheiroDepositar(JMenuItem menuItemDinheiroDepositar) {
        this.menuItemDinheiroDepositar = menuItemDinheiroDepositar;
    }

   
}
