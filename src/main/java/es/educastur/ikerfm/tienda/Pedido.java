package es.educastur.ikerfm.tienda;

import java.time.LocalDate;
import java.util.ArrayList;


public class Pedido {
    private String idPedido;
    private Cliente clientePedido;
    private LocalDate fechaPedido;
    private ArrayList <LineaPedido> cestaCompra;
    
    public Pedido (String idPedido, Cliente clientePedido, LocalDate fechaPedido, ArrayList cestaCompra){
        this.idPedido = idPedido;
        this.clientePedido = clientePedido;
        this.fechaPedido = fechaPedido;
        this.cestaCompra = cestaCompra;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getClientePedido() {
        return clientePedido;
    }

    public void setClientePedido(Cliente clientePedido) {
        this.clientePedido = clientePedido;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public ArrayList<LineaPedido> getCestaCompra() {
        return cestaCompra;
    }

    public void setCestaCompra(ArrayList<LineaPedido> cestaCompra) {
        this.cestaCompra = cestaCompra;
    }

    @Override
    public String toString() {
        return idPedido + "-" + clientePedido + "-" + fechaPedido + "-" + cestaCompra + '-';
    }
    
    
}
