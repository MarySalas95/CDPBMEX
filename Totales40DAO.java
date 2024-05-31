package com.comp.complementos.DAO;

/**
 *
 * @author Mary Villanueva
 */
public class Totales40DAO extends ComplementElement {

    double ld_totalRetencionesIVA, ld_totalTrasladosBaseIVA16, ld_totalTrasladosImpuestoIVA16, ld_totalTrasladosImpuestoIVA0, ld_totalTrasladosBaseIVA0, ld_montoTotalPagos;
    String ls_totalRetencionesISR, ls_totalRetencionesIEPS, ls_totalTrasladosBaseIVA8, ls_totalTrasladosBaseIVAexento;

    /**
     * @return the ld_totalRetencionesIVA
     */
    public double getLd_totalRetencionesIVA() {
        return ld_totalRetencionesIVA;
    }

    /**
     * @param ld_totalRetencionesIVA the ld_totalRetencionesIVA to set
     */
    public void setLd_totalRetencionesIVA(double ld_totalRetencionesIVA) {
        this.ld_totalRetencionesIVA = ld_totalRetencionesIVA;
    }

    /**
     * @return the ld_totalTrasladosBaseIVA16
     */
    public double getLd_totalTrasladosBaseIVA16() {
        return ld_totalTrasladosBaseIVA16;
    }

    /**
     * @param ld_totalTrasladosBaseIVA16 the ld_totalTrasladosBaseIVA16 to set
     */
    public void setLd_totalTrasladosBaseIVA16(double ld_totalTrasladosBaseIVA16) {
        this.ld_totalTrasladosBaseIVA16 = ld_totalTrasladosBaseIVA16;
    }

    /**
     * @return the ld_totalTrasladosImpuestoIVA16
     */
    public double getLd_totalTrasladosImpuestoIVA16() {
        return ld_totalTrasladosImpuestoIVA16;
    }

    /**
     * @param ld_totalTrasladosImpuestoIVA16 the ld_totalTrasladosImpuestoIVA16
     * to set
     */
    public void setLd_totalTrasladosImpuestoIVA16(double ld_totalTrasladosImpuestoIVA16) {
        this.ld_totalTrasladosImpuestoIVA16 = ld_totalTrasladosImpuestoIVA16;
    }

    /**
     * @return the ld_totalTrasladosImpuestoIVA0
     */
    public double getLd_totalTrasladosImpuestoIVA0() {
        return ld_totalTrasladosImpuestoIVA0;
    }

    /**
     * @param ld_totalTrasladosImpuestoIVA0 the ld_totalTrasladosImpuestoIVA0 to
     * set
     */
    public void setLd_totalTrasladosImpuestoIVA0(double ld_totalTrasladosImpuestoIVA0) {
        this.ld_totalTrasladosImpuestoIVA0 = ld_totalTrasladosImpuestoIVA0;
    }

    /**
     * @return the ld_totalTrasladosBaseIVA0
     */
    public double getLd_totalTrasladosBaseIVA0() {
        return ld_totalTrasladosBaseIVA0;
    }

    /**
     * @param ld_totalTrasladosBaseIVA0 the ld_totalTrasladosBaseIVA0 to set
     */
    public void setLd_totalTrasladosBaseIVA0(double ld_totalTrasladosBaseIVA0) {
        this.ld_totalTrasladosBaseIVA0 = ld_totalTrasladosBaseIVA0;
    }

    /**
     * @return the ld_montoTotalPagos
     */
    public double getLd_montoTotalPagos() {
        return ld_montoTotalPagos;
    }

    /**
     * @param ld_montoTotalPagos the ld_montoTotalPagos to set
     */
    public void setLd_montoTotalPagos(double ld_montoTotalPagos) {
        this.ld_montoTotalPagos = ld_montoTotalPagos;
    }

    /**
     * @return the ls_totalRetencionesISR
     */
    public String getLs_totalRetencionesISR() {
        return ls_totalRetencionesISR;
    }

    /**
     * @param ls_totalRetencionesISR the ls_totalRetencionesISR to set
     */
    public void setLs_totalRetencionesISR(String ls_totalRetencionesISR) {
        this.ls_totalRetencionesISR = ls_totalRetencionesISR;
    }

    /**
     * @return the ls_totalRetencionesIEPS
     */
    public String getLs_totalRetencionesIEPS() {
        return ls_totalRetencionesIEPS;
    }

    /**
     * @param ls_totalRetencionesIEPS the ls_totalRetencionesIEPS to set
     */
    public void setLs_totalRetencionesIEPS(String ls_totalRetencionesIEPS) {
        this.ls_totalRetencionesIEPS = ls_totalRetencionesIEPS;
    }

    /**
     * @return the ls_totalTrasladosBaseIVA8
     */
    public String getLs_totalTrasladosBaseIVA8() {
        return ls_totalTrasladosBaseIVA8;
    }

    /**
     * @param ls_totalTrasladosBaseIVA8 the ls_totalTrasladosBaseIVA8 to set
     */
    public void setLs_totalTrasladosBaseIVA8(String ls_totalTrasladosBaseIVA8) {
        this.ls_totalTrasladosBaseIVA8 = ls_totalTrasladosBaseIVA8;
    }

    /**
     * @return the ls_totalTrasladosBaseIVAexento
     */
    public String getLs_totalTrasladosBaseIVAexento() {
        return ls_totalTrasladosBaseIVAexento;
    }

    /**
     * @param ls_totalTrasladosBaseIVAexento the ls_totalTrasladosBaseIVAexento
     * to set
     */
    public void setLs_totalTrasladosBaseIVAexento(String ls_totalTrasladosBaseIVAexento) {
        this.ls_totalTrasladosBaseIVAexento = ls_totalTrasladosBaseIVAexento;
    }

    @Override
    public String createTXT() {

        String txt = "";

        txt += "[PAGOS]\r\n\r\n";
        txt += "[TOTALES]\r\n";
        txt += "TOTALRETENCIONESIVA;" + getLd_totalRetencionesIVA() + "\r\n";
        txt += "TOTALRETENCIONESISR;" + getLs_totalRetencionesISR() + "\r\n";
        txt += "TOTALRETENCIONESIEPS;" + getLs_totalRetencionesIEPS() + "\r\n";
        txt += "TOTALTRASLADOSBASEIVA16;" + getLd_totalTrasladosBaseIVA16() + "\r\n";
        txt += "TOTALTRASLADOSIMPUESTOIVA16;" + getLd_totalTrasladosImpuestoIVA16() + "\r\n";
        txt += "TOTALTRASLADOSBASEIVA8;" + getLs_totalTrasladosBaseIVA8() + "\r\n";
        txt += "TOTALTRASLADOSBASEIVA0;" + getLd_totalTrasladosBaseIVA0() + "\r\n";
        txt += "TOTALTRASLADOSIMPUESTOIVA0;" + getLd_totalTrasladosImpuestoIVA0() + "\r\n";
        txt += "TOTALTRASLADOSBASEIVAEXENTO;" + getLs_totalTrasladosBaseIVAexento() + "\r\n";
        txt += "MONTOTOTALPAGOS;" + getLd_montoTotalPagos() + "\r\n\n";

        return txt;

    }
    
    @Override
    public void fill(){
        
    }

}
