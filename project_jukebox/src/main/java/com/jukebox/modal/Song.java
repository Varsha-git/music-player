package com.jukebox.modal;


public class Song {
    private int id;
    private String name;
    private String location;
    private String duration;
    private int albumId;
    private String genre;
    private String artist;


    public Song(int id, String name, String location, String duration, int album, String genre, String artist) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.duration = duration;
        this.albumId = album;
        this.genre = genre;
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAlbum() {
        return albumId;
    }

    public void setAlbum(int album) {
        this.albumId = album;
    }

    public String toString()
    {
        String str;
        str=getId()+"   "+getName()+"   "+getDuration()+"   "+getArtist()+"  "+getGenre()+getAlbum();
        return str;
    }
}
