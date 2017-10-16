package edu.towson.cosc431.collinwoodruff.labs;

import android.content.Intent;

import java.util.List;

import edu.towson.cosc431.collinwoodruff.labs.model.Song;

/**
 * Created by Collin on 10/9/17.
 */

public interface IPresenter {
    void deleteSong(Song song);
    void launchAddSongActivity();
    void launchEditSong(Song song);
    void handleNewSongResult(Song song);
    void handleEditSongResult(Song song);
    List<Song> getSongsFromModel();
    void addNewSong(Song song);
    void editOldSong(Song song);
}
