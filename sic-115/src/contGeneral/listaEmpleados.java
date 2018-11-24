/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contGeneral;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import otros.conexionBD;

/**
 *
 * @author Alejandor
 */
public class listaEmpleados {
    ArrayList<Empleado> empleados = new ArrayList<Empleado>();
    conexionBD conexionCuentas = new conexionBD();
    ArrayList<Empleado> empleadoOrdenado = new ArrayList<Empleado>();
    
    public ArrayList<Empleado> getAsignacion() {

        this.empleados.clear();
        String sql = "SELECT public.empleado.id_empleado,public.empleado.nombre,public.empleado.idpuesto, public.orden.id_orden,public.orden.descripcion "
                + "FROM public.tiene "
                + " INNER JOIN public.empleado ON (public.tiene.id_empleado=public.empleado.id_empleado)"
                + " INNER JOIN public.orden ON (public.tiene.id_orden=public.orden.id_orden) order by public.empleado.id_empleado ;";

        try {
            PreparedStatement declaracion1;
            declaracion1 = null;

            declaracion1 = this.conexionCuentas.getConexion().prepareStatement(sql);

            ResultSet resultado1 = declaracion1.executeQuery();

            while (resultado1.next()) //consulta todas los usuario
            {
                Empleado aux = new Empleado();              
                aux.setDui(resultado1.getInt("id_empleado"));
                aux.setNombre(resultado1.getString("nombre"));
                aux.setCargo(resultado1.getInt("idpuesto"));
                aux.setIdOrden(resultado1.getInt("id_orden"));
                aux.setDescripcionOrden(resultado1.getString("descripcion"));
                this.empleados.add(aux);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return empleados;
    }
      public listaEmpleados() {
        generarLista();

    }

    public ArrayList<Empleado> getListaEmpleados() {

        return empleadoOrdenado;
    }

    public void generarLista() {
        this.empleados.clear();
        String sql = "SELECT public.empleado.id_empleado,public.empleado.nombre,public.empleado.idpuesto, public.orden.id_orden,public.orden.descripcion "
                + "FROM public.tiene "
                + " INNER JOIN public.empleado ON (public.tiene.id_empleado=public.empleado.id_empleado)"
                + " INNER JOIN public.orden ON (public.tiene.id_orden=public.orden.id_orden) order by public.empleado.id_empleado ;";

        try {
            PreparedStatement declaracion1;
            declaracion1 = null;

            declaracion1 = this.conexionCuentas.getConexion().prepareStatement(sql);

            ResultSet resultado1 = declaracion1.executeQuery();

            while (resultado1.next()) //consulta todas los usuario
            {
                Empleado aux = new Empleado();              
                aux.setDui(resultado1.getInt("id_empleado"));
                aux.setNombre(resultado1.getString("nombre"));
                aux.setCargo(resultado1.getInt("idpuesto"));
                aux.setIdOrden(resultado1.getInt("id_orden"));
                aux.setDescripcionOrden(resultado1.getString("descripcion"));
                this.empleadoOrdenado.add(aux);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }
            

    }  
}
