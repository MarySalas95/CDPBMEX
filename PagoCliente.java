package com.comp.complementos.DTO;

/**
 *
 * @author Mary Villanueva
 * 
 * Clase encargada de almacenar el numero de transaccion, 
 * dicha variable será utilizada durante el proceso de creacion de cada uno de los CDP.
 * 
 */
public class PagoCliente {

    private int transactionProcess;//Transaction Process

    public int getTransactionProcess() {
        return transactionProcess;
    }

    public void setTransactionProcess(int secuenciaTransaccion) {
        this.transactionProcess = secuenciaTransaccion;
    }

}
