package com.android.networkdemo.entity;

import java.io.Serializable;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/4/5 9:25
 */

public class Course implements Serializable{
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
}
