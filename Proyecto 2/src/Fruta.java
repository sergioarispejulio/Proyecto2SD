/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class Fruta implements ActionListener, TTTClientRemote{
    
    protected TTTService remoteTTTBoard;
    static Producto nue;
    public static String n;
    
    public Fruta()
    {
      try
      {
         System.out.println("Connecting...");
         String serverObjectName = "rmi://127.0.0.1:"
            + Servidor.ServicePort + "/"
            + Servidor.ServiceName;
         remoteTTTBoard = (TTTService)Naming.lookup(serverObjectName);
         //
         // the next two lines allow the server to call updateBoard:
         //
         UnicastRemoteObject.exportObject(this);
         remoteTTTBoard.registrarCompradoresFrutas(this);
         System.out.println("Conectado...");
      }
      catch ( Exception e )
      {
         System.out.println("Exception: " + e.getMessage());
         e.printStackTrace();
         return;
      }
    }
    
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void enviar(Producto new_board) throws RemoteException {
        
    }
    public void ofertarProducto( String nomProd,String compania,float costo,Integer cantidad,float costoEnvio,Integer idCompania) 
    {
        /*try
        {
            remoteTTTBoard.serOfertarProducto(nomProd,compania,costo,cantidad,costoEnvio,idCompania);
        }
        catch( RemoteException e )
        {
           System.out.println("Exception: " + e.getMessage());
           e.printStackTrace();
           System.exit(1);
         }*/
    }
    public void enviarfruta()
    {
       /* try
       {
           n = "1";
           remoteTTTBoard.obtenerfruta("hola");
       }
       catch( RemoteException e )
      {
         System.out.println("Exception: " + e.getMessage());
         e.printStackTrace();
         System.exit(1);
       }*/
    }
    public void actualizarFrutas(String po) throws RemoteException
    {
    
    }
    public static void main(String[] args) {
       //Fruta nue = new Fruta();
       //nue.enviarfruta();
       
       
       
       IUOfertarProducto prud = new IUOfertarProducto();
       prud.setVisible(true);
   }

    @Override
    public String obtenernombrecompañia() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
