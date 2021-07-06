package com.jukebox.daoimpl;

import com.jukebox.dao.DaoAlbum;
import com.jukebox.helper.MysqlConnectivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlbumImpl implements DaoAlbum {
    Connection connection;

    public AlbumImpl()
    {
        connection= MysqlConnectivity.getConnection();
    }


    //counting total records in album table
    @Override
    public int countAllAlbum()
    {
        String query="select *  from album";
        int count=0;
        try
        {
         Statement statement=  connection.createStatement();
         ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                count++;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return count;
    }
}
