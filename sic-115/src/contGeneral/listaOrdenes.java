/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contGeneral;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import otros.conexionBD;

/**
 *
 * @author Zoila Villatoro
 */
public class listaOrdenes {

    ArrayList<Orden> ordenes = new ArrayList<Orden>();
    conexionBD conexionCuentas = new conexionBD();
    ArrayList<Orden> ordenOrdenado = new ArrayList<Orden>();
    DecimalFormat formateador = new DecimalFormat("#.##");

    public ArrayList<Orden> getListaOrden() {

        this.ordenes.clear();
        String sql = "SELECT orden.id_orden as Numero, orden.costos_indirectos as cif, orden.costos_materia_p as mp,"
                + "orden.costos_mod as mod, pan.nombre_pan as tipo FROM public.orden, public.pan WHERE orden.id_pan = pan.id_pan and orden.finalizado=false;";

        try {
            PreparedStatement declaracion1;
            declaracion1 = null;

            declaracion1 = this.conexionCuentas.getConexion().prepareStatement(sql);

            ResultSet resultado1 = declaracion1.executeQuery();

            while (resultado1.next()) //consulta todas los usuario
            {
                Orden aux = new Orden();
                aux.setIdOrden(resultado1.getInt("Numero"));
                aux.setNombrePan(resultado1.getString("tipo"));
                aux.setCostos_mp(resultado1.getDouble("mp"));
                aux.setCostos_indirectos(resultado1.getDouble("cif"));
                aux.setMOD(resultado1.getDouble("mod"));

                this.ordenes.add(aux);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return ordenes;
    }

    public void CambiarEstadoOrden(Integer numOrden) {
        String sql = " UPDATE orden SET finalizado=TRUE WHERE id_orden = ?;";

        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, numOrden);
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean InsertarProducto(Integer numOrden,String pan,Integer cantidad,Double costoUni,Double costoTo){
        String sql = "INSERT INTO producto_t (id_orden,cantidad_producto,costounitarioprod,costototal,tipopan) "
                + "VALUES (?,?,?,?,?);";
boolean x= false;
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, numOrden);
            declaracion.setInt(2,cantidad);
            declaracion.setDouble(3, Double.parseDouble(formateador.format(costoUni)));
            declaracion.setDouble(4, Double.parseDouble(formateador.format(costoTo)));
            declaracion.setString(5, pan);
           x= declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

}
