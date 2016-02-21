package com.biryanistudio.apgarpeace;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.Arrays;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        String apgarData = getIntent().getStringExtra("DATA");
        List<String> apgarList = Arrays.asList(apgarData.split(","));
        setupChart(apgarData);
    }

    private void setupChart(String apgarData) {
        LineChart lineChart = (LineChart)findViewById(R.id.chart);
        lineChart.setDescription("APGAR");
        lineChart.setTouchEnabled(false);
        LineData data = new LineData();
        lineChart.setData(data);
        LineDataSet set = new LineDataSet(null, "Activity");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(Color.WHITE);
        set.setLineWidth(2f);
        set.setCircleRadius(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(9f);
        set.setDrawValues(false);
        data.addDataSet(set);

        List<String> apgarList = Arrays.asList(apgarData.split(","));
        Log.i(getClass().getSimpleName(), apgarList.toString());
        for(int i = 0;i<10;i++) {
            data.addXValue("");
            data.addEntry((new Entry(Float.parseFloat(apgarList.get(i)), i)), 0);
            lineChart.notifyDataSetChanged();
        }
    }
}
