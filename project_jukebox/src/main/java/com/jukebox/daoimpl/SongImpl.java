package com.jukebox.daoimpl;

import com.jukebox.dao.DaoSong;
import com.jukebox.helper.MysqlConnectivity;
import com.jukebox.modal.Song;
import com.jukebox.userdefinedexception.SongException;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class SongImpl implements DaoSong {

    Connection connection;
    Song song;
    Set<Song> hashSetList=new HashSet<>();
    Set<Song> sortGenre=new HashSet<>();



    public SongImpl() {
        connection = MysqlConnectivity.getConnection();
    }

    //this method is fetching all data of song table from mysql database
    @Override
    public Set<Song> getAllSongs()
    {
        String query="select * from song order by song_id";
        try
        {
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                song= new Song(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6),resultSet.getString(7));
                hashSetList.add(song);
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return hashSetList;
    }


    //this method will display all song sorted by genre attribute
    @Override
    public boolean sortByGenre()
    {
        String query="select * from song order by genre";
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);

           /* sortGenre= (Set<Song>) resultSet;
            sortGenre.stream().forEach(element-> System.out.format("%20s %20s %20s\n",element.getName(),element.getArtist(), element.getGenre()));
             */

             while(resultSet.next())
            {
               String songName=resultSet.getString("song_name");
               String artist=resultSet.getString("artist");
               String genre=resultSet.getString("genre");
               System.out.format("%20s %20s %20s\n",songName,artist,genre);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    //this method will display all song of given artist name
    @Override
    public int searchByArtist(String artist)
    {
        int count=0;
        String query="select * from song where artist=?";
        try
        {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,artist);
            ResultSet resultSet= statement.executeQuery();


            while(resultSet.next())
            {
                count++;
                String songName=resultSet.getString("song_name");
                String artist1=resultSet.getString("artist");
                System.out.format("%20s %20s \n",songName,artist1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }


    //this method is finding location of song
    @Override
    public String findSong(int songId)
    {
        String location="";
        String query="select location from song where song_id=?";
        try
        {
            PreparedStatement statement= connection.prepareStatement(query);
            statement.setInt(1,songId);
            ResultSet rs=statement.executeQuery();
            if(rs.next()) {
                location = rs.getString("location");
            }
        }
        catch (SQLException ex)
        {
            ex.getMessage();
        }
        return location;
    }


    @Override
    public void displayAllSongs(Set<Song> songSet) {


        songSet.stream().forEach(list->System.out.format("%-10s %-30s %-20s %-20s %-20s \n",list.getId(),list.getName(),list.getDuration(),list.getArtist(),list.getGenre()));
       /* for(Song list: songSet)
        {
            System.out.format("%-10s %-30s %-20s %-20s %-20s \n",list.getId(),list.getName(),list.getDuration(),list.getArtist(),list.getGenre());
        }*/
    }

}
