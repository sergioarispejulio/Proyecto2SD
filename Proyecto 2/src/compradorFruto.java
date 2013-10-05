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
/**
 *
 * @author garcia
 */
public class compradorFruto implements ActionListener, TTTClientRemote {
    protected TTTService remoteTTTBoard;
    
    public compradorFruto()
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
             remoteTTTBoard.registrarfrutas(this);
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
    public void enviar(Producto new_board) throws RemoteException  {}
    
   public void actualizarFrutas(String pro) throws RemoteException 
   {
       System.out.println("llega al comprador sisisisiisss"+pro);
       //for(int i=0 ; i<(pro.lista_frutas.size()); i++)
       {
         //  System.out.println("si "+pro.lista_frutas.get(i).Compania);
       }
   }
   public static void main(String[] args) {
       compradorFruto nue = new compradorFruto();
       
       
       
   }
   
}
