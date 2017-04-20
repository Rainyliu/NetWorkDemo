package com.android.networkdemo.video;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/4/19 19:47
 */

public interface IBasePlayEngine {
    public void play();
    public void pause();
    public void stop();
    public void skipTo(int time);
}
