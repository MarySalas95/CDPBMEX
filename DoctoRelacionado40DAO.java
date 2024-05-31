package com.comp.complementos.DAO;

/**
 *
 * @author Mary Villanueva
 */
public class DoctoRelacionado40DAO extends ComplementElement {

    String ls_idDocumento, ls_seriePago, ls_monedaDR, ls_objetoImp, ls_EquivalenciaDR;
    int li_folioPago, li_numParcialidad;
    double ld_impSaldoANT, ld_impPagado, ld_impSaldoInsoluto;
    boolean esUUID;

    /**
     * @return the ls_idDocumento
     */
    public String getLs_idDocumento() {
        return ls_idDocumento;
    }

    /**
     * @param ls_idDocumento the ls_idDocumento to set
     */
    public void setLs_idDocumento(String ls_idDocumento) {
        this.ls_idDocumento = ls_idDocumento;
    }

    /**
     * @return the ls_seriePago
     */
    public String getLs_seriePago() {
        return ls_seriePago;
    }

    /**
     * @param ls_seriePago the ls_seriePago to set
     */
    public void setLs_seriePago(String ls_seriePago) {
        this.ls_seriePago = ls_seriePago;
    }

    /**
     * @return the ls_monedaDR
     */
    public String getLs_monedaDR() {
        return ls_monedaDR;
    }

    /**
     * @param ls_monedaDR the ls_monedaDR to set
     */
    public void setLs_monedaDR(String ls_monedaDR) {
        this.ls_monedaDR = ls_monedaDR;
    }

    /**
     * @return the ls_objetoImp
     */
    public String getLs_objetoImp() {
        return ls_objetoImp;
    }

    /**
     * @param ls_objetoImp the ls_objetoImp to set
     */
    public void setLs_objetoImp(String ls_objetoImp) {
        this.ls_objetoImp = ls_objetoImp;
    }

    /**
     * @return the ls_EquivalenciaDR
     */
    public String getLs_EquivalenciaDR() {
        return ls_EquivalenciaDR;
    }

    /**
     * @param ls_EquivalenciaDR the ls_EquivalenciaDR to set
     */
    public void setLs_EquivalenciaDR(String ls_EquivalenciaDR) {
        this.ls_EquivalenciaDR = ls_EquivalenciaDR;
    }

    /**
     * @return the li_folioPago
     */
    public int getLi_folioPago() {
        return li_folioPago;
    }

    /**
     * @param li_folioPago the li_folioPago to set
     */
    public void setLi_folioPago(int li_folioPago) {
        this.li_folioPago = li_folioPago;
    }

    /**
     * @return the li_numParcialidad
     */
    public int getLi_numParcialidad() {
        return li_numParcialidad;
    }

    /**
     * @param li_numParcialidad the li_numParcialidad to set
     */
    public void setLi_numParcialidad(int li_numParcialidad) {
        this.li_numParcialidad = li_numParcialidad;
    }

    /**
     * @return the ld_impSaldoANT
     */
    public double getLd_impSaldoANT() {
        return ld_impSaldoANT;
    }

    /**
     * @param ld_impSaldoANT the ld_impSaldoANT to set
     */
    public void setLd_impSaldoANT(double ld_impSaldoANT) {
        this.ld_impSaldoANT = ld_impSaldoANT;
    }

    /**
     * @return the ld_impPagado
     */
    public double getLd_impPagado() {
        return ld_impPagado;
    }

    /**
     * @param ld_impPagado the ld_impPagado to set
     */
    public void setLd_impPagado(double ld_impPagado) {
        this.ld_impPagado = ld_impPagado;
    }

    /**
     * @return the ld_impSaldoInsoluto
     */
    public double getLd_impSaldoInsoluto() {
        return ld_impSaldoInsoluto;
    }

    /**
     * @param ld_impSaldoInsoluto the ld_impSaldoInsoluto to set
     */
    public void setLd_impSaldoInsoluto(double ld_impSaldoInsoluto) {
        this.ld_impSaldoInsoluto = ld_impSaldoInsoluto;
    }

    /**
     * @return the esUUID
     */
    public boolean isEsUUID() {
        return esUUID;
    }

    /**
     * @param esUUID the esUUID to set
     */
    public void setEsUUID(boolean esUUID) {
        this.esUUID = esUUID;
    }
    
    @Override
    public String createTXT() {

        String txt = "";

        txt += "[DOCTORELACIONADO]\r\n";
        txt += "IDDOCUMENTO;" + getLs_idDocumento() + "\r\n";
        txt += "SERIEPAGO;" + getLs_seriePago() + "\r\n";
        txt += "FOLIOPAGO;" + getLi_folioPago() + "\r\n";
        txt += "MONEDADR;" + getLs_monedaDR() + "\r\n";
        txt += "NUMPARCIALIDAD;" + getLi_numParcialidad() + "\r\n";
        txt += "IMPSALDOANT;" + getLd_impSaldoANT() + "\r\n";
        txt += "IMPPAGADO;" + getLd_impPagado() + "\r\n";
        txt += "IMPSALDOINSOLUTO;" + getLd_impSaldoInsoluto() + "\r\n";
        txt += "OBJETOIMP;" + getLs_objetoImp() + "\r\n";
        txt += "EQUIVALENCIADR;" + getLs_EquivalenciaDR() + "\r\n\n";

        return txt;

    }

    @Override
    public void fill() {

    }

}
