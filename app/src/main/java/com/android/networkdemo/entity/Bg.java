package com.android.networkdemo.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/5/12 13:25
 */

public class Bg implements Serializable{
    private int id;
    private Bitmap bm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    @Override
    public String toString() {
        return "Bg{" +
                "id=" + id +
                ", bm=" + bm +
                '}';
    }
}
