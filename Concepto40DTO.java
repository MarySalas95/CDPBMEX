package com.comp.complementos.DTO;

import com.comp.complementos.DAO.Concepto40DAO;
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
 * Clase encargada de crear el Nodo [CONCEPTO] del TXT
 * 
 */
public class Concepto40DTO extends Concepto40DAO {
    
    public Concepto40DTO(PagoCliente pagoCliente) {
        this.pagoCliente = pagoCliente;
    }

    @Override
    public void fill() {

        try {

            Connection BPCSConn = getInstance().getConnection();
            try (PreparedStatement ps = BPCSConn.prepareStatement(instance().getConsultaConcepto())) {
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    setLs_claveProdServ(rs.getString("CLAVE"));
                    setLs_cantidad("1");
                    setLs_claveUnidad(rs.getString("UNIDAD"));
                    setLs_descripción(rs.getString("DESCRIPCION"));
                    setLs_valorUnitario(rs.getString("VALOR"));
                    setLs_importe(rs.getString("IMPORTE"));
                    setLs_objetoIMP("01");
                }
            }
            getInstance().closeConnection(BPCSConn);

        } catch (SQLException e) {
            out.println("Error en Concepto40DTO: " + e.getMessage());
            getLogger(Concepto40DTO.class.getName()).log(SEVERE, null, e);
        }

    }

}
