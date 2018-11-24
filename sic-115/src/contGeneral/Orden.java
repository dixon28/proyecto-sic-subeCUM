/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contGeneral;

/**
 *
 * @author Zoila Villatoro
 */
public class Orden {
    private int idOrden;
    private float sueldo_por_hora;
    private int numero_horas;
    private String descripcion;
    private Double costos_indirectos;
    private Double costos_mp;
    private String fecha_de_creacion;
    private String fecha_de_entrega;
    private int idPan;
    private Double cif;
    private String nombrePan;
    private Double MOD;
    
    
    
    public Orden() {
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdPan() {
        return idPan;
    }

    public void setIdPan(int idPan) {
        this.idPan = idPan;
    }

    public float getSueldo_por_hora() {
        return sueldo_por_hora;
    }

    public void setSueldo_por_hora(float sueldo_por_hora) {
        this.sueldo_por_hora = sueldo_por_hora;
    }

    public int getNumero_horas() {
        return numero_horas;
    }

    public void setNumero_horas(int numero_horas) {
        this.numero_horas = numero_horas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCostos_indirectos() {
        return costos_indirectos;
    }

    public void setCostos_indirectos(Double costos_indirectos) {
        this.costos_indirectos =costos_indirectos;
    }

    public Double getCostos_mp() {
        return costos_mp;
    }

    public void setCostos_mp(Double costos_mp) {
        this.costos_mp = costos_mp;
    }

    public String getFecha_de_creacion() {
        return fecha_de_creacion;
    }

    public void setFecha_de_creacion(String fecha_de_creacion) {
        this.fecha_de_creacion = fecha_de_creacion;
    }

    public String getFecha_de_entrega() {
        return fecha_de_entrega;
    }

    public void setFecha_de_entrega(String fecha_de_entrega) {
        this.fecha_de_entrega = fecha_de_entrega;
    }

    public Double getCif() {
        return cif;
    }

    public void setCif(Double cif) {
        this.cif = cif;
    }

    public String getNombrePan() {
        return nombrePan;
    }

    public void setNombrePan(String nombrePan) {
        this.nombrePan = nombrePan;
    }

    public Double getMOD() {
        return MOD;
    }

    public void setMOD(Double MOD) {
        this.MOD = MOD;
    }
    
}
