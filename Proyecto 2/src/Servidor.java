/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author SergioArispe
 */
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class Servidor extends UnicastRemoteObject implements TTTService{
   public final static String ServiceName = "homer-ttt";
   public final static int ServicePort = 8186;
   
   
   List vendedoresfrutas = Collections.synchronizedList(new LinkedList());
   List vendedoresproducto = Collections.synchronizedList(new LinkedList());
   List compradoresFrutas = Collections.synchronizedList(new LinkedList());
   List compradoresProducto = Collections.synchronizedList(new LinkedList());
   
   protected productosGeneral frutasProductos = new productosGeneral();
   
   public Servidor() throws RemoteException
   {
      super();
   }
   
   public void registrarfrutas(TTTClientRemote newClient) throws RemoteException
   {
      vendedoresfrutas.add(newClient);
      System.out.println("vendedor frutas registrado");
   }
   public void registrarCompradorFrutas(TTTClientRemote newClient) throws RemoteException
   {
       compradoresFrutas.add(newClient);
       System.out.println("comprador frutas registrado");
   }
   
   public void registrarproductos(TTTClientRemote newClient) throws RemoteException
   {
      vendedoresproducto.add(newClient);
   }
   
   public void obtenerfruta(String n) throws RemoteException
   {
       Producto nue = new Producto();
       System.out.println(n);
       //nue.Cantidad = n;
       frutasProductos.lista_frutas.add(nue);
       System.out.println(nue.Nombre_Producto + " " + nue.Compania + " " + nue.Costo + " " + nue.Costo_Envio + " " + nue.Cantidad + " ANTES");
       mostrarfrutas();
   }
   public void serOfertarProducto( String nomProd,String compania,float costo,Integer cantidad,float costoEnvio,Integer idCompania) throws RemoteException
   {    Producto nuevo = new Producto();
        nuevo.Nombre_Producto = nomProd;
        nuevo.Compania = compania;
        nuevo.Costo = costo;
        nuevo.Cantidad = cantidad;
        nuevo.Costo_Envio = costoEnvio;
        nuevo.id_compania = idCompania;
        frutasProductos.lista_frutas.add(nuevo);
       System.out.println("si "+nomProd);
       actualizarClientesQueBuscanFrutas();
   }
   public void mostrarfrutas() 
   {
       for (Producto aux : frutasProductos.lista_frutas) {
           System.out.println(aux.Nombre_Producto + " " + aux.Compania + " " + aux.Costo + " " + aux.Costo_Envio + " " + aux.Cantidad);
       }
   }
   
   public void actualizarClientesQueBuscanFrutas()
   {
      Iterator it = compradoresFrutas.iterator();
      System.out.println("tam: "+compradoresFrutas.size());
      while ( it.hasNext() )
      {
         TTTClientRemote client = (TTTClientRemote)it.next();
         try
         {
            //client.actualizarFrutas(frutasProductos);
             client.actualizarFrutas("del servidor");
         }
         catch ( Exception e )
         {
            // note system does not halt in such situations!
            System.out.println("Could not update client " + client.toString());
         }
      }
          
   }
   
   public static void main(String args[])
   {
      //lista_frutas = new ArrayList<Producto>();
      System.out.println("Initializing TTTService...");
      try
      {
         TTTService cserv = new Servidor();
         String serverObjectName = "rmi://localhost:" + ServicePort
            + "/" + ServiceName;
         Naming.rebind(serverObjectName, cserv);
         System.out.println("Servidor corriendo...");
      }
      catch (Exception e)
      {
         System.out.println("Exception: " + e.getMessage());
         e.printStackTrace();
      }
   }
   
}
