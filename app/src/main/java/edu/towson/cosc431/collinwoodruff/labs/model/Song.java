package edu.towson.cosc431.collinwoodruff.labs.model;

import java.io.Serializable;

/**
 * Created by Collin on 9/24/2017.
 */

public class Song implements Serializable{
    private String name;
    private String artist;
    private int track;
    private boolean isAwesome;

    public Song(String name, String artist, int track, boolean isAwesome){
        this.name = name;
        this.artist = artist;
        this.track = track;
        this.isAwesome = isAwesome;
    }

    public Song(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public boolean isAwesome() {
        return isAwesome;
    }

    public void setAwesome(boolean awesome) {
        isAwesome = awesome;
    }

    public void toggleAwesome(){
        this.isAwesome = !this.isAwesome;
    }

}
