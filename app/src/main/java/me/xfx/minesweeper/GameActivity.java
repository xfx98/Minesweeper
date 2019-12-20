package me.xfx.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private int row, col, mines, nowOpen, nowMines;
    private long beginTime, nowTime;
    private TextView symines, time;
    private boolean sweeper = true;
    private ImageButton btn_use;
    private GridLayout game_area;
    static Handler myHandler;
    private int status[] = {R.drawable.mine_btn_selector, R.drawable.mine_btn_selector3, R.drawable.mine_btn_selector2};
    private int mineNum[] = {R.drawable.t0, R.drawable.t1, R.drawable.t2
            , R.drawable.t3, R.drawable.t4, R.drawable.t5,
            R.drawable.t6, R.drawable.t7, R.drawable.t8};
    private Mine[][] allMines;
    private boolean over;
    private int wz[][] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    AppCompatButton acbs[][];
    private static boolean isExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();

        myHandler = new Handler() {

            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        super.handleMessage(msg);
                        String s = msg.getData().getString("time");
                        time.setText(s);
                        time.invalidate();
                        break;
                    case 2:
                        break;
                    case 3:
                        drawOver();
                        break;

                }

                super.handleMessage(msg);
                String s = msg.getData().getString("time");
                time.setText(s);
                time.invalidate();
            }
        };

        symines.setText("" + mines);
        beginTime = System.currentTimeMillis();
        Thread t = new Thread(
                () -> {
                    while (!over) {
                        nowTime = System.currentTimeMillis();
                        String s = ("" + ((nowTime - beginTime) / 60 / 1000) + ":" + (((nowTime - beginTime) / 1000) % 60));
                        Message m = myHandler.obtainMessage();
                        m.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("time", s);
                        m.setData(bundle);
                        m.sendToTarget();
                        if(isExit){
                            return;
                        }
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                    }
                    Message m = myHandler.obtainMessage();
                    m.what = 3;
                    m.sendToTarget();
                    Intent i = new Intent(GameActivity.this,ShowResult.class);
                    if (nowOpen == row * col - mines) {
                        i.putExtra("status",1);
                    } else {
                        i.putExtra("status",2);
                    }
                    i.putExtra("time",nowTime - beginTime);
                    startActivity(i);
                }
        );
        t.start();

    }

    private void drawOver() {
        for (int m = 0; m < row; m++) {
            for (int n = 0; n < col; n++) {
                if (allMines[m][n].isOpened) {
                    continue;
                } else if (allMines[m][n].isMine) {
                    if (allMines[m][n].wa) {
                        acbs[m][n].setBackground(getDrawable(R.drawable.t11));
                    } else if (allMines[m][n].status == 1) {
                        acbs[m][n].setBackground(getDrawable(R.drawable.t10));
                    } else if (allMines[m][n].status == 0) {
                        acbs[m][n].setBackground(getDrawable(R.drawable.t12));
                    }
                } else {
                    acbs[m][n].setBackground(getDrawable(mineNum[allMines[m][n].mineNumber]));
                }
            }
        }
        String s = ("" + ((nowTime - beginTime) / 60 / 1000) + ":" + (((nowTime - beginTime) / 1000) % 60));
        Message m = myHandler.obtainMessage();
        m.what = 1;
        Bundle bundle = new Bundle();
        bundle.putString("time", s);
        m.setData(bundle);
        m.sendToTarget();
    }

    private void init() {
        Intent i = getIntent();
        row = i.getIntExtra("row", 9);
        col = i.getIntExtra("col", 9);
        mines = i.getIntExtra("mines", 10);
        nowMines = mines;
        nowOpen = 0;
        isExit = false;
        time = findViewById(R.id.time);
        symines = findViewById(R.id.symines);
        btn_use = findViewById(R.id.btn_use);
        game_area = findViewById(R.id.game_area);
        game_area.setRowCount(row);
        game_area.setColumnCount(col);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((int) (this.getResources().getDisplayMetrics().density * 20 + 0.5f), (int) (this.getResources().getDisplayMetrics().density * 20 + 0.5f));
        btn_use.setOnClickListener((e) -> {
            if (sweeper) {
                sweeper = false;
                btn_use.setBackground(getDrawable(R.drawable.qizi));
            } else {
                sweeper = true;
                btn_use.setBackground(getDrawable(R.drawable.chan));
            }
        });
        allMines = new Mine[row][col];
        acbs = new AppCompatButton[row][col];
        for (int m = 0; m < row; m++) {
            for (int n = 0; n < col; n++) {
                allMines[m][n] = new Mine(m, n);
                AppCompatButton acb = new AppCompatButton(this);
                acbs[m][n] = acb;
                acb.setLayoutParams(layoutParams);
                acb.setBackgroundDrawable(getDrawable(status[allMines[m][n].status]));
                acb.setText(m + " " + n);
                acb.setOnClickListener(this);
                game_area.addView(acb);
            }
        }
        HashSet<Mine> hs = new HashSet<>();
        while (hs.size() < mines) {
            Random rd = new Random();
            int a = rd.nextInt(row);
            int b = rd.nextInt(col);
            hs.add(allMines[a][b]);
            allMines[a][b].isMine = true;
        }

        for (int m = 0; m < row; m++) {
            for (int n = 0; n < col; n++) {
                for (int h = 0; h < wz.length; h++) {
                    if (m + wz[h][0] >= 0 && m + wz[h][0] < row && n + wz[h][1] >= 0 && n + wz[h][1] < col) {
                        if (allMines[m + wz[h][0]][n + wz[h][1]].isMine) {
                            allMines[m][n].mineNumber++;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isExit = true;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof AppCompatButton) {
            String s[] = ((AppCompatButton) v).getText().toString().split(" ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            if (sweeper) {
                if (allMines[x][y].isMine) {
                    over = true;
                    allMines[x][y].wa = true;
                    Message m = myHandler.obtainMessage();
                    m.what = 2;
                    m.sendToTarget();
                } else if (allMines[x][y].status != 1) {
                    Queue<Mine> q = new LinkedList<>();
                    q.add(allMines[x][y]);
                    allMines[x][y].isOpened = true;
                    while (!q.isEmpty()) {
                        Mine m = q.poll();
                        nowOpen++;
                        acbs[m.x][m.y].setBackground(getDrawable(mineNum[m.mineNumber]));
                        acbs[m.x][m.y].setClickable(false);
                        if (m.mineNumber == 0) {
                            for (int h = 0; h < wz.length; h++) {
                                if (m.x + wz[h][0] >= 0 && m.x + wz[h][0] < row && m.y + wz[h][1] >= 0 && m.y + wz[h][1] < col) {
                                    if (!allMines[m.x + wz[h][0]][m.y + wz[h][1]].isOpened) {
                                        q.add(allMines[m.x + wz[h][0]][m.y + wz[h][1]]);
                                        allMines[m.x + wz[h][0]][m.y + wz[h][1]].isOpened = true;
                                    }
                                }
                            }
                        }
                    }
                    if (nowOpen == row * col - mines) {
                        over = true;
                    }
                }
            } else {
                allMines[x][y].status = (allMines[x][y].status + 1) % 3;
                if (allMines[x][y].status == 1) {
                    nowMines--;
                } else if (allMines[x][y].status == 2) {
                    nowMines++;
                }
                ((AppCompatButton) v).setBackgroundDrawable(getDrawable(status[allMines[x][y].status]));
            }
        }
    }
}
