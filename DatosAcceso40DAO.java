package com.comp.complementos.DAO;

/**
 *
 * @author Mary Villanueva
 */
public class DatosAcceso40DAO extends ComplementElement {

    String ls_usuario, ls_password;

    /**
     * @return the ls_usuario
     */
    public String getLs_usuario() {
        return ls_usuario;
    }

    /**
     * @param ls_usuario the ls_usuario to set
     */
    public void setLs_usuario(String ls_usuario) {
        this.ls_usuario = ls_usuario;
    }

    /**
     * @return the ls_password
     */
    public String getLs_password() {
        return ls_password;
    }

    /**
     * @param ls_password the ls_password to set
     */
    public void setLs_password(String ls_password) {
        this.ls_password = ls_password;
    }
    
    @Override
    public String createTXT(){
        
        String txt = "";
        
        txt += "[DATOS_ACCESO]\r\n";
	txt += "USUARIO;"+getLs_usuario()+"\r\n";
	txt += "PASSWORD;"+getLs_password()+"\r\n\n";
        
        return txt;
    
    }
    
    @Override
    public void fill(){
        
    }

}
