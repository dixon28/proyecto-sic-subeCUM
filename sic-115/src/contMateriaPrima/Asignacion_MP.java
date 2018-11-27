/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contMateriaPrima;

import MateriaPrima.KardexMovimientos;
import MateriaPrima.Registro_Kardex;
import MateriaPrima.listaSalidasKardex;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import otros.TModeloMateria;
import otros.allFrames;
import otros.conexionBD;

/**
 *
 * @author Zoila Villatoro
 */
public class Asignacion_MP extends javax.swing.JFrame {

    conexionBD conexionCuentas = new conexionBD();
    TModeloMateria modelMateria = new TModeloMateria();
    public listaSalidasKardex listaC = new listaSalidasKardex();
    ArrayList<Registro_Kardex> list = listaC.getAsignacion();
    String idOrden = null;
    String idReg = null;
    String tipMate = null;
    String canti = null;
    String costo = null;
    String costoTo = null;
    Integer Cantidad=0;
    Integer tipo = null;
    int i = 0;

    /**
     * Creates new form Asignacion_MP
     */
    public Asignacion_MP() {
        initComponents();
        allFrames.aplicarTema(this);
        new allFrames().CargarIcono(this);
        FillcomboOrden();
        FillcomboTipo();
        inicializarColumnas();
        refrescarTabla();
        this.setLocationRelativeTo(null);
        this.setTitle("Menu Principal para Asignacion de Materia Prima");
        this.setResizable(false);
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
        jLabel2 = new javax.swing.JLabel();
        comboOrden = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        tcantidad = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMateria = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        prueba = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/insert_to_shopping_basket.png"))); // NOI18N
        jLabel1.setText("Asignación de Materia Prima");

        jLabel2.setText("Numero de Orden:");

        comboOrden.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Tipo de Materia Prima:");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoActionPerformed(evt);
            }
        });

        jLabel4.setText("Cantidad:");

        tcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tcantidadKeyTyped(evt);
            }
        });

        tablaMateria.setModel(modelMateria);
        tablaMateria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMateriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaMateria);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/shopping_basket_accept.png"))); // NOI18N
        jButton3.setText("Asignar");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/remove_from_shopping_basket.png"))); // NOI18N
        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        prueba.setText("jLabel5");

        jLabel5.setText("Unidad de Medida:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(74, 74, 74)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(prueba, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prueba)
                    .addComponent(jLabel5))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton1))
                .addGap(133, 133, 133))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        String mat = (String) comboTipo.getSelectedItem();
        String sql = "SELECT idtipomat FROM tipo_materia WHERE nombremateria= ? ;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setString(1, mat);

            //declaracion.execute();
            ResultSet result1 = declaracion.executeQuery();
            while (result1.next()) {
                tipo = result1.getInt("idtipomat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String Orden = (String) comboOrden.getSelectedItem();
        Integer idOrden = Integer.parseInt(Orden);
        Cantidad = Integer.parseInt(tcantidad.getText());
        if (String.valueOf(Cantidad).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese cantidad");
        } else {
            KardexMovimientos kardex = new KardexMovimientos();
            kardex.AsignacionMa_Orden(idOrden, tipo, Cantidad);
            JOptionPane.showMessageDialog(this, "La asignación fue guardado con exito");
            this.modelMateria.kardex.clear();
            refrescarTabla();
        }

    }//GEN-LAST:event_jButton3MouseClicked

    private void tablaMateriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMateriaMouseClicked
        i = tablaMateria.getSelectedRow();
        idOrden = tablaMateria.getValueAt(i, 0).toString();
        idReg = tablaMateria.getValueAt(i, 1).toString();
        tipMate = tablaMateria.getValueAt(i, 2).toString();
        canti = tablaMateria.getValueAt(i, 3).toString();
        costo = tablaMateria.getValueAt(i, 4).toString();
        costoTo = tablaMateria.getValueAt(i, 5).toString();


    }//GEN-LAST:event_tablaMateriaMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        KardexMovimientos kardex = new KardexMovimientos();
        kardex.EliminarSalida(Integer.parseInt(idOrden), Integer.parseInt(idReg));
        kardex.RegresarAInventario(Integer.parseInt(idReg), tipMate, Integer.parseInt(canti), Double.parseDouble(costo), Double.parseDouble(costoTo));
        kardex.EliminarSalidaEnTabla(tipMate);
        this.modelMateria.kardex.clear();
        refrescarTabla();
        JOptionPane.showMessageDialog(this, "Elimino con Exito");

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         String mat = (String) comboTipo.getSelectedItem();
        Integer tipo = null;
        String sql = "SELECT idtipomat FROM tipo_materia WHERE nombremateria= ? ;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setString(1, mat);

            //declaracion.execute();
            ResultSet result1 = declaracion.executeQuery();
            while (result1.next()) {
                tipo = result1.getInt("idtipomat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String Orden = (String) comboOrden.getSelectedItem();
        Integer idOrden = Integer.parseInt(Orden);
        KardexMovimientos kardex = new KardexMovimientos();
        kardex.GuardarCostoMP(Integer.parseInt(Orden));
        kardex.CambiarEstadoMPEnTablaSalida(idOrden);
        JOptionPane.showMessageDialog(this, "Ha guardado los Costos de Materia Prima de esta Orden");
        refrescarTabla();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tcantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcantidadKeyTyped
        int k=(int)evt.getKeyChar();
        //Primer if no permite el ingreso de letras y otros símbolos
        if ((k >= 32 && k <= 45) ||(k >= 58 && k <= 126)  ){
            evt.setKeyChar((char)KeyEvent.VK_CLEAR);
            evt.consume();
        }
        //Segundo if no permite el ingreso de "ñ" ," Ñ" ni "/"
        if(k==241 || k==209|| k==47){
            evt.setKeyChar((char)KeyEvent.VK_CLEAR);
            evt.consume();
        }
    }//GEN-LAST:event_tcantidadKeyTyped

    private void comboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoActionPerformed
        try {
            String name = (String) comboTipo.getSelectedItem();
            String sql = "SELECT unidaddemedida FROM tipo_materia  where nombremateria=?";
            PreparedStatement s = this.conexionCuentas.getConexion().prepareStatement(sql);
            s.setString(1, name);
            ResultSet r = s.executeQuery();
            while (r.next()) {
                this.prueba.setText(r.getString("unidaddemedida"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdquisicionMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboTipoActionPerformed

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
            java.util.logging.Logger.getLogger(Asignacion_MP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asignacion_MP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asignacion_MP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asignacion_MP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asignacion_MP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboOrden;
    private javax.swing.JComboBox comboTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel prueba;
    private javax.swing.JTable tablaMateria;
    private javax.swing.JTextField tcantidad;
    // End of variables declaration//GEN-END:variables
private void FillcomboOrden() {
        try {
            comboOrden.removeAllItems();
            String sqlGeneral = "select * from orden";
            PreparedStatement declaracion1;
            declaracion1 = null;
            declaracion1 = this.conexionCuentas.getConexion().prepareCall(sqlGeneral);
            ResultSet resultado1 = declaracion1.executeQuery();
            while (resultado1.next()) {
                String name = resultado1.getString("id_orden");
                comboOrden.addItem(name);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void FillcomboTipo() {
        try {
            comboTipo.removeAllItems();
            String sqlGeneral = "select * from tipo_materia";
            PreparedStatement declaracion1;
            declaracion1 = null;
            declaracion1 = this.conexionCuentas.getConexion().prepareCall(sqlGeneral);
            ResultSet resultado1 = declaracion1.executeQuery();
            while (resultado1.next()) {
                String name = resultado1.getString("nombremateria");
                comboTipo.addItem(name);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void inicializarColumnas() {
        TableColumnModel tColumnModel = new DefaultTableColumnModel();
        for (int i = 0; i < 6; i++) {
            TableColumn col = new TableColumn(i);
            switch (i) {
                case 0:
                    col.setHeaderValue("Número de Orden");
                    break;
                case 1:
                    col.setHeaderValue("Número de Registro");
                    break;
                case 2:
                    col.setHeaderValue("Tipo de Materia Prima");
                    break;
                case 3:
                    col.setHeaderValue("Cantidad");
                    break;
                case 4:
                    col.setHeaderValue("Costo Unitario");
                    break;
                case 5:
                    col.setHeaderValue("Costo Total");

            }
            tColumnModel.addColumn(col);
        }
        this.tablaMateria.setColumnModel(tColumnModel);

    }

    public void refrescarTabla() {

        this.list.clear();
        this.listaC.generarLista();
        list = listaC.getListaRegistro();
        modelMateria.kardex.clear();
        for (Registro_Kardex c : this.list) {
            this.modelMateria.kardex.add(c);
            System.err.println(c.getNombreMateria());
        }
        modelMateria.fireTableDataChanged();
    }
}
