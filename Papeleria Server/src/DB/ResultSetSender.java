/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.util.List;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ville
 */
public class ResultSetSender {
    private static final long serialVersionUID = 1L;
    
    public List<Integer> ints;
    private List<String> columnNames;
    private List<List<Object>> data;
    
    public ResultSetSender(ResultSet resultSet) throws SQLException {
        columnNames = new ArrayList<>();
        int columnCount = resultSet.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(resultSet.getMetaData().getColumnName(i));
        }
        data = new ArrayList<>();
        while (resultSet.next()) {
            List<Object> row = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(resultSet.getObject(i));
            }
            data.add(row);
        }
    }
    
    public void send(Socket socket) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
    }
    
    public List<String> getColumnNames() {
        return columnNames;
    }
    
    public List<List<Object>> getData() {
        return data;
    }
}
