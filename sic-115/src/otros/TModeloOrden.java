/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import contGeneral.Orden;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Zoila Villatoro
 */
public class TModeloOrden extends AbstractTableModel {

    public List<Orden> ordenes = new ArrayList<Orden>();

    @Override
    public int getRowCount() {
        return ordenes.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Orden orden = ordenes.get(rowIndex);
        Object valor = null;
        switch (columnIndex) {
            case 0:
                valor = orden.getIdOrden();
                break;
            case 1:
                valor = orden.getNombrePan();
                break;
            case 2:
                valor = orden.getCostos_mp();
                break;
            case 3:
                valor = orden.getCostos_indirectos();
                break;
            case 4:
                valor = orden.getMOD();
                

        }

        return valor;

    }

}
