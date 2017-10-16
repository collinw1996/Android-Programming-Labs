package edu.towson.cosc431.collinwoodruff.labs;

import java.util.List;

import edu.towson.cosc431.collinwoodruff.labs.model.Song;

/**
 * Created by Collin on 10/9/17.
 */

public class MainPresenter implements IPresenter {

    private final IView view;
    private final IModel model;

    public MainPresenter(IView view, IModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handleNewSongResult(Song song) {
        addNewSong(song);
    }

    @Override
    public List<Song> getSongsFromModel() {
        return model.getSongs();
    }

    @Override
    public void addNewSong(Song song) {
        model.addSong(song);
    }

    @Override
    public void handleEditSongResult(Song song) {
        editOldSong(song);
    }

    public void editOldSong(Song song) {
        model.editSong(song);
    }

    @Override
    public void deleteSong(Song song) {
        model.removeSong(song);
        view.refresh();
    }

    @Override
    public void launchAddSongActivity() {
        view.launchNewSong();
    }

    @Override
    public void launchEditSong(Song song) {
        view.launchEditSong(song);
    }
}
