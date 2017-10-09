package edu.towson.cosc431.collinwoodruff.labs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.towson.cosc431.collinwoodruff.labs.Controller;
import edu.towson.cosc431.collinwoodruff.labs.R;
import edu.towson.cosc431.collinwoodruff.labs.model.Song;

public class SongAdapter extends RecyclerView.Adapter<SongViewHolder> {

    List<Song> songs;
    Controller controller;
    public SongAdapter(List<Song> songs, Controller controller) {
        this.songs = songs;
        this.controller = controller;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.song, parent, false);
        SongViewHolder viewHolder = new SongViewHolder(view, controller);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.bindSong(song);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
