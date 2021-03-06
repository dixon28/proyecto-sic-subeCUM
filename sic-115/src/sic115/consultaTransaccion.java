/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sic115;

import contGeneral.ListaLibroDiario;
import contGeneral.libroDiario;
import contGeneral.transaccion;
import contGeneral.transaccionesOperaciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import otros.TModeloLibro;
import otros.allFrames;
import otros.conexionBD;

/**
 *
 * @author sic
 */
public class consultaTransaccion extends javax.swing.JFrame {

    TModeloLibro modelConsultar = new TModeloLibro();
    conexionBD conex = new conexionBD();

    /**
     * Creates new form consultaTransaccion
     */
    public consultaTransaccion() {
        initComponents();
        allFrames.aplicarTema(this);
        new allFrames().CargarIcono(this);
        inicializarColumnas();
        llenarCB();
        this.setLocationRelativeTo(null);
        this.setTitle("Consultar Transacción");
        this.setResizable(false);
        //btnconsulta.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        comboBoxLibros = new javax.swing.JComboBox<>();
        btnCargarLibro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTransaccion = new javax.swing.JTable();
        btnconsulta = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Periodo contable:");

        comboBoxLibros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxLibrosActionPerformed(evt);
            }
        });

        btnCargarLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/database_upload.png"))); // NOI18N
        btnCargarLibro.setText("Cargar");
        btnCargarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarLibroActionPerformed(evt);
            }
        });

        tablaTransaccion.setModel(modelConsultar);
        jScrollPane1.setViewportView(tablaTransaccion);

        btnconsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search_page.png"))); // NOI18N
        btnconsulta.setText("Ver Detalle");
        btnconsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconsultaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/book_search.png"))); // NOI18N
        jLabel2.setText("Consultar Transacción");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(comboBoxLibros, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCargarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnconsulta))
                            .addComponent(jLabel2))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboBoxLibros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCargarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxLibrosActionPerformed

    }//GEN-LAST:event_comboBoxLibrosActionPerformed

    private void btnCargarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarLibroActionPerformed
        llenartabla();

    }//GEN-LAST:event_btnCargarLibroActionPerformed

    private void btnconsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconsultaActionPerformed
        int indexselect, index;

        indexselect = tablaTransaccion.getSelectedRow();
        index = (Integer) tablaTransaccion.getValueAt(indexselect, 0);
        Date fecha = (Date) tablaTransaccion.getValueAt(indexselect, 1);
        String desc = (String) tablaTransaccion.getValueAt(indexselect, 2);
        if (indexselect != -1) {

            mostrarTransaccion mostrar = new mostrarTransaccion(fecha, desc, index);
            mostrar.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna transacción para mostrar");
        }


    }//GEN-LAST:event_btnconsultaActionPerformed

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
            java.util.logging.Logger.getLogger(consultaTransaccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consultaTransaccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consultaTransaccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultaTransaccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new consultaTransaccion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargarLibro;
    private javax.swing.JButton btnconsulta;
    private javax.swing.JComboBox<String> comboBoxLibros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaTransaccion;
    // End of variables declaration//GEN-END:variables

    private void inicializarColumnas() {
        TableColumnModel tColumnModel = new DefaultTableColumnModel();
        for (int i = 0; i < 3; i++) {
            TableColumn col = new TableColumn(i);
            switch (i) {
                case 0:
                    col.setHeaderValue("Codigo");
                    break;
                case 1:
                    col.setHeaderValue("Fecha");
                    break;
                case 2:
                    col.setHeaderValue("Descripción");
                    break;
            }
            tColumnModel.addColumn(col);
        }
        this.tablaTransaccion.setColumnModel(tColumnModel);
    }

    private void llenarCB() {

        DefaultComboBoxModel value;
        this.comboBoxLibros.removeAllItems();
        try {
            comboBoxLibros.removeAllItems();
            String sqlGeneral = "select *from librodiario";
            PreparedStatement declaracion1;
            declaracion1 = null;
            declaracion1 = this.conex.getConexion().prepareCall(sqlGeneral);
            ResultSet resultado1 = declaracion1.executeQuery();
            value = new DefaultComboBoxModel();
            comboBoxLibros.setModel(value);
            while (resultado1.next()) {
                libroDiario aux = new libroDiario();
                aux.setId(resultado1.getInt("id"));
                aux.setFechainicio(resultado1.getDate("fechainicio"));
                aux.setFechafin(resultado1.getDate("fechafin"));
                aux.setCerrado(resultado1.getBoolean("cerrado"));
                value.addElement(aux);
            }
        } catch (SQLException ex) {

        }
    }

    private void llenartabla() {
        int index;
        libroDiario lib = new libroDiario();
        lib = (libroDiario) this.comboBoxLibros.getSelectedItem();
        index = lib.getId();
        this.modelConsultar.trans.clear();
        try {
            String sql = "select * from transaccion where id =? ;";
            PreparedStatement declaracion1;
            declaracion1 = null;

            declaracion1 = this.conex.getConexion().prepareCall(sql);
            declaracion1.setInt(1, index);
            ResultSet resultado1 = declaracion1.executeQuery();

            while (resultado1.next()) {
                transaccion t = new transaccion();
                t.setId(resultado1.getInt("id"));
                t.setIdtransaccion(resultado1.getInt("idtransaccion"));
                t.setFecha(resultado1.getDate("fecha"));
                t.setDescripcion(resultado1.getString("descripcion"));
                this.modelConsultar.trans.add(t);
            }
            modelConsultar.fireTableDataChanged();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("no realizado");
        }

    }
}
