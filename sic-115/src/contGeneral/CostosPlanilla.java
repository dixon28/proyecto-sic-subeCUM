/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contGeneral;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import otros.conexionBD;

/**
 *
 * @author Zoila Villatoro
 */
public class CostosPlanilla {

    conexionBD conexionCuentas = new conexionBD();
    Empleado empleado = new Empleado();
    Orden orden = new Orden();
    DecimalFormat formateador = new DecimalFormat("#.##");

    public void guardarEmpleado(String dui, String nombre, String apellidos, long fecha, String cargo) {
        empleado.setDui(Integer.parseInt(dui));
        empleado.setNombre(nombre);
        empleado.setApellidos(apellidos);
        java.sql.Date fechaenviar = new java.sql.Date(fecha);
        empleado.setCargo(Integer.parseInt(cargo));
        String sql = "INSERT INTO empleado (id_empleado,idpuesto,nombre,apellido,fecha_de_contratacion) VALUES (?,?,?,?,?);";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, empleado.getDui());
            declaracion.setInt(2, empleado.getCargo());
            declaracion.setString(3, empleado.getNombre());
            declaracion.setString(4, empleado.getApellidos());
            declaracion.setDate(5, fechaenviar);
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarOrden(String idOrden, String pan, String sueldo_por_hora, String numero_hora, String descripcion, long fecha_creacion, long fecha_entrega) {
        orden.setIdOrden(Integer.parseInt(idOrden));
        orden.setIdPan(Integer.parseInt(pan));
        orden.setSueldo_por_hora(Double.parseDouble(sueldo_por_hora));
        orden.setNumero_horas(Integer.parseInt(numero_hora));
        orden.setDescripcion(descripcion);
        Double porcentajeIngreso = Double.parseDouble(formateador.format(RecuperacionCIF()));
        java.sql.Date fechaenviar = new java.sql.Date(fecha_creacion);
        java.sql.Date fechaenviar2 = new java.sql.Date(fecha_entrega);
        String sql = "INSERT INTO orden (id_orden,sueldo_x_hora,num_horas,descripcion,fecha_creacionorden,fecha_entregaorden,id_pan,porcentaje) VALUES (?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, orden.getIdOrden());
            declaracion.setDouble(2, orden.getSueldo_por_hora());
            declaracion.setInt(3, orden.getNumero_horas());
            declaracion.setString(4, orden.getDescripcion());
            declaracion.setDate(5, fechaenviar);
            declaracion.setDate(6, fechaenviar2);
            declaracion.setInt(7, orden.getIdPan());
            declaracion.setDouble(8, porcentajeIngreso);
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Double RecuperacionCIF() {
        Double porcentaje = 0.0;
        String sql = "SELECT porcentaje FROM cif;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            ResultSet resultado1 = declaracion.executeQuery();

            while (resultado1.next()) {
                porcentaje = resultado1.getDouble("porcentaje");
            }
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return porcentaje;

    }

    public void guardarAsignacion(int id_empleado, int id_orden) {

        String sql = "INSERT INTO tiene(id_empleado,id_orden) VALUES (?,?) ;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, id_empleado);
            declaracion.setInt(2, id_orden);

            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarAsignacion(int id_empleado, int id_orden) {

        String sql = " DELETE FROM tiene WHERE id_empleado=? AND id_orden=?;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, id_empleado);
            declaracion.setInt(2, id_orden);

            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificartrabajadores(int x, int id) {
        String sql = null;
        if (x == 1) {
            sql = " UPDATE orden SET cantidad_empleados =cantidad_empleados+1, "
                    + "costos_indirectos=0.7*sueldo_x_hora*num_horas*(SELECT cantidad_empleados+1 FROM orden WHERE id_orden = ?),"
                    + "costos_mod=sueldo_x_hora*num_horas*(SELECT cantidad_empleados+1 FROM orden WHERE id_orden=?)"
                    + " WHERE id_orden = ?;";
        }

        if (x == 2) {
            sql = " UPDATE orden SET cantidad_empleados =cantidad_empleados-1, "
                    + "costos_indirectos=0.7*sueldo_x_hora*num_horas*(SELECT cantidad_empleados-1 FROM orden WHERE id_orden = ?),"
                    + "costos_mod=sueldo_x_hora*num_horas*(SELECT cantidad_empleados-1 FROM orden WHERE id_orden=?)"
                    + " WHERE id_orden = ?;";
        }
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, id);
            declaracion.setInt(2, id);
            declaracion.setInt(3, id);
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verifacion(int id_orden, int ndui) {
        boolean aux = true;

        try {
            PreparedStatement s = conexionCuentas.getConexion().prepareStatement("select true  from tiene where exists(select id_empleado from tiene where id_empleado= ? and id_orden = ?);");
            s.setInt(1, ndui);
            s.setInt(2, id_orden);

            ResultSet r = s.executeQuery();
            while(r.next())
            if (r.getBoolean("bool")) {

                aux = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CostosPlanilla.class.getName()).log(Level.SEVERE, null, ex);
        }

        return aux;
    }
    public void CambiarEstadoMPEnTablaTiene(Integer idOrden) {
        String sql = " UPDATE tiene SET asignado=TRUE WHERE id_orden= ? ;";

        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, idOrden);

            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
