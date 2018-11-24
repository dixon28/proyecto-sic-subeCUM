/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contGeneral;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import otros.conexionBD;

/**
 *
 * @author Zoila Villatoro
 */
public class CuentaConsultas {

    conexionBD conexionCuentas = new conexionBD();
    Cuenta cuenta = new Cuenta();
    SubCuenta subcuen =new SubCuenta();
    int tipo;

    public void guardar(String codigo, String nombre, String especificacion, String tipo) {
        cuenta.setCodigo(Integer.parseInt(codigo));
        cuenta.setNombre(nombre);
        cuenta.setEspecificacion(especificacion);
        cuenta.setTipo(Integer.parseInt(tipo));

        String sql = "INSERT INTO cuenta(idcuenta,idtipo,nombrecuenta,debe_cuenta,haber_cuenta,descripcion) VALUES (?,?,?,0,0,?);";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, cuenta.getCodigo());
            declaracion.setInt(2, cuenta.getTipo());
            declaracion.setString(3, cuenta.getNombre());
            declaracion.setString(4, cuenta.getEspecificacion());

            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificar(String codigo, String nombre,String descripcion,String tipo) {
        String tipo1="Cuenta";
        String tipo2="Subcuenta";
        String sentencia="";    
        
        if(tipo==tipo1){
        sentencia="UPDATE cuenta set nombrecuenta=? ,descripcion=? where idcuenta= ? ;";
        }else{
        sentencia="UPDATE subcuenta set nombre=? ,descripcion=? where idsubcuenta= ? ;";
        }
        ;
        try {
            cuenta.setCodigo(Integer.parseInt(codigo));
            cuenta.setNombre(nombre);
            cuenta.setEspecificacion(descripcion);
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sentencia);
            declaracion.setString(1, cuenta.getNombre());
            declaracion.setString(2, cuenta.getEspecificacion());
            declaracion.setInt(3, cuenta.getCodigo());
            declaracion.executeUpdate();
        } catch (SQLException e) {
          
            e.printStackTrace();
        }
        
        /*subcuen.setIdsubcuenta(Integer.parseInt(codigo));
            subcuen.setNombre(nombre);
            subcuen.setDescripcion(descripcion);
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql1);
            declaracion.setString(1, subcuen.getNombre());
            declaracion.setString(2,subcuen.getDescripcion());
            declaracion.setInt(3, cuenta.getCodigo());
            declaracion.executeUpdate();*/
    }
    
     public void guardarSub(String codigo,String cuenta,String nombre,String descripcion) {
        subcuen.setIdsubcuenta(Integer.parseInt(codigo));
        subcuen.setIdcuenta(Integer.parseInt(cuenta));
        subcuen.setNombre(nombre);
        subcuen.setDebe_cuenta(0);
        subcuen.setHaber_cuenta(0);
        subcuen.setDescripcion(descripcion);

        String sql = "INSERT INTO subcuenta(idsubcuenta,idcuenta,nombre,debe,haber,descripcion) VALUES (?,?,?,?,?,?);";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, subcuen.getIdsubcuenta());
            declaracion.setInt(2, subcuen.getIdcuenta());
            declaracion.setString(3, subcuen.getNombre());
            declaracion.setFloat(4, subcuen.getDebe_cuenta());
            declaracion.setFloat(5, subcuen.getHaber_cuenta());
            declaracion.setString(6, subcuen.getDescripcion());

            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public void consultarDescripcion(String codigo, String nombre){
    
}
}
