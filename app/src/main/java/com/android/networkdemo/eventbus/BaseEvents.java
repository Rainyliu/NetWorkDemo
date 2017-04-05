package com.android.networkdemo.eventbus;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/3/30 19:07
 * 一个常用事件CommonEvent 实现了BaseEvents接口的两个方法。
 */

public interface BaseEvents {
    void setObject(Object obj);
    Object getObject();

    //事件定义
    enum CommonEvent implements BaseEvents{
        LOGIN,//登录
        LOGOUT;//登出
        private Object obj;
        @Override
        public void setObject(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object getObject() {
            return obj;
        }
    }

    //其他事件定义
}
