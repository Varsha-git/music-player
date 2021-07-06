package com.jukebox.dao;

import com.jukebox.modal.PlaylistDetail;

public interface DaoPlaylistDetail {

        void addSongToPlaylist(PlaylistDetail playlistDetail);
        void addPodcastToPlaylist(PlaylistDetail playlistDetail);
        boolean displaySongsFromPlaylist(int id);
        boolean displayPodcastFromPlaylist(int id);
        String findSongFromPlaylist(int songid,int playlistid);
        String findPodcastFromPlaylist(int songid,int playlistid);
}
