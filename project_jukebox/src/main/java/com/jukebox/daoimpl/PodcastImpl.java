package com.jukebox.daoimpl;

import com.jukebox.dao.DaoPodcast;
import com.jukebox.helper.MysqlConnectivity;
import com.jukebox.modal.Podcast;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PodcastImpl implements DaoPodcast {
    Connection connection;
    Podcast podcast;
    Set<Podcast> podcastSet=new HashSet<>();
    public PodcastImpl()
    {
        connection= MysqlConnectivity.getConnection();
    }


    //finding location of podcast in database
    @Override
    public String findPodcast(int id) {
        String location="";
        String query="select location from podcast where podcast_id=?";
        try
        {
            PreparedStatement statement= connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()) {
                location = resultSet.getString("location");
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return location;
    }


    //fetching all data in podcast table from database
    @Override
    public Set<Podcast> getAllPodcast() {

        String query="select * from podcast";
        try
        {
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);

            while(resultSet.next())
            {
                podcast=new Podcast(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getString(7));
                podcastSet.add(podcast);
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
        return podcastSet;
    }

    @Override
    public void displayAllPodcast(Set<Podcast> podcastSet) {

        podcastSet.stream().forEach(podcast1->System.out.format("%-10s %-40s %-20s %-20s \n",podcast1.getId(),podcast1.getTitle(),podcast1.getDuration(),podcast1.getCelebrityName()));
        /*for(Podcast podcast1: podcastSet)
        {
            System.out.format("%-10s %-40s %-20s %-20s \n",podcast1.getId(),podcast1.getTitle(),podcast1.getDuration(),podcast1.getCelebrityName());
        }*/
    }

}
