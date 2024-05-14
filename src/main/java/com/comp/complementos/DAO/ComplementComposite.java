package com.comp.complementos.DAO;

import java.util.ArrayList;

/**
 *
 * @author Mary Villanueva
 */
public class ComplementComposite extends ComplementElement {

    private ArrayList<ComplementElement> complementNodes;

    public ComplementComposite() {
        complementNodes = new ArrayList<>();
    }

    public void addElement(ComplementElement element) {
        complementNodes.add(element);
    }
    
    @Override
    public void fill(){                     
        for(ComplementElement componente:complementNodes){
            componente.fill();
        }
    }
    
    @Override
    public String createTXT() {
        String txt="";
        for(ComplementElement componente:complementNodes){
            txt+=componente.createTXT();
        }
        return txt;
    }

}
