/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
    import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DB.Inventario;
import DB.Ventas;
import DB.ResultSetSender;

/**
 *
 * @author ville
 */
public class Servidor extends Thread{
    
        public static void main(String[] args) throws IOException {
            new Servidor().run();
        }
        
        public void run() {
            try {
                new Servidor().server();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void server() throws ClassNotFoundException, SQLException{
        try {
            ServerSocket servSock = new ServerSocket(1234);
            
            while(true){
                Socket clntSock = servSock.accept();
                
                ObjectInputStream entrada = new ObjectInputStream(clntSock.getInputStream());
                ObjectOutputStream salida = new ObjectOutputStream(clntSock.getOutputStream());
                
                List<String> inp=(List<String>) entrada.readObject();
                
                switch(inp.get(0)){
                    case "1":
                        ResultSet rs =DB.Inventario.selectAll();
                        
                        ResultSetSender sender=new ResultSetSender(rs);
                        
                        salida.writeObject(sender.getData());
                        break;
                    case "2":
                        ResultSet rs2=DB.Inventario.selectOne(inp.get(1));
                        ResultSetSender sender2=new ResultSetSender(rs2);
                        salida.writeObject(sender2.getData());
                        
                        break;
                    case "3":
                        Integer rs3=DB.Inventario.selectCantidad(inp.get(1));
                        salida.writeObject(rs3);
                        break;
                    case "4":
                        Integer rs4=DB.Inventario.selectPreciov(inp.get(1));
                        salida.writeObject(rs4);
                        break;
                    case "5":
                        boolean b=DB.Inventario.updateCantidadLess(inp.get(1),Integer.parseInt(inp.get(2)+"") );
                       
                        break;
                    case "6":
                        boolean b2=DB.Ventas.Insertar(inp.get(1),inp.get(2));
                        
                        if(b2){
                            salida.writeObject(1);
                        }else{
                            salida.writeObject(0);
                        }
                        break;
                    case "7":
                        ResultSet rs5=DB.Inventario.selectLike(inp.get(1));
                        ResultSetSender sender3=new ResultSetSender(rs5);
                        salida.writeObject(sender3.getData());
                        break;
                    case "8":
                        boolean b3=DB.Inventario.Insertar(inp.get(1),inp.get(2),inp.get(3),inp.get(4),inp.get(5));
                        
                        if(b3){
                            salida.writeObject(1);
                        }else{
                            salida.writeObject(0);
                        }
                        break;
                        
                    case "9":
                        boolean b4=DB.Inventario.Actualizar(inp.get(1),inp.get(2),inp.get(3),inp.get(4),inp.get(5),inp.get(6));
                        
                        if(b4){
                            salida.writeObject(1);
                        }else{
                            salida.writeObject(0);
                        }
                        break;
                    case "10":
                         boolean b5=DB.Inventario.Borrar(inp.get(1));
                        
                        if(b5){
                            salida.writeObject(1);
                        }else{
                            salida.writeObject(0);
                        }
                        break;
                        
                }
                
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
