/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MateriaPrima;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import otros.conexionBD;

/**
 *
 * @author Zoila Villatoro
 */
public class listaSalidasKardex {

    ArrayList<Registro_Kardex> registros = new ArrayList<Registro_Kardex>();
    conexionBD conexionCuentas = new conexionBD();
    ArrayList<Registro_Kardex> registroOrdenado = new ArrayList<Registro_Kardex>();
    Registro_Kardex registro = new Registro_Kardex();

    public ArrayList<Registro_Kardex> getAsignacion() {

        this.registros.clear();
        String sql = "SELECT public.orden.id_orden,public.salida.id_registro,public.tipo_materia.nombremateria,public.registro_kardex.cantidad_mat,public.registro_kardex.costouni_mat,public.registro_kardex.costototal"
                + " FROM public.salida JOIN public.registro_kardex ON (public.salida.id_registro=public.registro_kardex.id_registro) JOIN public.orden ON"
                + " (public.salida.id_orden=public.orden.id_orden) JOIN public.tipo_materia ON (public.tipo_materia.idtipomat=public.registro_kardex.idtipomat) WHERE public.salida.asignado = FALSE ;";

        try {
            PreparedStatement declaracion1;
            declaracion1 = null;

            declaracion1 = this.conexionCuentas.getConexion().prepareStatement(sql);

            ResultSet resultado1 = declaracion1.executeQuery();

            while (resultado1.next()) //consulta todas los usuario
            {
                Registro_Kardex aux = new Registro_Kardex();
                aux.setIdOrden(resultado1.getInt("id_orden"));
                aux.setNombreMateria(resultado1.getString("nombremateria"));
                aux.setCantidad(resultado1.getInt("cantidad_mat"));
                aux.setCostoUnitario(resultado1.getDouble("costouni_mat"));
                aux.setCostoTotal(resultado1.getDouble("costototal"));
                this.registros.add(aux);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return registros;
    }

    public listaSalidasKardex() {
        // generarLista();
    }

    public ArrayList<Registro_Kardex> getListaRegistro() {

        return registroOrdenado;
    }

    public void generarLista() {
        this.registros.clear();
        String sql = "SELECT public.orden.id_orden,public.salida.id_registro,public.tipo_materia.nombremateria,public.registro_kardex.cantidad_mat,public.registro_kardex.costouni_mat,public.registro_kardex.costototal"
                + " FROM public.salida JOIN public.registro_kardex ON (public.salida.id_registro=public.registro_kardex.id_registro) JOIN public.orden ON"
                + " (public.salida.id_orden=public.orden.id_orden) JOIN public.tipo_materia ON (public.tipo_materia.idtipomat=public.registro_kardex.idtipomat) WHERE public.salida.asignado = FALSE;";

        try {
            PreparedStatement declaracion1;
            declaracion1 = null;

            declaracion1 = this.conexionCuentas.getConexion().prepareStatement(sql);

            ResultSet resultado1 = declaracion1.executeQuery();

            while (resultado1.next()) //consulta todas los usuario
            {
                Registro_Kardex aux = new Registro_Kardex();
                aux.setIdOrden(resultado1.getInt("id_orden"));
                aux.setIdregistro(resultado1.getInt("id_registro"));
                aux.setNombreMateria(resultado1.getString("nombremateria"));
                aux.setCantidad(resultado1.getInt("cantidad_mat"));
                aux.setCostoUnitario(resultado1.getDouble("costouni_mat"));
                aux.setCostoTotal(resultado1.getDouble("costototal"));
                this.registroOrdenado.add(aux);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

    }

    
}
