package edu.towson.cosc431.collinwoodruff.labs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Locale;

import edu.towson.cosc431.collinwoodruff.labs.IPresenter;
import edu.towson.cosc431.collinwoodruff.labs.R;
import edu.towson.cosc431.collinwoodruff.labs.model.Song;

public class SongViewHolder extends RecyclerView.ViewHolder {
    TextView songNameTv;
    TextView artistNameTv;
    TextView trackNumTv;
    CheckBox awesomeCb;
    Song song;
    Button delBtn;
    IPresenter controller;

    public SongViewHolder(View itemView, final IPresenter contoller) {
        super(itemView);
        this.controller = contoller;
        songNameTv = (TextView)itemView.findViewById(R.id.songName);
        songNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SongViewHolder.this.controller.launchEditSong(song);
            }
        });
        artistNameTv = (TextView)itemView.findViewById(R.id.songArtist);
        trackNumTv = (TextView)itemView.findViewById(R.id.songTrack);
        awesomeCb = (CheckBox) itemView.findViewById(R.id.isAwesome);
        awesomeCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                song.setAwesome(!song.isAwesome());
            }
        });
        delBtn = (Button)itemView.findViewById(R.id.deleteButton);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SongViewHolder.this.controller.deleteSong(song);
            }
        });
    }

    public void bindSong(Song song) {
        songNameTv.setText(song.getName());
        artistNameTv.setText(song.getArtist());
        trackNumTv.setText(String.format(Locale.US, "%d", song.getTrack()));
        awesomeCb.setChecked(song.isAwesome());
        this.song = song;
    }
}

