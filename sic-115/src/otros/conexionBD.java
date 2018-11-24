/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erick
 */

public class conexionBD {
 private Connection conexion;

    public conexionBD() {
      
    }

    public Connection getConexion() {
         conectar();
        return conexion;
    }

    private void conectar() {
if(conexion==null)
    {
        try {
    
 conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/panesal", "postgres","admin");

 } catch (SQLException ex) {
 Logger.getLogger(conexionBD.class.getName()).log(Level.SEVERE, null, ex);
 
 }
}

    }
  

public void desconectar()  {
     try {
         conexion.close();
     } catch (SQLException ex) {
         Logger.getLogger(conexionBD.class.getName()).log(Level.SEVERE, null, ex);
     }
        }
        

     

}
