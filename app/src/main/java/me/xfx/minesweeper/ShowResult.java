package me.xfx.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ShowResult extends AppCompatActivity {
    private DbOpenHelper dbHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DbOpenHelper(this, this.getFilesDir().toString() + "/LocalPhb.db3", 2);
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        int status = i.getIntExtra("status",2);
        long time = i.getLongExtra("time",0);
        if(status==1){
            setContentView(R.layout.activity_show_result_win);
            Button bok = findViewById(R.id.ok);
            Button bno = findViewById(R.id.no);
            TextView tv = findViewById(R.id.time);
            TextView name = findViewById(R.id.name);
            tv.setText("你的用时为："+time+"ms");
            bok.setOnClickListener((view)->{
                if(name.getText().toString()==null||name.getText().toString().trim().equals("")){
                    Toast.makeText(ShowResult.this,"名称不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                dbHelper.getReadableDatabase().execSQL("insert into LocalPhb values(null , ? , ?)",
                        new Object[]{name.getText().toString(), time});
                finish();
            });
            bno.setOnClickListener((view)->{
                if(name.getText().toString()==null||name.getText().toString().trim().equals("")){
                    Toast.makeText(ShowResult.this,"名称不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                dbHelper.getReadableDatabase().execSQL("insert into LocalPhb values(null , ? , ?)",
                        new Object[]{name.getText().toString(), time});
                finish();
            });

        }else{
            setContentView(R.layout.activity_show_result_lose);
            Button b = findViewById(R.id.ok);
            b.setOnClickListener((view)->{
                finish();
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper=null;
    }
}
