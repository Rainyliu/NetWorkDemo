package com.android.networkdemo.ui.video;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.android.networkdemo.R;

public class SurfaceVideoActivity2 extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {
    private int width = 0;
    private int height = 0;
    private MediaPlayer mMediaPlayer = null;
    private SurfaceView mSurfaceView = null;
    private SurfaceHolder holder = null;
    private String path = "";
    private static String TAG = "liuyuting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_video2);
        Log.d(TAG, "onCreate: ");

        mSurfaceView = (SurfaceView) this.findViewById(R.id.surfaceVideo_surfaceView);
        holder = mSurfaceView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);// 设置风格
    }

    public void playVedio() {
        try {
            Log.d(TAG, "playVedio: ");
            path = android.os.Environment.getExternalStorageDirectory() + "/moto_0012.3gp";
            mMediaPlayer = new MediaPlayer();
            // mMediaPlayer.setDataSource(path);

            String url = "http://9890.vod.myqcloud.com/9890_9c1fa3e2aea011e59fc841df10c92278.f20.mp4";
//            String url = "http://player.youku.com/player.php/sid/XNDYwOTEzNzQ4/v.swf";

            mMediaPlayer.setDataSource(this, Uri.parse(url));
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setDisplay(holder);
            mMediaPlayer.prepare();// 准备
            Log.e(TAG, mMediaPlayer.getDuration() + "");
            mMediaPlayer.setOnBufferingUpdateListener(this);
            mMediaPlayer.setOnCompletionListener(this);
            mMediaPlayer.setOnPreparedListener(this);
        } catch (Exception ex) {

        }
    }

    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        // TODO Auto-generated method stub
        Log.i(TAG, percent + "|" + mMediaPlayer.getCurrentPosition());
    }

    public void onCompletion(MediaPlayer mp)
    {
        // TODO Auto-generated method stub
        Log.i(TAG, "Completion");
    }

    public void onPrepared(MediaPlayer mp)
    {
        Log.d(TAG, "onPrepared: ");
        // TODO Auto-generated method stub
        width = mMediaPlayer.getVideoWidth();
        height = mMediaPlayer.getVideoHeight();
        if (width != 0 && height != 0)
        {
            holder.setFixedSize(width, height);// 设置视频高宽
            mMediaPlayer.start();
            Log.i(TAG, mMediaPlayer.getDuration() + "");
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated: ");
        playVedio();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged: ");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        if (mMediaPlayer != null)
        {
            if (mMediaPlayer.isPlaying())
            {
                mMediaPlayer.stop();
            }
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
