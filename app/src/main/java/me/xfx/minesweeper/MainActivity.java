package me.xfx.minesweeper;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button sel9, sel16, sel30, selother, phblocal;
    private static int row, col,mines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        sel9 = findViewById(R.id.sel8);
        sel16 = findViewById(R.id.sel16);
        sel30 = findViewById(R.id.sel30);
        selother = findViewById(R.id.selother);
        phblocal = findViewById(R.id.phblocal);
        sel9.setOnClickListener(this);
        sel16.setOnClickListener(this);
        sel30.setOnClickListener(this);
        selother.setOnClickListener(this);
        phblocal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean flag = false;
        switch (v.getId()) {
            case R.id.sel8:
                col = 8;
                row = 8;
                mines=10;
                flag = true;
                break;
            case R.id.sel16:
                col = 16;
                row = 16;
                mines = 40;
                flag = true;
                break;
            case R.id.sel30:
                col = 30;
                row = 30;
                mines = 120;
                flag = true;
                break;
        }
        if(flag){
            Intent i = new Intent(this,GameActivity.class);
            i.putExtra("row",row);
            i.putExtra("col",col);
            i.putExtra("mines",mines);
            startActivity(i);
            return;
        }
        if(v.getId()==R.id.selother){
            Intent i = new Intent(this,SelectAction.class);
            startActivity(i);
            return;
        }
        Intent i=null;
        switch (v.getId()) {
            case R.id.phblocal:
                i = new Intent(this,PhbActivity.class);
                i.putExtra("code",1);
                startActivity(i);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        row = data.getIntExtra("row",9);
        col = data.getIntExtra("col",9);
        mines = data.getIntExtra("mines",10);

    }
}
