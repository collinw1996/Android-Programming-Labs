package edu.towson.cosc431.collinwoodruff;

import org.junit.Test;
import org.mockito.Mockito;

import edu.towson.cosc431.collinwoodruff.labs.IModel;
import edu.towson.cosc431.collinwoodruff.labs.IPresenter;
import edu.towson.cosc431.collinwoodruff.labs.IView;
import edu.towson.cosc431.collinwoodruff.labs.MainPresenter;
import edu.towson.cosc431.collinwoodruff.labs.model.Song;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void PresenterShouldCallViewMethod() {
        // arrange
        IView mockView = Mockito.mock(IView.class);
        IModel mockModel = Mockito.mock(IModel.class);
        IPresenter presenter = new MainPresenter(mockView, mockModel);

        // act
        presenter.launchAddSongActivity();

        // assert
        Mockito.verify(mockView).launchNewSong();
    }

    @Test
    public void PresenterCallsModelAndViewOnDelete() {
        // arrange
        IView mockView = Mockito.mock(IView.class);
        IModel mockModel = Mockito.mock(IModel.class);
        IPresenter presenter = new MainPresenter(mockView, mockModel);
        Song song = new Song();

        // act
        presenter.deleteSong(song);

        Mockito.verify(mockView).refresh();
        Mockito.verify(mockModel).removeSong(song);
    }

    @Test
    public void PresenterCallsModelAndViewOnEdit() {
        // arrange
        IView mockView = Mockito.mock(IView.class);
        IModel mockModel = Mockito.mock(IModel.class);
        IPresenter presenter = new MainPresenter(mockView, mockModel);
        Song song = new Song();

        // act
        presenter.launchEditSong(song);

        Mockito.verify(mockView).launchEditSong(song);


    }
}