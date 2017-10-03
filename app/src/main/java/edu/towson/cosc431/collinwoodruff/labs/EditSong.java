package edu.towson.cosc431.collinwoodruff.labs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

import edu.towson.cosc431.collinwoodruff.labs.model.Song;

/**
 * Created by Collin on 9/28/2017.
 */

public class EditSong extends AppCompatActivity implements View.OnClickListener, EditSongController{

    EditText songName;
    EditText artistName;
    EditText trackNum;
    Button addBtn;
    CheckBox awesome;
    Song song;

    List<Song> songs;
    boolean flag = false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_song);
        actions();
        song = new Song();
    }

    public void actions(){
        songName = (EditText) findViewById(R.id.songName);
        artistName = (EditText)findViewById(R.id.artistName);
        trackNum = (EditText)findViewById(R.id.trackNum);
        addBtn = (Button)findViewById(R.id.addBtn);
        awesome = (CheckBox)findViewById(R.id.isAwesome);

        Intent intent = getIntent();
        Song song = (Song)intent.getSerializableExtra("EDIT");

        songName.setText(song.getName());
        artistName.setText(song.getArtist());
        trackNum.setText(Integer.toString(song.getTrack()));
        awesome.setChecked(song.isAwesome());

        addBtn.setOnClickListener(this);
        awesome.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.addBtn:
                song.setName(songName.getText().toString());
                song.setArtist(artistName.getText().toString());
                song.setTrack(Integer.parseInt(trackNum.getText().toString()));
                song.setAwesome(flag);
                editSong(song);
                break;
            case R.id.isAwesome:
                song.toggleAwesome();
                if(flag)
                    flag = false;
                else
                    flag = true;
                break;
        }
    }

    @Override
    public void editSong(Song song) {
        Intent intent = new Intent();
        intent.putExtra("EDIT",song);
        setResult(RESULT_OK,intent);
        finish();
    }
}
