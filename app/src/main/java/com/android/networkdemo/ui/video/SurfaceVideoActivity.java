package com.android.networkdemo.ui.video;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.networkdemo.R;
import com.android.networkdemo.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SurfaceVideoActivity extends AppCompatActivity {
    private TextView startTime, allTime,controllerUP;
    private SeekBar seekBar;
    private ProgressBar pb;
    private SurfaceView surfaceView;
    private LinearLayout controller;
    public static String TAG = "liuyuting";
    private SurfaceHolder holder;
    private MediaPlayer player;
    public static String URL = "http://9890.vod.myqcloud.com/9890_9c1fa3e2aea011e59fc841df10c92278.f20.mp4";
    private int currentPosition = 0;
    private int duration;
    private boolean isPlaying;
    private int width,height;
    private TextView bufferTV;
    private long lastTotalRxBytes = 0;
    private long lastTimeStamp = 0;
    private Animation animation;
    private Animation animation1;
    private Animation animationUP;
    private Animation animationUP1;
    private boolean isShow = true;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100){
                String current = (String) msg.obj;
//                Log.d(TAG, "handleMessage: 当前网速："+current);
                bufferTV.setText("当前网速："+current);
            }
        }
    };

    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        // SurfaceHolder被修改的时候回调
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.d(TAG, "surfaceCreated: 被创建");
            if (currentPosition >= 0) {
                // 创建SurfaceHolder的时候，如果存在上次播放的位置，则按照上次播放位置进行播放
                try {
                    //开始播放
                    start(currentPosition);
                    //并直接从指定位置开始播放
                    player.seekTo(currentPosition);
                    currentPosition = 0;
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Log.d(TAG, "surfaceChanged: 发生改变"+width+"===="+height);
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.d(TAG, "SurfaceHolder 被销毁");
            // 销毁SurfaceHolder的时候记录当前的播放位置并停止播放
            if(player != null && player.isPlaying()){
                currentPosition = player.getCurrentPosition();
                player.stop();

            }
        }
    };

    //监听进度条
    private SeekBar.OnSeekBarChangeListener change = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//            Log.d(TAG, "onProgressChanged: 进度条改变了"+"===progress=="+progress+"====fromUser==="+fromUser);
//            startTime.setText(progress+"毫秒");
            startTime.setText(StringUtils.msToString(progress));

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.d(TAG, "onStartTrackingTouch: 开始改变");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d(TAG, "onStopTrackingTouch: 结束改变");
            // 当进度条停止修改的时候触发
            // 取得当前进度条的刻度
            int progress = seekBar.getProgress();
            if(player != null && player.isPlaying()){
                player.seekTo(progress);//设置当前的播放位置
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_video);
        Log.d(TAG, "onCreate: ");
        lastTotalRxBytes = getTotalRxBytes();
        lastTimeStamp = System.currentTimeMillis();
        new Timer().schedule(task, 1000, 2000); // 1s后启动任务，每2s执行一次
        initView();
//        surfaceView.requestFocus();
        initSurface();
    }

    /**
     * 设置surfaceview相关
     */
    private void initSurface() {
        Log.d(TAG, "initSurface: ");
        holder = surfaceView.getHolder();

        //为surfaceHolder添加回调
        holder.addCallback(callback);
        Log.d(TAG, "initSurface: 添加完回调后");
        // 4.0版本之下需要设置的属性
        // 设置Surface不维护自己的缓冲区，而是等待屏幕的渲染引擎将内容推送到界面
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        Log.d(TAG, "initSurface: 设置了type");
        //设置播放时打开屏幕
        holder.setKeepScreenOn(true);
        Log.d(TAG, "initSurface: 让屏幕亮着");

       

        // 为进度条添加进度更改事件
        seekBar.setOnSeekBarChangeListener(change);
        Log.d(TAG, "initSurface: 为进度条添加回调");
    }

    /**
     * 初始化view
     */
    private void initView() {
        Log.d(TAG, "initView: ");
        startTime = (TextView) findViewById(R.id.startTime);
        allTime = (TextView) findViewById(R.id.allTime);
        controllerUP = (TextView) findViewById(R.id.controllerUP);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        pb = (ProgressBar) findViewById(R.id.pb);
        bufferTV = (TextView) findViewById(R.id.bufferTV);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        controller = (LinearLayout) findViewById(R.id.controller);
        animation = AnimationUtils.loadAnimation(this,R.anim.push_bottom_out);
        animation1 = AnimationUtils.loadAnimation(this,R.anim.push_bottom_in);
        animationUP = AnimationUtils.loadAnimation(this,R.anim.push_bottom_out1);
        animationUP1 = AnimationUtils.loadAnimation(this,R.anim.push_bottom_in1);

//        player = new MediaPlayer();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: keycode=====" + keyCode);
//        if(player != null){
//            if(!player.isPlaying()){
//                pb.setVisibility(View.VISIBLE);
//                bufferTV.setVisibility(View.VISIBLE);
//            }else {
//                pb.setVisibility(View.GONE);
//                bufferTV.setVisibility(View.GONE);
//            }
//        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {//电视遥控器是23，电脑键盘是66
            if(isShow){
                isShow = false;
                controller.startAnimation(animation1);
                controllerUP.startAnimation(animationUP1);
            }else {
                isShow = true;
                controller.startAnimation(animation);
                controllerUP.startAnimation(animationUP);
            }
            if(player != null && player.isPlaying()){
                player.pause();

            }else {
                player.start();

            }

            Log.d(TAG, "onKeyDown: 按下确认键");
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            player.seekTo(StringUtils.fastBackward(player.getDuration(),player.getCurrentPosition()));
            Log.d(TAG, "onKeyDown: 按下左键");//21
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            player.seekTo(StringUtils.fastForward(player.getDuration(),player.getCurrentPosition()));
            Log.d(TAG, "onKeyDown: 按下右键");//22
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {//4
            Log.d(TAG, "onKeyDown: 按下返回键");//4
            stop();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            Log.d(TAG, "onKeyDown: 按下上键");//19
            resume();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            Log.d(TAG, "onKeyDown: 按下下键");//20
            pause();
            return true;
        }else if (keyCode == KeyEvent.KEYCODE_MENU) {
            Log.d(TAG, "onKeyDown: 按下菜单键");
            replay();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyLongPress: ---------------------------------------------");
        return true;
//        return super.onKeyLongPress(keyCode, event);
    }

    /**
     * 继续播放
     */
    protected void resume() {
        if (player != null && !player.isPlaying()) {
            player.start();
        }
    }

    /**
     * 暂停播放
     */
    protected void pause() {
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }

    /**
     * 重新播放
     */
    protected void replay() {
        if (player != null && player.isPlaying()) {
            player.seekTo(0);
            return;
        }
        isPlaying = false;
        start(0);

    }

    /**
     * 停止播放
     */
    protected void stop() {
        player.stop();
        player.release();
        player = null;
        isPlaying = false;
    }

    /**
     * 开始播放
     *
     * @param seek 播放的初始位置
     */
    protected void start(int seek) {
        Log.d(TAG, "start: ");
        //获取视频文件的地址

        /**
         * 播放在线视频
         */
//        Uri uri = Uri.parse(URL);
//        if (uri == null) {
//            Toast.makeText(this, "播放地址有误", Toast.LENGTH_SHORT).show();
//            return;
//        }

        try {
            player = new MediaPlayer();
//            player.reset();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);

            //设置播放的视频源
            player.setDataSource(this,Uri.parse(URL));

            //设置显示视频的holder
            player.setDisplay(holder);

            Log.d(TAG, "start: 开始装载");
            //开始装载
//            player.prepareAsync();
            player.prepare();
//            player.start();
            Log.e(TAG, "duration====="+player.getDuration() + "");

            //设置播放监听回调
            setPlayerListener();
        } catch (Exception e) {
            e.printStackTrace();
        }


        /**
         * 播放本地的视频
         */
//        String path = URL.getText().toString().trim();
//        File file = new File(path);
//        if (!file.exists()) {
//            Toast.makeText(this, "视频文件路径错误", 0).show();
//            return;
//        }


    }

    /**
     * mediaPlayer回调函数
     */
    private void setPlayerListener() {
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, "onPrepared: 装载完成");
                Log.d(TAG, "onPrepared: ");
                // TODO Auto-generated method stub
                width = player.getVideoWidth();
                height = player.getVideoHeight();
                if (width != 0 && height != 0)
                {
                    holder.setFixedSize(width, height);// 设置视频高宽
                    player.start();
                    Log.i(TAG, player.getDuration() + "");
                }
                currentPosition = player.getCurrentPosition();
                duration = player.getDuration();
                startTime.setText(StringUtils.msToString(currentPosition));
                allTime.setText(StringUtils.msToString(duration));
                //按照初始位置播放
                player.seekTo(currentPosition);
                // 设置进度条的最大进度为视频流的最大播放时长
                seekBar.setMax(player.getDuration());

                // 开始线程，更新进度条的刻度
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            isPlaying = true;
                            while (isPlaying) {
                                int current = player.getCurrentPosition();
                                seekBar.setProgress(current);

                                sleep(500);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
//                Toast.makeText(SurfaceVideoActivity.this,"缓冲进度==="+percent,Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onBufferingUpdate: 当前缓冲进度==="+percent);
                if(percent >= 15){
                    pb.setVisibility(View.GONE);
                    bufferTV.setVisibility(View.GONE);
                }
            }
        });

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "onCompletion: 播放完毕");
                Toast.makeText(SurfaceVideoActivity.this,"播放完毕",Toast.LENGTH_SHORT).show();
            }
        });

        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d(TAG, "onError: 发生错误");
                //发生错误
                isPlaying = false;
                return false;
            }
        });

        player.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                Log.d(TAG, "onInfo: 视频的相关信息");//不知道何时会调用
                return false;
            }
        });

        player.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                Log.d(TAG, "onVideoSizeChanged: 屏幕大小改变");
            }
        });

        player.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                Log.d(TAG, "onSeekComplete: 快进完成");
            }
        });
    }


    /**
     * 测当前网络速度的
     */
    private void showNetSpeed() {

        long nowTotalRxBytes = getTotalRxBytes();
        long nowTimeStamp = System.currentTimeMillis();
        long speed = ((nowTotalRxBytes - lastTotalRxBytes) * 1000 / (nowTimeStamp - lastTimeStamp));//毫秒转换

        lastTimeStamp = nowTimeStamp;
        lastTotalRxBytes = nowTotalRxBytes;

        Message msg = handler.obtainMessage();
        msg.what = 100;
        msg.obj = String.valueOf(speed) + " kb/s";

        handler.sendMessage(msg);//更新界面
    }

    private long getTotalRxBytes() {
        return TrafficStats.getUidRxBytes(getApplicationInfo().uid)==TrafficStats.UNSUPPORTED ? 0 :(TrafficStats.getTotalRxBytes()/1024);//转为KB
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            showNetSpeed();
        }
    };
}
