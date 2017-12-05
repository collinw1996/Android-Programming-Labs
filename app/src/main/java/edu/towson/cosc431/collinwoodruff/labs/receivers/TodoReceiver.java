package edu.towson.cosc431.collinwoodruff.labs.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import edu.towson.cosc431.collinwoodruff.labs.MainActivity;


/**
 * Created by Collin on 11/27/2017.
 */

public class TodoReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        String title = intent.getStringExtra("TITLE");
        String body = intent.getStringExtra("BODY");
        MainActivity activity = (MainActivity)context;
        activity.updateUI(title, body);



    }
}
