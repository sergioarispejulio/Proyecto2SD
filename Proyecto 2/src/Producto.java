/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author SergioArispe
 */
public class Producto {
    public String Nombre_Producto;
    public String Compania;
    public float Costo;
    public Integer Cantidad;
    public float Costo_Envio;
    public Integer id_compania;
    public boolean vendido;

    
    public boolean comparar(Producto objecto)
    {
        if(objecto.Nombre_Producto == this.Nombre_Producto)
        {
            return false;
        }
        if(objecto.Compania == this.Compania)
        {
            return false;
        }
        if(objecto.Costo == this.Costo)
        {
            return false;
        }
        return true;
    }
}
