package tv.danmaku.ijk.media.example.activities;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VideoActivity11 extends AppCompatActivity {

    private static final String TAG = "VideoActivity11";

    private String mVideoPath;//网络视频地址
    private Uri mVideoUri;//本地存储的视频地址

    //自己实现的MediaController
    private AndroidMediaController mMediaController;
    //封装的videoView
    private IjkVideoView mVideoView;
    private TextView mToastTextView;

    //右边的布局
    private TableLayout mHudView;

    //根布局
    private DrawerLayout mDrawerLayout;

    //右边的帧布局
    private ViewGroup mRightDrawer;

    private ProgressBar pb;

//    private Settings mSettings;
    private boolean mBackPressed;

    public static Intent newIntent(Context context,String videoPath,String videoTitle){
        Intent intent = new Intent(context,VideoActivity11.class);
        intent.putExtra("videoPath", videoPath);
        intent.putExtra("videoTitle", videoTitle);
        return intent;
    }

    public static void intentTo(Context context, String videoPath, String videoTitle) {
        context.startActivity(newIntent(context, videoPath, videoTitle));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // handle arguments
        mVideoPath = getIntent().getStringExtra("videoPath");
        //视频播放地址
//        mVideoPath = "http://video.cdn.hd1905.com/bbaqcz-NO1.ts";

        Intent intent = getIntent();
        String intentAction = intent.getAction();

        if (!TextUtils.isEmpty(intentAction)) {
            if (intentAction.equals(Intent.ACTION_VIEW)) {
                mVideoPath = intent.getDataString();
            } else if (intentAction.equals(Intent.ACTION_SEND)) {
                mVideoUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    String scheme = mVideoUri.getScheme();
                    if (TextUtils.isEmpty(scheme)) {
                        Log.e(TAG, "Null unknown scheme\n");
                        finish();
                        return;
                    }
                    if (scheme.equals(ContentResolver.SCHEME_ANDROID_RESOURCE)) {
                        mVideoPath = mVideoUri.getPath();
                    } else if (scheme.equals(ContentResolver.SCHEME_CONTENT)) {
                        Log.e(TAG, "Can not resolve content below Android-ICS\n");
                        finish();
                        return;
                    } else {
                        Log.e(TAG, "Unknown scheme " + scheme + "\n");
                        finish();
                        return;
                    }
                }
            }
        }

        if (!TextUtils.isEmpty(mVideoPath)) {
            //最近播放存储
//            new RecentMediaStorage(this).saveUrlAsync(mVideoPath);
        }

        // init UI
        //布局上方的toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        mMediaController = new AndroidMediaController(this, false);
        mMediaController.setSupportActionBar(actionBar);

        //加载框
        pb = (ProgressBar) findViewById(R.id.pb);

        //弹出的toast文本框
        mToastTextView = (TextView) findViewById(R.id.toast_text_view);

        //右边的table布局，显示内容
        mHudView = (TableLayout) findViewById(R.id.hud_view);

        //根布局,抽屉布局
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //右边的帧布局
        mRightDrawer = (ViewGroup) findViewById(R.id.right_drawer);

        //设置透明背景问题
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);

        //initPlayer
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        //框架中封装的videoview
        mVideoView = (IjkVideoView) findViewById(R.id.video_view);
        //设置mediacontroller
        //这里的mediacontroller是自己实现的AndroidMediaController
        mVideoView.setMediaController(mMediaController);

        //设置右边显示布局内容的view
//        mVideoView.setHudView(mHudView);

        // prefer mVideoPath
        if(mVideoPath != null){
            mVideoView.setVideoPath(mVideoPath);
        }else if (mVideoUri != null)
            mVideoView.setVideoURI(mVideoUri);
        else {
            Log.e(TAG, "Null Data Source\n");
            finish();
            return;
        }

        mVideoView.start();
    }

    @Override
    public void onBackPressed() {
        mBackPressed = true;
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBackPressed || !mVideoView.isBackgroundPlayEnabled()) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        } else {
            mVideoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();

    }
}
