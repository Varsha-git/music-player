package com.jukebox;

import com.jukebox.daoimpl.PodcastImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PodcastTest {

    PodcastImpl podcast;

    @BeforeEach
    void setUp()
    {
        podcast=new PodcastImpl();
    }

    @AfterEach
    void tearDown()
    {
        podcast=new PodcastImpl();
    }

    @Test
    public void givenPodcastReturnLocation()
    {
        assertEquals("D:\\podcast\\headache.wav",podcast.findPodcast(1));
        assertEquals("D:\\podcast\\earthmove.wav",podcast.findPodcast(2));
        assertEquals("D:\\podcast\\time.wav",podcast.findPodcast(3));
        assertEquals("D:\\podcast\\human&music.wav",podcast.findPodcast(4));
    }


}
