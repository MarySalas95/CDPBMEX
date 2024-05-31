package com.comp.complementos.DTO;

import com.comp.complementos.DAO.TrasladosDR40DAO;
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
public class TrasladosDR40DTO extends TrasladosDR40DAO {

    private String impuesto;
    private int factura;
    private double tcFact;
    private Connection BPCSConn = null;

    public TrasladosDR40DTO(PagoCliente pagoCliente, String impuesto, int factura, double tcFact) {
        this.impuesto = impuesto;
        this.factura = factura;
        this.tcFact = tcFact;

        //MJSV 16052024D
        DecimalFormatSymbols simbol = new DecimalFormatSymbols();
        simbol.setDecimalSeparator('.');

        DecimalFormat dec = new DecimalFormat("#.000000", simbol);
        DecimalFormat deci = new DecimalFormat("#.00", simbol);
        DecimalFormat dectc = new DecimalFormat("#.0000000", simbol);

        double valIVA0 = 0, iva0Fact, porcentaje = 0, pagado = 0, baseTP = 0, impuestos = 0, iva00 = 0, tcFact1 = 0;
        int existe = 0;

        try {

            Connection BPCSConn = getInstance().getConnection();
            PreparedStatement ps = BPCSConn.prepareStatement(instance().getFacturaIva());
            ps.setString(1, "%IVA%");
            ps.setInt(2, pagoCliente.getTransactionProcess());
            ps.setInt(3, factura);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                existe = 1;

                if (tcFact == 1) {
                    valIVA0 = (rs.getDouble("VFACTURA") - rs.getDouble("BASETDR"));
                } else {
                    valIVA0 = (rs.getDouble("VFACTURA") - (rs.getDouble("BASETP") / 1.16)); //MJSV 19102023
                }

                if (impuesto.equals("IVA16")) {
                    //Caso de que se liquide la cuenta en un solo pago.
                    if (valIVA0 == 0) {
                        baseTP = baseTP + (rs.getDouble("BASETP"));
                        impuestos = impuestos + (baseTP * 0.16);
                        iva00 = 0;
                    } else {
                        if (tcFact == 1) {
                            if (rs.getString("IMPUESTO").trim().equals("IVA16")) {
                                iva0Fact = (rs.getDouble("VFACTURA") - Double.parseDouble(deci.format((rs.getDouble("BASETDR") + rs.getDouble("IMPORTETDR")))));
                                porcentaje = 100 - (iva0Fact * 100 / rs.getDouble("VFACTURA"));
                                pagado = rs.getDouble("BASETP") * (porcentaje / 100);
                                baseTP = baseTP + (pagado / 1.16);
                                impuestos = impuestos + (pagado / 1.16) * .16;
                                iva00 = iva00 + (rs.getDouble("BASETP") - pagado);
                            }
                        } else {
                            iva0Fact = (rs.getDouble("VFACTURA") - ((rs.getDouble("BASETP") / 1.16) + (rs.getDouble("BASETP") / 1.16) * .16));
                            porcentaje = 100 - (iva0Fact * 100 / rs.getDouble("VFACTURA"));
                            pagado = rs.getDouble("BASETP") * (porcentaje / 100);
                            baseTP = baseTP + (pagado / 1.16);
                            impuestos = impuestos + (pagado / 1.16) * .16;
                            iva00 = iva00 + (rs.getDouble("BASETP") - pagado);
                        }
                    }
                } else {
                    if (rs.getString("IMPUESTO").trim().equals("IVA00") || rs.getString("IMPUESTO").trim().equals("")) {

                        tcFact1 = rs.getDouble("BASETDR") / rs.getDouble("VFACTURA");
                        if (tcFact == 1) {
                            valIVA0 = (rs.getDouble("VFACTURA") - rs.getDouble("BASETDR"));
                        } else {
                            tcFact1 = Double.parseDouble(dectc.format(tcFact1));
                            valIVA0 = (rs.getDouble("VFACTURA") * tcFact1) - (rs.getDouble("BASETDR") + rs.getDouble("IMPORTETDR"));
                            valIVA0 = Double.parseDouble(deci.format(valIVA0));
                        }

                        if (valIVA0 <= 0) {
                            iva00 = iva00 + rs.getDouble("BASETP");
                        } else {

                            iva0Fact = (rs.getDouble("VFACTURA") * tcFact1) - (rs.getDouble("BASETDR") + rs.getDouble("IMPORTETDR"));
                            porcentaje = 100 - (iva0Fact * 100 / rs.getDouble("VFACTURA"));

                            if (porcentaje == 100) {
                                pagado = rs.getDouble("BASETP") * (0 / 100);
                            } else {
                                pagado = rs.getDouble("BASETP") * (porcentaje / 100);
                            }

                            baseTP = baseTP + (pagado / 1.16);
                            impuestos = impuestos + (pagado / 1.16) * .16;
                            iva00 = 0 + (baseTP - pagado);
                        }
                    } else {
                        iva0Fact = (rs.getDouble("VFACTURA") - Double.parseDouble(deci.format((rs.getDouble("BASETDR") + rs.getDouble("IMPORTETDR")))));
                        porcentaje = 100 - (iva0Fact * 100 / rs.getDouble("VFACTURA"));
                        pagado = rs.getDouble("BASETP") * (porcentaje / 100);
                        baseTP = baseTP + (pagado / 1.16);
                        impuestos = impuestos + (pagado / 1.16) * .16;
                        iva00 = iva00 + (rs.getDouble("BASETP") - pagado);
                    }
                }

            }

            if (existe == 0) {
                ps = BPCSConn.prepareStatement(instance().getImpuestosIVA00T());
                ps.setInt(1, pagoCliente.getTransactionProcess());
                ps.setInt(2, pagoCliente.getTransactionProcess());
                ps.setInt(3, factura);

                rs = ps.executeQuery();

                while (rs.next()) {
                    iva00 = iva00 + rs.getDouble("IMPAGO");
                }
            }

            if (impuesto.equals("IVA16")) {

                if (baseTP > 0) {
                    setLd_baseTDR(Double.parseDouble(dec.format(baseTP)));
                    setLs_impuestoTDR("002");
                    setLs_tipoFactorTDR("Tasa");
                    setLs_tasaOcuotaTDR("0.16");
                    setLd_importeTDR(Double.parseDouble(dec.format(impuestos)));
                }
            }

            iva00 = Double.parseDouble(dec.format(iva00));

            if (impuesto.equals("IVA00")) {
                if (iva00 > 0.0) {
                    setLd_baseTDR(iva00);
                    setLs_impuestoTDR("002");
                    setLs_tipoFactorTDR("Tasa");
                    setLs_tasaOcuotaTDR("0.0");
                    setLd_importeTDR(0);
                }
            }

            ps.close();
            getInstance().closeConnection(BPCSConn);

        } catch (SQLException e) {
            out.println("Error en TrasladosDR40DTO: " + e.getMessage());
            getLogger(TrasladosDR40DTO.class.getName()).log(SEVERE, null, e);
        }

    }
}
