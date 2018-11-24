/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contGeneral;

/**
 *
 * @author Alejandor
 */
public class PlanillaGeneral {
    private Integer dui;
    private String nombre;
    private String apellido;
    private Double salarioTotal;
    private Double isss;
    private Double afp;
    private Double insaforp;
    private Integer idOrden;
    

    public PlanillaGeneral() {
    }

    public Integer getDui() {
        return dui;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }
    public void setDui(Integer dui) {
        this.dui = dui;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Double getSalarioTotal() {
        return salarioTotal;
    }

    public void setSalarioTotal(Double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }

    public Double getIsss() {
        return isss;
    }

    public void setIsss(Double isss) {
        this.isss = isss;
    }

    public Double getAfp() {
        return afp;
    }

    public void setAfp(Double afp) {
        this.afp = afp;
    }

    public Double getInsaforp() {
        return insaforp;
    }

    public void setInsaforp(Double insaforp) {
        this.insaforp = insaforp;
    }
    
    
}
