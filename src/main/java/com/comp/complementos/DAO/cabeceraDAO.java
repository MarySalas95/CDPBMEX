package com.comp.complementos.DAO;

import java.util.List;

/**
 *
 * @author Mary Villanueva
 */
public class cabeceraDAO {
    
    //CABECERA CDP
    int fecha, cliente, folio, transaccion;;
    String lsCliente, lsBanco, lsCuenta, lsRFCBanco, lsMensaje, lsMensaje1, lsMensaje3, lsTXT;;
    
    private List<cabeceraDAO> cabecera;
    private List<detalleDAO> detalle;
    private List<ctasBancariasDAO> ctasBancarias;
    
    //CABECERA CDP

    /**
     * @return the fecha
     */
    public int getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the cliente
     */
    public int getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the lsCliente
     */
    public String getLsCliente() {
        return lsCliente;
    }

    /**
     * @param lsCliente the lsCliente to set
     */
    public void setLsCliente(String lsCliente) {
        this.lsCliente = lsCliente;
    }

    /**
     * @return the folio
     */
    public int getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(int folio) {
        this.folio = folio;
    }

    /**
     * @return the transaccion
     */
    public int getTransaccion() {
        return transaccion;
    }

    /**
     * @param transaccion the transaccion to set
     */
    public void setTransaccion(int transaccion) {
        this.transaccion = transaccion;
    }

    /**
     * @return the lsBanco
     */
    public String getLsBanco() {
        return lsBanco;
    }

    /**
     * @param lsBanco the lsBanco to set
     */
    public void setLsBanco(String lsBanco) {
        this.lsBanco = lsBanco;
    }

    /**
     * @return the lsCuenta
     */
    public String getLsCuenta() {
        return lsCuenta;
    }

    /**
     * @param lsCuenta the lsCuenta to set
     */
    public void setLsCuenta(String lsCuenta) {
        this.lsCuenta = lsCuenta;
    }

    /**
     * @return the lsRFCBanco
     */
    public String getLsRFCBanco() {
        return lsRFCBanco;
    }

    /**
     * @param lsRFCBanco the lsRFCBanco to set
     */
    public void setLsRFCBanco(String lsRFCBanco) {
        this.lsRFCBanco = lsRFCBanco;
    }

    /**
     * @return the lsMensaje
     */
    public String getLsMensaje() {
        return lsMensaje;
    }

    /**
     * @param lsMensaje the lsMensaje to set
     */
    public void setLsMensaje(String lsMensaje) {
        this.lsMensaje = lsMensaje;
    }
    
    /**
     * @return the lsMensaje1
     */
    public String getLsMensaje1() {
        return lsMensaje1;
    }

    /**
     * @param lsMensaje1 the lsMensaje1 to set
     */
    public void setLsMensaje1(String lsMensaje1) {
        this.lsMensaje1 = lsMensaje1;
    }
    
    /**
     * @return the lsMensaje3
     */
    public String getLsMensaje3() {
        return lsMensaje3;
    }

    /**
     * @param lsMensaje3 the lsMensaje3 to set
     */
    public void setLsMensaje3(String lsMensaje3) {
        this.lsMensaje3 = lsMensaje3;
    }

    /**
     * @return the lsTXT
     */
    public String getLsTXT() {
        return lsTXT;
    }

    /**
     * @param lsTXT the lsTXT to set
     */
    public void setLsTXT(String lsTXT) {
        this.lsTXT = lsTXT;
    }

    /**
     * @return the cabecera
     */
    public List<cabeceraDAO> getCabecera() {
        return cabecera;
    }

    /**
     * @param cabecera the cabecera to set
     */
    public void setCabecera(List<cabeceraDAO> cabecera) {
        this.cabecera = cabecera;
    }

    /**
     * @return the detalle
     */
    public List<detalleDAO> getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(List<detalleDAO> detalle) {
        this.detalle = detalle;
    }
    
     /**
     * @return the ctasBancarias
     */
    public List<ctasBancariasDAO> getCtasBancarias() {
        return ctasBancarias;
    }

    /**
     * @param ctasBancarias the ctasBancarias to set
     */
    public void setCtasBancarias(List<ctasBancariasDAO> ctasBancarias) {
        this.ctasBancarias = ctasBancarias;
    }

}
