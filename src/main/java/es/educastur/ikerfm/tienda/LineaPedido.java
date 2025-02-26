package es.educastur.ikerfm.tienda;

import java.io.Serializable;


public class LineaPedido implements Comparable<LineaPedido>, Serializable{
    private String idArticulo;
    private int unidades;
    
    public LineaPedido(String idArticulo, int unidades){
        this.idArticulo = idArticulo;
        this.unidades = unidades;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    @Override
    public String toString() {
        return "idArticulo: (" + idArticulo + ")" + " Unidades: " + unidades ;
    }

    @Override
    public int compareTo(LineaPedido L) {
        return this.getIdArticulo().compareTo(L.getIdArticulo());
    }
    
    
}
