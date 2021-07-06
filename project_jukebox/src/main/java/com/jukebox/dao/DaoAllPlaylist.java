package com.jukebox.dao;

import com.jukebox.modal.AllPlaylist;

public interface DaoAllPlaylist
{
    boolean createPlaylist(AllPlaylist playlist);
    void displayAllPlaylist();
    int generateNumber();
    int getPlaylistId(String name);
}
