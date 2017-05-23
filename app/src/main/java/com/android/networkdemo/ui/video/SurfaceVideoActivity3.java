package com.android.networkdemo.ui.video;

import android.content.ContentResolver;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.networkdemo.R;

import java.io.File;
import java.io.IOException;

public class SurfaceVideoActivity3 extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private TextView tvSound, tvCurrentT, tvDuration;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    private Uri uri;
    private Handler handler;
    private float downX, downY;
    private int screenWidth;
    private int FACTOR = 100;
    public static String URL = "http://9890.vod.myqcloud.com/9890_9c1fa3e2aea011e59fc841df10c92278.f20.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //TODO　将屏幕设置为横屏()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // TODO 将屏幕设置为竖屏() //
//         setRequestedOrientationetRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPEATION_PORTRAIT);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        /**surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        downY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // TODO 音量
                        float distanceX = event.getX() - downX;
                        float distanceY = event.getY() - downY;
                        if (downX > screenWidth - 200
                                && Math.abs(distanceX) < 50
                                && distanceY > FACTOR) {
                            // TODO 减小音量
                            setVolume(false);
                        } else if (downX > screenWidth - 200
                                && Math.abs(distanceX) < 50
                                && distanceY < -FACTOR) {
                            // TODO 增加音量
                            setVolume(true);

                        }
                        // TODO 播放进度调节
                        if (Math.abs(distanceY) < 50 && distanceX > FACTOR) {
                            // TODO 快进
                            int currentT = mediaPlayer.getCurrentPosition();//播放的位置
                            mediaPlayer.seekTo(currentT + 15000);
                            downX = event.getX();
                            downY = event.getY();
                            Log.i("info", "distanceX快进=" + distanceX);
                        } else if (Math.abs(distanceY) < 50
                                && distanceX < -FACTOR) {
                            // TODO 快退
                            int currentT = mediaPlayer.getCurrentPosition();
                            mediaPlayer.seekTo(currentT - 15000);
                            downX = event.getX();
                            downY = event.getY();
                            Log.i("info", "distanceX=" + distanceX);
                        }
                        break;
                }
                return true;
            }
        });**/

        tvSound = (TextView) findViewById(R.id.tv_sound);
        tvCurrentT = (TextView) findViewById(R.id.tv_current);
        tvDuration = (TextView) findViewById(R.id.tv_duration);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        mediaPlayer = new MediaPlayer();

        // TODO　给videoview设置播放源(通过本地存储卡来设置) //
//        uri = Uri.fromFile(new File("/sdcard/download/video1.mp4"));
        //TODO 给viedeoview设置播放源（通过资源文件来设置），PS 在调用资源文件时，在协议头后加上如“://”
//        uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + "/" + R.raw.video1);
        // TODO 在raw下添加video1视频（）
        uri = Uri.parse(URL);
        handler = new Handler();

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // 开始播放视频
                mediaPlayer.start();
                // 设置总时长
                tvDuration.setText(mp.getDuration() / 1000 + "");
                tvCurrentT.setText(mp.getCurrentPosition() / 1000 + "");
                progressBar.setMax(mp.getDuration());
                updateView();
            }
        });

        mediaPlayer
                .setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });

    }


    /**
     * 1.AudioManager.STREAM_MUSIC 多媒体 2.AudioManager.STREAM_ALARM 闹钟
     * 3.AudioManager.STREAM_NOTIFICATION 通知 4.AudioManager.STREAM_RING 铃音
     * 5.AudioManager.STREAM_SYSTEM 系统提示音 6.AudioManager.STREAM_VOICE_CALL
     * 电话
     * <p>
     * AudioManager.FLAG_SHOW_UI:显示音量控件
     */
    private void setVolume(boolean flag) {
        // 获取音量管理器
        AudioManager manager = (AudioManager) getSystemService(AUDIO_SERVICE);
        // 获取当前音量
        int curretnV = manager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (flag) {
            curretnV++;
        } else {
            curretnV--;
        }
        manager.setStreamVolume(AudioManager.STREAM_MUSIC, curretnV, AudioManager.FLAG_SHOW_UI);
        tvSound.setVisibility(View.VISIBLE);
        tvSound.setText("音量:" + curretnV);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvSound.setVisibility(View.GONE);
            }
        }, 1000);
    }

        @Override
        public void surfaceCreated (SurfaceHolder holder)
        { // 当surfaceView被创建完成之前才能绘制画布,所以只能在此回调方法之后开始播放
            try {
                // 1.指定播放源
                mediaPlayer.setDataSource(this, uri);
                // 2.将mediaplayer和surfaceView时行绑定
                mediaPlayer.setDisplay(holder);
                // 3.准备进行异步播放(当prepareAsync被调用后会执行mediaPlayer的onPrepared回调方法)
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged (SurfaceHolder holder,int format, int width, int height){

        }

        @Override
        public void surfaceDestroyed (SurfaceHolder holder){
            try {
                mediaPlayer.stop();
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 更新播放进度的递归
         */

    private void updateView() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO 设置进度控件
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    tvCurrentT.setText("进度:" + mediaPlayer.getCurrentPosition()
                            / 1000);
                    progressBar.setProgress(mediaPlayer.getCurrentPosition());
                }
                updateView();
            }
        }, 1000);
    }
}
