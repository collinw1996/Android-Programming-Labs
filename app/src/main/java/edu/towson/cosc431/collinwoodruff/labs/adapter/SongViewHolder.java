package edu.towson.cosc431.collinwoodruff.labs.adapter;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.towson.cosc431.valis.imageservice.ImageService;
import edu.towson.cosc431.collinwoodruff.labs.Controller;
import edu.towson.cosc431.collinwoodruff.labs.R;
import edu.towson.cosc431.collinwoodruff.labs.model.Song;

public class SongViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView body;
    ImageView imageView;
    ProgressBar progressBar;
    CheckBox isAwesome;
    Song song;
    Button deleteBtn;
    Controller controller;

    public SongViewHolder(View itemView, final Controller controller) {
        super(itemView);
        this.controller = controller;
        title = (TextView) itemView.findViewById(R.id.songName);
        body = (TextView) itemView.findViewById(R.id.artistName);
        imageView = (ImageView)itemView.findViewById(R.id.imageView);
        progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);
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

    public void bindSong(Song song, final Handler handler) {
        title.setText("Empty");
        body.setText("Empty");
        isAwesome.setChecked(song.isAwesome());
        this.song = song;
        final ImageService service = ImageService.getInstance();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Integer> imageIds = service.getAvailableImageIds();
                Integer firstId = imageIds.get(0);
                service.setImage("1", firstId);
                try{
                    final Bitmap bitmap = service.getImage("1");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                } catch (final Exception ex){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(imageView.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }
}

