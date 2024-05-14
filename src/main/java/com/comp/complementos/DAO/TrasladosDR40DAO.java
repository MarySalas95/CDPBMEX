package com.comp.complementos.DAO;

/**
 *
 * @author Mary Villanueva
 */
public class TrasladosDR40DAO extends ComplementElement {

    private double ld_baseTDR, ld_importeTDR;
    private String ls_impuestoTDR, ls_tipoFactorTDR, ls_tasaOcuotaTDR;

    /**
     * @return the ld_baseTDR
     */
    public double getLd_baseTDR() {
        return ld_baseTDR;
    }

    /**
     * @param ld_baseTDR the ld_baseTDR to set
     */
    public void setLd_baseTDR(double ld_baseTDR) {
        this.ld_baseTDR = ld_baseTDR;
    }

    /**
     * @return the ld_importeTDR
     */
    public double getLd_importeTDR() {
        return ld_importeTDR;
    }

    /**
     * @param ld_importeTDR the ld_importeTDR to set
     */
    public void setLd_importeTDR(double ld_importeTDR) {
        this.ld_importeTDR = ld_importeTDR;
    }

    /**
     * @return the ls_impuestoTDR
     */
    public String getLs_impuestoTDR() {
        return ls_impuestoTDR;
    }

    /**
     * @param ls_impuestoTDR the ls_impuestoTDR to set
     */
    public void setLs_impuestoTDR(String ls_impuestoTDR) {
        this.ls_impuestoTDR = ls_impuestoTDR;
    }

    /**
     * @return the ls_tipoFactorTDR
     */
    public String getLs_tipoFactorTDR() {
        return ls_tipoFactorTDR;
    }

    /**
     * @param ls_tipoFactorTDR the ls_tipoFactorTDR to set
     */
    public void setLs_tipoFactorTDR(String ls_tipoFactorTDR) {
        this.ls_tipoFactorTDR = ls_tipoFactorTDR;
    }

    /**
     * @return the ls_tasaOcuotaTDR
     */
    public String getLs_tasaOcuotaTDR() {
        return ls_tasaOcuotaTDR;
    }

    /**
     * @param ls_tasaOcuotaTDR the ls_tasaOcuotaTDR to set
     */
    public void setLs_tasaOcuotaTDR(String ls_tasaOcuotaTDR) {
        this.ls_tasaOcuotaTDR = ls_tasaOcuotaTDR;
    }

    @Override
    public String createTXT() {

        String txt = "";

        txt += "[TRASLADOSDR40]\r\n";
        txt += "BASETDR;" + getLd_baseTDR() + "\r\n";
        txt += "IMPUESTOTDR;" + getLs_impuestoTDR() + "\r\n";
        txt += "TIPOFACTORTDR;" + getLs_tipoFactorTDR() + "\r\n";
        txt += "TASAOCUOTATDR;" + getLs_tasaOcuotaTDR() + "\r\n";
        txt += "IMPORTETDR;" + getLd_importeTDR() + "\r\n\n";

        return txt;

    }
    
    @Override
    public void fill(){
        
    }

}
