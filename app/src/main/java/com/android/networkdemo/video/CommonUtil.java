package com.android.networkdemo.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.TrafficStats;

import com.android.networkdemo.utils.GlobalConsts;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/4/19 19:54
 */

public class CommonUtil {
    public static class ViewSize {
        public int width = 0;
        public int height = 0;
    }

    public static ViewSize getTextureSize(boolean flag, MediaPlayer m, int iconWidth){
        ViewSize v = new ViewSize();
        int width = m.getVideoWidth();
        int height = m.getVideoHeight();
        int width2 = GlobalConsts.screenWidth;
//		int height2 = GlobalConsts.screenHeigh;
        if(flag){
            v.width = width2;
            float r = 0;
            try{
                r = width2 * 1f / width * 1f;
            }catch(Exception e){
                e.printStackTrace();
            }
            v.height = (int) (height * r);
            if(v.height == 0)
                v.height = GlobalConsts.screenHeigh;
        }else{
            v.width = iconWidth;
            float r = 0;
            try{
                r = iconWidth * 1f/width * 1f;
            }catch(Exception e){
                e.printStackTrace();
            }
            v.height = (int) (height * r);
        }
        return v;
    }

    public static ViewSize getFitSize(Context context, int videoWidth,
                                      int videoHeight) {
        // int videoWidth = mediaPlayer.getVideoWidth();
        // int videoHeight = mediaPlayer.getVideoHeight();
        double fit1 = videoWidth * 1.0 / videoHeight;

        int width2 = GlobalConsts.screenWidth;
        int height2 = GlobalConsts.screenHeigh;
        double fit2 = width2 * 1.0 / height2;

        double fit = 1;
        if (fit1 > fit2) {
            fit = width2 * 1.0 / videoWidth;
        } else {
            fit = height2 * 1.0 / videoHeight;
        }

        ViewSize viewSize = new ViewSize();
        viewSize.width = (int) (fit * videoWidth);
        viewSize.height = (int) (fit * videoHeight);

        return viewSize;
    }

    private static long m_lSysNetworkSpeedLastTs = 0;
    private static long m_lSystNetworkLastBytes = 0;
    private static float m_fSysNetowrkLastSpeed = 0.0f;

    public static float getSysNetworkDownloadSpeed() {
        long nowMS = System.currentTimeMillis();
        long nowBytes = TrafficStats.getTotalRxBytes();

        long timeinterval = nowMS - m_lSysNetworkSpeedLastTs;
        long bytes = nowBytes - m_lSystNetworkLastBytes;

        if (timeinterval > 0)
            m_fSysNetowrkLastSpeed = (float) bytes * 1.0f / (float) timeinterval;
        m_lSysNetworkSpeedLastTs = nowMS;
        m_lSystNetworkLastBytes = nowBytes;
        return m_fSysNetowrkLastSpeed;
    }

    public static String formateTime(long millis) {
        String str = "";
        int hour = 0;
        int time = (int) (millis / 1000);
        int second = time % 60;
        int minute = time / 60;
        hour = minute / 60;
        minute %= 60;
        str = String.format("%02d:%02d:%02d", hour, minute, second);
        return str;
    }

    private void test() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

            }
        });
        t.start();
        t.stop();
        t.yield();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
