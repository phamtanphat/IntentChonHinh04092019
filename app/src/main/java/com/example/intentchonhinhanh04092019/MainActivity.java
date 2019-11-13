package com.example.intentchonhinhanh04092019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton imgPlay;
    ImageView imgHinhGoc,imgHinhChon;
    TextView txtThoiGian;
    FrameLayout frameLayoutPlay;
    boolean isPlay = false;
    long totalTime = 4000;
    String[] arrayNameAnimals;
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
                startGame();
            }
        });
    }
    private void mapview() {
        arrayNameAnimals = getResources().getStringArray(R.array.arrayAnimal);
    }

    private void startGame() {
        randomImageHinhGoc();
        CountDownTimer countDownTimer = new CountDownTimer(totalTime,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtThoiGian.setText("Time : " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Hết thời gian!!", Toast.LENGTH_SHORT).show();
            }
        };
        countDownTimer.start();
    }
    private void randomImageHinhGoc(){
        int hinhgoc = getResources().
                getIdentifier(
                        arrayNameAnimals[5],
                        "drawable",
                        getPackageName());
        imgHinhGoc.setImageResource(hinhgoc);
    }
}
