package com.android.networkdemo.rxbus;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/4/1 10:23
 */

public class UserEvent {
    long id;
    String name;

    public UserEvent(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
