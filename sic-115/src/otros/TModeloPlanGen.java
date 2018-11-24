/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import contGeneral.PlanillaGeneral;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alejandor
 */
public class TModeloPlanGen extends AbstractTableModel{
    public List<PlanillaGeneral> planilla = new ArrayList<PlanillaGeneral>();
    public int getRowCount() {
        return planilla.size();

    }

    public int getColumnCount() {
        return 5;

    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        PlanillaGeneral orden= planilla.get(rowIndex);
        Object valor = null;
        switch (columnIndex) {
            case 0:
                valor = orden.getDui();
                break;
            case 1:
                valor = orden.getNombre();
                break;
            case 2:
                valor = orden.getApellido();
                break;
            case 3:
                valor=orden.getIdOrden();
                break;
            case 4:
                valor = orden.getSalarioTotal();

        }

        return valor;

    }
}
