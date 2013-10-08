/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.*;
import java.util.ArrayList;
/**
 *
 * @author SergioArispe
 */
public interface TTTService extends Remote{
    
    public void registrarvendedoresfrutas(TTTClientRemote newClient)      throws RemoteException;
    public void registrarCompradoresFrutas(TTTClientRemote newClient)      throws RemoteException;
    public void registrarvendedoresProductos(TTTClientRemote newClient)      throws RemoteException;
    public void registrarCompradoresProductos(TTTClientRemote newClient)      throws RemoteException;
    public ArrayList<Producto> obtenerfrutas(String nomProd,float costo,float cantidad)                            throws RemoteException;
    public ArrayList<Producto> obtenerproducto(String nomProd,float costo,float cantidad)                            throws RemoteException;
    public void agregarfruta(String nomProd,String compania,float costo,Integer cantidad,float costoEnvio,Integer idCompania)                throws RemoteException;
    public void agregarproducto(String nomProd,String compania,float costo,Integer cantidad,float costoEnvio,Integer idCompania)                throws RemoteException;
    public void comprarfruta(String nomProd,String compania,float costo,Integer cantidad,float costoEnvio)                throws RemoteException;
    public void comprarproducto(String nomProd,String compania,float costo,Integer cantidad,float costoEnvio)                throws RemoteException;
    
}
