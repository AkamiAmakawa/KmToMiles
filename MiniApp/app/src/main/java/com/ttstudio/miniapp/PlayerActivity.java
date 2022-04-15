package com.ttstudio.miniapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PlayerActivity extends AppCompatActivity {
    MediaPlayer player;
    Button playBtn;
    Button pauseBtn;
    ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        //Assign View elements
        playBtn = findViewById(R.id.playBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        layout = findViewById(R.id.player_main_layout);
        Intent caller = getIntent();
        //Initialize
        layout.setBackgroundResource(caller.getIntExtra("wallpaperId", R.drawable.wallpaper1));
        player = MediaPlayer.create(this, R.raw.music);
        //Assign Button
        playBtn.setOnClickListener((btn) -> player.start());
        pauseBtn.setOnClickListener((btn) -> {
            if(player.isPlaying())
            {
                player.pause();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}