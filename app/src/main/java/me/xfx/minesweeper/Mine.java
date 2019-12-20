package me.xfx.minesweeper;

import java.util.Objects;

public class Mine {
    int x;
    int y;
    int mineNumber;
    boolean isOpened;
    boolean isMine;
    int status;
    boolean wa;


    public Mine() {
    }

    public Mine(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Mine(int x, int y, int mineNumber) {
        this.x = x;
        this.y = y;
        this.mineNumber = mineNumber;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mine mine = (Mine) o;
        return x == mine.x &&
                y == mine.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
