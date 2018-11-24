/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import contGeneral.Cuenta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Erick
 */
public class TModeloCatalogo extends AbstractTableModel {

    public List<Cuenta> cuentas = new ArrayList<Cuenta>();

    @Override
    public int getRowCount() {
        return cuentas.size();

    }

    @Override
    public int getColumnCount() {
        return 3;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cuenta cuenta = cuentas.get(rowIndex);
        Object valor = null;
        switch (columnIndex) {
            case 0:
                valor = cuenta.getCodigo();
                break;
            case 1:
                valor = cuenta.getNombre();
                break;
            case 2:
                valor = cuenta.getEspecificacion();

        }

        return valor;

    }

}
