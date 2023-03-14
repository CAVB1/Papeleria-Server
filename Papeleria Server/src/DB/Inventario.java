/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ville
 */
public class Inventario {
    
    private static final Connection cn=Parametros.conexion();
    
    public static ResultSet selectAll(){
        ResultSet rs=Parametros.consultar("SELECT * FROM Inventario;");
        if(rs!=null){
            return rs;
        }else{
            return null;
            
        }
    }
    
     public static ResultSet selectOne(String id){
        ResultSet rs=Parametros.consultar("SELECT * FROM Inventario WHERE ID="+id);
        if(rs!=null){
            return rs;
        }else{
            return null;
            
        }
    }
    
    public static ResultSet selectLike(String param){
        ResultSet rs=Parametros.consultar("SELECT * FROM Inventario WHERE ID LIKE '%"+param+"%' OR Nombre LIKE '%"+param+"%'");
        if(rs!=null)
        {
            return rs;
        }else{
            return null;
        }
    }
    
    public static boolean Insertar(String Nombre, String Desc, String Cantidad, String Precioc, String Preciov){
        return Parametros.consultarN("INSERT INTO Inventario(`ID`, `Nombre`, `Des`, `Cantidad`, `PrecioC`, `PrecioV`) VALUES (NULL, '"+Nombre+"', '"+Desc+"', '"+Cantidad+"', '"+Precioc+"', '"+Preciov+"');");
    }
    
    public static boolean Borrar(String Id){
                return Parametros.consultarN( "DELETE FROM Inventario WHERE ID="+Id);
 
    }
    
    public static boolean Actualizar(String Id,String Nombre, String Desc, String Cantidad, String Precioc, String Preciov){
        return Parametros.consultarN("UPDATE Inventario SET Nombre='"+Nombre+"', Des='"+Desc+"', Cantidad='"+Cantidad+"',PrecioC='"+Precioc+"',PrecioV='"+Preciov+"' WHERE ID="+Id);
    }
    
    public static int selectCantidad(String Id){
        ResultSet rs=Parametros.consultar("SELECT Cantidad FROM Inventario WHERE ID="+Id);
        if(rs!=null)
        {
            try {
                while(rs.next()){
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            }
            return -1;
            
        }
        
        else{
            return -1;
        }
        
        
    }
    
    public static int selectPreciov(String Id){
        ResultSet rs=Parametros.consultar("SELECT PrecioV FROM Inventario WHERE ID="+Id);
        if(rs!=null)
        {
            try {
                while(rs.next()){
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            }
            return -1;
            
        }
        
        else{
            return -1;
        }
        
        
    }
    
    public static boolean updateCantidadAdd(String id){
        int cantidad=selectCantidad(id)+1;
        return Parametros.consultarN("UPDATE Inventario SET Cantidad='"+cantidad+"' WHERE Id="+id);
    }
    
    public static boolean updateCantidadLess(String id, int cant){
        int cantidad=selectCantidad(id)-cant;
        return Parametros.consultarN("UPDATE Inventario SET Cantidad='"+cantidad+"' WHERE Id="+id);
    }
    
    
    
    
    
}
