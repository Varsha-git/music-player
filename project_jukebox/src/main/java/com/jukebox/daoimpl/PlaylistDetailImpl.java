package com.jukebox.daoimpl;

import com.jukebox.dao.DaoPlaylistDetail;
import com.jukebox.helper.MysqlConnectivity;
import com.jukebox.modal.PlaylistDetail;
import com.jukebox.userdefinedexception.PlaylistDetailException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistDetailImpl implements DaoPlaylistDetail {
    Connection connection;
    public PlaylistDetailImpl()
    {
      connection= MysqlConnectivity.getConnection();
    }

    //to add song in the playlist
    @Override
    public void addSongToPlaylist(PlaylistDetail playlistDetail) {
        String query="insert into playlist_detail values(?,?,?,?)";
        try {
            {
                PreparedStatement statement= connection.prepareStatement(query);
                statement.setInt(1,playlistDetail.getId());
                statement.setInt(2,playlistDetail.getItem_id());
                statement.setString(3,playlistDetail.getType());
                statement.setInt(4,playlistDetail.getPlaylistId());
                int result=statement.executeUpdate();
                if(result>0)
                    System.out.println(" Song added ");
            }
        } catch (SQLException throwables) {
            throwables.getMessage();
        }
    }


    //this method is adding podcast into playilst
    @Override
    public void addPodcastToPlaylist(PlaylistDetail playlistDetail) {
        String query="insert into playlist_detail values(?,?,?,?)";
        try {
            {
                PreparedStatement statement= connection.prepareStatement(query);
                statement.setInt(1,playlistDetail.getId());
                statement.setInt(2,playlistDetail.getItem_id());
                statement.setString(3,playlistDetail.getType());
                statement.setInt(4,playlistDetail.getPlaylistId());
                int result=statement.executeUpdate();
                if(result>0)
                    System.out.println("Record Inserted !!! ");
                else
                {
                    throw  new PlaylistDetailException("Adding podcast unsuccessful");
                }
            }
        } catch (SQLException | PlaylistDetailException throwables) {
            throwables.getMessage();
        }
    }


    //displaying song which are in given playlist
    @Override
    public boolean displaySongsFromPlaylist(int id) {
        boolean answer=false;
        String query="select song_id,song_name from song s join playlist_detail pd on s.song_id=pd.item_id where all_playlist_id =? and pd.list_type="+"'"+"song"+"'";
        try
        {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next()) {
               do {
                    System.out.println(resultSet.getInt("song_id") + "\t" + resultSet.getString("song_name"));
                } while (resultSet.next());
                answer=true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return answer;
    }


    //showing all podcast which are present in given playlist name
    @Override
    public boolean displayPodcastFromPlaylist(int id) {
        boolean answer=false;
        String query="select podcast_id,podcast_title,celebrity_name from podcast p join playlist_detail pd on p.podcast_id=pd.item_id where all_playlist_id =? and pd.list_type=+"+"'"+"podcast"+"'";
        try
        {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next()) {
               do {
                    System.out.println(resultSet.getInt("podcast_id") + "\t" + resultSet.getString("podcast_title") + "\t" + resultSet.getString("celebrity_name"));
                } while (resultSet.next());
                answer=true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return answer;
    }

    // finding location of song which is present in playlist
    @Override
    public String findSongFromPlaylist(int songid,int playlistid) {
        String location="";
        String query="select location from song s join playlist_detail pd on s.song_id=pd.item_id where pd.item_id=? and all_playlist_id=?;";
        try
        {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,songid);
            statement.setInt(2,playlistid);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next())
            {
             location=resultSet.getString("location");
            }
        }
        catch(SQLException ex)
        {
            ex.getMessage();
        }
        catch(Exception ex)
        {
            ex.getMessage();
        }
        return location;
    }


    //this method is finding location which is in given playlist
    @Override
    public String findPodcastFromPlaylist(int songid, int playlistid) {
        String location="";
        String query="select location from podcast p join playlist_detail pd on p.podcast_id=pd.item_id where pd.item_id=? and all_playlist_id=?;";
        try
        {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,songid);
            statement.setInt(2,playlistid);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next())
            {
                location=resultSet.getString("location");
            }
        }
        catch(SQLException ex)
        {
            ex.getMessage();
        }
        catch(Exception ex)
        {
            ex.getMessage();
        }
        return location;
    }
}
