/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contGeneral;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
  import otros.conexionBD;
import java.util.Date;
/**
 *
 * @author sic
 */
public class ListaLibroDiario {
 ArrayList<libroDiario> ActivosEinactivos = new ArrayList<libroDiario>();
libroDiario ActivoActual = new libroDiario();
 
  conexionBD conect= new conexionBD();

  
  
  
  
  
  
    public ArrayList<libroDiario> getActivosEinactivos() {
       
          String sql="select *from librodiario";
     this.ActivosEinactivos.clear();
         try {
            PreparedStatement declaracion1;
            declaracion1 = null;
      
            declaracion1 = this.conect.getConexion().prepareStatement(sql);
            ResultSet resultado1 = declaracion1.executeQuery();
         
            while (resultado1.next()) 
            {
                    libroDiario aux= new libroDiario();
          aux.setFechainicio(resultado1.getDate("fechainicio"));
         aux.setFechafin(resultado1.getDate("fechafin"));
         aux.setCerrado(resultado1.getBoolean("cerrado"));
         aux.setId(resultado1.getInt("id"));
      
            this.ActivosEinactivos.add(aux);
            }
          
           
        } catch (SQLException ex) {

            ex.printStackTrace();

        }
              
        
        
        return ActivosEinactivos;
    }

    public void setActivosEinactivos(ArrayList<libroDiario> ActivosEinactivos) {
       
        
        
        
        this.ActivosEinactivos = ActivosEinactivos;
    }

    public libroDiario getActivoActual() {
         libroDiario aux= new libroDiario();
     this.ActivosEinactivos.clear();
         try {
            PreparedStatement declaracion1;
            declaracion1 = null;
          
          String sql="select *from librodiario";
            declaracion1 = this.conect.getConexion().prepareStatement(sql);
            ResultSet resultado1 = declaracion1.executeQuery();
         
            while (resultado1.next()) 
            {
                if(!resultado1.getBoolean("cerrado"))
                    
            {
          aux.setFechainicio(resultado1.getDate("fechainicio"));
         aux.setFechafin(resultado1.getDate("fechafin"));
         aux.setCerrado(resultado1.getBoolean("cerrado"));
         aux.setId(resultado1.getInt("id"));
            
           }
            }
          
           
        } catch (SQLException ex) {

            ex.printStackTrace();

        }
              
        
        
        
        return aux;
    }

 
  

  
    public void cerrarLibro() throws SQLException
            
    {
        
        String sql="update librodiario set cerrado=true";
        PreparedStatement sentencia= this.conect.getConexion().prepareStatement(sql);
        
        sentencia.executeUpdate();
        
    }
    
    
    
    
}
