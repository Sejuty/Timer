package com.nishat00.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    CountDownTimer count;
    SeekBar seekBar;
    boolean active = false;

    public void reset(){
        active = false;
        button.setText("Start");
        seekBar.setEnabled(true);
        seekBar.setProgress(300);
        textView.setText("5:00");
        count.cancel();

    }

    public void start(View v) {

        if (active) {
          reset();

        } else {
            button.setText("STOP!!");
            seekBar.setEnabled(false);
            active = true;
            count = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {

                @Override
                public void onTick(long l) {
                    updateSeekBar((int) l / 1000);
                }

                @Override
                public void onFinish() {
//                    textView.setText("FINISHED!");
//                    MediaPlayer audio = MediaPlayer.create(MainActivity.this, R.raw.hoho);
//                    audio.start();
                    reset();
                }
            }.start();
        }
    }

    public void updateSeekBar(int progress) {
        int minutes = progress / 60;
        int seconds = progress - (minutes * 60);

        String secondText = Integer.toString(seconds);
        if (seconds <= 9)
            secondText = "0" + seconds;

        textView.setText(Integer.toString(minutes) + " : " + secondText);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.seconds);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(300);
        textView.setText("5:00");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateSeekBar(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}