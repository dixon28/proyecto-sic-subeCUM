/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import MateriaPrima.kardexAux;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sic
 */
public class TModeloKardex extends AbstractTableModel{
    
    
  public  ArrayList<kardexAux> listaRegK= new ArrayList<kardexAux>();

    @Override
    public int getRowCount() {
return listaRegK.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
        
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

    kardexAux registro = listaRegK.get(rowIndex);
 Object valor = null;
 switch(columnIndex){
 case 0: valor =registro.getFecha();
    break;
 case 1: valor = registro.getCantE();
    break;
 case 2: valor= registro.getCuE();
    break;
 case 3: valor= registro.getCtE();
    break;
 case 4: valor=registro.getCantS();
    break;
 case 5: valor= registro.getCuS();
    break;
 case 6: valor=registro.getCtS();
    break;
 case 7: valor=registro.getCantSaldo();
    break;
 case 8: valor=registro.getCuSaldo();
    break;
 case 9: valor= registro.getCtSaldo();
    break;       
 } 
 return valor;
}
    
    
    
    
    
    
    
    
    
}
