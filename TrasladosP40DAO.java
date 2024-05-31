package com.comp.complementos.DAO;

/**
 *
 * @author Mary Villanueva
 */
public class TrasladosP40DAO extends ComplementElement {

    double ld_baseTP, ld_importeTP;
    String ls_impuestoTP, ls_tipoFactorTP, ls_tasaOcuotaTP;

    /**
     * @return the ld_baseTP
     */
    public double getLd_baseTP() {
        return ld_baseTP;
    }

    /**
     * @param ld_baseTP the ld_baseTP to set
     */
    public void setLd_baseTP(double ld_baseTP) {
        this.ld_baseTP = ld_baseTP;
    }

    /**
     * @return the ld_importeTP
     */
    public double getLd_importeTP() {
        return ld_importeTP;
    }

    /**
     * @param ld_importeTP the ld_importeTP to set
     */
    public void setLd_importeTP(double ld_importeTP) {
        this.ld_importeTP = ld_importeTP;
    }

    /**
     * @return the ls_impuestoTP
     */
    public String getLs_impuestoTP() {
        return ls_impuestoTP;
    }

    /**
     * @param ls_impuestoTP the ls_impuestoTP to set
     */
    public void setLs_impuestoTP(String ls_impuestoTP) {
        this.ls_impuestoTP = ls_impuestoTP;
    }

    /**
     * @return the ls_tipoFactorTP
     */
    public String getLs_tipoFactorTP() {
        return ls_tipoFactorTP;
    }

    /**
     * @param ls_tipoFactorTP the ls_tipoFactorTP to set
     */
    public void setLs_tipoFactorTP(String ls_tipoFactorTP) {
        this.ls_tipoFactorTP = ls_tipoFactorTP;
    }

    /**
     * @return the ls_tasaOcuotaTP
     */
    public String getLs_tasaOcuotaTP() {
        return ls_tasaOcuotaTP;
    }

    /**
     * @param ls_tasaOcuotaTP the ls_tasaOcuotaTP to set
     */
    public void setLs_tasaOcuotaTP(String ls_tasaOcuotaTP) {
        this.ls_tasaOcuotaTP = ls_tasaOcuotaTP;
    }

    @Override
    public String createTXT() {

        String txt = "";

        txt += "[TRASLADOSP40]\r\n";
        txt += "BASETP;" + getLd_baseTP() + "\r\n";
        txt += "IMPUESTOTP;" + getLs_impuestoTP() + "\r\n";
        txt += "TIPOFACTORTP;" + getLs_tipoFactorTP() + "\r\n";
        txt += "TASAOCUOTATP;" + getLs_tasaOcuotaTP() + "\r\n";
        txt += "IMPORTETP;" + getLd_importeTP() + "\r\n\n";

        return txt;

    }
    
    @Override
    public void fill(){
        
    }

}
