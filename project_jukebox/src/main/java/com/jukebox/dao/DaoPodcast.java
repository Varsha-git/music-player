package com.jukebox.dao;

import com.jukebox.modal.Podcast;

import java.util.Set;

public interface DaoPodcast {

    String findPodcast(int id);
    Set<Podcast> getAllPodcast();
   void displayAllPodcast( Set<Podcast> podcastSet);
}
