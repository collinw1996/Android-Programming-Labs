package edu.towson.cosc431.collinwoodruff.labs;

import java.util.List;

import edu.towson.cosc431.collinwoodruff.labs.model.Song;

/**
 * Created by Collin on 10/9/2017.
 */

public interface IModel {
    List<Song> getSongs();
    void removeSong(Song song);
    void addSong(Song song);
    void editSong(Song song);
}
