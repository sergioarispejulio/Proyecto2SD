/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.*;
/**
 *
 * @author SergioArispe
 */
public interface TTTService extends Remote{
    
    public void registrarfrutas(TTTClientRemote newClient)      throws RemoteException;
    public void registrarproductos(TTTClientRemote newClient)   throws RemoteException;
    public void obtenerfruta(String n)                            throws RemoteException;
    
    public void serOfertarProducto( String nomProd,String compania,float costo,Integer cantidad,float costoEnvio,Integer idCompania)                throws RemoteException;
    
}
