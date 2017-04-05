package com.android.networkdemo.entity;

import java.io.Serializable;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/3/29 11:44
 */

public class LoginResult<T> implements Serializable {
    private int code;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
