package com.example.intentchonhinhanh04092019;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class GalleryAnimalActivity extends AppCompatActivity {

    String[] arrayNameGalleryAnimals;
    long currentTime = -1;
    CountDownTimer countDownTimer;
    TableLayout tableLayout;
    int count = 0;
    String nameImage = "";
    int idHinh = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_animal);
        init();
        mapview();

        // 18 tam hinh
//        => so dong : 6 => tabrow
//        => so cot : 3 => imageview
//        int vitri = cot * sodonghientai + y(Cthuc tinh vi theo dong va cot)
        for (int i = 0 ; i < 6 ; i++){
            TableRow tableRow = new TableRow(this);
            for(int y = 0 ; y < 3 ; y++){
                final ImageView imageView = new ImageView(this);
                // xac dinh id : 0 -17 : done
                // lay ten theo id de tra ve file hinh
                nameImage = arrayNameGalleryAnimals[count++];
                idHinh = getResources().getIdentifier(nameImage,"drawable",getPackageName());
                // truyen file hinh vao image
                imageView.setImageResource(idHinh);
                imageView.setTag(idHinh);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("idhinhchon",Integer.parseInt(v.getTag().toString()));
                        intent.putExtra("time",currentTime);
                        setResult(RESULT_OK);
                        finish();
                    }
                });
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
            setResult(RESULT_CANCELED);
            finish();
            Toast.makeText(this, "Het thoi gian", Toast.LENGTH_SHORT).show();
        }else{
            countDownTimer = new CountDownTimer(currentTime,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    currentTime = millisUntilFinished;
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


