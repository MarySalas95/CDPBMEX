package com.comp.complementos.DTO;

import static com.comp.complementos.DB.ConnectionPool.getInstance;
import static com.comp.complementos.DB.ConsultasFacade.instance;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mary Villanueva
 * 
 * Clase encargada de actualizar la informacion de la Excepcion 02:Falta capturar UUID
 * esta excepcion viene de la plantilla Principal.jsp
 * 
 */
public class ActualizarUUIDDTO {

    public int ActualizarUUID(String uuid, String prefijo, String folio, String status, String mensaje, String transaccion) {

        int act = 0;

        try {
            
            out.println("Actualizando UUID....");

            Connection BPCSConn = getInstance().getConnection();
            PreparedStatement ps = BPCSConn.prepareStatement(instance().getUUID());
            ps.setString(1, uuid);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ps = BPCSConn.prepareStatement(instance().setUpdateUUID());
                ps.setString(1, prefijo);
                ps.setString(2, folio);
                ps.setString(3, status);
                ps.setString(4, uuid);
                act = ps.executeUpdate();
                out.println("UUID " + uuid + " Actualizado, registros=" + act);
            } else {
                ps = BPCSConn.prepareStatement(instance().setInsertUUID());
                ps.setString(1, prefijo);
                ps.setString(2, folio);
                ps.setString(3, uuid);
                ps.setString(4, status);
                act = ps.executeUpdate();
                out.println("UUID " + uuid + " Insertado, registros=" + act);
            }

            mensaje = mensaje.replace("02", "");

            if (act > 0) {
                //Actualizar el mensaje de la factura 
                ps = BPCSConn.prepareStatement(instance().setUpdateMSG());
                ps.setString(1, mensaje);
                ps.setString(2, folio);
                ps.setString(3, transaccion);
                ps.executeUpdate();
            }

            ps.close();
            getInstance().closeConnection(BPCSConn);

        } catch (SQLException e) {
            out.println("Error en ActualizarUUIDDTO: " + e.getMessage());
            /*try {
                c.rollback();
                out.println("Error en ActualizarUUIDDTO: " + e.getMessage());
            } catch (SQLException ex) {
                getLogger(ActualizarUUIDDTO.class.getName()).log(SEVERE, null, ex);
            }*/
        }

        out.println("UUID Actualizado");
        return act;

    }

}
