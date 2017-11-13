package edu.towson.cosc431.collinwoodruff.labs.database;

import android.provider.BaseColumns;

/**
 * Created by Collin on 11/2/2017.
 */

public class DatabaseContract implements BaseColumns {

    public static final String TABLE_NAME = "Song";
    public static final String TITLE_COLUMN_NAME = "Title";
    public static final String ARTIST_COLUMN_NAME = "Artist";
    public static final String TRACK_COLUMN_NAME = "Track";
    public static final String ISAWESOME_COLUMN_NAME = "IsAwesome";

}
