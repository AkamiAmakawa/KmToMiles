package com.ttstudio.miniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    NumberPicker wallpaperPicker;
    Button saveWallpaperBtn;
    Button goToPlayerBtn;
    ImageView wallpaperPreview;
    int wallpapers[] = {R.drawable.wallpaper1, R.drawable.wallpaper2, R.drawable.wallpaper3};
    int currentWallpaper = 0;
    int currentPreviewWallpaper = 0;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Assign View elements
        preferences = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
        editor = preferences.edit();
        wallpaperPicker = findViewById(R.id.wallpaperPicker);
        saveWallpaperBtn = findViewById(R.id.saveWallpaperBtn);
        goToPlayerBtn = findViewById(R.id.goToPlayerBtn);
        wallpaperPreview = findViewById(R.id.wallpaperPreview);

        //Set picker value
        wallpaperPicker.setDisplayedValues(new String[]{
                "Wallpaper 1",
                "Wallpaper 2",
                "Wallpaper 3"
        });
        wallpaperPicker.setMinValue(0);
        wallpaperPicker.setMaxValue(wallpapers.length - 1);
        wallpaperPicker.setOnValueChangedListener((numberPicker, oldVar, newVar) -> {
            currentPreviewWallpaper = numberPicker.getValue();
            refreshPreview();
        });
        //Save Button listener
        saveWallpaperBtn.setOnClickListener((btn) -> {
            currentWallpaper = currentPreviewWallpaper;
            editor.putInt("wallpaperIndex", currentWallpaper);
            editor.commit();
        });
        //Go to Player Button listener
        goToPlayerBtn.setOnClickListener((btn) -> {
            Intent goToPlayer = new Intent();
            goToPlayer.setClass(this, PlayerActivity.class);
            goToPlayer.putExtra("wallpaperId", wallpapers[currentWallpaper]);
            startActivity(goToPlayer);
        });

        //Load preference and initialize
        currentWallpaper = preferences.getInt("wallpaperIndex", 0);
        currentPreviewWallpaper = currentWallpaper;
        wallpaperPicker.setValue(currentPreviewWallpaper);
        refreshPreview();
    }

    private void refreshPreview(){
        wallpaperPreview.setImageResource(wallpapers[currentPreviewWallpaper]);
    }
}