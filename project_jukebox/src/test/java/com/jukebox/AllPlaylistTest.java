package com.jukebox;

import com.jukebox.daoimpl.AllPlaylistImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class AllPlaylistTest {

    AllPlaylistImpl allPlaylist;

    @BeforeEach
    void setUp()
    {
        allPlaylist=new AllPlaylistImpl();
    }

    @AfterEach
    void tearDown()
    {
        allPlaylist=new AllPlaylistImpl();
    }

    @Test
    public void givenPlaylistNameReturnId()
    {
        assertEquals(32,allPlaylist.getPlaylistId("favourites"));
        assertEquals(7,allPlaylist.getPlaylistId("tt"));
        assertEquals(31,allPlaylist.getPlaylistId("fun songs"));
        assertEquals(33,allPlaylist.getPlaylistId("workout playlist"));
    }



}
