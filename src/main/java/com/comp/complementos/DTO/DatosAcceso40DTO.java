package com.comp.complementos.DTO;

import com.comp.complementos.DAO.DatosAcceso40DAO;
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
 * Clase encargada de crear el Nodo [DATOS_ACCESO] del TXT
 * 
 */
public class DatosAcceso40DTO extends DatosAcceso40DAO {

    @Override
    public void fill() {

        try {
            Connection BPCSConn = getInstance().getConnection();
            try (PreparedStatement ps = BPCSConn.prepareStatement(instance().getAcceso())) {
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    setLs_usuario(rs.getString("DATO1"));
                    setLs_password(rs.getString("DATO2"));
                }
            }
            getInstance().closeConnection(BPCSConn);

        } catch (SQLException e) {
            out.println("Error en DatosAcceso40DTO: " + e.getMessage());
            getLogger(DatosAcceso40DTO.class.getName()).log(SEVERE, null, e);
        }

    }

}
