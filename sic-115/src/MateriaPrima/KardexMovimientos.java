/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MateriaPrima;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import otros.conexionBD;

/**
 *
 * @author Zoila Villatoro
 */
public class KardexMovimientos {

    conexionBD conexionCuentas = new conexionBD();
    Registro_Kardex kardex = new Registro_Kardex();
    Registro_Kardex kardex2 = new Registro_Kardex();
    Registro_Kardex kardex3 = new Registro_Kardex();
    Integer idRegistro = 0;
    Integer cantidadEntrante = 0;
    Double costoAnterior = 0.0;
    Double costoUNI = 0.0;// se ocupa en el metodo RescateCP
    Date fechaSalida = null;//se ocupa en RescateFecha
    Integer numero_registro = 0; //se ocupa en el metodo RecateCP
    Double recuperacionCostoUni = 0.0;// se ocupan en la recuperacionCostoTotal
DecimalFormat formateador = new DecimalFormat("#.##");
    public KardexMovimientos() {
    }

    public void agregarMateria(Integer idtipo, Integer Cantidad, Double costo, long fecha) {
        Double costito = 0.0;
        String sql = null;
        Integer numero = recuperacion(idtipo);
        if (numero == null) {
            kardex.setIdTipo(idtipo);
            kardex.setCantidad(Cantidad);
            kardex.setCostoUnitario(costo);
            java.sql.Date fechaenviar = new java.sql.Date(fecha);
            costito = Cantidad * costo;
            DecimalFormat formateador = new DecimalFormat("#.##");
            kardex.setCostoTotal(Double.parseDouble(formateador.format(costito)));
            sql = "INSERT INTO registro_kardex (idtipomat,cantidad_mat,costouni_mat,fecha_entrada,costototal) "
                    + "VALUES (?,?,?,?,?);";
            try {
                PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
                declaracion.setInt(1, kardex.getIdTipo());
                declaracion.setInt(2, kardex.getCantidad());
                declaracion.setDouble(3, kardex.getCostoUnitario());
                declaracion.setDate(4, fechaenviar);
                declaracion.setDouble(5, kardex.getCostoTotal());
                declaracion.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            recuperacionDeValores(idtipo, idRegistro);
            Integer cantidadABase = cantidadEntrante + Cantidad;
            Double costoABase = costoAnterior + (Cantidad * costo);
            Double costoCP = costoABase / cantidadABase;
            kardex.setIdTipo(idtipo);
            kardex.setCantidad(cantidadABase);
            kardex.setCostoUnitario(costoCP);
            DecimalFormat formateador = new DecimalFormat("#.##");
            kardex.setCostoTotal(Double.parseDouble(formateador.format(costoABase)));
            kardex.setCostoUnitario(Double.parseDouble(formateador.format(costoCP)));
            java.sql.Date fechaenviar = new java.sql.Date(fecha);
            sql = "INSERT INTO registro_kardex (idtipomat,cantidad_mat,costouni_mat,fecha_entrada,costototal) "
                    + "VALUES (?,?,?,?,?);";
            try {
                PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
                declaracion.setInt(1, kardex.getIdTipo());
                declaracion.setInt(2, kardex.getCantidad());
                declaracion.setDouble(3, kardex.getCostoUnitario());
                declaracion.setDate(4, fechaenviar);
                declaracion.setDouble(5, kardex.getCostoTotal());
                declaracion.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public Integer recuperacion(Integer idTipo) {
        Integer prueba = 0;
        String sql = "SELECT MAX(id_registro) FROM registro_kardex WHERE idtipomat= ? AND entrada=TRUE;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, idTipo);
            ResultSet resultado1 = declaracion.executeQuery();

            while (resultado1.next()) {
                prueba = resultado1.getInt("max");
            }
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        idRegistro = prueba;
        return prueba;
    }

    public void recuperacionDeValores(Integer idtipo, Integer idregistro) {
        String sql = "SELECT cantidad_mat,costototal FROM registro_kardex WHERE idtipomat= ? AND id_registro= ?;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, idtipo);
            declaracion.setInt(2, idregistro);
            ResultSet resultado1 = declaracion.executeQuery();
            while (resultado1.next()) {
                cantidadEntrante = resultado1.getInt("cantidad_mat");
                costoAnterior = resultado1.getDouble("costototal");
            }
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*Metodos para recuperacion y salida de kardex*/
    public void RescateCP(Integer idtipomp) {
        numero_registro = recuperacion(idtipomp);
        String sql = "SELECT costouni_mat FROM registro_kardex WHERE id_registro=? AND idtipomat=?;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, numero_registro);
            declaracion.setInt(2, idtipomp);
            ResultSet resultado1 = declaracion.executeQuery();
            while (resultado1.next()) {
                costoUNI = resultado1.getDouble("costouni_mat");
            }
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RescateFecha(Integer idOrden) {
        String sql = "SELECT fecha_creacionorden FROM orden WHERE id_orden=?;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, idOrden);
            ResultSet resultado1 = declaracion.executeQuery();
            while (resultado1.next()) {
                fechaSalida = resultado1.getDate("fecha_creacionorden");
            }
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SalidaKardex(Integer idtipomp, Integer Cantidad) {
        DecimalFormat formateador = new DecimalFormat("#.##");
        kardex.setIdTipo(idtipomp);
        kardex.setCantidad(Cantidad);
        kardex.setCostoUnitario(Double.parseDouble(formateador.format(costoUNI)));
        java.sql.Date fechaenviar = new java.sql.Date(fechaSalida.getTime());
        Double costito = Cantidad * costoUNI;
        kardex.setCostoTotal(Double.parseDouble(formateador.format(costito)));
        System.out.println("costoUNI\n" + costoUNI + "\nfecha Salida\n" + fechaSalida + "\ntipo materia\n" + idtipomp + "\nCantidad\n" + Cantidad + "\ncostototal " + costito);

        String sql = "INSERT INTO registro_kardex (idtipomat,cantidad_mat,costouni_mat,fecha_entrada,costototal,entrada) "
                + "VALUES (?,?,?,?,?,FALSE);";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, kardex.getIdTipo());
            declaracion.setInt(2, kardex.getCantidad());
            declaracion.setDouble(3, kardex.getCostoUnitario());
            declaracion.setDate(4, fechaenviar);
            declaracion.setDouble(5, kardex.getCostoTotal());
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AsignacionMa_Orden(Integer idOrden, Integer idMatPrim, Integer cantidadMat) {
        RescateFecha(idOrden);
        RescateCP(idMatPrim);
        SalidaKardex(idMatPrim, cantidadMat);
        salida_TablaCambio(idOrden, recuperacionMAXSALIDA(idMatPrim));
        CambioEstadoKardex(idMatPrim, cantidadMat);
        AgregarMateriaPrimaSalidas(idMatPrim, cantidadMat, idOrden);
    }

    public void salida_TablaCambio(Integer idOrden, Integer idregistro) {
        String sql = "INSERT INTO salida (id_registro,id_orden) "
                + "VALUES (?,?);";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, idregistro);
            declaracion.setInt(2, idOrden);
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer recuperacionMAXSALIDA(Integer idTipo) {
        Integer prueba = 0;
        String sql = "SELECT MAX(id_registro) FROM registro_kardex WHERE idtipomat=? AND entrada= FALSE;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, idTipo);
            ResultSet resultado1 = declaracion.executeQuery();

            while (resultado1.next()) {
                prueba = resultado1.getInt("max");
            }
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prueba;
    }

    //UPDATE para el tipo de materia 
    public void CambioEstadoKardex(Integer idTipo, Integer Cantidad) {
        Integer recuperarID = recuperacion(idTipo);
        RecuperarCostoUnitario(recuperarID);
        DecimalFormat formateador = new DecimalFormat("#.##");
        Double costoTotalEntrante = Cantidad * recuperacionCostoUni;
        String sql = " UPDATE registro_kardex SET cantidad_mat=cantidad_mat-?,costototal=costototal-? WHERE id_registro= ?;";

        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, Cantidad);
            declaracion.setDouble(2, Double.parseDouble(formateador.format( costoTotalEntrante)));
            declaracion.setInt(3, recuperarID);

            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RecuperarCostoUnitario(Integer idRegistro) {

        String sql = " SELECT costouni_mat FROM registro_kardex WHERE id_registro= ?;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, idRegistro);
            ResultSet resultado1 = declaracion.executeQuery();

            while (resultado1.next()) {
                recuperacionCostoUni = resultado1.getDouble("costouni_mat");
            }
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void AgregarMateriaPrimaEntradas(Integer idtipo, Integer Cantidad, Double costo, long fecha) {
        kardex2.setIdTipo(idtipo);
        kardex2.setCantidad(Cantidad);
        
        
        java.sql.Date fechaenviar2 = new java.sql.Date(fecha);
        kardex2.setCostoUnitario(Double.parseDouble(formateador.format(costo)));
        Double costito = Cantidad * costo;
        kardex2.setCostoTotal(Double.parseDouble(formateador.format(costito)));
        String sql = "INSERT INTO entradas (idtipomat,cantidad_entrada,costounitario_en,fecha_entrada,costototal_entrada) "
                + "VALUES (?,?,?,?,?);";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, kardex2.getIdTipo());
            declaracion.setInt(2, kardex2.getCantidad());
            declaracion.setDouble(3, kardex2.getCostoUnitario());
            declaracion.setDate(4, fechaenviar2);
            declaracion.setDouble(5, kardex2.getCostoTotal());
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //esto es para eliminar registro
    public void EliminarSalida(Integer idOrden, Integer idregistro) {
        String sql = "DELETE FROM salida WHERE id_registro= ? AND id_orden= ?;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, idregistro);
            declaracion.setInt(2, idOrden);
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql2 = "DELETE FROM registro_kardex WHERE id_registro= ? ;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql2);
            declaracion.setInt(1, idregistro);

            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Integer RecuperacionIDsalida(Integer idTipo) {
        Integer prueba = 0;
        String sql = "SELECT MAX(id_salidas) FROM salidas_kardex WHERE idtipomat=?;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, idTipo);
            ResultSet resultado1 = declaracion.executeQuery();

            while (resultado1.next()) {
                prueba = resultado1.getInt("max");
            }
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prueba;
    }

    public void EliminarSalidaEnTabla(String tipoMateria) {
        Integer tip = RecuperarIdTipoMateria(tipoMateria);
        Integer idSalida = RecuperacionIDsalida(tip);
        String sql3 = "DELETE FROM salidas_kardex WHERE id_salidas = ? ;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql3);
            declaracion.setInt(1, idSalida);
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RegresarAInventario(Integer idregistro, String tipoMateria, Integer Cantidad, Double costoUnitario, Double CostoTotal) {
        Integer tip = RecuperarIdTipoMateria(tipoMateria);
        Integer idRegistro = recuperacion(tip);
        recuperacionDeValores(tip, idRegistro);
        Integer cantidadNueva = cantidadEntrante + Cantidad;
        Double costoNuevo = costoAnterior + CostoTotal;
        String sql = "UPDATE registro_kardex SET cantidad_mat= ? ,costototal= ? WHERE id_registro= ?";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, cantidadNueva);
            declaracion.setDouble(2, Double.parseDouble(formateador.format(costoNuevo)));
            declaracion.setInt(3, idRegistro);

            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer RecuperarIdTipoMateria(String tipoMateria) {
        Integer idtipo = 0;
        String sql = "SELECT idtipomat FROM tipo_materia WHERE nombremateria = ? ;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setString(1, tipoMateria);
            ResultSet resultado1 = declaracion.executeQuery();

            while (resultado1.next()) {
                idtipo = resultado1.getInt("idtipomat");
            }
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idtipo;

    }

    public Double CalcularCostoMP(Integer idOrden) {
        Double CostoTotal = 0.0;
        String sql = "SELECT SUM (public.registro_kardex.costototal) as mod FROM public.salida JOIN public.registro_kardex ON (public.salida.id_registro=public.registro_kardex.id_registro) JOIN public.orden ON (public.salida.id_orden=public.orden.id_orden) JOIN public.tipo_materia ON (public.tipo_materia.idtipomat=public.registro_kardex.idtipomat)  WHERE public.orden.id_orden= ? ;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, idOrden);
            ResultSet resultado1 = declaracion.executeQuery();

            while (resultado1.next()) {
                CostoTotal = resultado1.getDouble("mod");
            }
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CostoTotal;
    }

    public void GuardarCostoMP(Integer idOrden) {
        Double costos = CalcularCostoMP(idOrden);
        String sql = "UPDATE orden SET costos_materia_p = ? WHERE id_orden= ?;";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setDouble(1, costos);
            declaracion.setInt(2, idOrden);

            System.out.println("Calculo: " + costos);

            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AgregarMateriaPrimaSalidas(Integer idtipomp, Integer Cantidad, Integer idOrden) {
        RescateFecha(idOrden);
        kardex.setIdTipo(idtipomp);
        kardex.setCantidad(Cantidad);
        kardex.setCostoUnitario(Double.parseDouble(formateador.format(costoUNI)));
        java.sql.Date fechaenviar = new java.sql.Date(fechaSalida.getTime());
        Double costito = Cantidad * costoUNI;
        kardex.setCostoTotal(Double.parseDouble(formateador.format(costito)));
        System.out.println("costoUNI\n" + costoUNI + "\nfecha Salida\n" + fechaSalida + "\ntipo materia\n" + idtipomp + "\nCantidad\n" + Cantidad + "\ncostototal " + costito);

        String sql = "INSERT INTO salidas_kardex (idtipomat,cantidad_salida,costosalida,fecha_salida,costototalsal) "
                + "VALUES (?,?,?,?,?);";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, kardex.getIdTipo());
            declaracion.setInt(2, kardex.getCantidad());
            declaracion.setDouble(3, kardex.getCostoUnitario());
            declaracion.setDate(4, fechaenviar);
            declaracion.setDouble(5, kardex.getCostoTotal());
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void CambiarEstadoMPEnTablaSalida(Integer idOrden) {
        String sql = " UPDATE salida SET asignado=TRUE WHERE id_orden= ? ;";

        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1, idOrden);

            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
