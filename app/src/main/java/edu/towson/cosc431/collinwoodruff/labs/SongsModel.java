package edu.towson.cosc431.collinwoodruff.labs;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.towson.cosc431.collinwoodruff.labs.database.IDataSource;
import edu.towson.cosc431.collinwoodruff.labs.model.Song;

/**
 * Created by Collin on 10/9/17.
 */

public class SongsModel implements IModel {
    private final IDataSource dataSource;

    public SongsModel(IDataSource ds) {
        dataSource = ds;
        seedSongs();
    }

    public void seedSongs(){
        dataSource.addSong(new Song("Kids", "MGMT", 1, true));
        dataSource.addSong(new Song("Creep", "Radiohead", 2, false));
        dataSource.addSong(new Song("Redbone", "Gambino", 3, true));
        dataSource.addSong(new Song("Loyalty", "Kendrick", 4, true));
        dataSource.addSong(new Song("Stargazing", "Kygo", 5, false));
        dataSource.addSong(new Song("Reptilia", "Strokes", 6, false));
        dataSource.addSong(new Song("Free Fallin", "Tom Petty", 7, true));
        dataSource.addSong(new Song("Dive", "Ed Sheeran", 8, false));
        dataSource.addSong(new Song("Starboy", "Weekend", 9, true));
        dataSource.addSong(new Song("Feels", "Calvin Harris", 10, true));
    }


    @Override
    public List<Song> getSongs() {
        return dataSource.getAllSongs();
    }

    @Override
    public void removeSong(Song song) {
        dataSource.deleteSong(song);
    }

    @Override
    public void addSong(Song song) {
        dataSource.addSong(song);
    }

    @Override
    public void editSong(Song song) {
        dataSource.updateSong(song);
    }
}