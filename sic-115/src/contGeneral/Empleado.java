/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contGeneral;

/**
//     *
 * @author Zoila Villatoro
 */
public class Empleado {
    private int dui;
    private String nombre;
    private String apellidos;
    private String fechaDeContratacion;
    private int Cargo;
    //creados para asignacion
    private int idOrden;
    private String descripcionOrden;

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getDescripcionOrden() {
        return descripcionOrden;
    }

    public void setDescripcionOrden(String descripcionOrden) {
        this.descripcionOrden = descripcionOrden;
    }

    public int getCargo() {
        return Cargo;
    }

    public void setCargo(int Cargo) {
        this.Cargo = Cargo;
    }

    public Empleado() {
    }

    public int getDui() {
        return dui;
    }

    public void setDui(int dui) {
        this.dui = dui;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaDeContratacion() {
        return fechaDeContratacion;
    }

    public void setFechaDeContratacion(String fechaDeContratacion) {
        this.fechaDeContratacion = fechaDeContratacion;
    }
}
