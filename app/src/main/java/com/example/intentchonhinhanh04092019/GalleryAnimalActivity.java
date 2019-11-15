package com.example.intentchonhinhanh04092019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class GalleryAnimalActivity extends AppCompatActivity {

    String[] arrayNameGalleryAnimals;
    long currentTime = -1;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_animal);
        init();
        mapview();
    }

    private void init() {

    }

    private void mapview() {
        arrayNameGalleryAnimals = getResources().getStringArray(R.array.arrayAnimal);
        Intent intent = getIntent();
        currentTime = intent.getLongExtra("currentTime", -1);
        runCountDown();
    }
    private void runCountDown(){
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (currentTime / 1000 <= 1){
            Toast.makeText(this, "Het thoi gian", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            countDownTimer = new CountDownTimer(currentTime,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    currentTime = millisUntilFinished ;
                    Log.d("BBB","Lon hon 1 " + currentTime + "");
                }
                @Override
                public void onFinish() {
                    Toast.makeText(GalleryAnimalActivity.this, "Hết thời gian!!", Toast.LENGTH_SHORT).show();
                }
            };
            countDownTimer.start();
        }
    }

}