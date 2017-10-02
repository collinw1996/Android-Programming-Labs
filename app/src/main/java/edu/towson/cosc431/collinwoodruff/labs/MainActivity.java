package edu.towson.cosc431.collinwoodruff.labs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.towson.cosc431.collinwoodruff.labs.model.Song;
import edu.towson.cosc431.collinwoodruff.labs.view.SongView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Controller {

    public static final int ADD_SONG_REQUEST_CODE = 1;
    public static final int EDIT_SONG_REQUEST_CODE = 2;
    public static final String TAG = MainActivity.class.getSimpleName();

    List<Song> songs;
    int current;

    SongView songView;
    TextView empty;
    Button previous;
    Button next;
    Button add;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        current = 0;
        songs = new ArrayList<>();
        addSongs();
        actions();
    }

    private void actions(){
        previous = (Button)findViewById(R.id.previousButton);
        next = (Button)findViewById(R.id.nextButton);
        add = (Button)findViewById(R.id.addBtn);
        previous.setOnClickListener(this);
        next.setOnClickListener(this);
        add.setOnClickListener(this);
        empty = (TextView)findViewById(R.id.empty);
        songView = new SongView(this, (LinearLayout)findViewById(R.id.song));
        display();
    }

    private void addSongs(){
        songs.add(new Song("Fugue", "Gould", 1, true));
        songs.add(new Song("Photograph", "Def Leppard", 2, false));
        songs.add(new Song("OMG", "Vic Mensa", 3, false));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.previousButton:
                prev();
                break;
            case R.id.nextButton:
                next();
                break;
            case R.id.addBtn:
                addSong();
                break;
        }
    }

    private void display(){
        if(songs.size() > 0)
            songView.renderSong(songs.get(current));
        logSongs();
    }

    private void logSongs(){
        for(Song song : songs)
            Log.d("Tag", song.toString());
    }

    @Override
    public void next() {
        if(current < songs.size() - 1)
            current = current + 1;
        display();
    }

    @Override
    public void prev() {
        if(current > 0)
            current = current - 1;
        display();
    }

    @Override
    public void delete(Song song) {
        songs.remove(song);
        if(songs.isEmpty()){
            empty.setVisibility(View.VISIBLE);
            songView.hide();
        }
        else{
            current = songs.size() - 1;
            display();
        }
    }

    @Override
    public void toggle() {
        Song currentSong = songs.get(current);
        currentSong.toggleAwesome();
        Log.d("Tag", currentSong.getName() + " is awesome?" + currentSong.isAwesome());
    }

    @Override
    public void addSong() {
        Intent addIntent = new Intent(this, AddSong.class);
        startActivityForResult(addIntent, ADD_SONG_REQUEST_CODE);
    }

    public void editSong(){
        Intent saveIntent = new Intent(this, AddSong.class);
        startActivityForResult(saveIntent, ADD_SONG_REQUEST_CODE);
    }

    public void newSong(Intent data) {
        Song song = (Song)data.getSerializableExtra("SONG");
        songs.add(new Song(song.getName(),song.getArtist(),song.getTrack(),song.isAwesome()));

    }

    public void saveSong(Intent data){
        Song song = (Song)data.getSerializableExtra("SONG");
        songs.set(current, new Song (song.getName(),song.getArtist(), song.getTrack(), song.isAwesome()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_SONG_REQUEST_CODE:
                    newSong(data);
                    break;
            }
        }
    }
}