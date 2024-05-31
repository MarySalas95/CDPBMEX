package com.comp.complementos.DTO;

import com.comp.complementos.DAO.Retenciones40DAO;
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
 * Clase encargada de crear el Nodo [RETENCIONESP40] del archivo TXT,
 * este nodo solamente será visible si la Factura cuenta con retenciones.
 * 
 */
public class Retenciones40DTO extends Retenciones40DAO {

    public Retenciones40DTO(PagoCliente pagoCliente) {
        this.pagoCliente = pagoCliente;
    }

    @Override
    public void fill() {

        //boolean existeRet = false;
        double baseTP = 0, importeTP = 0;

        try {

            Connection BPCSConn = getInstance().getConnection();
            try (PreparedStatement ps = BPCSConn.prepareStatement(instance().getImpuestos())) {
                ps.setString(1, "RET16");
                ps.setInt(2, pagoCliente.getTransactionProcess());
                
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    //existeRet = true;
                    baseTP = baseTP + rs.getDouble("BASETP");
                    importeTP = importeTP + (rs.getDouble("BASETDR") * rs.getDouble("TASAOCUOTATDR"));
                    setImpuestoRP(rs.getString("IMPUESTOTDR"));
                    setTipoFactorRP(rs.getString("TIPOFACTORTDR"));
                    setTasaOcuotaRP(rs.getDouble("TASAOCUOTATDR"));
                }
                
                setBaseRP(baseTP);
                setImporteRP(importeTP);
            }
            getInstance().closeConnection(BPCSConn);

        } catch (SQLException e) {
            out.println("Error en Retenciones40DTO: " + e.getMessage());
            getLogger(Retenciones40DTO.class.getName()).log(SEVERE, null, e);
        }

    }
}
