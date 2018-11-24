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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import otros.conexionBD;
//comentario

/**
 *
 * @author sic
 */
public class listaCuentas {

    ArrayList<Cuenta> cuentasOrdenadas = new ArrayList<Cuenta>();//no estan ordenadas aun solo almacena todas las c en un arreglo
    ArrayList<Cuenta> cuentasGenerales = new ArrayList<Cuenta>();
    ArrayList<Cuenta> subCuentas = new ArrayList<Cuenta>();
    conexionBD conexionCuentas = new conexionBD();

//Arreglo de cuentas padres
    public ArrayList<Cuenta> getCuentasGenerales() {
        this.cuentasGenerales.clear();
        String sqlGeneral = "select *from cuenta order by idcuenta";

        try {
            PreparedStatement declaracion1;
            declaracion1 = null;

            declaracion1 = this.conexionCuentas.getConexion().prepareStatement(sqlGeneral);

            ResultSet resultado1 = declaracion1.executeQuery();

            while (resultado1.next()) //consulta todas los usuario
            {
                Cuenta aux = new Cuenta();
                aux.setCodigo(resultado1.getInt("idcuenta"));
                aux.setNombre(resultado1.getString("nombrecuenta"));

                this.cuentasGenerales.add(aux);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return cuentasGenerales;
    }

    //Arreglo de cuentas hijas
    public ArrayList<Cuenta> getSubCuentas() {

        this.subCuentas.clear();
        String sqlSub = "select *from subcuenta order by idsubcuenta";

        try {
            PreparedStatement declaracion1;
            declaracion1 = null;

            declaracion1 = this.conexionCuentas.getConexion().prepareStatement(sqlSub);

            ResultSet resultado1 = declaracion1.executeQuery();

            while (resultado1.next()) //consulta todas los usuario
            {
                Cuenta aux = new Cuenta();
                aux.setCodigo(resultado1.getInt("idcuenta"));
                aux.setNombre(resultado1.getString("nombre"));

                this.subCuentas.add(aux);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return subCuentas;
    }

    public listaCuentas() {
        generarLista();

    }

    public ArrayList<Cuenta> getCuentasOrdenadas() {

        return cuentasOrdenadas;
    }

    public void generarLista() {
        String sqlGeneral = "select *from cuenta order by idcuenta";
        String sqlSub = "select *from subcuenta order by idsubcuenta";
        this.cuentasOrdenadas.clear();
        try {
            PreparedStatement declaracion1;
            declaracion1 = null;
            PreparedStatement declaracion2;
            declaracion2 = null;
            declaracion1 = this.conexionCuentas.getConexion().prepareStatement(sqlGeneral);
            declaracion2 = this.conexionCuentas.getConexion().prepareStatement(sqlSub);
            ResultSet resultado1 = declaracion1.executeQuery();
            ResultSet resultado2 = declaracion2.executeQuery();
            while (resultado1.next()) //consulta todas los usuario
            {
                Cuenta aux = new Cuenta();
                aux.setCodigo(resultado1.getInt("idcuenta"));
                aux.setNombre(resultado1.getString("nombrecuenta"));
                aux.setEspecificacion(resultado1.getString("descripcion"));
                this.cuentasOrdenadas.add(aux);
            }
            while (resultado2.next()) //consulta todas los usuario
            {
                Cuenta aux = new Cuenta();
                aux.setCodigo(resultado2.getInt("idsubcuenta"));
                aux.setNombre(resultado2.getString("nombre"));
                aux.setEspecificacion(resultado2.getString("descripcion"));
                this.cuentasOrdenadas.add(aux);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

    }

}
