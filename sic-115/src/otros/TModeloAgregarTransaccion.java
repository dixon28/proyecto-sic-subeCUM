/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import contGeneral.SubCuenta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sic
 */
public class TModeloAgregarTransaccion extends AbstractTableModel {
    public List<SubCuenta> scuentas= new ArrayList<SubCuenta>();
    @Override
    public int getRowCount() {
        return scuentas.size();

    }

    @Override
    public int getColumnCount() {
        return 4;
  
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SubCuenta sub = scuentas.get(rowIndex);
 Object valor = null;
 switch(columnIndex){
  case 0: valor = sub.getIdsubcuenta();
 break;
 case 1: valor = sub.getNombre();
 break;
 case 2: valor = sub.getDebe_cuenta();
 break;
 case 3: valor=sub.getHaber_cuenta();
 
     
 }
        
        
        return valor;

    }
    
    
}
