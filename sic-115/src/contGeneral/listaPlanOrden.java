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
public class listaPlanOrden {
    ArrayList<PlanillaOrden> ordenes = new ArrayList<PlanillaOrden>();
    conexionBD conexionCuentas = new conexionBD();
    ArrayList<PlanillaOrden> planOrdenado = new ArrayList<PlanillaOrden>();
    PlanillaOrden planOr=new PlanillaOrden();

    public listaPlanOrden() {
    }
    

   
    public ArrayList<PlanillaOrden> getListaOrdenes() {

        return planOrdenado;
    }

   
    public void BuscarOrden(Integer idOrden){
      this.ordenes.clear();
         String sql = "SELECT public.empleado.id_empleado, public.empleado.nombre, public.empleado.apellido,"
                + " public.orden.id_orden, public.orden.sueldo_x_hora, public.orden.num_horas"
                + " FROM public.tiene INNER JOIN public.empleado ON (public.tiene.id_empleado=public.empleado.id_empleado)"
                + " INNER JOIN public.orden ON (public.tiene.id_orden=public.orden.id_orden) WHERE public.orden.id_orden= ? order by public.empleado.id_empleado ;";

        try {
            PreparedStatement declaracion1;
            declaracion1 = null;

            declaracion1 = this.conexionCuentas.getConexion().prepareStatement(sql);
            declaracion1.setInt(1, idOrden);
            ResultSet resultado1 = declaracion1.executeQuery();

            while (resultado1.next()) //consulta todas los usuario
            {
                PlanillaOrden aux = new PlanillaOrden();
                aux.setId_empleado(resultado1.getInt("id_empleado"));
                aux.setNombre(resultado1.getString("nombre"));
                aux.setApellido(resultado1.getString("apellido"));
                aux.setId_orden(resultado1.getInt("id_orden"));
                aux.setSalario(resultado1.getDouble("sueldo_x_hora"));
                aux.setNumHoras(resultado1.getInt("num_horas"));
               
                this.planOrdenado.add(aux);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }  
    }
    
 public void guardarPlanOrden(String dui,Double Salario,String idOrden) {
         planOr.setId_empleado(Integer.parseInt(dui));
         planOr.setSalario(Salario);
         planOr.setId_orden(Integer.parseInt(idOrden));
        String sql = "INSERT INTO planilla_orden (id_empleado,salario_nominal_orden,id_orden) VALUES (?,?,?);";
        try {
            PreparedStatement declaracion = conexionCuentas.getConexion().prepareStatement(sql);
            declaracion.setInt(1,planOr.getId_empleado());
            declaracion.setDouble(2, planOr.getSalario());
            declaracion.setInt(3, planOr.getId_orden());
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
