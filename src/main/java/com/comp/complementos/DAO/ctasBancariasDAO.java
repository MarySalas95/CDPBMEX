package com.comp.complementos.DAO;

/**
 *
 * @author Mary Villanueva
 */
public class ctasBancariasDAO {
    
    String Banco, RFC, ctaOrdenante, cliente;

    /**
     * @return the Banco
     */
    public String getBanco() {
        return Banco;
    }

    /**
     * @param Banco the Banco to set
     */
    public void setBanco(String Banco) {
        this.Banco = Banco;
    }

    /**
     * @return the RFC
     */
    public String getRFC() {
        return RFC;
    }

    /**
     * @param RFC the RFC to set
     */
    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    /**
     * @return the ctaOrdenante
     */
    public String getCtaOrdenante() {
        return ctaOrdenante;
    }

    /**
     * @param ctaOrdenante the ctaOrdenante to set
     */
    public void setCtaOrdenante(String ctaOrdenante) {
        this.ctaOrdenante = ctaOrdenante;
    }

    /**
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
}
