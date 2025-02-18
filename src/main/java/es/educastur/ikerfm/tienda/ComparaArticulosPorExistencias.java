package es.educastur.ikerfm.tienda;

import java.util.Comparator;


public class ComparaArticulosPorExistencias implements Comparator <Articulo>{

    @Override
    public int compare(Articulo a1, Articulo a2) {
        return Integer.compare(a1.getExistencias(), a2.getExistencias());
    }
    
}
