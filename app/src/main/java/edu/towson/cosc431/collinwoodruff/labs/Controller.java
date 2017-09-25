package edu.towson.cosc431.collinwoodruff.labs;

import edu.towson.cosc431.collinwoodruff.labs.model.Song;
/**
 * Created by Collin on 9/24/2017.
 */

public interface Controller {
    void next();
    void prev();
    void delete(Song song);
    void toggle();
}
