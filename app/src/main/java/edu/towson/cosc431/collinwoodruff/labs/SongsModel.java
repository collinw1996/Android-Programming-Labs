package edu.towson.cosc431.collinwoodruff.labs;
import java.util.ArrayList;
import java.util.List;
import edu.towson.cosc431.collinwoodruff.labs.model.Song;

/**
 * Created by Collin on 10/9/17.
 */

public class SongsModel implements IModel {
    List<Song> songs;

    public SongsModel() {
        songs = new ArrayList<>();
        makeSongs();
    }

    private void makeSongs() {
            songs.add(new Song("Fugue", "Gould", 1, true));
            songs.add(new Song("Aria", "Bach", 10, false));
    }

    @Override
    public List<Song> getSongs() {
        return songs;
    }

    @Override
    public void removeSong(Song song) {
        songs.remove(song);
    }

    @Override
    public void addSong(Song song) {
        songs.add(song);
    }

    @Override
    public void editSong(Song song){
        songs.set(songs.indexOf(song), song);
    }
}
