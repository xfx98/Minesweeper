package me.xfx.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private int row,col,mines;
    private TextView symines,time;
    private ImageButton btn_use;
    private GridLayout game_area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();


    }

    private void init() {
        Intent i = getIntent();
        row = i.getIntExtra("row",9);
        col = i.getIntExtra("col",9);
        mines = i.getIntExtra("mines",10);
        time = findViewById(R.id.time);
        symines = findViewById(R.id.symines);
        btn_use = findViewById(R.id.btn_use);
        game_area = findViewById(R.id.game_area);
        game_area.setRowCount(row);
        game_area.setColumnCount(col);
        for(int m = 0;m<row;m++){
            for(int n = 0;n<col;n++){
                ImageButton ib = new ImageButton(this);


                game_area.addView(ib);
            }
        }
    }
}
