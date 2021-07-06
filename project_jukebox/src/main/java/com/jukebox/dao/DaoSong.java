package com.jukebox.dao;

import com.jukebox.modal.Song;

import java.util.Set;

public interface DaoSong
{

    Set<Song> getAllSongs();

    boolean sortByGenre();

    int searchByArtist(String artist);
    String findSong(int id);
    void displayAllSongs(Set<Song> songSet);
}
