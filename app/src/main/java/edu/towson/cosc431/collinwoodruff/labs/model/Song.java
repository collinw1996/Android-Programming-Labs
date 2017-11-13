package edu.towson.cosc431.collinwoodruff.labs.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Created by Collin on 9/24/2017.
 */

public class Song implements Parcelable{
    private String name;
    private String artist;
    private int track;
    private boolean isAwesome;
    private String _id;

    public Song(String name, String artist, int track, boolean isAwesome){
        this.name = name;
        this.artist = artist;
        this.track = track;
        this.isAwesome = isAwesome;
        this._id = UUID.randomUUID().toString();
    }

    public Song(){

    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
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

    public int getIndex(int index){
        return index;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(artist);
        out.writeInt(track);
        out.writeByte((byte) (isAwesome ? 1 : 0)); //if isAwesome == true, byte == 1
        out.writeString(_id);
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    private Song(Parcel in) {
        name = in.readString();
        artist = in.readString();
        track = in.readInt();
        isAwesome = in.readByte() != 0; //isAwesome == true if byte != 0
        _id = in.readString();
    }

}
