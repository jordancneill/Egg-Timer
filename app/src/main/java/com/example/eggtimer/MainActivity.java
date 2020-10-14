package com.example.eggtimer;

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

    TextView timeTextView;
    SeekBar eggSeekBar;
    boolean countdownActive = false;
    Button startButton;
    CountDownTimer countDownTimer;

    public void resetTimer() {

        timeTextView.setText("0:30");
        eggSeekBar.setProgress(30);
        eggSeekBar.setEnabled(true);
        countDownTimer.cancel();
        startButton.setText("Start");
        countdownActive = false;

    }

    public void startTimer(View view) {

        if (countdownActive) {

            resetTimer();

        } else {

            countdownActive = true;
            eggSeekBar.setEnabled(false);
            startButton.setText("Stop");

            countDownTimer = new CountDownTimer(eggSeekBar.getProgress() * 1000 + 200, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int secondsRemaining) {
        int minutes = secondsRemaining / 60;
        int seconds = secondsRemaining - (minutes * 60);

        String secondsString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondsString = "0" + secondsString;
        }

        timeTextView.setText(Integer.toString(minutes) + ":" + (secondsString));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eggSeekBar = findViewById(R.id.eggSeekBar);
        timeTextView = findViewById(R.id.timeTextView);
        startButton = findViewById(R.id.startButton);

        eggSeekBar.setMax(1200);
        eggSeekBar.setProgress(30);

        eggSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
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