package com.ttstudio.kmtomiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Button mileToKmBtn;
    private Button kmToMileBtn;
    private EditText mileInputField;
    private EditText kmInputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mileToKmBtn = findViewById(R.id.mileToKmBtn);
        kmToMileBtn = findViewById(R.id.kmToMileBtn);
        mileInputField = findViewById(R.id.mileInputField);
        kmInputField = findViewById(R.id.kmInputField);
        DecimalFormat dFormal = new DecimalFormat("##.##");
        mileToKmBtn.setOnClickListener(view -> {
            double vMiles = Double.valueOf(mileInputField.getText().toString());
            double vKm = vMiles / 0.62137;
            kmInputField.setText(dFormal.format(vKm));
        });
        kmToMileBtn.setOnClickListener(view -> {
            double vKm = Double.valueOf(kmInputField.getText().toString());
            double vMiles = vKm * 0.62137;
            mileInputField.setText(dFormal.format(vMiles));
        });
    }
}