package com.jukebox.modal;

public class PlaylistDetail
{
    private int id;
    private int item_id;
    private String type;
    private int allPlaylistId;

    public PlaylistDetail(int id, int item_id, String type, int playlistId) {
        this.id = id;
        this.item_id = item_id;
        this.type = type;

        this.allPlaylistId = playlistId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPlaylistId() {
        return allPlaylistId;
    }

    public void setPlaylistId(int playlistId) {
        this.allPlaylistId = playlistId;
    }
}
