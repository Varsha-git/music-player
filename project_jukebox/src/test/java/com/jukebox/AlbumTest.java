package com.jukebox;

import com.jukebox.daoimpl.AlbumImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlbumTest {

   AlbumImpl album;


    @BeforeEach
    void setUp()
    {
        album=new AlbumImpl();
    }

    @AfterEach
    void tearDown()
    {
        album=new AlbumImpl();
    }

    @Test
    public void returnCountAlbum()
    {
        assertEquals(8,album.countAllAlbum());
    }
}
