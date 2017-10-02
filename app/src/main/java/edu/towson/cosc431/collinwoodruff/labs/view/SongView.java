package edu.towson.cosc431.collinwoodruff.labs.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

import edu.towson.cosc431.collinwoodruff.labs.AddSong;
import edu.towson.cosc431.collinwoodruff.labs.Controller;
import edu.towson.cosc431.collinwoodruff.labs.R;
import edu.towson.cosc431.collinwoodruff.labs.model.Song;

public class SongView {

    private Button delete;
    private TextView name;
    private TextView artist;
    private TextView track;
    private CheckBox awesome;
    private Song current;
    private LinearLayout layout;

    public void hide(){
        layout.setVisibility(View.GONE);
    }

    public void show(){
        layout.setVisibility((View.VISIBLE));
    }

    private Controller controller;

    public SongView(final Controller controller, LinearLayout layout){
        this.controller = controller;
        this.layout = layout;
        this.name = (TextView) layout.findViewById(R.id.songName);
        this.name.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                SongView.this.controller.editSong();
            }
        });
        this.artist = (TextView) layout.findViewById(R.id.songArtist);
        this.track = (TextView) layout.findViewById(R.id.songTrack);
        this.awesome = (CheckBox) layout.findViewById(R.id.isAwesome);
        this.awesome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                SongView.this.controller.toggle();
            }
        });
        this.delete = (Button)layout.findViewById(R.id.deleteButton);
        this.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SongView.this.controller.delete(current);
            }
        });
    }

    public void renderSong(Song song){
        name.setText(song.getName());
        artist.setText(song.getArtist());
        track.setText(String.format(Locale.US, "%d", song.getTrack()));
        awesome.setChecked(song.isAwesome());
        current = song;
    }
}
