package com.biryanistudio.apgarpeace;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by sravan953 on 2/20/2016.
 */
public class RegistrationIntentService extends IntentService {
    private final String TOKEN_KEY = "TOKEN_KEY";

    public RegistrationIntentService() {
        super("RegistrationIntentService");
    }

    public RegistrationIntentService(String name) {
        super(name);
    }

    @Override
    public void onHandleIntent(Intent intent) {
        Log.i(getClass().getSimpleName(), "onHandleIntent()");
        InstanceID instanceID = InstanceID.getInstance(this);
        try {
            String token = instanceID.getToken(getString(R.string.senderID),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            if(!token.isEmpty()) storeToken(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void storeToken(String token) {
        Log.i(getClass().getSimpleName(), "storeToken()");
        SharedPreferences.Editor prefsEditor = (SharedPreferences.Editor) PreferenceManager.getDefaultSharedPreferences(this).edit();
        prefsEditor.putString(TOKEN_KEY, token);
        prefsEditor.commit();
        Log.i(getClass().getSimpleName(), token);
        //subscribeTopic(token);
    }

    /*private void subscribeTopic(String token) {
        try {
            Log.i(getClass().getSimpleName(), "subscribeTopic()");
            GcmPubSub pubSub = GcmPubSub.getInstance(this);
            pubSub.subscribe(token, "/topics/apgar", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}