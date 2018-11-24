/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import contGeneral.PlanillaOrden;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alejandor
 */
public class TModeloPlanOrden extends AbstractTableModel {
    public List<PlanillaOrden> ordenes = new ArrayList<PlanillaOrden>();

    public int getRowCount() {
        return ordenes.size();

    }

    public int getColumnCount() {
        return 6;

    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        PlanillaOrden orden = ordenes.get(rowIndex);
        Object valor = null;
        switch (columnIndex) {
            case 0:
                valor = orden.getId_empleado();
                break;
            case 1:
                valor = orden.getNombre();
                break;
            case 2:
                valor = orden.getApellido();
                break;
            case 3:
                valor = orden.getId_orden();
                break;
            case 4:
                valor = orden.getSalario();
                break;
            case 5:
                valor=orden.getNumHoras();

        }

        return valor;

    }
}
