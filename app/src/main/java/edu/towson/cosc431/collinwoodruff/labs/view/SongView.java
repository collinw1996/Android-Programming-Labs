package edu.towson.cosc431.collinwoodruff.labs.view;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Locale;

import edu.towson.cosc431.collinwoodruff.labs.Controller;
import edu.towson.cosc431.collinwoodruff.labs.R;
import edu.towson.cosc431.collinwoodruff.labs.model.Song;

/**
 * Created by Collin on 9/24/2017.
 */

public class SongView {
    private Button delete;
    private TextView name;
    private TextView artist;
    private TextView track;
    private CheckBox awesome;
    private Song current;
    private ConstraintLayout layout;

    public void hide(){
        layout.setVisibility(View.GONE);
    }

    public void show(){
        layout.setVisibility((View.VISIBLE));
    }

    private Controller controller;

    public SongView(final Controller controller, ConstraintLayout layout){
        this.controller = controller;
        this.layout = layout;
        this.name = (TextView)layout.findViewById(R.id.songName);
        this.artist = (TextView)layout.findViewById(R.id.artistName);
        this.track = (TextView)layout.findViewById(R.id.trackName);
        this.awesome = (CheckBox)layout.findViewById(R.id.toggleAwesome);
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
        track.setText(song.getTrack());
        awesome.setChecked(song.isAwesome());
        current = song;
    }
}
