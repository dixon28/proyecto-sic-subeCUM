/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contGeneral;

public class Cuenta {
    private int codigo;
    private String nombre;
    private String especificacion;
    private int tipo;
    private Double debe_cuenta;
    private Double haber_cuenta;

    public double getDebe_cuenta() {
        return debe_cuenta;
    }

    public void setDebe_cuenta(Double debe_cuenta) {
        this.debe_cuenta = debe_cuenta;
    }

    public Double getHaber_cuenta() {
        return haber_cuenta;
    }

    public void setHaber_cuenta(Double haber_cuenta) {
        this.haber_cuenta = haber_cuenta;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Cuenta() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }
    


}
