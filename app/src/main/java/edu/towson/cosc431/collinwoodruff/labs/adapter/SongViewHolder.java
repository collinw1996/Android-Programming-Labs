package edu.towson.cosc431.collinwoodruff.labs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Locale;

import edu.towson.cosc431.collinwoodruff.labs.Controller;
import edu.towson.cosc431.collinwoodruff.labs.R;
import edu.towson.cosc431.collinwoodruff.labs.model.Song;

public class SongViewHolder extends RecyclerView.ViewHolder {
    TextView songName;
    TextView songArtist;
    TextView trackNum;
    CheckBox isAwesome;
    Song song;
    Button deleteBtn;
    Controller controller;

    public SongViewHolder(View itemView, final Controller controller) {
        super(itemView);
        this.controller = controller;
        songName = (TextView) itemView.findViewById(R.id.songName);
        songName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SongViewHolder.this.controller.editSong(song);
            }
        });
        songArtist = (TextView)itemView.findViewById(R.id.songArtist);
        trackNum = (TextView)itemView.findViewById(R.id.songTrack);
        isAwesome = (CheckBox) itemView.findViewById(R.id.isAwesome);
        isAwesome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                song.setAwesome(!song.isAwesome());
            }
        });
        deleteBtn = (Button) itemView.findViewById(R.id.deleteButton);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SongViewHolder.this.controller.delete(song);
            }
        });
    }

    public void bindSong(Song song) {
        songName.setText(song.getName());
        songArtist.setText(song.getArtist());
        trackNum.setText(String.format(Locale.US, "%d", song.getTrack()));
        isAwesome.setChecked(song.isAwesome());
        this.song = song;
    }
}

