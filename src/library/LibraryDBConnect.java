/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author phuong pham
 */
public class LibraryDBConnect {
    Connection conn;
    
    public Connection getConnectMySQL() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
         String url = "jdbc:sqlserver://localhost:1433;"
                 + "databaseName=coffee;username=naunem;password=11";
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         conn = DriverManager.getConnection(url);

         return conn;
    }
    
    public void closeDb() throws SQLException{
        conn.close();
    }
    
}
