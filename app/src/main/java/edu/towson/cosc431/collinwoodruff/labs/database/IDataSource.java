package edu.towson.cosc431.collinwoodruff.labs.database;

import java.util.List;

import edu.towson.cosc431.collinwoodruff.labs.model.Song;

/**
 * Created by Collin on 11/3/2017.
 */

public interface IDataSource {
    List<Song> getAllSongs();
    List<Song> getSong(String id);
    void addSong(Song song);
    void updateSong(Song song);
    void deleteSong(Song song);
}
