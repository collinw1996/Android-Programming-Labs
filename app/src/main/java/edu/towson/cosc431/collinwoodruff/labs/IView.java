package edu.towson.cosc431.collinwoodruff.labs;

import edu.towson.cosc431.collinwoodruff.labs.model.Song;

/**
 * Created by Collin on 10/9/2017.
 */

public interface IView {
    void refresh();
    void launchNewSong();
    void launchEditSong(Song song);
}
