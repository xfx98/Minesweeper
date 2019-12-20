package me.xfx.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SelectAction extends AppCompatActivity {
    private EditText et_row,et_col,et_mines;
    private int row,col,mines;
    private Button beginGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        init();

        beginGame.setOnClickListener((e)->{
            try{
                row = Integer.parseInt(et_row.getText().toString());
                col = Integer.parseInt(et_col.getText().toString());
                mines = Integer.parseInt(et_mines.getText().toString());
                Intent i = new Intent(this,GameActivity.class);
                i.putExtra("row",row);
                i.putExtra("col",col);
                i.putExtra("mines",mines);
                if(mines>=row*col-1){
                    throw new RuntimeException();
                }
                startActivity(i);
                finish();
            }catch (Exception ex){
                Toast.makeText(SelectAction.this,"输入错误" ,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        et_row = findViewById(R.id.et_row);
        et_col = findViewById(R.id.et_col);
        et_mines = findViewById(R.id.et_mines);
        beginGame = findViewById(R.id.beginGame);
    }
}
