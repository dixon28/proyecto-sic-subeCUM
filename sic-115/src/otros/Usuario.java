/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

/**
 *
 * @author Erick
 */
public class Usuario {
    private String id;
    private String password;
    private String nombres;
    
    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String user) {
        this.password = user;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    void getId(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
