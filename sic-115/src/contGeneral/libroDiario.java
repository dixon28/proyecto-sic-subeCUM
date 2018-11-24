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
import java.util.Date;
import otros.conexionBD;

/**
 *
 * @author sic
 */
public class libroDiario {
private Integer id;  
private Date fechainicio;
private Date fechafin;
private Boolean cerrado;

 
  
    public libroDiario() {
        

        
    }
       
  
       
       

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        if(this.getCerrado()){
            
        return "Periodo contable: " + fechainicio + " Hasta " + fechafin + " Cerrado" ;
        }
        
        else
            {
         return "Periodo contable: " + fechainicio + " Hasta " + fechafin + " Activo" ;  
            }
            
    }


    
    
    
    
}
