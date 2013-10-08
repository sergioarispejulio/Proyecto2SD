
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SergioArispe
 */
public class Procesado implements ActionListener, TTTClientRemote{

    protected TTTService remoteTTTBoard;
    private String nombre;
    
    public Procesado(String nombres)
    {
        nombre = nombres;
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enviar(Producto new_board) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarFrutas(String pro) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenernombrecompañia() throws RemoteException {
        return nombre;
    }
    
    public ArrayList<Producto> obtenerfrutas(String nomProd,float costo,float cantidad) throws RemoteException  
    {
        return remoteTTTBoard.obtenerfrutas(nomProd, costo, cantidad);
    }
    
    public void comprarfruta(String nomProd,String compania,float costo,Integer cantidad,float costoEnvio) throws RemoteException
    {
        try {
            remoteTTTBoard.comprarfruta(nomProd, compania, costo, cantidad, costoEnvio);
        } catch (RemoteException ex) {
            Logger.getLogger(CompradorFrutas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
