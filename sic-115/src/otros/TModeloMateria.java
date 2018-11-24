/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import MateriaPrima.Registro_Kardex;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Zoila Villatoro
 */
public class TModeloMateria extends AbstractTableModel{
 public List<Registro_Kardex> kardex = new ArrayList<Registro_Kardex>();
    public int getRowCount() {
        return kardex.size();

    }

    public int getColumnCount() {
        return 6;

    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Registro_Kardex registro= kardex.get(rowIndex);
        Object valor = null;
        switch (columnIndex) {
            case 0:
                valor =registro.getIdOrden();
                break;
            case 1:
                valor=registro.getIdregistro();
                break;
            case 2:
                valor = registro.getNombreMateria();
                break;
            case 3:
                valor = registro.getCantidad();
                break;
            case 4:
                valor= registro.getCostoUnitario();
                break;
            case 5:
                valor = registro.getCostoTotal();

        }

        return valor;

    } 
}
