package org.example;

import com.jukebox.dao.*;
import com.jukebox.daoimpl.*;
import com.jukebox.modal.AllPlaylist;
import com.jukebox.modal.PlaylistDetail;
import com.jukebox.modal.Podcast;
import com.jukebox.modal.Song;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class App
{

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
        Set<Podcast> podcastSet;
        Set<Song> songSet;
        DaoSong song = new SongImpl();
        songSet = song.getAllSongs();

        DaoPodcast podcast = new PodcastImpl();
        podcastSet = podcast.getAllPodcast();

        DaoPlaySong song1;
        DaoPlayPodcast song2;

        AllPlaylistImpl allPlaylist = new AllPlaylistImpl();
        AllPlaylist playlist;
        PlaylistDetail playlistDetail;
        DaoPlaylistDetail detail=new PlaylistDetailImpl();

        Date util_date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(util_date.getTime());
        boolean value = false;
        boolean value1=true;
        boolean value3=true;
        boolean value4=true;

        System.out.println("HELLO !!! THIS IS JUKEBOX");

        System.out.println("---------------------Available Song------------------");
        System.out.format("%-10s %-30s %-20s %-20s %-20s\n", "Song_No", "Song", "Duration", "Artist", "Genre");
        song.displayAllSongs(songSet);
        System.out.println();
        System.out.println("---------------------Available Podcast------------------");
        System.out.format("%-10s %-40s %-20s %-20s\n", "Podcast_No", "Title", "Duration", "Celebrity");
        podcast.displayAllPodcast(podcastSet);

        System.out.println();

        do {
            System.out.println();
            System.out.println("---------------------Hey Buddy ! What do you wanna do now -----------------");
            System.out.println("1.SORT BY Genre \t 2.SEARCH By Artist \t 3.PLAY SONG \t  4.PLAY A PODCAST  5.CREATE PLAYLIST \t 6. GO TO EXISTING PLAYLIST \t 7.EXIT");
            int choice =Integer.parseInt(reader.readLine());

            switch (choice)
            {
                case 1:
                    System.out.println("----------------Sorted List By Genre------------------------------");
                    System.out.format("%20s %20s %20s \n ", "Song Name", "Artist","Genre");
                    song.sortByGenre();
                    break;
                case 2:
                    System.out.println("----------------Enter Artist name---------------------------------");
                    String artist = reader.readLine();
                    System.out.format("%20s %20s \n ", "Song Name", "Artist");
                    song.searchByArtist(artist);
                    break;
                case 3:
                    song.displayAllSongs(songSet);
                    while (value1) {
                        System.out.println("Enter song number you wanna listen ::");
                        int id = Integer.parseInt(reader.readLine());
                        String location = song.findSong(id);
                        if (!location.isEmpty()) {
                            song1 = new PlaySongImpl(location);
                            song1.playSong();
                            System.out.println("Type Yes to keep listening another song else type No");
                            String answer = reader.readLine();
                            if (answer.equalsIgnoreCase("no"))
                                value1 = false;
                        } else {
                            System.out.println("This song is not available");
                        }
                    }
                    break;
                case 4:
                    podcast.displayAllPodcast(podcastSet);
                    while (value4) {
                        System.out.println("Enter podcast number you wanna listen ::");
                        int podcast_id = Integer.parseInt(reader.readLine());
                        String location1 = podcast.findPodcast(podcast_id);
                        if (!location1.isEmpty()) {

                            song2 = new PlayPodcastImpl(location1);
                            song2.playPodcast();
                            System.out.println("Type Yes to keep listening another podcast else type No");
                            String answer = reader.readLine();
                            if (answer.equalsIgnoreCase("no"))
                                value4 = false;
                        } else
                            System.out.println("This podcast is not available");
                    }
                    break;
                case 5:
                    int num = allPlaylist.generateNumber();
                    System.out.println("Enter name of your new playlist");
                    String playlist_name = reader.readLine();
                    playlist = new AllPlaylist(num, playlist_name, sqlDate);
                    allPlaylist.createPlaylist(playlist);
                    break;
                case 6:

                    System.out.println("--------------Your all playlist---------------");
                    allPlaylist.displayAllPlaylist();
                    System.out.println("Enter your playlist name: ");
                    String playName = reader.readLine();
                    int playId = allPlaylist.getPlaylistId(playName);
                    do {
                        System.out.println("Enter your choice \n 1. ADD SONG IN PLAYLIST \n 2.PLAY SONG FROM PLAYLIST \n3.ADD PODCAST IN PLAYLIST \n4. PLAY PODCAST FROM PLAYLIST");
                        int ch = Integer.parseInt(reader.readLine());
                        if (ch == 1) {
                            System.out.println("---------------------Available Song------------------");
                            System.out.format("%-10s %-30s %-20s %-20s %-20s\n", "Song_No", "Song", "Duration", "Artist", "Genre");
                            song.displayAllSongs(songSet);
                            System.out.println("Enter song number to add in playlist---");
                            int song_id = Integer.parseInt(reader.readLine());

                            int number1 = allPlaylist.generateNumber();
                            playlistDetail = new PlaylistDetail(number1, song_id, "song", playId);
                            detail.addSongToPlaylist(playlistDetail);
                        } else if (ch == 2) {
                            int play_Id = allPlaylist.getPlaylistId(playName);
                            boolean flag = true;
                            boolean plist = detail.displaySongsFromPlaylist(play_Id);

                            if (plist) {
                                while (flag) {
                                    System.out.println("Enter song number to be played::");
                                    int s_number = Integer.parseInt(reader.readLine());
                                    String location3 = detail.findSongFromPlaylist(s_number,playId);
                                    if (!location3.isEmpty())
                                    {
                                        song1 = new PlaySongImpl(location3);
                                        song1.playSong();
                                        System.out.println("wanna listen more ? Type YES or NO");
                                        String result = reader.readLine();
                                        if (result.equalsIgnoreCase("no")) {
                                            flag = false;
                                        }
                                    } else
                                        System.out.println("Your requested song is not in this list");
                                }
                            } else
                                System.out.println("No songs in this playlist ");
                        } else if (ch == 3) {
                            System.out.println("---------------------Available Podcast------------------");
                            System.out.format("%-10s %-40s %-20s %-20s\n", "Podcast_No", "Title", "Duration", "Celebrity");
                            podcast.displayAllPodcast(podcastSet);
                            System.out.println("Enter podcast number to add in playlist---");
                            int podcast_id1 = Integer.parseInt(reader.readLine());
                            int play_Id1 = allPlaylist.getPlaylistId(playName);
                            int number_1 = allPlaylist.generateNumber();
                            playlistDetail = new PlaylistDetail(number_1, podcast_id1, "podcast", play_Id1);
                            detail.addPodcastToPlaylist(playlistDetail);
                        } else if (ch == 4) {
                            int play_Id2 = allPlaylist.getPlaylistId(playName);
                            System.out.println("Podcast in playlist");
                            boolean list = detail.displayPodcastFromPlaylist(play_Id2);
                            boolean flag2 = true;
                            if (list) {
                                while (flag2) {
                                    System.out.println("Enter podcast number to be played::");
                                    int p_number = Integer.parseInt(reader.readLine());
                                    String location3 = detail.findPodcastFromPlaylist(p_number,play_Id2);
                                    if (!location3.isEmpty()) {
                                        song2 = new PlayPodcastImpl(location3);
                                        song2.playPodcast();
                                        System.out.println("Wanna listen more ? Type YES or NO");
                                        String result = reader.readLine();
                                        if (result.equalsIgnoreCase("no")) {
                                            flag2 = false;
                                        }
                                    } else
                                        System.out.println("This podcast not available");
                                }
                            } else
                                System.out.println("Podcast list is empty");
                        } else if (ch == 5) {
                            value3 = false;
                        }
                    }while (!value3) ;
                    break;
                   case 7:  value = true;
                    break;
            }
        } while (!value);
    }
}