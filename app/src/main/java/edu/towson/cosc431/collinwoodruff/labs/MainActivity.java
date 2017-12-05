package edu.towson.cosc431.collinwoodruff.labs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.towson.cosc431.collinwoodruff.labs.adapter.SongAdapter;
import edu.towson.cosc431.collinwoodruff.labs.model.Song;
import edu.towson.cosc431.collinwoodruff.labs.receivers.TodoReceiver;
import edu.towson.cosc431.collinwoodruff.labs.services.JsonService;
import edu.towson.cosc431.collinwoodruff.labs.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Controller {

    private static final int ADD_SONG_REQUEST_CODE = 1;
    private static final int EDIT_SONG_REQUEST_CODE = 2;

    TextView title2;
    TextView body2;
    private SongAdapter adapter;
    List<Song> songs;
    RecyclerView recyclerView;
    Button addBtn, serviceBtn;
    int position;
    boolean flag;
    BroadcastReceiver todoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            addBtn.setText("HELLO WORLD");
        }
    };

    IntentFilter newTodoMsg = new IntentFilter("NEW_TODO");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings_menu:
                // launch settings activity
                startActivity(new Intent(this, SettingsActivity.class));
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(todoReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(todoReceiver, newTodoMsg );
        startService(new Intent(this, JsonService.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songs = new ArrayList<>();
        addSongs();
        actions();

        todoReceiver = new TodoReceiver();

        Executor executor = (Executor) Executors.newFixedThreadPool(1);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("Lab11", JsonDownloader.downloadJson());
            }
        });
    }

    private void actions(){
        addBtn = (Button)findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
        serviceBtn = (Button)findViewById(R.id.serviceBtn);
        serviceBtn.setOnClickListener(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Handler handler = new Handler();
        adapter = new SongAdapter(songs, (Controller)this, handler);
        recyclerView.setAdapter(adapter);
    }

    private void addSongs(){
        songs.add(new Song("Fugue", "Gould", 1, true));
        songs.add(new Song("Photograph", "Def Leppard", 2, false));
        songs.add(new Song("OMG", "Vic Mensa", 3, false));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addBtn:
                addSong();
                break;
            case R.id.serviceBtn:
                startService(new Intent(this, JsonService.class));
                break;
        }
    }

    public void logSongs(){
        for(Song song : songs)
            Log.d("Tag", song.toString());
    }

    public void updateUI(String title, String body){
        title2 = (TextView)findViewById(R.id.songName);
        body2 = (TextView) findViewById(R.id.artistName);
        title2.setText(title);
        body2.setText(body);
    }

    @Override
    public void delete(Song song) {
        songs.remove(song);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addSong() {
        Intent addIntent = new Intent(this, AddSong.class);
        startActivityForResult(addIntent, ADD_SONG_REQUEST_CODE);
    }

    public void editSong(Song song){
        Intent saveIntent = new Intent(this, EditSong.class);
        saveIntent.putExtra("EDIT" , song);
        startActivityForResult(saveIntent, EDIT_SONG_REQUEST_CODE);
        position = songs.indexOf(song);
    }

    public void newSong(Intent data) {
        Song song = data.getParcelableExtra("SONG");
        songs.add(new Song(song.getName(),song.getArtist(),song.getTrack(),song.isAwesome()));
        adapter.notifyDataSetChanged();

    }

    public void saveSong(Intent data){
        Song song = data.getParcelableExtra("EDIT");
        songs.set(position, new Song (song.getName(),song.getArtist(), song.getTrack(), song.isAwesome()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_SONG_REQUEST_CODE:
                    newSong(data);
                    break;
                case EDIT_SONG_REQUEST_CODE:
                    saveSong(data);
                    break;
            }
        }
    }
}