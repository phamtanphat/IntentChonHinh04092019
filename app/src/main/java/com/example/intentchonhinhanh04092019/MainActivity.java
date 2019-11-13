package com.example.intentchonhinhanh04092019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton imgPlay;
    ImageView imgHinhGoc,imgHinhChon;
    TextView txtThoiGian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgPlay = findViewById(R.id.imagebuttonPlay);
        imgHinhGoc = findViewById(R.id.imageviewHinhGoc);
        imgHinhChon = findViewById(R.id.imageviewHinhChon);
        txtThoiGian = findViewById(R.id.textviewThoiGian);
    }
}
