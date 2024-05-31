package com.comp.complementos.DAO;

/**
 *
 * @author Mary Villanueva
 */
public class RetencionesDR40DAO extends ComplementElement {

    double baseDR, tasaOcuotaDR, importeDR;
    String impuestoDR, tipoFactorDR;

    /**
     * @return the baseDR
     */
    public double getBaseDR() {
        return baseDR;
    }

    /**
     * @param baseDR the baseDR to set
     */
    public void setBaseDR(double baseDR) {
        this.baseDR = baseDR;
    }

    /**
     * @return the tasaOcuotaDR
     */
    public double getTasaOcuotaDR() {
        return tasaOcuotaDR;
    }

    /**
     * @param tasaOcuotaDR the tasaOcuotaDR to set
     */
    public void setTasaOcuotaDR(double tasaOcuotaDR) {
        this.tasaOcuotaDR = tasaOcuotaDR;
    }

    /**
     * @return the importeDR
     */
    public double getImporteDR() {
        return importeDR;
    }

    /**
     * @param importeDR the importeDR to set
     */
    public void setImporteDR(double importeDR) {
        this.importeDR = importeDR;
    }

    /**
     * @return the impuestoDR
     */
    public String getImpuestoDR() {
        return impuestoDR;
    }

    /**
     * @param impuestoDR the impuestoDR to set
     */
    public void setImpuestoDR(String impuestoDR) {
        this.impuestoDR = impuestoDR;
    }

    /**
     * @return the tipoFactorDR
     */
    public String getTipoFactorDR() {
        return tipoFactorDR;
    }

    /**
     * @param tipoFactorDR the tipoFactorDR to set
     */
    public void setTipoFactorDR(String tipoFactorDR) {
        this.tipoFactorDR = tipoFactorDR;
    }

    @Override
    public String createTXT() {

        String txt = "";

        txt += "[RETENCIONESDR40]\r\n";
        txt += "BASERDR;" + getBaseDR() + "\r\n";
        txt += "IMPUESTORDR;" + getImpuestoDR() + "\r\n";
        txt += "TIPOFACTORRDR;" + getTipoFactorDR() + "\r\n";
        txt += "TASAOCUOTARDR;" + getTasaOcuotaDR() + "\r\n";
        txt += "IMPORTERDR;" + getImporteDR() + "\r\n\n";

        return txt;

    }
    
    @Override
    public void fill(){
        
    }

}
