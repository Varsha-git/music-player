package com.jukebox.daoimpl;

import com.jukebox.dao.DaoPlayPodcast;
import com.jukebox.helper.MysqlConnectivity;
import com.jukebox.modal.SongStatus;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

public class PlayPodcastImpl implements DaoPlayPodcast {

    Boolean value=true;
    Connection connection;
    // to store current position
    Long currentFrame;
    Clip clip;
    String filepath;
    // current status of clip
    String status;
    AudioInputStream audioInputStream;
    public PlayPodcastImpl(String location) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        connection= MysqlConnectivity.getConnection();
        this.filepath=location;
        audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);
    }

    public void playPodcast()
    {
        // create AudioInputStream object
        try {
            play();
            Scanner sc = new Scanner(System.in);
            while (value)
            {
                System.out.println("Option \n PAUSE \t RESUME \t RESTART \t STOP \t JUMP");
                String choice=sc.next();
                if(SongStatus.PAUSE.toString().equalsIgnoreCase(choice)) {
                    pause();
                }
                else if(SongStatus.RESUME.toString().equalsIgnoreCase(choice)) {
                    resumeAudio();
                }
                else if(SongStatus.RESTART.toString().equalsIgnoreCase(choice)) {

                    restart();
                }
                else if(SongStatus.STOP.toString().equalsIgnoreCase(choice)) {
                    stop();
                    value=false;
                }
                else if(SongStatus.JUMP.toString().equalsIgnoreCase(choice)) {
                    System.out.println("Enter time (" + 0 +
                            ", " + clip.getMicrosecondLength() + ")");
                    Scanner scan = new Scanner(System.in);
                    long c1 = scan.nextLong();
                    jump(c1);
                   // break;
                }
            }
            //sc.close();
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // Method to play the audio
    public void play()
    {
        //start the clip
        clip.start();
        status = "play";
    }

    // Method to pause the audio
    public void pause()
    {
        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        if (status.equals("play"))
        {
            System.out.println("Audio is already "+
                    "being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        play();

    }

    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    // Method to jump over a specific part
    public void jump(long c) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        if (c > 0 && c < clip.getMicrosecondLength())
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filepath).getAbsoluteFile());
        clip.open(audioInputStream);
    }
}
