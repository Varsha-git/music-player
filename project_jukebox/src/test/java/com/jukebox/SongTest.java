package com.jukebox;

import com.jukebox.daoimpl.SongImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class SongTest {

    SongImpl songImpl;

    @BeforeEach
    void setUp()
    {
        songImpl=new SongImpl();
    }


    @AfterEach
    void tearDown()
    {
        songImpl=new SongImpl();
    }

   /* @Test
    public void givenArtistNameReturnTotalRecords()
    {
        assertEquals(1,songImpl.searchByArtist("taylor"));
        assertEquals(1,songImpl.searchByArtist("selena"));
    }
*/

    @Test
    public void givenSongIdReturnLocation()
    {
        assertEquals("D:\\songs\\safarnama.wav",songImpl.findSong(4));
        assertEquals("File not found",songImpl.findSong(10));
    }
}


