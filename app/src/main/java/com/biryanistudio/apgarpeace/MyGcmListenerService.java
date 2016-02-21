package com.biryanistudio.apgarpeace;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;

import java.util.ArrayList;

/**
 * Created by sravan953 on 2/20/2016.
 */
public class MyGcmListenerService extends GcmListenerService {
    public interface updateText {
        void updateTextView(String text);
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String grimace = data.getString("grimace");
        String activity = data.getString("activity");
        String appearance = data.getString("appearance");
        ArrayList<String> intentData = new ArrayList<>();
        intentData.add(grimace);
        intentData.add(activity);
        intentData.add(appearance);
        Log.i(getClass().getSimpleName(), "Message: " + activity);
        LocalBroadcastManager.getInstance(getApplicationContext())
                .sendBroadcast(new Intent(getApplicationContext(), MainActivity.class)
                        .setAction("APGAR")
                        .putStringArrayListExtra("DATA", intentData));
    }
}
