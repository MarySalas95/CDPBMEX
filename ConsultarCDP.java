package com.complementos;

import static com.comp.complementos.DB.ConnectionPool.getInstance;
import static com.comp.complementos.DB.ConsultasFacade.instance;
import com.comp.complementos.DTO.PagoCliente;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mary Villanueva
 *
 * Clase encargada de Consultar los complementos de pago en base a los filtros
 * aplicados en la Plantilla Principal.html
 *
 */
public class ConsultarCDP {

    public void sendComplementos(int FechaI, int FechaF) {

        try {

            Connection c = getInstance().getConnection();

            CrearTXT txt = new CrearTXT();
            PagoCliente pago = new PagoCliente();
            //BPCSConn = getConnection();

            out.println("Llamando a sendComplementos....");

            try (PreparedStatement ps = c.prepareStatement(instance().getComplementos())) {
                ps.setInt(1, FechaI);
                ps.setInt(2, FechaF);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    if (rs.getInt("TRANSACCION") == pago.getTransactionProcess()) {
                    } else {
                        out.println("Transaccion: " + rs.getInt("TRANSACCION"));
                        pago.setTransactionProcess(rs.getInt("TRANSACCION"));
                        txt.CreateTXT(pago);
                    }
                }
            }
            getInstance().closeConnection(c);
            out.println("Termina llamada sendComplementos");

        } catch (SQLException e) {
            out.println("Error en SendComplementos");
            /*try {
                c.rollback();
                out.println("Error en Consultas: " + e.getMessage());
            } catch (SQLException ex) {
                getLogger(ConsultarCDP.class.getName()).log(SEVERE, null, ex);
            }*/
        }

    }

}
