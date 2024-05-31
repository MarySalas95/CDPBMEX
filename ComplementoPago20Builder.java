package com.complementos;

import com.comp.complementos.DAO.ComplementComposite;
import com.comp.complementos.DTO.Cfdi40DTO;
import com.comp.complementos.DTO.Concepto40DTO;
import com.comp.complementos.DTO.DatosAcceso40DTO;
import com.comp.complementos.DTO.DoctoRelacionado40DTO;
import com.comp.complementos.DTO.Pago40DTO;
import com.comp.complementos.DTO.PagoCliente;
import com.comp.complementos.DTO.Receptor40DTO;
import com.comp.complementos.DTO.Retenciones40DTO;
import com.comp.complementos.DTO.Totales40DTO;
import com.comp.complementos.DTO.TrasladosP40DTO;
import static com.complementos.Impuestos.retP40;

/**
 *
 * @author Mary Villanueva
 *
 * Clase encargada de construir cada uno de los nodos del archivo TXT.
 *
 */
public class ComplementoPago20Builder extends ComplementoPagoBuilder {

    public ComplementoPago20Builder(PagoCliente pagoCliente) {
        super(pagoCliente);
    }

    @Override
    public ComplementoPago buildComplemento() {

        complementoPago = new ComplementoPago();
        arbolComplemento = new ComplementComposite();
        complementoPago.setArbolComplemento(arbolComplemento);

        arbolComplemento.addElement(new DatosAcceso40DTO());
        arbolComplemento.addElement(new Cfdi40DTO(pagoCliente));
        arbolComplemento.addElement(new Receptor40DTO(pagoCliente));
        arbolComplemento.addElement(new Concepto40DTO(pagoCliente));
        arbolComplemento.addElement(new Totales40DTO(pagoCliente));
        arbolComplemento.addElement(new Pago40DTO(pagoCliente));
        //arbolComplemento.addElement(new TrasladosP40DTO(pagoCliente));
        if (Impuestos.Iva16T(pagoCliente, "IVA16") == true) {
            arbolComplemento.addElement(new TrasladosP40DTO(pagoCliente, "IVA16"));
        }
        if (Impuestos.Iva00T(pagoCliente, "IVA00") == true) {
            arbolComplemento.addElement(new TrasladosP40DTO(pagoCliente, "IVA00"));
        }
        if (retP40(pagoCliente)) {
            arbolComplemento.addElement(new Retenciones40DTO(pagoCliente));
        }
        arbolComplemento.addElement(new DoctoRelacionado40DTO(pagoCliente));

        return complementoPago;
    }

}
