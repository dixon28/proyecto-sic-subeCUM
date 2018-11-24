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
public class listaPlanGen {

    ArrayList<PlanillaGeneral> planGen = new ArrayList<PlanillaGeneral>();
    conexionBD conexionCuentas = new conexionBD();
    ArrayList<PlanillaGeneral> planGenOrdenado = new ArrayList<PlanillaGeneral>();
    PlanillaGeneral planilla=new PlanillaGeneral();
  
    public listaPlanGen() {
    }

    

    public ArrayList<PlanillaGeneral> getListaPlanGen() {

        return planGenOrdenado;
    }


    public void BuscarPlanilla(int empleado) {
      this.planGen.clear();
         String sql = "select public.empleado.id_empleado, public.empleado.nombre, "
                + "public.empleado.apellido, public.planilla_orden.salario_nominal_orden, public.planilla_orden.id_orden "
                + "from empleado inner join planilla_orden on (public.empleado.id_empleado=public.planilla_orden.id_empleado) "
                + "where public.empleado.id_empleado= ? order by public.empleado.id_empleado ;";
        try {
            PreparedStatement declaracion1;
            declaracion1 = null;

            declaracion1 = this.conexionCuentas.getConexion().prepareStatement(sql);
            declaracion1.setInt(1,empleado);
            ResultSet resultado1 = declaracion1.executeQuery();

            while (resultado1.next()) //consulta todas los usuario
            {
                PlanillaGeneral aux = new PlanillaGeneral();
                aux.setDui(resultado1.getInt("id_empleado"));
                aux.setNombre(resultado1.getString("nombre"));
                aux.setApellido(resultado1.getString("apellido"));
                aux.setIdOrden(resultado1.getInt("id_orden"));
                aux.setSalarioTotal(resultado1.getDouble("salario_nominal_orden"));

                this.planGenOrdenado.add(aux);
            }
        } catch (SQLException ex) {

            ex.printStackTrace();

        }  
    }
    public Double CalcularSalario(String idDui){
        Integer dui=Integer.parseInt(idDui);
        double salario=0.0;
         String sql = "select sum(salario_nominal_orden) as suma from planilla_orden where id_empleado = ? ;";
        try {
            PreparedStatement declaracion1;
            declaracion1 = null;

            declaracion1 = this.conexionCuentas.getConexion().prepareStatement(sql);
            declaracion1.setInt(1,dui);
            ResultSet resultado1 = declaracion1.executeQuery();

            while (resultado1.next()) //consulta todas los usuario
            {
               
                salario=(resultado1.getDouble("suma"));
            }
        } catch (SQLException ex) {

            ex.printStackTrace();

        }
        return salario;
    }
    
     public String nombre(String idDui){
        Integer dui=Integer.parseInt(idDui);
        String nom=null;
         String sql = "select nombre from empleado where id_empleado = ? ;";
        try {
            PreparedStatement declaracion1;
            declaracion1 = null;

            declaracion1 = this.conexionCuentas.getConexion().prepareStatement(sql);
            declaracion1.setInt(1,dui);
            ResultSet resultado1 = declaracion1.executeQuery();

            while (resultado1.next()) //consulta todas los usuario
            {
               
                nom=(resultado1.getString("nombre"));
            }
        } catch (SQLException ex) {

            ex.printStackTrace();

        }
        return nom;
    
    }
     
     
      public String apellido(String idDui){
        Integer dui=Integer.parseInt(idDui);
        String ape=null;
         String sql = "select apellido from empleado where id_empleado = ? ;";
        try {
            PreparedStatement declaracion1;
            declaracion1 = null;

            declaracion1 = this.conexionCuentas.getConexion().prepareStatement(sql);
            declaracion1.setInt(1,dui);
            ResultSet resultado1 = declaracion1.executeQuery();

            while (resultado1.next()) //consulta todas los usuario
            {
               
                ape=(resultado1.getString("apellido"));
            }
        } catch (SQLException ex) {

            ex.printStackTrace();

        }
        return ape;
    }
    public void guardarPlanGen(String dui,Double Salario,Double isss, Double afp, Double insaforp) {
        planilla.setDui(Integer.parseInt(dui));
        planilla.setSalarioTotal(Salario);
        planilla.setAfp(afp);
        planilla.setInsaforp(insaforp);
        planilla.setIsss(isss);
        String sql = "INSERT INTO planilla_general (id_empleado,salario_total,afp_planillageneral,insaforp_planillageneral, "
                + "isss_planillageneral) VALUES (?,?,?,?,?);";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1,planilla.getDui());
            declaracion.setDouble(2, planilla.getSalarioTotal());
            declaracion.setDouble(3, planilla.getAfp());
            declaracion.setDouble(4, planilla.getInsaforp());
            declaracion.setDouble(5, planilla.getIsss());
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    

