package com.comp.complementos.DTO;

import static com.comp.complementos.DB.ConnectionPool.getInstance;
import static com.comp.complementos.DB.ConsultasFacade.instance;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Mary Villanueva
 * 
 * Clase encargada de actualizar la infromacion de la Excepcion 01:Multiples Cuentas Bancarias,
 * esta excepcion viene de la plantilla Principal.jsp
 * 
 */
public class ActualizarCtasBancariasDTO {

    public int ActualizarCtas(String rfc, String banco, String cuenta, String transaccion, String mensaje) {

        int act = 0;
        String msg = mensaje.replace("01", "");

        try {

            out.println("Actualizando Ctas. Bancarias....");
            
            Connection BPCSConn = getInstance().getConnection();
            try (PreparedStatement ps = BPCSConn.prepareStatement(instance().setUpdateCtasBancarias())) {
                ps.setString(1, banco);
                ps.setString(2, cuenta);
                ps.setString(3, rfc);
                ps.setString(4, msg);
                ps.setString(5, transaccion);
                
                act = ps.executeUpdate();
            }
            getInstance().closeConnection(BPCSConn);

        } catch (SQLException e) {
            out.println("Error en ActualizarCtasBancariasDTO: " + e.getMessage());
            /*try{
                c.rollback();
                out.println("Error en ActualizarCtasBancariasDTO: " + e.getMessage());
            }catch(SQLException ex){
                getLogger(ActualizarCtasBancariasDTO.class.getName()).log(SEVERE, null, ex);
            }*/
        }

        out.println("Ctas. Bancarias Actualizadas");
        return act;

    }

}
