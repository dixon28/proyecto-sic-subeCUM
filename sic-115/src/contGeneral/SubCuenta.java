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
public class SubCuenta {
    private int idsubcuenta;
    private int idcuenta;
    private String nombre;
    private float debe_cuenta;
    private float haber_cuenta;
    private String descripcion;

    public SubCuenta() {
    }

    public int getIdsubcuenta() {
        return idsubcuenta;
    }

    public void setIdsubcuenta(int idsubcuenta) {
        this.idsubcuenta = idsubcuenta;
    }

    public int getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(int idcuenta) {
        this.idcuenta = idcuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getDebe_cuenta() {
        return debe_cuenta;
    }

    public void setDebe_cuenta(float debe_cuenta) {
        this.debe_cuenta = debe_cuenta;
    }

    public float getHaber_cuenta() {
        return haber_cuenta;
    }

    public void setHaber_cuenta(float haber_cuenta) {
        this.haber_cuenta = haber_cuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return   +idsubcuenta+"-"+ idcuenta +  "-" + nombre ;
    }
    
}
