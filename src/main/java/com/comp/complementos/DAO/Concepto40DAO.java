package com.comp.complementos.DAO;

/**
 *
 * @author Mary Villanueva
 */
public class Concepto40DAO extends ComplementElement {

    String ls_claveProdServ, ls_cantidad, ls_claveUnidad, ls_descripción, ls_valorUnitario, ls_importe, ls_objetoIMP;

    /**
     * @return the ls_claveProdServ
     */
    public String getLs_claveProdServ() {
        return ls_claveProdServ;
    }

    /**
     * @param ls_claveProdServ the ls_claveProdServ to set
     */
    public void setLs_claveProdServ(String ls_claveProdServ) {
        this.ls_claveProdServ = ls_claveProdServ;
    }

    /**
     * @return the ls_cantidad
     */
    public String getLs_cantidad() {
        return ls_cantidad;
    }

    /**
     * @param ls_cantidad the ls_cantidad to set
     */
    public void setLs_cantidad(String ls_cantidad) {
        this.ls_cantidad = ls_cantidad;
    }

    /**
     * @return the ls_claveUnidad
     */
    public String getLs_claveUnidad() {
        return ls_claveUnidad;
    }

    /**
     * @param ls_claveUnidad the ls_claveUnidad to set
     */
    public void setLs_claveUnidad(String ls_claveUnidad) {
        this.ls_claveUnidad = ls_claveUnidad;
    }

    /**
     * @return the ls_descripción
     */
    public String getLs_descripción() {
        return ls_descripción;
    }

    /**
     * @param ls_descripción the ls_descripción to set
     */
    public void setLs_descripción(String ls_descripción) {
        this.ls_descripción = ls_descripción;
    }

    /**
     * @return the ls_valorUnitario
     */
    public String getLs_valorUnitario() {
        return ls_valorUnitario;
    }

    /**
     * @param ls_valorUnitario the ls_valorUnitario to set
     */
    public void setLs_valorUnitario(String ls_valorUnitario) {
        this.ls_valorUnitario = ls_valorUnitario;
    }

    /**
     * @return the ls_importe
     */
    public String getLs_importe() {
        return ls_importe;
    }

    /**
     * @param ls_importe the ls_importe to set
     */
    public void setLs_importe(String ls_importe) {
        this.ls_importe = ls_importe;
    }

    /**
     * @return the ls_objetoIMP
     */
    public String getLs_objetoIMP() {
        return ls_objetoIMP;
    }

    /**
     * @param ls_objetoIMP the ls_objetoIMP to set
     */
    public void setLs_objetoIMP(String ls_objetoIMP) {
        this.ls_objetoIMP = ls_objetoIMP;
    }
    
    @Override
    public String createTXT(){
        
        String txt = "";
        
        txt += "[CONCEPTO]\r\n";
        txt += "CLAVEPRODSERV;"+getLs_claveProdServ()+"\r\n";
        txt += "CANTIDAD;"+getLs_cantidad()+"\r\n";
        txt += "CLAVEUNIDAD;"+getLs_claveUnidad()+"\r\n";
        txt += "DESCRIPCION;"+getLs_descripción()+"\r\n";
        txt += "VALORUNITARIO;"+getLs_valorUnitario()+"\r\n";
        txt += "IMPORTE;"+getLs_importe()+"\r\n";
        txt += "OBJETOIMP;"+getLs_objetoIMP()+"\r\n\n";
        
        return txt;
        
    }
    
    @Override
    public void fill(){
        
    }

}
