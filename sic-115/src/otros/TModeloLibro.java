/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import contGeneral.libroDiario;
import contGeneral.transaccion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sic
 */
public class TModeloLibro extends AbstractTableModel{
    public List<transaccion> trans= new ArrayList<transaccion>();
    
    public int getRowCount() {
        return trans.size();

    }
    public int getColumnCount() {
        return 3;
    }

  
    public Object getValueAt(int rowIndex, int columnIndex) {
        transaccion t = trans.get(rowIndex);
        Object valor = null;
        switch(columnIndex){
            case 0: valor = t.getIdtransaccion();
            break;
            case 1: valor = t.getFecha();
            break;
            case 2: valor = t.getDescripcion();   
        }
    
        return valor;
}
}
