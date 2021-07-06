package com.jukebox.modal;

import java.util.Date;

public class Album {

    private int id;
    private Date releasedDate;
    private String name;

    public Album(int id, Date releasedDate, String name) {
        this.id = id;
        this.releasedDate = releasedDate;
        this.name = name;
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

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String toString()
    {
        String str;
        str=getName()+"\t"+getReleasedDate();
        return str;
    }
}
