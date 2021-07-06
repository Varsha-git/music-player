package com.jukebox.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnectivity {


    private static Connection connection;
    //this method is creating connection with MySQL
    public static Connection getConnection()
    {
        String url="jdbc:mysql://localhost:3306/jukebox";
        String username="root";
        String pwd="varsha";
        try
        {
            connection= DriverManager.getConnection(url,username,pwd);
            return connection;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }


}
