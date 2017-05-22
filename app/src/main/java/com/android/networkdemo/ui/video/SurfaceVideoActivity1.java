package com.android.networkdemo.ui.video;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.android.networkdemo.R;

public class SurfaceVideoActivity1 extends AppCompatActivity implements View.OnClickListener {
    Button btnplay, btnstop, btnpause;
    SurfaceView surfaceView;
    MediaPlayer mediaPlayer;
    int position;
    public static String URL = "http://9890.vod.myqcloud.com/9890_9c1fa3e2aea011e59fc841df10c92278.f20.mp4";
    String TAG = "liuyuting";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_video1);
        Log.d(TAG, "onCreate: ");
        btnplay=(Button)this.findViewById(R.id.btnplay);
        btnstop=(Button)this.findViewById(R.id.btnstop);
        btnpause=(Button)this.findViewById(R.id.btnpause);

        btnstop.setOnClickListener(this);
        btnplay.setOnClickListener(this);
        btnpause.setOnClickListener(this);

        mediaPlayer=new MediaPlayer();
        surfaceView=(SurfaceView) this.findViewById(R.id.surfaceView);

        //设置SurfaceView自己不管理的缓冲区
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (position>0) {
                    try {
                        //开始播放
                        play();
                        //并直接从指定位置开始播放
                        mediaPlayer.seekTo(position);
                        position=0;
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                       int height) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play://播放
                play();
                break;
            case R.id.btn_pause://暂停
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }else{
                    mediaPlayer.start();
                }
                break;
            case R.id.btnstop://停止
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                break;
        }
    }

    @Override
    protected void onPause() {
        //先判断是否正在播放
        if (mediaPlayer.isPlaying()) {
            //如果正在播放我们就先保存这个播放位置
            position=mediaPlayer.getCurrentPosition()
            ;
            mediaPlayer.stop();
        }
        super.onPause();
    }

    private void play() {
        try {
            mediaPlayer.reset();
            mediaPlayer
                    .setAudioStreamType(AudioManager.STREAM_MUSIC);
            //设置需要播放的视频
//            mediaPlayer.setDataSource("/mnt/sdcard/movie.3gp");
            mediaPlayer.setDataSource(this, Uri.parse(URL));
            //把视频画面输出到SurfaceView
            mediaPlayer.setDisplay(surfaceView.getHolder());
            mediaPlayer.prepare();
            //播放
            mediaPlayer.start();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
