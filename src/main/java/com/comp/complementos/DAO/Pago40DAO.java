package com.comp.complementos.DAO;

/**
 *
 * @author Mary Villanueva
 */
public class Pago40DAO extends ComplementElement {

    String ls_fechaPago, ls_formaPago, ls_monedaP, ls_numOperacion, ls_rfcemisorctaord, ls_nomBancoOrdext, ls_ctaOrdenante, ls_rfcEmisorctaben, ls_ctaBeneficiario;
    double ld_tipoCambioP, ld_monto;

    /**
     * @return the ls_fechaPago
     */
    public String getLs_fechaPago() {
        return ls_fechaPago;
    }

    /**
     * @param ls_fechaPago the ls_fechaPago to set
     */
    public void setLs_fechaPago(String ls_fechaPago) {
        this.ls_fechaPago = ls_fechaPago;
    }

    /**
     * @return the ls_formaPago
     */
    public String getLs_formaPago() {
        return ls_formaPago;
    }

    /**
     * @param ls_formaPago the ls_formaPago to set
     */
    public void setLs_formaPago(String ls_formaPago) {
        this.ls_formaPago = ls_formaPago;
    }

    /**
     * @return the ls_monedaP
     */
    public String getLs_monedaP() {
        return ls_monedaP;
    }

    /**
     * @param ls_monedaP the ls_monedaP to set
     */
    public void setLs_monedaP(String ls_monedaP) {
        this.ls_monedaP = ls_monedaP;
    }

    /**
     * @return the ld_tipoCambioP
     */
    public double getLd_tipoCambioP() {
        return ld_tipoCambioP;
    }

    /**
     * @param ld_tipoCambioP the ld_tipoCambioP to set
     */
    public void setLd_tipoCambioP(double ld_tipoCambioP) {
        this.ld_tipoCambioP = ld_tipoCambioP;
    }

    /**
     * @return the ld_monto
     */
    public double getLd_monto() {
        return ld_monto;
    }

    /**
     * @param ld_monto the ld_monto to set
     */
    public void setLd_monto(double ld_monto) {
        this.ld_monto = ld_monto;
    }

    /**
     * @return the ls_numOperacion
     */
    public String getLs_numOperacion() {
        return ls_numOperacion;
    }

    /**
     * @param ls_numOperacion the ls_numOperacion to set
     */
    public void setLs_numOperacion(String ls_numOperacion) {
        this.ls_numOperacion = ls_numOperacion;
    }

    /**
     * @return the ls_rfcemisorctaord
     */
    public String getLs_rfcemisorctaord() {
        return ls_rfcemisorctaord;
    }

    /**
     * @param ls_rfcemisorctaord the ls_rfcemisorctaord to set
     */
    public void setLs_rfcemisorctaord(String ls_rfcemisorctaord) {
        this.ls_rfcemisorctaord = ls_rfcemisorctaord;
    }

    /**
     * @return the ls_nomBancoOrdext
     */
    public String getLs_nomBancoOrdext() {
        return ls_nomBancoOrdext;
    }

    /**
     * @param ls_nomBancoOrdext the ls_nomBancoOrdext to set
     */
    public void setLs_nomBancoOrdext(String ls_nomBancoOrdext) {
        this.ls_nomBancoOrdext = ls_nomBancoOrdext;
    }

    /**
     * @return the ls_ctaOrdenante
     */
    public String getLs_ctaOrdenante() {
        return ls_ctaOrdenante;
    }

    /**
     * @param ls_ctaOrdenante the ls_ctaOrdenante to set
     */
    public void setLs_ctaOrdenante(String ls_ctaOrdenante) {
        this.ls_ctaOrdenante = ls_ctaOrdenante;
    }

    /**
     * @return the ls_rfcEmisorctaben
     */
    public String getLs_rfcEmisorctaben() {
        return ls_rfcEmisorctaben;
    }

    /**
     * @param ls_rfcEmisorctaben the ls_rfcEmisorctaben to set
     */
    public void setLs_rfcEmisorctaben(String ls_rfcEmisorctaben) {
        this.ls_rfcEmisorctaben = ls_rfcEmisorctaben;
    }

    /**
     * @return the ls_ctaBeneficiario
     */
    public String getLs_ctaBeneficiario() {
        return ls_ctaBeneficiario;
    }

    /**
     * @param ls_ctaBeneficiario the ls_ctaBeneficiario to set
     */
    public void setLs_ctaBeneficiario(String ls_ctaBeneficiario) {
        this.ls_ctaBeneficiario = ls_ctaBeneficiario;
    }

    @Override
    public String createTXT() {

        String txt = "";

        txt += "[PAGO]\r\n";
        txt += "FECHAPAGO;" + getLs_fechaPago() + "\r\n";
        txt += "FORMADEPAGOP;" + getLs_formaPago() + "\r\n";
        txt += "MONEDAP;" + getLs_monedaP() + "\r\n";
        txt += "TIPOCAMBIOP;" + getLd_tipoCambioP() + "\r\n";
        txt += "MONTO;" + getLd_monto() + "\r\n";
        txt += "NUMOPERACION;" + getLs_numOperacion() + "\r\n";
        txt += "RFCEMISORCTAORD;" + getLs_rfcemisorctaord() + "\r\n";
        txt += "NOMBANCOORDEXT;" + getLs_nomBancoOrdext() + "\r\n";
        txt += "CTAORDENANTE;" + getLs_ctaOrdenante() + "\r\n";
        txt += "RFCEMISORCTABEN;" + getLs_rfcEmisorctaben() + "\r\n";
        txt += "CTABENEFICIARIO;" + getLs_ctaBeneficiario() + "\r\n\n";

        return txt;

    }
    
    @Override
    public void fill(){
        
    }

}
