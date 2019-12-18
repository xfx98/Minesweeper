package me.xfx.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PhbActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phb);
        ListView phb = findViewById(R.id.phb);
        Intent i = getIntent();
        int code = i.getIntExtra("code",0);
        switch (code){
            case 1:

                break;
            case 2:

                break;
        }

    }
}
