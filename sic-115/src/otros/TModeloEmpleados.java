/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import contGeneral.Empleado;
import contGeneral.Orden;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Zoila Villatoro
 */
public class TModeloEmpleados extends AbstractTableModel {

    public List<Empleado> empleados = new ArrayList<Empleado>();
    public List<Orden> ordenes = new ArrayList<Orden>();

    public int getRowCount() {
        return empleados.size();

    }
public int getRowCount2() {
        return ordenes.size();

    }
    public int getColumnCount() {
        return 5;

    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Empleado empleado = empleados.get(rowIndex);
        Object valor = null;
        switch (columnIndex) {
            case 0:
                valor = empleado.getDui();
                break;
            case 1:
                valor = empleado.getNombre();
                break;
            case 2:
                valor = empleado.getCargo();
                break;
            case 3:
                valor= empleado.getIdOrden();
                break;
            case 4:
                valor=empleado.getDescripcionOrden();

        }

        return valor;

    }
}
