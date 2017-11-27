package edu.towson.cosc431.collinwoodruff.labs.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import edu.towson.cosc431.collinwoodruff.labs.JsonDownloader;
import edu.towson.cosc431.collinwoodruff.labs.MainActivity;

/**
 * Created by Collin on 11/20/2017.
 */

public class JsonService extends IntentService {

    public JsonService(){
        super("JsonService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_input_add);
        builder.setContentText("Downloading...");
        builder.setContentTitle("Json Downloader");
        Notification notification = builder.build();
        NotificationManagerCompat.from(this).notify(1, notification);
        try{
            Thread.sleep(20000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        String json = JsonDownloader.downloadJson();
        Log.d(JsonService.class.getSimpleName(), json);
        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.ic_input_add);

        try {
            JSONObject object = new JSONObject(json);
            builder.setContentText(object.getString("body"));
            builder.setContentTitle(object.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        builder.setAutoCancel(true);
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, mainActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        notification = builder.build();
        NotificationManagerCompat.from(this).notify(1, notification);
    }
}
