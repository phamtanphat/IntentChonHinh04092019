package com.example.intentchonhinhanh04092019;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton imgPlay;
    ImageView imgHinhGoc, imgHinhChon;
    TextView txtThoiGian;
    FrameLayout frameLayoutPlay;
    boolean isPlay = false;
    long totalTime = 6000;
    String[] arrayNameAnimals;
    int hinhgoc = 0;
    int Request_Code_Animal = 123;
    long currentTime = 0;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgPlay = findViewById(R.id.imagebuttonPlay);
        imgHinhGoc = findViewById(R.id.imageviewHinhGoc);
        imgHinhChon = findViewById(R.id.imageviewHinhChon);
        txtThoiGian = findViewById(R.id.textviewThoiGian);
        frameLayoutPlay = findViewById(R.id.framelayout);

        // Khi click play sẽ bắt đầu chơi
        // Tạo ra 1 hình ảnh random trong khoảng thời gian cho phép bên góc trái
        // Nếu hết giờ chưa chọn đúng trừ điểm
        // Hết điểm thông báo chơi lại hay thoát app

        // Khi click play
//            + Tắt giao diện play
//            + Chạy radom cho hình ảnh và chạy bộ đếm thời gian
        mapview();
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPlay = true;
                frameLayoutPlay.setVisibility(View.GONE);
                startGame(0);
            }
        });
        imgHinhChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GalleryAnimalActivity.class);
                intent.putExtra("currentTime", currentTime);
                countDownTimer.cancel();
                countDownTimer = null;
                startActivityForResult(intent, Request_Code_Animal);
            }
        });
    }

    private void mapview() {
        arrayNameAnimals = getResources().getStringArray(R.array.arrayAnimal);
    }

    private void startGame(long totalTime) {
        randomImageHinhGoc();
        if (totalTime == 0){
            runCountDown(0, 0);
        }else{
            runCountDown(totalTime, 0);
        }
    }

    private void runCountDown(long time, int position) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (position == 0) {
            countDownTimer = new CountDownTimer(totalTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    currentTime = millisUntilFinished;
                    txtThoiGian.setText("Time : " + currentTime / 1000);
                }

                @Override
                public void onFinish() {
                    Toast.makeText(MainActivity.this, "Hết thời gian!!", Toast.LENGTH_SHORT).show();
                }
            };
            countDownTimer.start();

        } else {
            if (time <= 1) {
                Toast.makeText(this, "Het thoi gian", Toast.LENGTH_SHORT).show();
                txtThoiGian.setText("Time : " + 1);
            } else {
                countDownTimer = new CountDownTimer(time, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        currentTime = millisUntilFinished;
                        txtThoiGian.setText("Time : " + currentTime / 1000);
                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this, "Hết thời gian!!", Toast.LENGTH_SHORT).show();
                    }
                };
                countDownTimer.start();
            }
        }


    }

    private void randomImageHinhGoc() {
        //Thay doi tat ca vi tri trong mang
//        Collections.shuffle(Arrays.asList(arrayNameAnimals));
        hinhgoc = getResources().
                getIdentifier(
                        arrayNameAnimals[new Random().nextInt(arrayNameAnimals.length)],
                        "drawable",
                        getPackageName());
        imgHinhGoc.setImageResource(hinhgoc);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Request_Code_Animal && resultCode == RESULT_OK && data != null){
            int idHinhChon = data.getIntExtra("idhinhchon",-1);
            imgHinhChon.setImageResource(idHinhChon);
            currentTime = data.getLongExtra("time",-1);
            runCountDown(currentTime,1);
            if (idHinhChon == hinhgoc){
                Toast.makeText(this, "Chinh xac", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startGame(totalTime);
                    }
                },1000);
            }else{
                Toast.makeText(this, "Sai roi", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == Request_Code_Animal && resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Sai roi", Toast.LENGTH_SHORT).show();
        }

    }
}
