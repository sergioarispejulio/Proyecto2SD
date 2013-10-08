

//
// TTTClientRemote.java: interface for callbacks from server to client
//

import java.rmi.*;
import java.util.ArrayList;

public interface TTTClientRemote extends Remote
{
   void enviar(Producto new_board)                          throws RemoteException;
   void actualizarFrutas(String pro)           throws RemoteException;
   String obtenernombrecompañia()           throws RemoteException;
}
