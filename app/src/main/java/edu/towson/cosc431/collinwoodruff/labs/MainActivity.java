package edu.towson.cosc431.collinwoodruff.labs;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import edu.towson.cosc431.collinwoodruff.labs.adapter.SongAdapter;
import edu.towson.cosc431.collinwoodruff.labs.model.Song;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IView {

    private static final int ADD_SONG_REQUEST_CODE = 1;
    private static final int EDIT_SONG_REQUEST_CODE = 2;
    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    Button addBtn;
    private SongAdapter adapter;
    IPresenter presenter;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this, new SongsModel());
        bindView();
    }

    private void bindView() {
        addBtn = (Button)findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SongAdapter(presenter.getSongsFromModel(), presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.addBtn:
                presenter.launchAddSongActivity();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_SONG_REQUEST_CODE:
                    song = (Song)data.getParcelableExtra("SONG");
                    presenter.handleNewSongResult(song);
                    refresh();
                    break;
                case EDIT_SONG_REQUEST_CODE:
                    song = (Song) data.getParcelableExtra("EDIT");
                    presenter.handleEditSongResult(song);
                    break;
            }
        }
    }

    @Override
    public void refresh() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void launchNewSong() {
        Intent intent = new Intent(this, AddSong.class);
        startActivityForResult(intent, ADD_SONG_REQUEST_CODE);
    }

    @Override
    public void launchEditSong(Song song){
        Intent intent = new Intent(this, EditSong.class);
        intent.putExtra("EDIT" , song);
        startActivityForResult(intent, EDIT_SONG_REQUEST_CODE);
    }
}