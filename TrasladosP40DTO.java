package com.comp.complementos.DTO;

import com.comp.complementos.DAO.TrasladosP40DAO;
import static com.comp.complementos.DB.ConnectionPool.getInstance;
import static com.comp.complementos.DB.ConsultasFacade.instance;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Mary Villanueva
 *
 * Clase encargada de crear el Nodo [TRASLADOSDR40] en el archivo TXT, se pueden
 * crear dos nodos de este tipo, dependiendo del tipo de tasa que contiene la
 * Factura. TASA 0.00 ó TASA 0.16
 *
 */
public class TrasladosP40DTO extends TrasladosP40DAO {

    private String impuesto;

    public TrasladosP40DTO(PagoCliente pagoCliente, String impuesto) {
        this.pagoCliente = pagoCliente;
        this.impuesto = impuesto;
    }

    @Override
    public void fill() {

        //MJSV 16052024D
        DecimalFormatSymbols simbol = new DecimalFormatSymbols();
        simbol.setDecimalSeparator('.');

        DecimalFormat dec = new DecimalFormat("#.000000", simbol);
        DecimalFormat dectc = new DecimalFormat("#.00000000", simbol); //MJSV 08122023
        DecimalFormat deci = new DecimalFormat("#.00", simbol);
        double montoTotPagos = 0, valIVA0 = 0, iva0Fact, porcentaje = 0, pagado = 0, baseTP = 0, impuestos = 0, iva00 = 0, tcFact = 0;

        try {

            Connection BPCSConn = getInstance().getConnection();
            PreparedStatement ps = BPCSConn.prepareStatement(instance().getPagCam());
            ps.setInt(1, pagoCliente.getTransactionProcess());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                montoTotPagos = Double.parseDouble(dec.format(rs.getDouble("MONTO") * rs.getDouble("TCAMBIO")));
                tcFact = Double.parseDouble(dec.format(rs.getDouble("TCAMBIO")));
            }

            //Complementos con IVA16 - IVA0
            ps = BPCSConn.prepareStatement(instance().getImpuestos());
            ps.setString(1, "IVA16");
            ps.setInt(2, pagoCliente.getTransactionProcess());

            rs = ps.executeQuery();

            while (rs.next()) {

                if (tcFact == 1) {
                    valIVA0 = (rs.getDouble("VFACTURA") - rs.getDouble("BASETDR"));
                } else {
                    valIVA0 = (rs.getDouble("VFACTURA") - (rs.getDouble("BASETP") / 1.16)); //MJSV 19102023
                }

                //Caso de que se liquide la cuenta en un solo pago.
                if (valIVA0 == 0) {
                    baseTP = baseTP + (rs.getDouble("BASETP"));
                    impuestos = impuestos + (baseTP * 0.16);
                    iva00 = 0;
                } else {
                    if (rs.getString("IMPUESTO").trim().equals("IVA16")) {
                        if (tcFact == 1) {
                            iva0Fact = (rs.getDouble("VFACTURA") - Double.parseDouble(dec.format((rs.getDouble("BASETDR") + rs.getDouble("IMPORTETDR")))));
                            porcentaje = 100 - (iva0Fact * 100 / rs.getDouble("VFACTURA"));
                            pagado = rs.getDouble("BASETP") * (porcentaje / 100);
                            baseTP = baseTP + (pagado / 1.16);
                            impuestos = impuestos + (pagado / 1.16) * .16;
                            iva00 = iva00 + (rs.getDouble("BASETP") - pagado);
                        } else {
                            iva0Fact = (rs.getDouble("VFACTURA") - ((rs.getDouble("BASETP") / 1.16) + (rs.getDouble("BASETP") / 1.16) * .16));
                            porcentaje = 100 - (iva0Fact * 100 / rs.getDouble("VFACTURA"));
                            pagado = rs.getDouble("BASETP") * (porcentaje / 100);
                            baseTP = baseTP + (pagado / 1.16);
                            impuestos = impuestos + (pagado / 1.16) * .16;
                            iva00 = iva00 + (rs.getDouble("BASETP") - pagado);
                        }
                    }

                }

            }

            //Complementos con IVA16 - IVA0
            ps = BPCSConn.prepareStatement(instance().getImpuestos());
            ps.setString(1, "IVA00");
            ps.setInt(2, pagoCliente.getTransactionProcess());

            rs = ps.executeQuery();

            while (rs.next()) {

                tcFact = rs.getDouble("BASETDR") / rs.getDouble("VFACTURA"); //MJSV 08122023
                tcFact = Double.parseDouble(dectc.format(tcFact)); //MJSV 08122023
                if (tcFact == 1) {
                    valIVA0 = (rs.getDouble("VFACTURA") - rs.getDouble("BASETDR"));
                } else {
                    //tcFact = Double.parseDouble(dectc.format(tcFact));
                    valIVA0 = (rs.getDouble("VFACTURA") * tcFact) - (rs.getDouble("BASETDR") + rs.getDouble("IMPORTETDR"));
                    valIVA0 = Double.parseDouble(deci.format(valIVA0));
                }

                if (valIVA0 <= 0) {
                    iva00 = iva00 + rs.getDouble("BASETP");
                } else {

                    if (rs.getString("IMPUESTO").trim().equals("IVA00")) {
                        iva0Fact = (rs.getDouble("VFACTURA") * tcFact) - (rs.getDouble("BASETDR") + rs.getDouble("IMPORTETDR"));
                        porcentaje = 100 - (iva0Fact * 100 / rs.getDouble("VFACTURA"));

                        if (porcentaje == 100) {
                            pagado = rs.getDouble("BASETP") * (0 / 100);
                        } else {
                            pagado = rs.getDouble("BASETP") * (porcentaje / 100);
                        }

                        baseTP = baseTP + (pagado / 1.16);
                        impuestos = impuestos + (pagado / 1.16) * .16;
                        iva00 = montoTotPagos;
                    }
                }

            }

            if (iva00 == 0) {

                ps = BPCSConn.prepareStatement(instance().getImpuestos());
                ps.setString(1, "IVA16");
                ps.setInt(2, pagoCliente.getTransactionProcess());

                rs = ps.executeQuery();
                pagado = 0;

                while (rs.next()) {
                    iva00 = rs.getDouble("VFACTURA") - (rs.getDouble("BASETDR") + rs.getDouble("IMPORTETDR"));
                    porcentaje = 100 - (iva00 / rs.getDouble("VFACTURA"));
                    pagado = pagado + rs.getDouble("BASETP") * (porcentaje / 100);
                }

                ps = BPCSConn.prepareStatement(instance().getPagCam());
                ps.setInt(1, pagoCliente.getTransactionProcess());

                rs = ps.executeQuery();

                if (rs.next()) {
                    iva00 = rs.getDouble("MONTO") - pagado;
                }

            }

            //Truncar Decimales
            baseTP = Double.parseDouble(dec.format(baseTP));
            impuestos = Double.parseDouble(dec.format(impuestos));

            if (impuesto.equals("IVA16")) {
                if (baseTP > 0.0) {

                    setLd_baseTP(baseTP);
                    setLs_impuestoTP("002");
                    setLs_tipoFactorTP("Tasa");
                    setLs_tasaOcuotaTP("0.16");
                    setLd_importeTP(impuestos);
                }
            }

            iva00 = Double.parseDouble(dec.format(iva00));

            if (impuesto.equals("IVA00")) {
                if (iva00 > 0.0) {

                    setLd_baseTP(iva00);
                    setLs_impuestoTP("002");
                    setLs_tipoFactorTP("Tasa");
                    setLs_tasaOcuotaTP("0.0");
                    setLd_importeTP(0);
                }
            }

            ps.close();
            getInstance().closeConnection(BPCSConn);

        } catch (SQLException e) {
            out.println("Error en TrasladosP40DTO: " + e.getMessage());
            getLogger(TrasladosP40DTO.class.getName()).log(SEVERE, null, e);
        }
    }

}
