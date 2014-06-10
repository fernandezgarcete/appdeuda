package util;


import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class EmpleadoFilter implements Filter{
    private String iniciador;
    
    public EmpleadoFilter(String iniciador){
        this.iniciador = iniciador.toLowerCase();
    }

    @Override
    public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {
        String mueca = ("" + item.getItemProperty("nombre").getValue() + 
                item.getItemProperty("aquien").getValue()).toLowerCase();
        
        return mueca.contains(iniciador);
    }

    @Override
    public boolean appliesToProperty(Object propertyId) {
        return true;
    }
    
}
