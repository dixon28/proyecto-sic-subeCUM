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
import java.util.logging.Level;
import java.util.logging.Logger;
import otros.conexionBD;

/**
 *
 * @author sic
 */
public class operacionesCierre {
    ArrayList<SubCuenta> cuentasAlRegistro= new ArrayList<>();
    conexionBD conect = new conexionBD();
    ListaLibroDiario list= new ListaLibroDiario();
    
    public operacionesCierre() {
  
        
    }
    
   public void crearcopia(int id)
           {
    
          cuentasAlRegistro.clear();
        String sql="select *from subcuenta";
        String sqlenvio= "insert into historial_cuentas(id,idsubcuenta,debe,haber) values(?,?,?,?)";
        
        try {
            PreparedStatement sentenciaExtraccion= this.conect.getConexion().prepareStatement(sql);
            
            ResultSet resultado=sentenciaExtraccion.executeQuery();
            while (resultado.next()) {
           PreparedStatement sentenciaEnvio= this.conect.getConexion().prepareStatement(sqlenvio);
           
           sentenciaEnvio.setInt(1, id);
           sentenciaEnvio.setInt(2,resultado.getInt("idsubcuenta"));
           sentenciaEnvio.setDouble(3,resultado.getDouble("debe"));
           sentenciaEnvio.setDouble(4,resultado.getDouble("haber"));
           sentenciaEnvio.execute();
           
           
           
           
                   
                
            }
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(operacionesCierre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
   
   
   
 public void  recuperar(int id){//con este id del libro diario que se desea recuperar 
     
     
       String sql="select *from historial_cuentas where id=?";
        String sqlenvio= "update subcuenta set debe=? , haber=? where idsubcuenta=?";
        
        try {
            PreparedStatement sentenciaExtraccion= this.conect.getConexion().prepareStatement(sql);
            sentenciaExtraccion.setInt(1, id);
            ResultSet resultado=sentenciaExtraccion.executeQuery();
            while (resultado.next()) {
           PreparedStatement sentenciaEnvio= this.conect.getConexion().prepareStatement(sqlenvio);
           
           sentenciaEnvio.setDouble(1, resultado.getDouble("debe"));
           sentenciaEnvio.setDouble(2, resultado.getDouble("haber"));
           sentenciaEnvio.setInt(3, resultado.getInt("idsubcuenta"));
         
           sentenciaEnvio.execute();
           
           
           
           
                   
                
            }
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(operacionesCierre.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
     
     
     
     
     
     
     
     
 }
     public void cuentasliquidadas(){//solo suma debes y haber en realidad no liquida
         String SqlCuentasacer="update cuenta set debe_cuenta=0, haber_cuenta=0";
         String sqlSubcuentas="select *from subcuenta";
         String sql="UPDATE cuenta set debe_cuenta=debe_cuenta+?  , haber_cuenta=haber_cuenta+? where idcuenta=?";
        try {
            PreparedStatement extraerSubcuentas=this.conect.getConexion().prepareStatement(sqlSubcuentas);
          PreparedStatement vaciar= this.conect.getConexion().prepareStatement( SqlCuentasacer);
          vaciar.execute();
            
            ResultSet resultado1= extraerSubcuentas.executeQuery();
            while(resultado1.next())
                {
                    
                    
                     PreparedStatement actualizar= this.conect.getConexion().prepareStatement(sql); 
                     actualizar.setDouble(1,resultado1.getDouble("debe"));
                     actualizar.setDouble(2, resultado1.getDouble("haber"));
                     actualizar.setInt(3, resultado1.getInt("idcuenta"));
                     actualizar.execute();
                    
                }
                
            
            

        } catch (SQLException ex) {
            Logger.getLogger(operacionesCierre.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
         
     }
                
    
     
     
     public void cuentasliquidadasGenerales(){//aqui se cumple la cuenta liquidada o bien queda el haber o no
         String sql= "Select *from cuenta";
         String sqlcasodebe= "Update cuenta set debe_cuenta=debe_cuenta-haber_cuenta , haber_cuenta=0 where idcuenta=?";
         String sqlcasoHaber= "Update cuenta set debe_cuenta=0 , haber_cuenta=haber_cuenta-debe_cuenta where idcuenta=?";
         String sqlcasoIgual= "Update cuenta set debe_cuenta=0 , haber_cuenta=0 where idcuenta=?";
         
        try {
            PreparedStatement sentencia= this.conect.getConexion().prepareStatement(sql);
            ResultSet resultado= sentencia.executeQuery();
            
            while(resultado.next())
              {
                  if(resultado.getDouble("debe_cuenta")>resultado.getDouble("haber_cuenta"))
                        {
                            //caso debe
                            PreparedStatement sentenciaDebe= this.conect.getConexion().prepareStatement(sqlcasodebe);
                             sentenciaDebe.setInt(1, resultado.getInt("idcuenta"));
                             sentenciaDebe.execute();
                        }
                  else
                      {
                          if(resultado.getDouble("haber_cuenta")>resultado.getDouble("debe_cuenta"))
                          {
                              
                              //caso haber
                              PreparedStatement sentenciaHaber = this.conect.getConexion().prepareStatement(sqlcasoHaber);
                              
                              sentenciaHaber.setInt(1, resultado.getInt("idcuenta"));
                              sentenciaHaber.execute()
                                      ;
                              
                          }
                          else
                               {
                                   
                                //caso igual
                                   
                                 PreparedStatement sentenciaigual= this.conect.getConexion().prepareStatement(sqlcasoIgual)
                                         ;
                                 sentenciaigual.setInt(1, resultado.getInt("idcuenta"));
                                         
                                   sentenciaigual.execute();
                                   
                                   
                                   
                               } 
                          
                          
                          
                      }
                      
                      
              }  
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(operacionesCierre.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
         
     }
     
     
     
     public void cuentasLiquidasDespuesER()
             
     {

     String sql="update cuenta set debe_cuenta=0, haber_cuenta=0 where idcuenta=5101 or idcuenta=4103 or idcuenta=4110 or idcuenta=4107 or idcuenta=4108 or idcuenta=4109";
        try {
            PreparedStatement sentencia= this.conect.getConexion().prepareStatement(sql);
            sentencia.execute();
           
        } catch (SQLException ex) {
            Logger.getLogger(operacionesCierre.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }
     
     public void cuentasLiquidadasDespuesEC()
             {
                
                 
String sql="update cuenta set debe_cuenta=0, haber_cuenta=0 where idcuenta=4201 or idcuenta=3201";
PreparedStatement sentencia;
        try {
            sentencia = conect.getConexion().prepareStatement(sql);
            sentencia.execute();
        } catch (SQLException ex) {
            Logger.getLogger(operacionesCierre.class.getName()).log(Level.SEVERE, null, ex);
        }


//codigo para limpiar cuentas que participan en el estado capital
                 
             }
     
     
     
     
     public void restablecer()
             {
                 
                  String sqlPararecuperar="select *from historial_cuentas where id=(select max(id) from librodiario)";
                 String sqlParaLlenar= "update subcuenta set debe=? , haber=? where idsubcuenta=?";
                 String sqlFormateadoCuentas="update  cuenta set debe_cuenta=0, haber_cuenta=0";
        try {
            PreparedStatement recuperar= conect.getConexion().prepareStatement(sqlPararecuperar);
             PreparedStatement borrar=conect.getConexion().prepareStatement(sqlFormateadoCuentas);
                 ResultSet resultado=  recuperar.executeQuery();
                 borrar.execute();
               while(resultado.next())
               {
                     PreparedStatement mapearBase= conect.getConexion().prepareStatement(sqlParaLlenar);
                     mapearBase.setDouble(1, resultado.getDouble("debe"));
                     mapearBase.setDouble(2, resultado.getDouble("haber"));
                     
                     mapearBase.execute();
                   
               }
               
               
        } catch (SQLException ex) {
            Logger.getLogger(operacionesCierre.class.getName()).log(Level.SEVERE, null, ex);
        }
              
                         
             }
             
     
    
}
