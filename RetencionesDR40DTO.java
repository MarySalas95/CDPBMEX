package com.comp.complementos.DTO;

import com.comp.complementos.DAO.RetencionesDR40DAO;
import static com.comp.complementos.DB.ConnectionPool.getInstance;
import static com.comp.complementos.DB.ConsultasFacade.instance;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Mary Villanueva
 * 
 * Clase encargada de crear el Nodo [RETENCIONESDR40] del archivo TXT,
 * este nodo solamente será visible si la Factura cuenta con retenciones.
 * 
 */
public class RetencionesDR40DTO extends RetencionesDR40DAO {

    private final String impuesto;
    private final int factura;
    private final double tcFact;
    
    public RetencionesDR40DTO(PagoCliente pagoCliente, String impuesto, int factura, double tcFact) {
        this.pagoCliente = pagoCliente;
        this.impuesto = impuesto;
        this.factura = factura;
        this.tcFact = tcFact;

        try {

            Connection BPCSConn = getInstance().getConnection();
            try (PreparedStatement ps = BPCSConn.prepareStatement(instance().getImpuestosDR())) {
                ps.setString(1, impuesto);
                ps.setInt(2, pagoCliente.getTransactionProcess());
                ps.setInt(3, factura);
                
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    setBaseDR(rs.getDouble("BASETP"));
                    setImpuestoDR(rs.getString("IMPUESTOTDR"));
                    setTipoFactorDR(rs.getString("TIPOFACTORTDR"));
                    setTasaOcuotaDR(rs.getDouble("TASAOCUOTATDR"));
                    setImporteDR((rs.getDouble("BASETDR") * rs.getDouble("TASAOCUOTATDR")));
                }
            }
            getInstance().closeConnection(BPCSConn);

        } catch (SQLException e) {
            out.println("Error en RetencionesDR40DTO: " + e.getMessage());
            getLogger(RetencionesDR40DTO.class.getName()).log(SEVERE, null, e);
        }

    }
}
