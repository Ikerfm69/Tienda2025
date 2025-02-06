package es.educastur.ikerfm.tienda;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class TIENDA implements Serializable{
    Scanner sc = new Scanner(System.in);
    private HashMap <String, Articulo> articulos;
    private HashMap <String, Cliente> clientes;
    private ArrayList <Pedido> pedidos;
    
    public TIENDA(){
        this.pedidos = new ArrayList<>();
        this.articulos = new HashMap<>();
        this.clientes = new HashMap<>();
    }

    public static void main(String[] args) {
        TIENDA t = new TIENDA();
        t.cargaDatos();
        t.menu();
    }
    
    //<editor-fold defaultstate="collapsed" desc="MENUS">
    public void menu(){
        Scanner sc= new Scanner(System.in);
        int opcion=0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU DE LA TIENDA\n");
            System.out.println("1. PEDIDOS");
            System.out.println("2. ARTÍCULOS");
            System.out.println("3. CLIENTES");
            System.out.println("9. SALIR");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: {
                    menuPedidos();
                    break;
                }
                case 2: {
                    menuArticulos();
                    break;
                }
                case 3: {
                    menuClientes();
                    break;
                }
            }
        } while (opcion != 9);

    }
    
    private void menuPedidos() {
        Scanner sc= new Scanner(System.in);
        int opcion=0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU PEDIDOS\n");
            System.out.println("1. NUEVO PEDIDO");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("9. SALIR");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: {
                    nuevoPedido();
                    break;
                }
                case 2: {
                    
                }
                case 3: {
                    
                }
            }
        } while (opcion != 9);
    }

    private void menuArticulos() {
        Scanner sc= new Scanner(System.in);
        int opcion=0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU ARTICULOS\n");
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("9. SALIR");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: {
                    
                }
                case 2: {
                    
                }
                case 3: {
                    
                }
            }
        } while (opcion != 9);
    }

    private void menuClientes() {
        Scanner sc= new Scanner(System.in);
        int opcion=0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU CLIENTES\n");
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("9. SALIR");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: {
                    
                }
                case 2: {
                    
                }
                case 3: {
                    
                }
            }
        } while (opcion != 9);
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="PEDIDOS">
    public void stock(int unidadesPed, String id) throws StockAgotado, StockInsuficiente{
    int n=articulos.get(id).getExistencias();
        if (n==0){
            throw new StockAgotado ("Stock AGOTADO para el articulo: "+ articulos.get(id).getDescripcion());
        }else if (n < unidadesPed){
            throw new StockInsuficiente ("No hay Stock suficiente. Me pide  " + unidadesPed + " de "
                                        + articulos.get(id).getDescripcion()
                                        + " y sólo se dispone de: "+ n);
        }
    } 

    public String generaIdPedido(String idCliente){ 
    int contador = 0;     
    String nuevoId;
    for (Pedido p: pedidos){
        if (p.getClientePedido().getDni().equalsIgnoreCase(idCliente)){
            contador++;
        }
    }
    contador++;
    nuevoId= idCliente + "-" + String.format("%03d", contador) + "/" + LocalDate.now().getYear();
    return nuevoId;
    }

    public void nuevoPedido() {
    //ARRAYLIST AUXILIAR PARA CREAR EL PEDIDO
    ArrayList<LineaPedido> CestaCompraAux = new ArrayList();
    String dniT, idT, opc, pedidasS;
    int pedidas=0;

    sc.nextLine();

    do{
        System.out.println("CLIENTE PEDIDO (DNI):");
        dniT=sc.nextLine().toUpperCase();
        if (dniT.isBlank()) break;
        if (!MetodosAux.validarDNI(dniT)) System.out.println("El DNI no es un DNI válido");;
    }while (!clientes.containsKey(dniT));

    if (!dniT.isBlank()){
        System.out.println("\t\tCOMENZAMOS CON EL PEDIDO");
        System.out.println("INTRODUZCA LOS ARTÍCULOS DEL PEDIDO UNO A UNO: ");
        idT = sc.nextLine();
        
        while(!dniT.isBlank()){
            if (!articulos.containsKey(idT)){
                System.out.print("El ID artículo no existe");
            }else{
                System.out.println("(" + articulos.get(idT).getDescripcion()+ ") - UNIDADES? ");
                do {
                    pedidasS=sc.next();
                }while(!MetodosAux.esInt(pedidasS)); 

                pedidas=Integer.parseInt(pedidasS);

                try{
                    stock(pedidas,idT); // LLAMO AL METODO STOCK, PUEDEN SALTAR 2 EXCEPCIONES
                    CestaCompraAux.add(new LineaPedido(idT,pedidas));
                    articulos.get(idT).setExistencias(articulos.get(idT).getExistencias()-pedidas);
                }catch (StockAgotado e){
                    System.out.println(e.getMessage());
                }catch (StockInsuficiente e){
                    System.out.println(e.getMessage());
                    int disponibles=articulos.get(idT).getExistencias();
                    System.out.print("QUIERES LAS " + disponibles + " UNIDADES DISPONIBLES? (S/N) ");
                    opc=sc.next();
                    if (opc.equalsIgnoreCase("S")){
                        CestaCompraAux.add(new LineaPedido(idT,disponibles));
                        articulos.get(idT).setExistencias(articulos.get(idT).getExistencias()-disponibles);
                    }
                }
            }
            System.out.println("INTRODUCE CODGIO ARTICULO (RETURN PARA TERMINAR): ");
            idT = sc.nextLine();
        }
        
        
        //IMPRIMO EL PEDIDO Y SOLICITO ACEPTACION DEFINITIVA DEL MISMO 
        for (LineaPedido l:CestaCompraAux){
            System.out.println(articulos.get(l.getIdArticulo()).getDescripcion() + " - ("+ l.getUnidades() + ")");
        }
        System.out.println("ESTE ES TU PEDIDO. PROCEDEMOS? (S/N)   ");
        opc=sc.next();
        if (opc.equalsIgnoreCase("S")){
        // ESCRIBO EL PEDIDO DEFINITIVAMENTE Y DESCUENTO LAS EXISTENCIAS PEDIDAS DE CADA ARTICULO
            LocalDate hoy=LocalDate.now();
            pedidos.add(new Pedido(generaIdPedido(dniT),clientes.get(dniT),hoy,CestaCompraAux));
        }else{
            for (LineaPedido l:CestaCompraAux)
            {
                articulos.get(l.getIdArticulo()).setExistencias(articulos.get(l.getIdArticulo()).getExistencias()+l.getUnidades());
            }   
        }
    }
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="ARTÍCULOS">
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="CLIENTES">
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="OTROS">
    public void cargaDatos(){
        
       clientes.put("80580845T",new Cliente("80580845T","ANA ","658111111","ana@gmail.com"));
       clientes.put("36347775R",new Cliente("36347775R","LOLA","649222222","lola@gmail.com"));
       clientes.put("63921307Y",new Cliente("63921307Y","JUAN","652333333","juan@gmail.com"));
       clientes.put("02337565Y",new Cliente("02337565Y","EDU","634567890","edu@gmail.com"));
              
       articulos.put("1-11",new Articulo("1-11","RATON LOGITECH ST ",14,15));
       articulos.put("1-22",new Articulo("1-22","TECLADO STANDARD  ",9,18));
       articulos.put("2-11",new Articulo("2-11","HDD SEAGATE 1 TB  ",16,80));
       articulos.put("2-22",new Articulo("2-22","SSD KINGSTOM 256GB",9,70));
       articulos.put("2-33",new Articulo("2-33","SSD KINGSTOM 512GB",0,200));
       articulos.put("3-22",new Articulo("3-22","EPSON PRINT XP300 ",5,80));
       articulos.put("4-11",new Articulo("4-11","ASUS  MONITOR  22 ",5,100));
       articulos.put("4-22",new Articulo("4-22","HP MONITOR LED 28 ",5,180));
       articulos.put("4-33",new Articulo("4-33","SAMSUNG ODISSEY G5",12,580));
       
       LocalDate hoy = LocalDate.now();
       pedidos.add(new Pedido("80580845T-001/2024",clientes.get("80580845T"),hoy.minusDays(1), new ArrayList<>
        (List.of(new LineaPedido("1-11",3),new LineaPedido("4-22",3)))));                                                                                                                                                               
       pedidos.add(new Pedido("80580845T-002/2024",clientes.get("80580845T"),hoy.minusDays(2), new ArrayList<>
        (List.of(new LineaPedido("4-11",3),new LineaPedido("4-22",2),new LineaPedido("4-33",4)))));
       pedidos.add(new Pedido("36347775R-001/2024",clientes.get("36347775R"),hoy.minusDays(3), new ArrayList<>
        (List.of(new LineaPedido("4-22",1),new LineaPedido("2-22",3)))));
       pedidos.add(new Pedido("36347775R-002/2024",clientes.get("36347775R"),hoy.minusDays(5), new ArrayList<>
        (List.of(new LineaPedido("4-33",3),new LineaPedido("2-11",3)))));
       pedidos.add(new Pedido("63921307Y-001/2024",clientes.get("63921307Y"),hoy.minusDays(4), new ArrayList<>
        (List.of(new LineaPedido("2-11",5),new LineaPedido("2-33",3),new LineaPedido("4-33",2)))));
    }
//</editor-fold>
}
