package com.complementos;

import static com.comp.complementos.DB.ConnectionPool.getInstance;
import static com.comp.complementos.DB.ConsultasFacade.instance;
import com.comp.complementos.DTO.PagoCliente;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Mary Villanueva
 *
 * Clase encargada de realizar la validacion del tipo de impuestos que tiene la
 * factura. en base a esta validacion se crean los nodos de: [RETENCIONESP40],
 * [RETENCIONESDR40], [TRASLADOSP40] y [TRASLADOSDR40] estos dos ultimos
 * dependiendo del tipo de tasa IVA 0.00 ó IVA 0.16
 *
 */
public class Impuestos {

    public static boolean Iva16(PagoCliente pagoCliente, int factura) {

        try {

            boolean esIva16 = false;

            Connection c = getInstance().getConnection();
            try (PreparedStatement ps = c.prepareStatement(instance().getImpuestosBB())) {
                ps.setString(1, "IVA16");
                ps.setInt(2, pagoCliente.getTransactionProcess());
                ps.setInt(3, factura);

                ResultSet rs = ps.executeQuery();

                esIva16 = rs.next();
            }
            getInstance().closeConnection(c);

            return esIva16;

        } catch (SQLException e) {
            out.println("Error en Impuestos Iva16: " + e.getMessage());
            getLogger(Impuestos.class.getName()).log(SEVERE, null, e);
            return false;
        }

    }

    public static boolean Iva00(PagoCliente pagoCliente, int factura) {

        boolean esiva16 = false, esiva0 = false;

        try {

            Connection c = getInstance().getConnection();
            PreparedStatement ps = c.prepareStatement(instance().getImpuestosBB());
            ps.setString(1, "IVA00");
            ps.setInt(2, pagoCliente.getTransactionProcess());
            ps.setInt(3, factura);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                esiva0 = true;
            } else {
                ps = c.prepareStatement(instance().getImpuestosIVA00());
                ps.setInt(1, factura);

                rs = ps.executeQuery();

                while (rs.next()) {
                    esiva0 = !rs.getString("IVA").trim().equals("IVA16");
                }
            }

            /*if (esiva16) {
                esiva0 = false;
            } else {

                ps = c.prepareStatement(instance().getImpuestosIVA00());
                ps.setInt(1, factura);

                rs = ps.executeQuery();

                while (rs.next()) {
                    esiva0 = !rs.getString("IVA").trim().equals("IVA16");
                }
            }*/
            ps.close();
            getInstance().closeConnection(c);

        } catch (SQLException e) {
            out.println("Error en Impuestos Iva00: " + e.getMessage());
            getLogger(Impuestos.class.getName()).log(SEVERE, null, e);
        }

        return esiva0;
    }

    public static boolean ret(PagoCliente pagoCliente, int factura) {

        boolean esRet = false;

        try {

            Connection c = getInstance().getConnection();
            try (PreparedStatement ps = c.prepareStatement(instance().getImpuestosBB())) {
                ps.setString(1, "RET16");
                ps.setInt(2, pagoCliente.getTransactionProcess());
                ps.setInt(3, factura);

                ResultSet rs = ps.executeQuery();

                esRet = rs.next();
            }
            getInstance().closeConnection(c);

        } catch (SQLException e) {
            out.println("Error en Impuestos ret: " + e.getMessage());
            getLogger(Impuestos.class.getName()).log(SEVERE, null, e);
        }

        return esRet;

    }

    public static boolean retP40(PagoCliente pagoCliente) {

        boolean esRet = false;

        try {

            Connection c = getInstance().getConnection();
            try (PreparedStatement ps = c.prepareStatement(instance().getImpuestos())) {
                ps.setString(1, "RET16");
                ps.setInt(2, pagoCliente.getTransactionProcess());

                ResultSet rs = ps.executeQuery();

                esRet = rs.next();
            }
            getInstance().closeConnection(c);

        } catch (SQLException e) {
            out.println("Error en Impuestos retP40: " + e.getMessage());
            getLogger(Impuestos.class.getName()).log(SEVERE, null, e);
        }

        return esRet;

    }

    public static boolean Iva16T(PagoCliente pagoCliente, String ivA16) {

        boolean esIVA16 = false;

        try {

            Connection c = getInstance().getConnection();
            try (PreparedStatement ps = c.prepareStatement(instance().getImpuestosAA())) {
                ps.setInt(1, pagoCliente.getTransactionProcess());
                ps.setString(2, ivA16);

                ResultSet rs = ps.executeQuery();

                esIVA16 = rs.next();
            }
            getInstance().closeConnection(c);

        } catch (SQLException e) {
            out.println("Error en Impuestos Iva16T: " + e.getMessage());
            getLogger(Impuestos.class.getName()).log(SEVERE, null, e);
        }

        return esIVA16;

    }

    public static boolean Iva00T(PagoCliente pagoCliente, String ivA00) {

        boolean esIVA00 = false;
        double pagIVA = 0, pag = 0, VFACTURA = 0;

        try {

            Connection c = getInstance().getConnection();
            PreparedStatement ps = c.prepareStatement(instance().getImpuestosAA());
            ps.setInt(1, pagoCliente.getTransactionProcess());
            ps.setString(2, ivA00);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString("TXTRCD").trim().equals("IVA00")) {
                    esIVA00 = true;
                } else {
                    esIVA00 = false;
                }
            } else {
                ps = c.prepareStatement(instance().getImpuestos());
                ps.setString(1, "IVA16");
                ps.setInt(2, pagoCliente.getTransactionProcess());

                rs = ps.executeQuery();

                while (rs.next()) {
                    pagIVA = pagIVA + rs.getDouble("BASETP");
                    if (rs.getDouble("BASETDR") == rs.getDouble("VFACTURA")) {
                        esIVA00 = false;
                    } else {
                        if ((rs.getDouble("IMPORTETDR") + rs.getDouble("BASETDR") != rs.getDouble("VFACTURA"))) {
                            VFACTURA = rs.getDouble("VFACTURA") - (rs.getDouble("IMPORTETDR") + rs.getDouble("BASETDR"));
                        }
                    }
                }

                ps = c.prepareStatement(instance().getConsultaPago());
                ps.setInt(1, pagoCliente.getTransactionProcess());
                rs = ps.executeQuery();

                while (rs.next()) {
                    pag = pag + rs.getDouble("MONTO");
                }

                if (pag == pagIVA) {
                    if (VFACTURA > 0) {
                        esIVA00 = true;
                    } else {
                        esIVA00 = false;
                    }
                } else {
                    esIVA00 = true;
                }

            }
            ps.close();
            getInstance().closeConnection(c);
            return esIVA00;

        } catch (SQLException ex) {
            Logger.getLogger(Impuestos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return esIVA00;

    }

}
