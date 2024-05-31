package com.comp.complementos.DTO;

import com.comp.complementos.DAO.Totales40DAO;
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
 * Clase encargada de crear el Nodo [TOTALES] del archivo TXT
 *
 */
public class Totales40DTO extends Totales40DAO {

    public Totales40DTO(PagoCliente pagoCliente) {

        try {

            //MJSV 16052024
            DecimalFormatSymbols simbol = new DecimalFormatSymbols();
            simbol.setDecimalSeparator('.');

            DecimalFormat dec = new DecimalFormat("#.00", simbol);
            DecimalFormat dect = new DecimalFormat("#.000000", simbol);
            double montoTotPagos = 0, valIVA0 = 0, iva0Fact = 0, porcentaje = 0, pagado = 0, baseTP = 0, impuestos = 0, iva00 = 0, tcFact = 0, tc = 0;

            Connection BPCSConn = getInstance().getConnection();
            PreparedStatement ps = BPCSConn.prepareStatement(instance().getPagCam());
            ps.setInt(1, pagoCliente.getTransactionProcess());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                montoTotPagos = Double.parseDouble(dec.format(rs.getDouble("MONTO") * rs.getDouble("TCAMBIO")));
                setLd_montoTotalPagos(montoTotPagos);

                tc = Double.parseDouble(dect.format(rs.getDouble("TCAMBIO")));
            }

            //Complementos con IVA16 - IVA0
            ps = BPCSConn.prepareStatement(instance().getImpuestos());
            ps.setString(1, "IVA16");
            ps.setInt(2, pagoCliente.getTransactionProcess());

            rs = ps.executeQuery();

            while (rs.next()) {

                if (tc == 1) {
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
                        if (tc == 1) { //MJSV 19102023
                            iva0Fact = (rs.getDouble("VFACTURA") - Double.parseDouble(dec.format(rs.getDouble("BASETDR") + rs.getDouble("IMPORTETDR"))));
                            porcentaje = 100 - (iva0Fact * 100 / rs.getDouble("VFACTURA"));
                            pagado = rs.getDouble("BASETP") * (porcentaje / 100);
                            baseTP = baseTP + (pagado / 1.16);
                            impuestos = impuestos + (pagado / 1.16) * .16;
                            iva00 = iva00 + (rs.getDouble("BASETP") - pagado);
                        } else { //MJSV 19102023
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

                tcFact = rs.getDouble("BASETDR") / rs.getDouble("VFACTURA");
                if (tc == 1) {
                    valIVA0 = (rs.getDouble("VFACTURA") - rs.getDouble("BASETDR"));
                } else {
                    //tcFact = Double.parseDouble(dectc.format(tcFact));
                    valIVA0 = (rs.getDouble("VFACTURA") * tcFact) - (rs.getDouble("BASETDR") + rs.getDouble("IMPORTETDR"));
                    valIVA0 = Double.parseDouble(dec.format(valIVA0));
                }

                if (valIVA0 <= 0) {
                    iva00 = iva00 + rs.getDouble("BASETP");

                } else {

                    if (rs.getString("IMPUESTO").trim().equals("IVA00")) {

                        iva0Fact = (rs.getDouble("VFACTURA") * tc) - (rs.getDouble("BASETDR") + rs.getDouble("IMPORTETDR"));
                        porcentaje = 100 - (iva0Fact * 100 / rs.getDouble("VFACTURA"));

                        if (porcentaje >= 100) {
                            pagado = rs.getDouble("BASETP") * (0 / 100);
                        } else {
                            pagado = rs.getDouble("BASETP") * (porcentaje / 100);
                        }

                        baseTP = baseTP + (pagado / 1.16);
                        impuestos = impuestos + (pagado / 1.16) * .16;
                        iva00 = getLd_montoTotalPagos();

                    }
                }

            }

            if (iva00 == 0) {
                if (tc == 1) {
                    if (getLd_montoTotalPagos() / 1.16 == baseTP) {
                        setLd_totalTrasladosBaseIVA0(0.0);
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

                //MJSV 10112023 ADECUACION PARA VALIDAR RETENCIONES 
                if (porcentaje >= 100) {
                    iva00 = 0;
                } else {

                    ps = BPCSConn.prepareStatement(instance().getPagCam());
                    ps.setInt(1, pagoCliente.getTransactionProcess());

                    rs = ps.executeQuery();

                    if (rs.next()) {
                        iva00 = rs.getDouble("MONTO") - Double.parseDouble(dec.format(pagado));
                    }
                }

                //MJSV 21052024 ADECUACION PARA MOSTRAR EL VALOR DE BASE IVA0
                if (pagado < getLd_montoTotalPagos()) {
                    iva00 = getLd_montoTotalPagos() - (pagado * (porcentaje / 100));
                }

            }

            //Truncar Decimales
            if (tc == 1) {
                baseTP = Double.parseDouble(dec.format(baseTP));
                impuestos = Double.parseDouble(dec.format(impuestos));
                iva00 = Double.parseDouble(dec.format(iva00));
            } else {
                baseTP = Double.parseDouble(dec.format(baseTP * tc));
                impuestos = Double.parseDouble(dec.format(impuestos * tc));
                iva00 = Double.parseDouble(dec.format(iva00 * tc));
            }

            setLd_totalTrasladosBaseIVA16(baseTP);
            setLd_totalTrasladosImpuestoIVA16(impuestos);
            setLd_totalTrasladosBaseIVA0(iva00);
            setLd_totalTrasladosImpuestoIVA0(0);

            //Control de Retenciones 
            ps = BPCSConn.prepareStatement(instance().getImpuestos());
            ps.setString(1, "RET16");
            ps.setInt(2, pagoCliente.getTransactionProcess());

            rs = ps.executeQuery();
            baseTP = 0;

            while (rs.next()) {
                baseTP = baseTP + rs.getDouble("BASETP");
            }

            ps.close();
            getInstance().closeConnection(BPCSConn);

            if (baseTP != 0) {
                setLd_totalRetencionesIVA(baseTP * .16);
            } else {
                setLd_totalRetencionesIVA(0);
            }

            //Otras retenciones que no aplican
            setLs_totalRetencionesISR("0");
            setLs_totalRetencionesIEPS("0");
            setLs_totalTrasladosBaseIVA8("0");
            setLs_totalTrasladosBaseIVAexento("0");

        } catch (SQLException e) {
            out.println("Error en Totales40DAO: " + e.getMessage());
            getLogger(Totales40DAO.class.getName()).log(SEVERE, null, e);
        }

    }
}
