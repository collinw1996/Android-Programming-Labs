package edu.towson.cosc431.collinwoodruff.labs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.towson.cosc431.collinwoodruff.labs.model.Song;

/**
 * Created by Collin on 11/2/2017.
 */

public class SongDataSource implements IDataSource {

    private static SongDataSource instance;
    private SongsDBHelper dbHelper;

    private SongDataSource(Context ctx) {
        dbHelper = new SongsDBHelper(ctx);
    }

    public static SongDataSource getInstance(Context ctx) {
        if(instance == null) {
            instance = new SongDataSource(ctx);
        }
        return instance;
    }

    public List<Song> getAllSongs() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DatabaseContract.TABLE_NAME, null);
        List<Song> songs = new ArrayList<>();
        while(cursor.moveToNext()) {
            Song song = new Song();
            String id = cursor.getString(cursor.getColumnIndex(DatabaseContract._ID));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.TITLE_COLUMN_NAME));
            String artist = cursor.getString(cursor.getColumnIndex(DatabaseContract.ARTIST_COLUMN_NAME));
            int track = cursor.getInt(cursor.getColumnIndex(DatabaseContract.TRACK_COLUMN_NAME));
            boolean isAwesome = cursor.getInt(cursor.getColumnIndex(DatabaseContract.ISAWESOME_COLUMN_NAME)) == 1;
            song.setId(id);
            song.setName(title);
            song.setArtist(artist);
            song.setTrack(track);
            song.setAwesome(isAwesome);
            songs.add(song);
        }
        return songs;
    }

    public List<Song> getSong(String id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DatabaseContract.TABLE_NAME + " where " + DatabaseContract._ID + " = ?", new String[]{id});
        List<Song> songs = new ArrayList<>();
        while(cursor.moveToNext()) {
            Song song = new Song();
            String title = cursor.getString(cursor.getColumnIndex(DatabaseContract.TITLE_COLUMN_NAME));
            String artist = cursor.getString(cursor.getColumnIndex(DatabaseContract.ARTIST_COLUMN_NAME));
            int track = cursor.getInt(cursor.getColumnIndex(DatabaseContract.TRACK_COLUMN_NAME));
            boolean isAwesome = cursor.getInt(cursor.getColumnIndex(DatabaseContract.ISAWESOME_COLUMN_NAME)) == 1;
            song.setId(id);
            song.setName(title);
            song.setArtist(artist);
            song.setTrack(track);
            song.setAwesome(isAwesome);
            songs.add(song);
        }
        return songs;
    }

    @Override
    public void addSong(Song song) {
        ContentValues cv = songToContentValues(song);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(DatabaseContract.TABLE_NAME, null, cv);
    }

    @Override
    public void updateSong(Song song){
        ContentValues cv = songToContentValues(song);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(DatabaseContract.TABLE_NAME, cv, DatabaseContract.TITLE_COLUMN_NAME + "= ?", new String[]{song.getName()});
        //db.update(DatabaseContract.TABLE_NAME, cv, DatabaseContract._ID + "= ?", new String[]{song.getId()});
    }

    @Override
    public void deleteSong(Song song) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseContract.TABLE_NAME, DatabaseContract.TITLE_COLUMN_NAME + "= ?", new String[]{song.getName()});
        //db.delete(DatabaseContract.TABLE_NAME, DatabaseContract._ID + "= ?", new String[]{song.getId()});
    }

    private ContentValues songToContentValues(Song song) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract._ID, song.getId());
        cv.put(DatabaseContract.TITLE_COLUMN_NAME, song.getName());
        cv.put(DatabaseContract.ARTIST_COLUMN_NAME, song.getArtist());
        cv.put(DatabaseContract.TRACK_COLUMN_NAME, song.getTrack());
        cv.put(DatabaseContract.ISAWESOME_COLUMN_NAME, song.isAwesome());
        return cv;
    }

}
