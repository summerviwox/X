package com.summer.app.model;

import java.io.Serializable;

/**
 * tangjie 2023/1/16 17:27
 **/
public class Position implements Serializable {

    public int x;
    public int y;

    public Position() {
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int getDeltaX(Position start,Position end){
        return end.x - start.x;
    }

    public static int getDeltaY(Position start,Position end){
        return end.y - start.y;
    }
}
