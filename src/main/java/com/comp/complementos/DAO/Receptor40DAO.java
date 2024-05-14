package com.comp.complementos.DAO;

/**
 *
 * @author Mary Villanueva
 */
public class Receptor40DAO extends ComplementElement {

    String ls_noCliente, ls_rfcR, ls_nombreR, ls_email1, ls_email2, ls_email3, ls_numRegIdTrib, ls_domicilioFiscalReceptor, ls_regimenFiscalReceptor;
    //String usocfdi;

    /**
     * @return the ls_noCliente
     */
    public String getLs_noCliente() {
        return ls_noCliente;
    }

    /**
     * @param ls_noCliente the ls_noCliente to set
     */
    public void setLs_noCliente(String ls_noCliente) {
        this.ls_noCliente = ls_noCliente;
    }

    /**
     * @return the ls_rfcR
     */
    public String getLs_rfcR() {
        return ls_rfcR;
    }

    /**
     * @param ls_rfcR the ls_rfcR to set
     */
    public void setLs_rfcR(String ls_rfcR) {
        this.ls_rfcR = ls_rfcR;
    }

    /**
     * @return the ls_nombreR
     */
    public String getLs_nombreR() {
        return ls_nombreR;
    }

    /**
     * @param ls_nombreR the ls_nombreR to set
     */
    public void setLs_nombreR(String ls_nombreR) {
        this.ls_nombreR = ls_nombreR;
    }

    /**
     * @return the ls_email1
     */
    public String getLs_email1() {
        return ls_email1;
    }

    /**
     * @param ls_email1 the ls_email1 to set
     */
    public void setLs_email1(String ls_email1) {
        this.ls_email1 = ls_email1;
    }

    /**
     * @return the ls_email2
     */
    public String getLs_email2() {
        return ls_email2;
    }

    /**
     * @param ls_email2 the ls_email2 to set
     */
    public void setLs_email2(String ls_email2) {
        this.ls_email2 = ls_email2;
    }

    /**
     * @return the ls_email3
     */
    public String getLs_email3() {
        return ls_email3;
    }

    /**
     * @param ls_email3 the ls_email3 to set
     */
    public void setLs_email3(String ls_email3) {
        this.ls_email3 = ls_email3;
    }

    /**
     * @return the ls_numRegIdTrib
     */
    public String getLs_numRegIdTrib() {
        return ls_numRegIdTrib;
    }

    /**
     * @param ls_numRegIdTrib the ls_numRegIdTrib to set
     */
    public void setLs_numRegIdTrib(String ls_numRegIdTrib) {
        this.ls_numRegIdTrib = ls_numRegIdTrib;
    }

    /**
     * @return the ls_domicilioFiscalReceptor
     */
    public String getLs_domicilioFiscalReceptor() {
        return ls_domicilioFiscalReceptor;
    }

    /**
     * @param ls_domicilioFiscalReceptor the ls_domicilioFiscalReceptor to set
     */
    public void setLs_domicilioFiscalReceptor(String ls_domicilioFiscalReceptor) {
        this.ls_domicilioFiscalReceptor = ls_domicilioFiscalReceptor;
    }

    /**
     * @return the ls_regimenFiscalReceptor
     */
    public String getLs_regimenFiscalReceptor() {
        return ls_regimenFiscalReceptor;
    }

    /**
     * @param ls_regimenFiscalReceptor the ls_regimenFiscalReceptor to set
     */
    public void setLs_regimenFiscalReceptor(String ls_regimenFiscalReceptor) {
        this.ls_regimenFiscalReceptor = ls_regimenFiscalReceptor;
    }

    @Override
    public String createTXT() {

        String txt = "";

        txt += "[RECEPTOR]\r\n";
        txt += "NOCLIENTE;" + getLs_noCliente() + "\r\n";
        txt += "RFCR;" + getLs_rfcR() + "\r\n";
        txt += "NOMBRER;" + getLs_nombreR() + "\r\n";
        txt += "EMAIL1;" + getLs_email1() + "\r\n";
        txt += "EMAIL2;" + getLs_email2() + "\r\n";
        txt += "EMAIL3;" + getLs_email3() + "\r\n";
        txt += "NUMREGIDTRIB;" + getLs_numRegIdTrib() + "\r\n";
        txt += "DOMICILIOFISCALRECEPTOR;" + getLs_domicilioFiscalReceptor() + "\r\n";
        txt += "REGIMENFISCALRECEPTOR;" + getLs_regimenFiscalReceptor() + "\r\n\n";

        return txt;

    }
    
    @Override
    public void fill(){
        
    }

}
