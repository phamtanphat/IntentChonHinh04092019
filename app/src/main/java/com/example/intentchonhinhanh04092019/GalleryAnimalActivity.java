package com.example.intentchonhinhanh04092019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class GalleryAnimalActivity extends AppCompatActivity {

    String[] arrayNameGalleryAnimals;
    long currentTime = -1;
    CountDownTimer countDownTimer;
    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_animal);
        init();
        mapview();

        // 18 tam hinh
//        => so dong : 6 => tabrow
//        => so cot : 3 => imageview
        for (int i = 0 ; i < 6 ; i++){
            TableRow tableRow = new TableRow(this);
            for(int y = 0 ; y < 3 ; y++){
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.bo);
                tableRow.addView(imageView);
            }
            tableLayout.addView(tableRow);
        }



    }

    private void init() {
        tableLayout = findViewById(R.id.tableLayoutContainer);

    }

    private void mapview() {
        arrayNameGalleryAnimals = getResources().getStringArray(R.array.arrayAnimal);
        Intent intent = getIntent();
        currentTime = intent.getLongExtra("currentTime", -1);
        runCountDown();
    } private void runCountDown(){
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (currentTime / 1000 <= 1){
            Toast.makeText(this, "Het thoi gian", Toast.LENGTH_SHORT).show();
        }else{
            countDownTimer = new CountDownTimer(currentTime,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    currentTime = millisUntilFinished;
                    Log.d("BBB",currentTime + "");
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


