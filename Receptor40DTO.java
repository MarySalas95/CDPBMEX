package com.comp.complementos.DTO;

import com.comp.complementos.DAO.Receptor40DAO;
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
 * Clase encargada de crea el Nodo [RECEPTOR] del archivo TXT
 * 
 */
public class Receptor40DTO extends Receptor40DAO {
    
    public Receptor40DTO(PagoCliente pagoCliente) {
        this.pagoCliente = pagoCliente;
    }

    @Override
    public void fill() {

        try {

            Connection BPCSConn = getInstance().getConnection();
            PreparedStatement ps = BPCSConn.prepareStatement(instance().getConsultaReceptor());
            ps.setInt(1, pagoCliente.getTransactionProcess());

            ResultSet rs = ps.executeQuery();

            //Obtenemos parte de la informacion del Nodo [RECEPTOR]
            if (rs.next()) {
                setLs_noCliente(rs.getString("NOCLIENTE"));
                if (rs.getString("TIP").trim().equals("SP")) {
                    setLs_rfcR(rs.getString(2)); //"RFDR"
                    setLs_nombreR(rs.getString("NOMBRER"));
                    setLs_email1(rs.getString("EMAIL1"));
                    setLs_email2(rs.getString("EMAIL2"));
                    setLs_email3(rs.getString("EMAIL3"));
                    setLs_numRegIdTrib(rs.getString("NUMREGIDTRIB"));
                    setLs_domicilioFiscalReceptor(rs.getString("DOMICILIOFISCALRECEPTOR"));
                    setLs_regimenFiscalReceptor(rs.getString("REGIMENFISCALRECEPTOR"));
                } else {
                    //Validacion para determinar si es Facturaje Financiero FF, es decir se recibe el pago a nombre de otro cliente.
                    ps = BPCSConn.prepareStatement(instance().getFinanciera());
                    ps.setString(1, rs.getString("NOCLIENTE"));

                    rs = ps.executeQuery();
                    if (rs.next()) {
                        setLs_rfcR(rs.getString("RFC"));
                        setLs_nombreR(rs.getString("RAZONSOCIAL"));
                        setLs_email1(rs.getString("EMAIL1"));
                        setLs_email2(rs.getString("EMAIL2"));
                        setLs_email3(rs.getString("EMAIL3"));
                        setLs_numRegIdTrib(rs.getString("NIT"));
                        setLs_domicilioFiscalReceptor(rs.getString("DOMICILIOFISCALRECEPTOR"));
                        setLs_regimenFiscalReceptor(rs.getString("REGIMENFISCALRECEPTOR"));
                    }
                }

            }
            
            ps.close();
            getInstance().closeConnection(BPCSConn);

        } catch (SQLException e) {
            out.println("Error en Receptor40DTO: " + e.getMessage());
            getLogger(Receptor40DTO.class.getName()).log(SEVERE, null, e);
        }

    }

}
