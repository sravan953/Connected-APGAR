package com.biryanistudio.apgarpeace;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    LocalBroadcastManager localBroadcastManager;
    String grimace;
    String activity;
    String appearance;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<String> intentData = intent.getStringArrayListExtra("DATA");
            grimace = intentData.get(0);
            activity = intentData.get(1);
            appearance = intentData.get(2);
            ((TextView)findViewById(R.id.text1)).setText("Appearance: " + appearance);
            ((TextView)findViewById(R.id.text2)).setText("Grimace: " + grimace);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        registerApp();
        findViewById(R.id.button).setOnClickListener(this);
    }

    private void registerApp() {
        Log.i(getClass().getSimpleName(), "registerApp()");
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("APGAR");
        localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onClick(View v) {
        if(activity==null || activity.isEmpty()) {
            Toast.makeText(this, "Try later", Toast.LENGTH_LONG).show();
        } else {
            Intent intent2 = new Intent(this, ChartActivity.class);
            intent2.putExtra("DATA", activity);
            startActivity(intent2);
        }
    }
}
