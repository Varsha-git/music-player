package com.jukebox.modal;

public class Podcast {

    private int id;
    private String title;
    private String celebrityName;
    private String duration;
    private String location;
    private int albumId;
    private String genre;


    public Podcast(int id, String title, String celebrityName, String duration,String location,int albumId,String genre) {
        this.id = id;
        this.title = title;
        this.celebrityName = celebrityName;
        this.location = location;
        this.duration = duration;
        this.genre = genre;
        this.albumId=albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCelebrityName() {
        return celebrityName;
    }

    public void setCelebrityName(String celebrityName) {
        this.celebrityName = celebrityName;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}
