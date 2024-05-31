package com.comp.complementos.DAO;

/**
 *
 * @author Mary Villanueva
 */
public class Retenciones40DAO extends ComplementElement {

    double baseRP, tasaOcuotaRP, importeRP;
    String impuestoRP, tipoFactorRP;

    /**
     * @return the baseRP
     */
    public double getBaseRP() {
        return baseRP;
    }

    /**
     * @param baseRP the baseRP to set
     */
    public void setBaseRP(double baseRP) {
        this.baseRP = baseRP;
    }

    /**
     * @return the tasaOcuotaRP
     */
    public double getTasaOcuotaRP() {
        return tasaOcuotaRP;
    }

    /**
     * @param tasaOcuotaRP the tasaOcuotaRP to set
     */
    public void setTasaOcuotaRP(double tasaOcuotaRP) {
        this.tasaOcuotaRP = tasaOcuotaRP;
    }

    /**
     * @return the importeRP
     */
    public double getImporteRP() {
        return importeRP;
    }

    /**
     * @param importeRP the importeRP to set
     */
    public void setImporteRP(double importeRP) {
        this.importeRP = importeRP;
    }

    /**
     * @return the impuestoRP
     */
    public String getImpuestoRP() {
        return impuestoRP;
    }

    /**
     * @param impuestoRP the impuestoRP to set
     */
    public void setImpuestoRP(String impuestoRP) {
        this.impuestoRP = impuestoRP;
    }

    /**
     * @return the tipoFactorRP
     */
    public String getTipoFactorRP() {
        return tipoFactorRP;
    }

    /**
     * @param tipoFactorRP the tipoFactorRP to set
     */
    public void setTipoFactorRP(String tipoFactorRP) {
        this.tipoFactorRP = tipoFactorRP;
    }

    @Override
    public String createTXT() {

        String txt = "";

        txt += "[RETENCIONESP40]\r\n";
        txt += "BASERP;" + getBaseRP() + "\r\n";
        txt += "IMPUESTORP;" + getImpuestoRP() + "\r\n";
        txt += "TIPOFACTORRP;" + getTipoFactorRP() + "\r\n";
        txt += "TASAOCUOTARP;" + getTasaOcuotaRP() + "\r\n";
        txt += "IMPORTERP;" + getImporteRP() + "\r\n\n";

        return txt;

    }
    
    @Override
    public void fill(){
        
    }

}
