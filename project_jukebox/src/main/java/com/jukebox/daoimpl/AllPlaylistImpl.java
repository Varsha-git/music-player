package com.jukebox.daoimpl;

import com.jukebox.dao.DaoAllPlaylist;
import com.jukebox.helper.MysqlConnectivity;
import com.jukebox.modal.AllPlaylist;
import com.jukebox.userdefinedexception.AllPlaylistCustomException;

import java.sql.*;
import java.util.Random;

public class AllPlaylistImpl implements DaoAllPlaylist {
    Connection connection;

    public AllPlaylistImpl()
    {
       connection= MysqlConnectivity.getConnection();
    }

    // this method is creating new playlist and inserting to all playlist table
    @Override
    public boolean createPlaylist(AllPlaylist allPlaylist) {

        boolean result=false;
        String query="insert into all_playlist values(?,?,?)";
        try
        {
            PreparedStatement statement= connection.prepareStatement(query);
            statement.setInt(1,allPlaylist.getId());
            statement.setString(2,allPlaylist.getName());
            statement.setDate(3, allPlaylist.getCreatedDate());
            int resultSet= statement.executeUpdate();
            if(resultSet>0)
                result=true;
            else
                result= false;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }

    //displaying all data from all playlist
    @Override
    public void displayAllPlaylist() {
        String query="select * from all_playlist";
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            while(resultSet.next())
            {
                String playlistName=resultSet.getString("playlist_name");
                System.out.println(playlistName);
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public int generateNumber() {
        Random  random=new Random();
        int min=102,max=199;
        int number=random.nextInt(max-min)+min;
        return  number;
    }

    //fetching playlist id from all playlist table
    @Override
    public int getPlaylistId(String name) {
        int id=0;
        String query="Select id from all_playlist where playlist_name=?";

        try
        {
           PreparedStatement statement=connection.prepareStatement(query);
           statement.setString(1,name);
           ResultSet resultSet= statement.executeQuery();
            if(resultSet.next())
            {
               id=resultSet.getInt("id");
            }
            else
            {
                throw  new AllPlaylistCustomException("This playlist not found!!!");
            }
        }
        catch(SQLException | AllPlaylistCustomException ex)
        {

            ex.getMessage();
        }
        return id;
    }
}
