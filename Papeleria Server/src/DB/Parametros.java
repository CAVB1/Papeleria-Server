/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lambda
 */
public class Parametros {
    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String bd_ubicacion = "jdbc:mysql://localhost/punto de venta";
    public static final String bd_usuario = "root";
    public static final String bd_password = "";
    
    private static Connection conect;

    
    public static Connection conexion(){
        try {
             Class.forName(Parametros.driver);
            Connection con=DriverManager.getConnection(Parametros.bd_ubicacion,
                    Parametros.bd_usuario, Parametros.bd_password);
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Parametros.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Parametros.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public static ResultSet consultar(String consulta) {
        ResultSet resultado = null;
        int conteo = 0;
        try {
            Statement st;

            conect = conexion();
            st = conect.createStatement();
            String tsql = consulta;
            resultado = st.executeQuery(tsql);
            return resultado;
        } catch (Exception e) {
        }
        return null;
    }
     
     public static boolean consultarN(String consulta) {
        ResultSet resultado = null;
        int conteo = 0;
        try {
            Statement st;

            conect = conexion();
            st = conect.createStatement();
            String tsql = consulta;
            st.execute(tsql);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }
    
    
}
