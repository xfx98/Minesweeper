package me.xfx.minesweeper;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PhbActivity extends AppCompatActivity {
    private DbOpenHelper dbHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phb);
        dbHelper = new DbOpenHelper(this, this.getFilesDir().toString() + "/LocalRecord.db", 1);
        ListView phb = findViewById(R.id.phb);
        Intent i = getIntent();
        int code = i.getIntExtra("code",0);
        findViewById(R.id.phb_ok).setOnClickListener((view)->{
            finish();
        });
        switch (code){
            case 1:
                Cursor cursor = dbHelper.getReadableDatabase()
                        .rawQuery("select * from LocalRecord", null);
                inflateList(cursor);
                break;
            case 2:

                break;
        }

    }
    private void inflateList(Cursor cursor) {
        // 填充SimpleCursorAdapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                PhbActivity.this, R.layout.cell, cursor, new String[]{"name", "time","seltype"},
                new int[]{R.id.tv_Name, R.id.tv_Time,R.id.tv_Type}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        // 显示数据
        ListView lv_show = findViewById(R.id.phb);
        lv_show.setAdapter(adapter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper=null;
    }

}
