package me.xfx.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button sel9,sel16,sel30,selother,phblocal,phblink;
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


    }
}
