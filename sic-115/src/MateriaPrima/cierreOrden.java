/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MateriaPrima;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import otros.conexionBD;

/**
 *
 * @author sic
 */
public class cierreOrden {

    conexionBD conect = new conexionBD();

    public void recuperarBagguette() {

        try {
            String sql = "SELECT sum( producto_t.costototal) FROM   public.producto_t where producto_t.tipopan='Baguette';";
            String sql2 = "Update subcuenta SET debe=debe+? where idsubcuenta=110410";
            String sql3 = "Update subcuenta  SET haber=haber+? where idsubcuenta=110101";
            PreparedStatement sentencia = this.conect.getConexion().prepareStatement(sql);
            ResultSet res = sentencia.executeQuery();

            while (res.next()) {

                PreparedStatement sentencia1 = this.conect.getConexion().prepareStatement(sql2);
                sentencia1.setDouble(1, res.getDouble("sum"));
                sentencia1.execute();

                PreparedStatement sentencia2 = this.conect.getConexion().prepareStatement(sql3);

                sentencia2.setDouble(1, res.getDouble("sum"));
                sentencia2.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(cierreOrden.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void recuperarGalleta() {

        try {
            String sql = "SELECT sum( producto_t.costototal) FROM   public.producto_t where producto_t.tipopan='Galleta Maria';";
            String sql2 = "Update subcuenta SET debe=debe+? where idsubcuenta=110411";
            String sql3 = "Update subcuenta  SET haber=haber+? where idsubcuenta=110101";
            PreparedStatement sentencia = this.conect.getConexion().prepareStatement(sql);
            ResultSet res = sentencia.executeQuery();

            while (res.next()) {

                PreparedStatement sentencia1 = this.conect.getConexion().prepareStatement(sql2);
                sentencia1.setDouble(1, res.getDouble("sum"));
                sentencia1.execute();

                PreparedStatement sentencia2 = this.conect.getConexion().prepareStatement(sql3);

                sentencia2.setDouble(1, res.getDouble("sum"));
                sentencia2.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(cierreOrden.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void RegistrarUsuario(String nombre, String pass, String id, Integer valor) {
        Integer v = 0;
        String sql1 = "SELECT count (*) FROM users WHERE id_usuario =?; ";

        try {
            PreparedStatement declaracion = conect.getConexion().prepareStatement(sql1);
            declaracion.setString(1, id);
            ResultSet resultado1 = declaracion.executeQuery();
            while (resultado1.next()) {
                v = resultado1.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (v == 0) {
            if (valor == 1) {
                String sql = "INSERT INTO users (nombres,contrasenia,id_usuario,administrador) "
                        + "VALUES (?,?,?,TRUE);";
                try {
                    PreparedStatement declaracion = conect.getConexion().prepareStatement(sql);
                    declaracion.setString(1, nombre);
                    declaracion.setString(2, pass);
                    declaracion.setString(3, id);
                    declaracion.execute();
                    JOptionPane.showMessageDialog(null, "Ingresado con exito");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {

                String sql = "INSERT INTO users (nombres,contrasenia,id_usuario) "
                        + "VALUES (?,?,?);";
                try {
                    PreparedStatement declaracion = conect.getConexion().prepareStatement(sql);
                    declaracion.setString(1, nombre);
                    declaracion.setString(2, pass);
                    declaracion.setString(3, id);
                    declaracion.execute();
                    JOptionPane.showMessageDialog(null, "Ingresado con exito");     
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ya Existe un Usuario,por favor cambie su nombre de Usuario");
        }

    }
}
