package edu.towson.cosc431.collinwoodruff.labs;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
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

    List<Song> songs;
    int current;

    SongView songView;
    TextView empty;
    Button previous;
    Button next;

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
        previous.setOnClickListener(this);
        next.setOnClickListener(this);
        empty = (TextView)findViewById(R.id.empty);
        songView = new SongView(this, (LinearLayout)findViewById(R.id.song));
        display();
    }

    private void addSongs(){
        songs.add(new Song("Fugue", "Gould", 1));
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
}