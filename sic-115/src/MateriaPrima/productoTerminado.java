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
import otros.conexionBD;

/**
 *
 * @author sic
 */
public class productoTerminado {
    conexionBD con=new conexionBD();
    public void baguette()
    {
        try {
            String  sqlExtract="select sum(costototal) as valor from producto_t where tipopan='Baguette';";
            String slqEnvio1="update subcuenta set debe=debe+? where idsubcuenta='110410'";
            String slqEnvio2="update subcuenta set haber= haber+? where idsubcuenta='110101'";
            PreparedStatement sentencia= con.getConexion().prepareStatement(sqlExtract);
            
            ResultSet res= sentencia.executeQuery();
            while(res.next()){
            PreparedStatement sentencia1= con.getConexion().prepareStatement(slqEnvio1);
            sentencia1.setDouble(1, res.getDouble("valor"));
            
            PreparedStatement sentencia2= con.getConexion().prepareStatement(slqEnvio2);
            sentencia2.setDouble(1, res.getDouble("valor"));
            
            sentencia1.execute();
            sentencia2.execute();
            
            
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(productoTerminado.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    
    public void galletaMaria()
    {
    
   
    try {  String  sqlExtract="select sum(costototal) as valor from producto_t where tipopan='Galleta Maria';";
 String slqEnvio1="update subcuenta set debe=debe+? where idsubcuenta='110411'";
 String slqEnvio2="update subcuenta set haber= haber+? where idsubcuenta='110101'";
 
        
        
        
            PreparedStatement sentencia= con.getConexion().prepareStatement(sqlExtract);
            
            ResultSet res= sentencia.executeQuery();
            while(res.next()){
            PreparedStatement sentencia1= con.getConexion().prepareStatement(slqEnvio1);
            sentencia1.setDouble(1, res.getDouble("valor"));
            
            PreparedStatement sentencia2= con.getConexion().prepareStatement(slqEnvio2);
            sentencia2.setDouble(1, res.getDouble("valor"));
            
            sentencia1.execute();
            sentencia2.execute();
            
            
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(productoTerminado.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    
    
    
    public void productoTerminadoVacio()
    {
    
        try {
            PreparedStatement sentencia= con.getConexion().prepareStatement("Delete from producto_t");
            sentencia.execute();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(productoTerminado.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    }
    
    
    
    
    
}
