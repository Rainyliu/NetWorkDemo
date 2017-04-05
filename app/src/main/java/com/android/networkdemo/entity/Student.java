package com.android.networkdemo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/4/5 9:17
 */

public class Student implements Serializable{
    private String name;
    private List<Course> courses;

//    public Student(String name) {
//        this.name = name;
//    }

    public Student(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", courses=" + courses +
                '}';
    }
}
