package edu.towson.cosc431.collinwoodruff.labs.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by Collin on 11/27/2017.
 */

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "System has Booted!", Toast.LENGTH_SHORT).show();

    }
}
