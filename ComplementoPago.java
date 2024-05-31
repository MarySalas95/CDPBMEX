package com.complementos;

import com.comp.complementos.DAO.ComplementComposite;
import com.comp.complementos.DAO.ComplementElement;

/**
 *
 * @author Mary Villanueva
 * 
 */
public class ComplementoPago extends ComplementElement {
    
    private ComplementComposite arbolComplemento;
    
    @Override
    public void fill(){       
        arbolComplemento.fill();        
    }

    public ComplementElement getArbolComplemento() {
        return arbolComplemento;
    }

    public void setArbolComplemento(ComplementComposite arbolComplemento) {
        this.arbolComplemento = arbolComplemento;
    }

    @Override
    public String createTXT() {
        return arbolComplemento.createTXT();
    }
    
}
