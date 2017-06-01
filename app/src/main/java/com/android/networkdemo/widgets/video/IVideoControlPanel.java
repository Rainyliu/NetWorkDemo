package com.android.networkdemo.widgets.video;

import android.view.View;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/6/1 13:33
 */


interface IVideoControlPanel {

    /**
     * 隐藏操作面板
     */
    public void hide();

    /**
     * 显示操作面板
     */
    public void show();

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title);

    /**
     * 获取挂断的View
     *
     * @return
     */
    public View getHangupView();

    /**
     * 获取切换的View
     *
     * @return
     */
    public View getChangeView();
}
