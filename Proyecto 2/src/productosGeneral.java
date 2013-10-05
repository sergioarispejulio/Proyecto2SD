
import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author garcia
 */
public class productosGeneral implements Serializable
{
    public ArrayList<Producto> lista_frutas;
    public ArrayList<Producto> lista_producto;
    public productosGeneral()
    {
        lista_frutas = new ArrayList<>();
        lista_producto = new ArrayList<>();
    }
}
