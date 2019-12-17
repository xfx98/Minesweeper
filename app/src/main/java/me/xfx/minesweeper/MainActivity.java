package me.xfx.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    private Button sel9,sel16,sel30,selother,phblocal,phblink;
    private static int row,col;
    private Intent i = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sel9 = findViewById(R.id.sel9);
        sel16 = findViewById(R.id.sel16);
        sel30 = findViewById(R.id.sel30);
        selother = findViewById(R.id.selother);
        phblocal = findViewById(R.id.phblocal);
        phblocal = findViewById(R.id.phblink);

        sel9.setOnClickListener((v)-> {
            row = 9;
            col = 9;
        });

    }
}
