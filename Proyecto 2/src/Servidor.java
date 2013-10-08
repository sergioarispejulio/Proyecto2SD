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
   
   public void registrarvendedoresfrutas(TTTClientRemote newClient) throws RemoteException
   {
      vendedoresfrutas.add(newClient);
      System.out.println("vendedor frutas registrado");
   }
   public void registrarCompradoresFrutas(TTTClientRemote newClient) throws RemoteException
   {
       compradoresFrutas.add(newClient);
       System.out.println("comprador frutas registrado");
   }
   public void registrarvendedoresProductos(TTTClientRemote newClient)      throws RemoteException
   {
      vendedoresfrutas.add(newClient);
      System.out.println("vendedor producto registrado"); 
   }
   public void registrarCompradoresProductos(TTTClientRemote newClient)      throws RemoteException
   {
      vendedoresfrutas.add(newClient);
      System.out.println("comprador producto registrado");
   }
   
   public ArrayList<Producto> obtenerfrutas(String nomProd,float costo,float cantidad) throws RemoteException
   {
       ArrayList<Producto> resultado = new ArrayList<Producto>();
       for (Producto objecto : frutasProductos.lista_frutas) {
           if(objecto.Nombre_Producto == nomProd && objecto.Costo <= costo && objecto.Cantidad >= cantidad && objecto.vendido == false)
           {
               resultado.add(objecto);
           }
       }
       return resultado;
   }
   
   public ArrayList<Producto> obtenerproducto(String nomProd,float costo,float cantidad) throws RemoteException
   {
       ArrayList<Producto> resultado = new ArrayList<Producto>();
       for (Producto objecto : frutasProductos.lista_frutas) {
           if(objecto.Nombre_Producto == nomProd && objecto.Costo <= costo && objecto.Cantidad >= cantidad && objecto.vendido == false)
           {
               resultado.add(objecto);
           }
       }
       System.out.println("Se dio objetos");
       return resultado;
   }
   
   public void agregarfruta( String nomProd,String compania,float costo,Integer cantidad,float costoEnvio,Integer idCompania) throws RemoteException
   {    
        Producto nuevo = new Producto();
        nuevo.Nombre_Producto = nomProd;
        nuevo.Compania = compania;
        nuevo.Costo = costo;
        nuevo.Cantidad = cantidad;
        nuevo.Costo_Envio = costoEnvio;
        nuevo.id_compania = idCompania;
        nuevo.vendido = false;
        frutasProductos.lista_frutas.add(nuevo);
        System.out.println("si "+nomProd);
        actualizarClientesQueBuscanFrutas();
   }
   
   public void agregarproducto(String nomProd,String compania,float costo,Integer cantidad,float costoEnvio,Integer idCompania) throws RemoteException
   {
       Producto nuevo = new Producto();
        nuevo.Nombre_Producto = nomProd;
        nuevo.Compania = compania;
        nuevo.Costo = costo;
        nuevo.Cantidad = cantidad;
        nuevo.Costo_Envio = costoEnvio;
        nuevo.id_compania = idCompania;
        nuevo.vendido = false;
        frutasProductos.lista_producto.add(nuevo);
   }
   
   public void comprarfruta(String nomProd,String compania,float costo,Integer cantidad,float costoEnvio) throws RemoteException
   {
        Producto nuevo = new Producto();
        Producto selec = new Producto();
        nuevo.Nombre_Producto = nomProd;
        nuevo.Compania = compania;
        nuevo.Costo = costo;
        nuevo.Cantidad = cantidad;
        nuevo.Costo_Envio = costoEnvio;
        for (Producto objecto : frutasProductos.lista_frutas) {
            if(objecto.comparar(nuevo))
            {
                selec = objecto;
                objecto.vendido = true;
                break;
            }
        }
        Iterator it = vendedoresfrutas.iterator();
        while ( it.hasNext() )
        {
           TTTClientRemote client = (TTTClientRemote)it.next();
           try
           {
              if(client.obtenernombrecompañia() == selec.Compania)
              {
                  ;//Aquí se actualiza al que oferto la fruta de que su producto se vendio
              }
           }
           catch ( Exception e )
           {
              // note system does not halt in such situations!
              System.out.println("Could not update client " + client.toString());
           }
        }
   }
   
   public void comprarproducto(String nomProd,String compania,float costo,Integer cantidad,float costoEnvio)                throws RemoteException
   {
        Producto nuevo = new Producto();
        Producto selec = new Producto();
        nuevo.Nombre_Producto = nomProd;
        nuevo.Compania = compania;
        nuevo.Costo = costo;
        nuevo.Cantidad = cantidad;
        nuevo.Costo_Envio = costoEnvio;
        for (Producto objecto : frutasProductos.lista_producto) {
            if(objecto.comparar(nuevo))
            {
                selec = objecto;
                objecto.vendido = true;
                break;
            }
        }
        Iterator it = vendedoresproducto.iterator();
        while ( it.hasNext() )
        {
           TTTClientRemote client = (TTTClientRemote)it.next();
           try
           {
              if(client.obtenernombrecompañia() == selec.Compania)
              {
                  ;//Aquí se actualiza al que oferto la fruta de que su producto se vendio
              }
           }
           catch ( Exception e )
           {
              // note system does not halt in such situations!
              System.out.println("Could not update client " + client.toString());
           }
        }
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
