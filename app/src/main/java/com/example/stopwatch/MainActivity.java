package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    Chronometer m_chronometer;
     long pauseOffset;
     boolean running;
    private LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_chronometer = findViewById(R.id.chronometer);
        m_chronometer.setFormat("Time: %s");
        m_chronometer.setBase(SystemClock.elapsedRealtime());
        lottieAnimationView = findViewById(R.id.lottie);
        m_chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 10000) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(MainActivity.this, "Bing!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void startChronometer(View v) {
        if (!running) {
            m_chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            m_chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer(View v) {
        if (running) {
            m_chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - m_chronometer.getBase();
            running = false;
        }
    }

    public void resetChronometer(View v) {
        m_chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
    @Override
    protected void onResume() {
        super.onResume();
        lottieAnimationView.playAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        lottieAnimationView.cancelAnimation();
    }
}




