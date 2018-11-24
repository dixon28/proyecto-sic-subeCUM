/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contGeneral;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import otros.conexionBD;

/**
 *
 * @author sic
 */
public class transaccionesOperaciones {

    Integer ultimo;
    conexionBD conexion = new conexionBD();
    SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yyyy");

    public transaccionesOperaciones() {
    }

    public void guardarTransaccion(int idlibro, String descripcion, Date fecha) {

        String sql = "insert into transaccion(id,descripcion,fecha) values(?,?,?)";

        try {

            PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
            sentencia.setInt(1, idlibro);
            sentencia.setString(2, descripcion);
            long d = fecha.getTime();
            java.sql.Date fechaenviar = new java.sql.Date(d);

            sentencia.setDate(3, fechaenviar);

            sentencia.execute();
            System.out.printf(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void guardarSaldos(ArrayList<SubCuenta> subcuentas) {
        String sql = "insert into cambiodemonto(cambiodebe,cambiohaber,idsubcuenta,idtransaccion) values(?,?,?,(select  max(idtransaccion) from transaccion as idtrans));";

        String sqlactualizar = "UPDATE subcuenta set debe=(SELECT SUM(cambiodebe) from cambiodemonto where idsubcuenta=?  ),haber=(select sum(cambioHaber) from cambiodemonto where  idsubcuenta=?  ) WHERE idsubcuenta=?;";

        //and idtransaccion=(select  max(idtransaccion) from transaccion as idtrans)
        //and idtransaccion=(select  max(idtransaccion) from transaccion as idtrans)
        for (SubCuenta s : subcuentas) {

            try {
                PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
                PreparedStatement sentenciaupdate = conexion.getConexion().prepareStatement(sqlactualizar);

                s.getDebe_cuenta();
                sentencia.setDouble(1, s.getDebe_cuenta());
                sentencia.setDouble(2, s.getHaber_cuenta());
                sentencia.setInt(3, s.getIdsubcuenta());
                //hasta aqui para mandar a cambios de monto

                sentencia.execute();

                sentenciaupdate.setInt(1, s.getIdsubcuenta());
                sentenciaupdate.setInt(2, s.getIdsubcuenta());
                sentenciaupdate.setInt(3, s.getIdsubcuenta());
                sentenciaupdate.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(transaccionesOperaciones.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
