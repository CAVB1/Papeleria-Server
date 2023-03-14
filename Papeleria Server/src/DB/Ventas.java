/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

/**
 *
 * @author ville
 */
public class Ventas {
    public static boolean Insertar(String productos, String Total){
        return Parametros.consultarN("INSERT INTO ventas(Productos, Total) VALUES('"+productos+"','"+Total+"')");
    }
}
