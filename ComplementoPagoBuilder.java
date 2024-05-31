package com.complementos;

import com.comp.complementos.DAO.ComplementComposite;
import com.comp.complementos.DTO.PagoCliente;

/**
 *
 * @author Mary Villanueva
 * 
 */
public abstract class ComplementoPagoBuilder {
    
    protected ComplementoPago complementoPago;
    protected ComplementComposite arbolComplemento;
    protected PagoCliente pagoCliente;

    public ComplementoPagoBuilder(PagoCliente pagoCliente) {
        this.pagoCliente = pagoCliente;
    }
    
    public abstract ComplementoPago buildComplemento();

    public PagoCliente getPagoCliente() {
        return pagoCliente;
    }

    public void setPagoCliente(PagoCliente pagoCliente) {
        this.pagoCliente = pagoCliente;
    }
}
