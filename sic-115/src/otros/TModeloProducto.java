/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import contGeneral.ProductoTerminado;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Zoila Villatoro
 */
public class TModeloProducto extends AbstractTableModel {

    public List<ProductoTerminado> productos = new ArrayList<ProductoTerminado>();

    @Override
    public int getRowCount() {
        return productos.size();

    }

    @Override
    public int getColumnCount() {

        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductoTerminado p = productos.get(rowIndex);
        Object valor = null;
        switch (columnIndex) {
            case 0:
                valor = p.getIdOrden();
                break;
            case 1:
                valor = p.getPan();
                break;
            case 2:
                valor = p.getCantidad();
                break;
            case 3:
                valor = p.getCostoUni();
                break;
            case 4:
                valor = p.getCostoTotal();

        }
        return valor;
    }
}
