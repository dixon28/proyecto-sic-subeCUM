/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MateriaPrima;

import contGeneral.CostosPlanilla;
import contGeneral.ListaLibroDiario;
import contGeneral.libroDiario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import otros.conexionBD;

/**
 *
 * @author Zoila Villatoro
 */
public class OpCierreMP {
    conexionBD conect=new conexionBD();
ListaLibroDiario list=  new ListaLibroDiario();
    libroDiario libroactual=list.getActivoActual();
    
   
    
   
   public void recuperarMPHarina()
   {
   
        try {//110404
            String sqlenvio="UPDATE subcuenta SET debe=debe+? where idsubcuenta=110404";// inventarios cargados
            String sqlenvio2="UPDATE subcuenta SET haber=haber+? where idsubcuenta=110101";
            String sql="select costototal as costofinal from registro_kardex where id_registro=( select max(id_registro) from registro_kardex where idtipomat=1 and entrada=true);";
            PreparedStatement sentencia=  this.conect.getConexion().prepareStatement(sql);
              PreparedStatement sentenciaenv1=  this.conect.getConexion().prepareStatement(sqlenvio);
                PreparedStatement sentenciaenv2=  this.conect.getConexion().prepareStatement(sqlenvio2);
            ResultSet res=    sentencia.executeQuery();
            while(res.next()){
            sentenciaenv1.setDouble(1,res.getDouble("costofinal"));
            sentenciaenv2.setDouble(1,res.getDouble("costofinal"));
            sentenciaenv1.execute();
            sentenciaenv2.execute();
            } 
           
        } catch (SQLException ex) {
            Logger.getLogger(OpCierreMP.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   
   
   }
    
     public void recuperarMantecaMP()
   {
   
        try {//110404
            String sqlenvio="UPDATE subcuenta SET debe=debe+? where idsubcuenta=110405";// inventarios cargados
            String sqlenvio2="UPDATE subcuenta SET haber=haber+? where idsubcuenta=110101";
            String sql="select costototal as costofinal from registro_kardex where id_registro=( select max(id_registro) from registro_kardex where idtipomat=2 and entrada=true);";
            PreparedStatement sentencia=  this.conect.getConexion().prepareStatement(sql);
              PreparedStatement sentenciaenv1=  this.conect.getConexion().prepareStatement(sqlenvio);
                PreparedStatement sentenciaenv2=  this.conect.getConexion().prepareStatement(sqlenvio2);
            ResultSet res=    sentencia.executeQuery();
            while(res.next()){
            sentenciaenv1.setDouble(1,res.getDouble("costofinal"));
            sentenciaenv2.setDouble(1,res.getDouble("costofinal"));
            sentenciaenv1.execute();
            sentenciaenv2.execute();
            } 
           
        } catch (SQLException ex) {
            Logger.getLogger(OpCierreMP.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   
   
   }
     
         public void recuperarMPAzucar()
   {
   
        try {//110404
            String sqlenvio="UPDATE subcuenta SET debe=debe+? where idsubcuenta=110406";// inventarios cargados
            String sqlenvio2="UPDATE subcuenta SET haber=haber+? where idsubcuenta=110101";
            String sql="select costototal as costofinal from registro_kardex where id_registro=( select max(id_registro) from registro_kardex where idtipomat=3 and entrada=true);";
            PreparedStatement sentencia=  this.conect.getConexion().prepareStatement(sql);
              PreparedStatement sentenciaenv1=  this.conect.getConexion().prepareStatement(sqlenvio);
                PreparedStatement sentenciaenv2=  this.conect.getConexion().prepareStatement(sqlenvio2);
            ResultSet res=    sentencia.executeQuery();
            while(res.next()){
            sentenciaenv1.setDouble(1,res.getDouble("costofinal"));
            sentenciaenv2.setDouble(1,res.getDouble("costofinal"));
            sentenciaenv1.execute();
            sentenciaenv2.execute();
            } 
           
        } catch (SQLException ex) {
            Logger.getLogger(OpCierreMP.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   
   
   }
          public void recuperarMpsal()
   {
   
        try {//110404
            String sqlenvio="UPDATE subcuenta SET debe=debe+? where idsubcuenta=110407";// inventarios cargados
            String sqlenvio2="UPDATE subcuenta SET haber=haber+? where idsubcuenta=110101";
            String sql="select costototal as costofinal from registro_kardex where id_registro=( select max(id_registro) from registro_kardex where idtipomat=4 and entrada=true);";
            PreparedStatement sentencia=  this.conect.getConexion().prepareStatement(sql);
              PreparedStatement sentenciaenv1=  this.conect.getConexion().prepareStatement(sqlenvio);
                PreparedStatement sentenciaenv2=  this.conect.getConexion().prepareStatement(sqlenvio2);
            ResultSet res=    sentencia.executeQuery();
            while(res.next()){
            sentenciaenv1.setDouble(1,res.getDouble("costofinal"));
            sentenciaenv2.setDouble(1,res.getDouble("costofinal"));
            sentenciaenv1.execute();
            sentenciaenv2.execute();
            } 
           
        } catch (SQLException ex) {
            Logger.getLogger(OpCierreMP.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   
   
   }
     
          
               public void recuperarMPLevadura()
   {
   
        try {//110404
            String sqlenvio="UPDATE subcuenta SET debe=debe+? where idsubcuenta=110408";// inventarios cargados
            String sqlenvio2="UPDATE subcuenta SET haber=haber+? where idsubcuenta=110101";
            String sql="select costototal as costofinal from registro_kardex where id_registro=( select max(id_registro) from registro_kardex where idtipomat=5 and entrada=true);";
            PreparedStatement sentencia=  this.conect.getConexion().prepareStatement(sql);
              PreparedStatement sentenciaenv1=  this.conect.getConexion().prepareStatement(sqlenvio);
                PreparedStatement sentenciaenv2=  this.conect.getConexion().prepareStatement(sqlenvio2);
            ResultSet res=    sentencia.executeQuery();
            while(res.next()){
            sentenciaenv1.setDouble(1,res.getDouble("costofinal"));
            sentenciaenv2.setDouble(1,res.getDouble("costofinal"));
            sentenciaenv1.execute();
            sentenciaenv2.execute();
            } 
           
        } catch (SQLException ex) {
            Logger.getLogger(OpCierreMP.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   
   
   }
     
       public void recuperarMPHuevos()
   {
   
        try {//110404
            String sqlenvio="UPDATE subcuenta SET debe=debe+? where idsubcuenta=110409";// inventarios cargados
            String sqlenvio2="UPDATE subcuenta SET haber=haber+? where idsubcuenta=110101";
            String sql="select costototal as costofinal from registro_kardex where id_registro=( select max(id_registro) from registro_kardex where idtipomat=6 and entrada=true);";
            PreparedStatement sentencia=  this.conect.getConexion().prepareStatement(sql);
              PreparedStatement sentenciaenv1=  this.conect.getConexion().prepareStatement(sqlenvio);
                PreparedStatement sentenciaenv2=  this.conect.getConexion().prepareStatement(sqlenvio2);
            ResultSet res=    sentencia.executeQuery();
            while(res.next()){
            sentenciaenv1.setDouble(1,res.getDouble("costofinal"));
            sentenciaenv2.setDouble(1,res.getDouble("costofinal"));
            sentenciaenv1.execute();
            sentenciaenv2.execute();
            } 
           
        } catch (SQLException ex) {
            Logger.getLogger(OpCierreMP.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   
   
   }
       
       
       
     public void copiaKardex(){
   
        try {
            String sql1="select *from registro_kardex where id_registro=( select max(id_registro) from registro_kardex where idtipomat=1 and entrada=true);";
             String sql2="select *from registro_kardex where id_registro=( select max(id_registro) from registro_kardex where idtipomat=2 and entrada=true);";
             
              String sql3="select  *from registro_kardex where id_registro=( select max(id_registro) from registro_kardex where idtipomat=3 and entrada=true);";
               String sql4="select  *from registro_kardex where id_registro=( select max(id_registro) from registro_kardex where idtipomat=4 and entrada=true);";
                String sql5="select  *from registro_kardex where id_registro=( select max(id_registro) from registro_kardex where idtipomat=5 and entrada=true);";
                 String sql6="select * from registro_kardex where id_registro=( select max(id_registro) from registro_kardex where idtipomat=6 and entrada=true);";
                 
            PreparedStatement sentencia=  this.conect.getConexion().prepareStatement(sql1);
            PreparedStatement sentencia2= this.conect.getConexion().prepareStatement(sql2);
            
            PreparedStatement sentencia3= this.conect.getConexion().prepareStatement(sql3);
            
            PreparedStatement sentencia4= this.conect.getConexion().prepareStatement(sql4);
           
            PreparedStatement sentencia5= this.conect.getConexion().prepareStatement(sql5);
            
            PreparedStatement sentencia6= this.conect.getConexion().prepareStatement(sql6);
            
            ResultSet resultado1= sentencia.executeQuery();
            
            ResultSet resultado2= sentencia2.executeQuery();
            
            ResultSet resultado3= sentencia3.executeQuery();
            
            ResultSet resultado4= sentencia4.executeQuery();
            
            ResultSet resultado5= sentencia5.executeQuery();
            
            ResultSet resultado6= sentencia6.executeQuery();
            
            while(resultado1.next())
            {
             PreparedStatement sentenciaenv1= this.conect.getConexion().prepareStatement("insert into historial_kardex(id_libro,materia_id,cantidad,costo_total,costo_unitario) values(?,?,?,?,?)");
            sentenciaenv1.setInt(1,list.getActivoActual().getId());
            sentenciaenv1.setInt(2,resultado1.getInt("idtipomat"));
            sentenciaenv1.setInt(3,resultado1.getInt("cantidad_mat"));
            sentenciaenv1.setDouble(4,resultado1.getDouble("costototal"));
            sentenciaenv1.setDouble(5,resultado1.getDouble("costouni_mat"));
            sentenciaenv1.execute();
            
            }
            
            while(resultado2.next())
            {
                PreparedStatement sentenciaenv2= this.conect.getConexion().prepareStatement("insert into historial_kardex(id_libro,materia_id,cantidad,costo_total,costo_unitario) values(?,?,?,?,?)");
            sentenciaenv2.setInt(1,list.getActivoActual().getId());
            sentenciaenv2.setInt(2,resultado2.getInt("idtipomat"));
            sentenciaenv2.setInt(3,resultado2.getInt("cantidad_mat"));
            sentenciaenv2.setDouble(4,resultado2.getDouble("costototal"));
            sentenciaenv2.setDouble(5,resultado2.getDouble("costouni_mat"));
            sentenciaenv2.execute();
            
            
            }
            while(resultado3.next())
            {
                PreparedStatement sentenciaenv3= this.conect.getConexion().prepareStatement("insert into historial_kardex(id_libro,materia_id,cantidad,costo_total,costo_unitario) values(?,?,?,?,?)");
            sentenciaenv3.setInt(1,list.getActivoActual().getId());
            sentenciaenv3.setInt(2,resultado3.getInt("idtipomat"));
            sentenciaenv3.setInt(3,resultado3.getInt("cantidad_mat"));
            sentenciaenv3.setDouble(4,resultado3.getDouble("costototal"));
            sentenciaenv3.setDouble(5,resultado3.getDouble("costouni_mat"));
            sentenciaenv3.execute();
            
            
            }
           
            while(resultado4.next())
            {
            
                  PreparedStatement sentenciaenv4= this.conect.getConexion().prepareStatement("insert into historial_kardex(id_libro,materia_id,cantidad,costo_total,costo_unitario) values(?,?,?,?,?)");
            sentenciaenv4.setInt(1,list.getActivoActual().getId());
            sentenciaenv4.setInt(2,resultado4.getInt("idtipomat"));
            sentenciaenv4.setInt(3,resultado4.getInt("cantidad_mat"));
            sentenciaenv4.setDouble(4,resultado4.getDouble("costototal"));
            sentenciaenv4.setDouble(5,resultado4.getDouble("costouni_mat"));
            sentenciaenv4.execute();
            
            }
            
            while(resultado5.next())
            {
            
                  PreparedStatement sentenciaenv5= this.conect.getConexion().prepareStatement("insert into historial_kardex(id_libro,materia_id,cantidad,costo_total,costo_unitario) values(?,?,?,?,?)");
            sentenciaenv5.setInt(1,list.getActivoActual().getId());
            sentenciaenv5.setInt(2,resultado5.getInt("idtipomat"));
            sentenciaenv5.setInt(3,resultado5.getInt("cantidad_mat"));
            sentenciaenv5.setDouble(4,resultado5.getDouble("costototal"));
            sentenciaenv5.setDouble(5,resultado5.getDouble("costouni_mat"));
            sentenciaenv5.execute();
            
            }
            
            
            
            
            while(resultado6.next())
            {
                  PreparedStatement sentenciaenv6= this.conect.getConexion().prepareStatement("insert into historial_kardex(id_libro,materia_id,cantidad,costo_total,costo_unitario) values(?,?,?,?,?)");
            sentenciaenv6.setInt(1,list.getActivoActual().getId());
            sentenciaenv6.setInt(2,resultado6.getInt("idtipomat"));
            sentenciaenv6.setInt(3,resultado6.getInt("cantidad_mat"));
            sentenciaenv6.setDouble(4,resultado6.getDouble("costototal"));
            sentenciaenv6.setDouble(5,resultado6.getDouble("costouni_mat"));
            sentenciaenv6.execute();
            
            
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OpCierreMP.class.getName()).log(Level.SEVERE, null, ex);
        }
             
     
     }  
       
     public void vaciarkardex(){
     
        try {
            String sql="delete from salidas_kardex ";
            String sql2="delete from entradas";
            String sql3="delete from salida ";
            String sql4="delete from registro_kardex";
            
            PreparedStatement sent1= conect.getConexion().prepareStatement(sql);
            PreparedStatement sent2= conect.getConexion().prepareStatement(sql2);
            PreparedStatement sent3= conect.getConexion().prepareStatement(sql3);
            PreparedStatement sent4= conect.getConexion().prepareStatement(sql4);
            
            
            sent1.execute();
            sent2.execute();
            sent3.execute();
            sent4.execute();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OpCierreMP.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
     
     
     
     
     
     
     
     }  
     
     
     
     public void recuperarMPanteriorAkardex(Date fecha)
     {
     KardexMovimientos kardex= new KardexMovimientos();
        try {
            String sql= "SELECT   * FROM  public.historial_kardex where id_libro=(select max(id_libro) from historial_kardex)";
            String sqlenviados="";
            PreparedStatement sentencia= this.conect.getConexion().prepareStatement(sql);
            ResultSet res= sentencia.executeQuery();
            
            while(res.next())
            {
        
                kardex.agregarMateria(res.getInt("materia_id"),res.getInt("cantidad"), res.getDouble("costo_unitario"), fecha.getTime());
                kardex.AgregarMateriaPrimaEntradas(res.getInt("materia_id"),res.getInt("cantidad"), res.getDouble("costo_unitario"), fecha.getTime());
                    
            
            
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OpCierreMP.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      
      
      

 

     
     }
       
       
       
       
       
       
       
       
       
       
       
             
}
