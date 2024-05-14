package com.comp.complementos.DAO;

/**
 *
 * @author Mary Villanueva
 */
public class detalleDAO {

   //DETALLE DCP
    int transaccion, numFactura, parcialidad;
    double ldSaldoAnterior, ldMontoPagado, ldNvoSaldo;
    String lsMoneda, lsBanco, lsCuenta, lsRFCBanco, lsMensaje, lsMensaje2, lsPrefijo;
    
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

    //DETALE DE CDP
    /**
     * @return the numFactura
     */
    public int getNumFactura() {
        return numFactura;
    }

    /**
     * @param numFactura the numFactura to set
     */
    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    /**
     * @return the parcialidad
     */
    public int getParcialidad() {
        return parcialidad;
    }

    /**
     * @param parcialidad the parcialidad to set
     */
    public void setParcialidad(int parcialidad) {
        this.parcialidad = parcialidad;
    }

    /**
     * @return the ldSaldoAnterior
     */
    public double getLdSaldoAnterior() {
        return ldSaldoAnterior;
    }

    /**
     * @param ldSaldoAnterior the ldSaldoAnterior to set
     */
    public void setLdSaldoAnterior(double ldSaldoAnterior) {
        this.ldSaldoAnterior = ldSaldoAnterior;
    }

    /**
     * @return the ldMontoPagado
     */
    public double getLdMontoPagado() {
        return ldMontoPagado;
    }

    /**
     * @param ldMontoPagado the ldMontoPagado to set
     */
    public void setLdMontoPagado(double ldMontoPagado) {
        this.ldMontoPagado = ldMontoPagado;
    }

    /**
     * @return the ldNvoSaldo
     */
    public double getLdNvoSaldo() {
        return ldNvoSaldo;
    }

    /**
     * @param ldNvoSaldo the ldNvoSaldo to set
     */
    public void setLdNvoSaldo(double ldNvoSaldo) {
        this.ldNvoSaldo = ldNvoSaldo;
    }

    /**
     * @return the lsMoneda
     */
    public String getLsMoneda() {
        return lsMoneda;
    }

    /**
     * @param lsMoneda the lsMoneda to set
     */
    public void setLsMoneda(String lsMoneda) {
        this.lsMoneda = lsMoneda;
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
     * @return the lsMensaje2
     */
    public String getLsMensaje2() {
        return lsMensaje2;
    }

    /**
     * @param lsMensaje2 the lsMensaje2 to set
     */
    public void setLsMensaje2(String lsMensaje2) {
        this.lsMensaje2 = lsMensaje2;
    }

    /**
     * @return the lsPrefijo
     */
    public String getLsPrefijo() {
        return lsPrefijo;
    }

    /**
     * @param lsPrefijo the lsPrefijo to set
     */
    public void setLsPrefijo(String lsPrefijo) {
        this.lsPrefijo = lsPrefijo;
    }

}
