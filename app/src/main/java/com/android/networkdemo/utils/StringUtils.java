package com.android.networkdemo.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * 字符串工具类
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/5/18 上午10:55
 */

public class StringUtils {
    /**
     * 验证手机号是否位合法的手机号
     *
     * @param moblies
     * @return
     */
    public static boolean isMoblieNo(String moblies) {
        //[1]代表第一位为数字1，
        //[34587]代表第二位可以为3，4，5，8，7中的一个，
        // \\d{9}代表后面是可以是0～9的数字，有9位
        String telRegex = "[1][34587]\\d{9}";
        if (TextUtils.isEmpty(moblies)) {
            return false;
        } else {
            return moblies.matches(telRegex);
        }
    }

    /**
     *
     * 把毫秒值转化成00：00：00格式的字符串
     *
     * @return
     */
    public static String msToString(long time) {
        if (time == 0) {
            return "00:00:00";
        } else if (time > 0) {
            int h = (int)(time / 1000 / 60 / 60);
            int m = (int)(time / 1000 / 60 % 60);
            int s = (int)(time / 1000 %60);
//            Log.d("liuyuting", "msToString: h:m:s===="+h+":"+m+":"+s);
            String h1 = h+"";
            String m1 = m+"";
            String s1 = s+"";
            if(h < 10){
                h1 = "0"+h1;
            }
            if(m < 10){
                m1 = "0"+m1;
            }
            if(s < 10){
                s1 = "0"+s1;
            }
            return h1+":"+m1+":"+s1;

        }
        return null;
    }

    /**
     * 快进1%
     * @param duration
     * @param current
     * @return
     */
    public static int fastForward(int duration,int current){
        if(current > (duration - duration/100)){
            return duration;
        }else{
            Log.d("liuyuting", "fastForward: ====="+(current + duration/100));
            return (current + duration/100);
        }

    }

    /**
     * 快退1%
     * @param duration
     * @param current
     * @return
     */
    public static int fastBackward(int duration,int current){
        Log.d("liuyuting", "fastBackward:==duration== "+duration+";====current=="+current);
        if(current <= (duration/100)){
            return 0;
        }else {
            Log.d("liuyuting", "fastBackward: ====="+(current - duration/100));
            return (current - duration/100);
        }
    }



}
