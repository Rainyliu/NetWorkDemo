//package com.android.networkdemo.views;
//
//import android.content.Context;
//import android.os.Handler;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.View;
//import android.view.animation.AlphaAnimation;
//import android.view.animation.TranslateAnimation;
//import android.widget.Button;
//import android.widget.FrameLayout;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.SeekBar;
//import android.widget.TextView;
//
//import com.android.networkdemo.R;
//import com.android.networkdemo.utils.DoTextSize;
//import com.android.networkdemo.utils.GlobalConsts;
//import com.android.networkdemo.utils.UIUtils;
//import com.android.networkdemo.video.VideoPlayEngineImpl;
//
///**
// * Author: liuyuting
// * Description: NetWorkDemo
// * Since: 2017/4/19 19:20
// */
//
//public class VideoViewNative extends FrameLayout {
//    private int duration; // 上次观看时间
//    private Context mContext;
//    private FrameLayout innerFrameLayout;
//    private UIManager mUIManager;
//
//    private final static int REFRESH_CURPOS = 0x0001;
//    private final static int HIDE_TOOL = 0x0002;
//    private final static int EXIT_ACTIVITY = 0x0003;
//    private final static int REFRESH_SPEED = 0x0004;
//    private final static int CHECK_DELAY = 0x0005;
//    private final static int LONG_PRESS = 6;
//    private final static int HIDE_DELAY_TIME = 3000;
//
//    private Handler mHandler;// 刷新 时间textview,seekbar e.g
//
//    private VideoPlayEngineImpl mPlayerEngineImpl;
//
//    private VideoPlayEngineListener mPlayEngineListener;
//
//
//    /**
//     * 设置上次观看时间
//     *
//     * @param dura
//     */
//    public void setDura(int dura) {
//        duration = dura;
//    }
//
//    public VideoViewNative(@NonNull Context context) {
//        this(context, null);
//    }
//
//    public VideoViewNative(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        mContext = context;
//        initPlayer();
//    }
//
//    /**
//     * 初始化播放设置
//     */
//    private void initPlayer() {
//        innerFrameLayout = (FrameLayout) inflate(mContext, R.layout.new_custom_video, null);
//        addView(innerFrameLayout);
//    }
//
//
//    /**
//     * Author: liuyuting
//     * Description: NetWorkDemo
//     * Since: 2017/4/19 19:26
//     * 播放界面-
//     */
//
//    public class UIManager implements SurfaceHolder.Callback, SeekBar.OnSeekBarChangeListener {
//
//
//        public View mPrepareView;
//        public TextView mTVPrepareSpeed;
//        public TextView tv_prepare_name;
//
//        public View mLoadView;
//        public TextView mTVLoadSpeed;
//
//        public View mControlView;
//        public RelativeLayout mUpToolView;
//        public View mDownToolView;
//
//        public ImageButton mBtnPlay;
//        public ImageButton mBtnPause;
//        public SeekBar mSeekBar;
//        public TextView mTVCurTime;
//        public TextView mTVTotalTime;
//        public TextView mTVTitle;
//
//        private RelativeLayout video_cnl_rl;
//        private ProgressBar media_prepare_p_id;
//        private ProgressBar media_loading_p_id;
//        private SurfaceView mSurfaceView;
//        public SurfaceHolder holder = null; // video 在 surfaceview中播放
//        private ImageButton btn_fast_forward, btn_fast_back;
//
//        private RelativeLayout restart_rl;
//        private TextView restart_text;
//
//        private TranslateAnimation mHideDownTransformation;
//        private TranslateAnimation mHideUpTransformation;
//        private AlphaAnimation mAlphaHideTransformation;
//
//        private RelativeLayout overtestPlay;//试看区域
//        private Button buttonExit;
//        private TextView testLook;
//
//        private UIManager() {
//            initView();
//        }
//
//
//        private void initView() {
//            mPrepareView = findViewById(R.id.prepare_panel);
//            mTVPrepareSpeed = (TextView) findViewById(R.id.tv_prepare_speed);
//            tv_prepare_name = (TextView) findViewById(R.id.tv_prepare_name);
//
//            overtestPlay = (RelativeLayout) findViewById(R.id.overtestPlay);
//            buttonExit = (Button) findViewById(R.id.buttonExit);
//            testLook = (TextView) findViewById(R.id.testLook);
//
//            UIUtils.setViewSizeXY(overtestPlay, (int) (GlobalConsts.Rx * 890), (int) (GlobalConsts.Ry * 390));
//            UIUtils.setViewSizeXY(buttonExit, (int) (GlobalConsts.Rx * 190), (int) (GlobalConsts.Ry * 80));
//
//
//            DoTextSize.setTextSize(testLook, 60);
//            DoTextSize.setTextSize(buttonExit, 40);
//            DoTextSize.setTextSize(mTVPrepareSpeed, 40);
//            DoTextSize.setTextSize(tv_prepare_name, 44);
//
//            mLoadView = findViewById(R.id.loading_panel);
//            mTVLoadSpeed = (TextView) findViewById(R.id.tv_speed);
//            DoTextSize.setTextSize(mTVLoadSpeed, 40);
//            mTVPrepareSpeed.setVisibility(isShowLoad ? View.VISIBLE : View.INVISIBLE);
//            mTVLoadSpeed.setVisibility(isShowLoad ? View.VISIBLE : View.INVISIBLE);
//
//            mControlView = findViewById(R.id.control_panel);
//            mUpToolView = (RelativeLayout) findViewById(R.id.up_toolview);
//            UIUtils.setViewSizeY(mUpToolView, (int) (GlobalConsts.Rx * 90));
//            mDownToolView = findViewById(R.id.down_toolview);
//
//            mTVTitle = (TextView) findViewById(R.id.tv_title);
//            DoTextSize.setTextSize(mTVTitle, 48);
//
//            mBtnPlay = (ImageButton) findViewById(R.id.btn_play);
//            mBtnPause = (ImageButton) findViewById(R.id.btn_pause);
//            mSeekBar = (SeekBar) findViewById(R.id.playback_seeker);
//            mTVCurTime = (TextView) findViewById(R.id.tv_curTime);
//            mTVTotalTime = (TextView) findViewById(R.id.tv_totalTime);
//            DoTextSize.setTextSize(mTVCurTime, 38);
//            DoTextSize.setTextSize(mTVTotalTime, 38);
//
//            setSeekbarListener(UIManager.this);
//            mSeekBar.setFocusable(false);
//            mSeekBar.setFocusableInTouchMode(false);
//
////            btn_fast_forward = (ImageButton) findViewById(R.id.btn_fast_forward);
////            btn_fast_back = (ImageButton) findViewById(R.id.btn_fast_back);
//            UIUtils.setViewSizeX(btn_fast_forward, (int) (339 * GlobalConsts.Rx));
//            UIUtils.setViewSizeY(btn_fast_forward, (int) (405 * GlobalConsts.Rx));
//            UIUtils.setViewSizeX(btn_fast_back, (int) (339 * GlobalConsts.Rx));
//            UIUtils.setViewSizeY(btn_fast_back, (int) (405 * GlobalConsts.Rx));
//
//            mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
//            holder = mSurfaceView.getHolder();
//            holder.addCallback(this);
//            holder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
//
//            media_prepare_p_id = (ProgressBar) findViewById(R.id.media_prepare_p_id);
//            UIUtils.setViewSizeX(media_prepare_p_id,
//                    (int) (339 * GlobalConsts.Rx));
//            UIUtils.setViewSizeY(media_prepare_p_id,
//                    (int) (405 * GlobalConsts.Rx));
//            ImageView media_prepare_image_bg = (ImageView) findViewById(R.id.media_prepare_image_bg);
//            UIUtils.setViewSizeX(media_prepare_image_bg,
//                    (int) (339 * GlobalConsts.Rx));
//            UIUtils.setViewSizeY(media_prepare_image_bg,
//                    (int) (405 * GlobalConsts.Rx));
//
//            video_cnl_rl = (RelativeLayout) findViewById(R.id.video_cnl_rl);
//
//            media_loading_p_id = (ProgressBar) findViewById(R.id.media_loading_p_id);
//            UIUtils.setViewSizeX(media_loading_p_id,
//                    (int) (339 * GlobalConsts.Rx));
//            UIUtils.setViewSizeY(media_loading_p_id,
//                    (int) (405 * GlobalConsts.Rx));
//
//            // 浮层----从头播放
//            restart_rl = (RelativeLayout) findViewById(R.id.restart_rl);
//            restart_text = (TextView) findViewById(R.id.restart_text);
//            UIUtils.setViewSizeXY(restart_rl, (int) (GlobalConsts.Rx * 449), (int) (GlobalConsts.Ry * 124));
//
//            DoTextSize.setTextSize(restart_text, 35);
//
//            try {
//                UIUtils.setViewMarginSize(mTVPrepareSpeed, 0, (int) (330 * GlobalConsts.Rx), 0, 0);
//
//                UIUtils.setViewMarginSize(mTVLoadSpeed, 0, (int) (330 * GlobalConsts.Rx), 0, 0);
//
//                UIUtils.setViewMarginSize(tv_prepare_name, 0, (int) (20 * GlobalConsts.Rx), 0, 0);
//
//                UIUtils.setViewMarginSize(restart_rl, (int) (GlobalConsts.Rx * 1418), (int) (GlobalConsts.Ry * 73), (int) (GlobalConsts.Rx * 45), 0);
//
//                UIUtils.setViewMarginSize(overtestPlay, (int) (520 * GlobalConsts.Rx), (int) (320 * GlobalConsts.Rx), 0, 0);
//                UIUtils.setViewMarginSize(testLook, 0, (int) (50 * GlobalConsts.Rx), 0, 0);
//                UIUtils.setViewMarginSize(buttonExit, 0, (int) (130 * GlobalConsts.Rx), 0, 0);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            UIUtils.setViewSizeX(mBtnPlay, (int) (339 * GlobalConsts.Rx));
//            UIUtils.setViewSizeY(mBtnPlay, (int) (405 * GlobalConsts.Rx));
//            UIUtils.setViewSizeX(mBtnPause, (int) (339 * GlobalConsts.Rx));
//            UIUtils.setViewSizeY(mBtnPause, (int) (405 * GlobalConsts.Rx));
//            try {
//                UIUtils.setViewMarginSize(mSeekBar, 0, (int) (5 * GlobalConsts.Rx), 0, 0);
//                UIUtils.setViewMarginSize(video_cnl_rl, -(int) (2 * GlobalConsts.Rx), -(int) (2 * GlobalConsts.Rx), 0, 0);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            mHideDownTransformation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 200.0f);
//            mHideDownTransformation.setDuration(1000);
//
//            mAlphaHideTransformation = new AlphaAnimation(1, 0);
//            mAlphaHideTransformation.setDuration(500);
//
//            mHideUpTransformation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -124.0f);
//            mHideUpTransformation.setDuration(1000);
//        }
//
//        // type = 1 显示快进,type = 0 显示快退
//        private void showFForFB(int type) {
//            if (btn_fast_back == null || btn_fast_forward == null) {
//                return;
//            }
//            if (type == 0) {
//                btn_fast_back.setVisibility(View.VISIBLE);
//            } else {
//                btn_fast_forward.setVisibility(View.VISIBLE);
//            }
//            mBtnPlay.setVisibility(View.INVISIBLE);
//            mBtnPause.setVisibility(View.INVISIBLE);
//        }
//
//        // 隐藏 快进,快退 button
//        private void hideFForFB() {
//            if (btn_fast_back == null || btn_fast_forward == null) {
//                return;
//            }
//            btn_fast_back.setVisibility(View.INVISIBLE);
//            btn_fast_forward.setVisibility(View.INVISIBLE);
//            try {
//                calcuateTime.stop();
//                calcuateTime = null;
//            } catch (Exception e) {
//            }
//            calcuate = false;
//            seekPos = 5000;
//        }
//
//        // 显示初次加载页面下载速度
//        private void showPrepareLoadView(boolean isShow) {
//            if (isShow) {
//                mPrepareView.setVisibility(View.VISIBLE);
//                mTVPrepareSpeed.setVisibility(isShowLoad ? View.VISIBLE : View.INVISIBLE);
//            } else {
//                mPrepareView.setVisibility(View.GONE);
//            }
//        }
//
//        // 显示 seekbar
//        private void showControlView() {
//            removeHideMessage();
//            mUpToolView.setVisibility(View.VISIBLE);
//            mDownToolView.setVisibility(View.VISIBLE);
//        }
//
//
//        //显示试看结束
//        private void showOverShikan(boolean isShow) {
//            if (isShow) {
//                overtestPlay.setVisibility(View.VISIBLE);
//                isTestShow =true;
//
//            } else {
//                overtestPlay.setVisibility(View.GONE);
//            }
//        }
//
//        private void showControlView(boolean isShow) {
//            if (isShow) {
//                mUpToolView.setVisibility(View.VISIBLE);
//                mDownToolView.setVisibility(View.VISIBLE);
//                mPrepareView.setVisibility(View.GONE);
//                delayToHideControlPanel();
//            } else {
//                if (mDownToolView.isShown()) {
//                    mDownToolView.startAnimation(mHideDownTransformation);
//                    mUpToolView.startAnimation(mHideUpTransformation);
//                    mUpToolView.setVisibility(View.GONE);
//                    mDownToolView.setVisibility(View.GONE);
//                }
//            }
//        }
//
//        // 显示正常播放后de下载速度,pause or loading 调用
//        private void showLoadView(boolean isShow) {
//            if (isShow) {
//                mLoadView.setVisibility(View.VISIBLE);
//                if (GlobalConsts.IS_PROFESSNAL_VERSION) {
//                    mTVLoadSpeed.setVisibility(isShowLoad ? View.VISIBLE : View.INVISIBLE);
//                }
//                video_cnl_rl.setVisibility(View.VISIBLE);
//            } else {
//                if (mLoadView.isShown()) {
//                    mLoadView.setVisibility(View.GONE);
//                    video_cnl_rl.setVisibility(View.GONE);
//                }
//            }
//        }
//
//        private boolean isSeekbarTouch = false;
//
//        @Override
//        public void surfaceCreated(SurfaceHolder holder) {
//            mPlayerEngineImpl.mMediaPlayer.setDisplay(holder);
//            play();
//
//        }
//
//        @Override
//        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//        }
//
//        @Override
//        public void surfaceDestroyed(SurfaceHolder holder) {
//
//        }
//
//        @Override
//        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//            mUIManager.setcurTime(progress);
//
//        }
//
//        @Override
//        public void onStartTrackingTouch(SeekBar seekBar) {
//            isSeekbarTouch = true;
//
//        }
//
//        // 按着滑动条放开时候,seeTo到相应位置
//        @Override
//        public void onStopTrackingTouch(SeekBar seekBar) {
//            isSeekbarTouch = false;
//            seek(seekBar.getProgress());
//            mUIManager.showControlView(true);
//
//        }
//
//        private void showPlay(boolean bShow) {
//            if (bShow) {
//                mBtnPlay.setVisibility(View.VISIBLE);
//                mBtnPause.setVisibility(View.INVISIBLE);
//            } else {
//                mBtnPause.setVisibility(View.VISIBLE);
//                mBtnPlay.setVisibility(View.INVISIBLE);
//            }
//        }
//
//        // 播放暂停 ,按按键时
//        private void togglePlayPause() {
//            if (!isPlay) {
//                play();
//            } else {
//                pause();
//            }
//        }
//
//        private void setSeekbarProgress(int time) {
//            if (!isSeekbarTouch) {
//                mSeekBar.setProgress(time);
//            }
//            curentProgress = time;
//            mUIManager.setcurTime(time);
//            if (mediaListener != null) {
//                mediaListener.onCurrentPlay(curentProgress);
//            }
//        }
//
//        private void setSeekbarSecondProgress(int time) {
//            mSeekBar.setSecondaryProgress(time);
//        }
//
//        private void setSeekbarMax(int max) {
//            mSeekBar.setMax(max);
//        }
//
//        private void setcurTime(int curTime) {
//            String timeString = CommonUtil.formateTime(curTime);
//            mTVCurTime.setText(timeString);
//        }
//
//        private void setTotalTime(int totalTime) {
//            VideoViewNative.this.totalTime = totalTime;
//            String timeString = CommonUtil.formateTime(totalTime);
//            mTVTotalTime.setText(timeString);
//            if (mediaListener != null) {
//                mediaListener.onTotalPlay(totalTime);
//            }
//        }
//
//        // 显示视频信息,第一次调用
//        private void updateMediaInfoView(MediaVideoModel mediaInfo) {
//            setcurTime(0);
//            setTotalTime(0);
//            setSeekbarMax(100);
//            setSeekbarProgress(0);
//            mTVTitle.setText(mediaInfo.getTitle());
//            tv_prepare_name.setText("正在加载: " + mediaInfo.getTitle().trim());
//        }
//
//        private void setSpeed(float speed) {
//            String showString = (int) speed + " KB/s";
//            mTVPrepareSpeed.setText(showString);
//            mTVLoadSpeed.setText(showString);
//            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mTVPrepareSpeed.getLayoutParams();
//            params.leftMargin = (((RelativeLayout) mTVPrepareSpeed.getParent()).getLayoutParams().width - params.width) / 2 + (int) (GlobalConsts.Rx * 90);
//            resetTVLoadSpeedVisible(currentPSDialog);
//        }
//
//        public void resetTVLoadSpeedVisible(boolean flag) {
//            if (flag) {
//                if (mTVLoadSpeed.getVisibility() == View.VISIBLE) {
//                    mTVLoadSpeed.setVisibility(View.INVISIBLE);
//                }
//            } else {
//                if (mTVLoadSpeed.getVisibility() != View.VISIBLE) {
//                    mTVLoadSpeed.setVisibility(View.VISIBLE);
//                }
//            }
//        }
//
//        private void setSeekbarListener(SeekBar.OnSeekBarChangeListener listener) {
//            mSeekBar.setOnSeekBarChangeListener(listener);
//        }
//
//        // 显示正常播放后的seekbar
//        private boolean isControlViewShow() {
//            return mDownToolView.getVisibility() == View.VISIBLE ? true : false;
//        }
//
//        // 显示正常播放后的滚动圈
//        private boolean isLoadViewShow() {
//            if (mLoadView.getVisibility() == View.VISIBLE
//                    || mPrepareView.getVisibility() == View.VISIBLE) {
//                return true;
//            }
//            return false;
//        }
//
//        private void showPlayErrorTip() {
//        }
//
//        // 浮层从头开始相关
//        public void showRestart(boolean flag) {
//            if (flag) {
//                restart_rl.setVisibility(View.VISIBLE);
//            } else {
//                restart_rl.setVisibility(View.GONE);
//            }
//        }
//
//    }
//
//
//    // 浮层---从头播放相关
//    public void showShadowRestart(boolean flag) {
//        if (mUIManager != null) {
//            mUIManager.showRestart(flag);
//        }
//    }
//
//    private boolean showRestart; // 浮层是否可见
//    Handler restartHandler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            if (msg.what == 1 && showRestart) {
//                showRestart = false;
//                showShadowRestart(false); // 关闭浮层
//            } else if (msg.what == 2) {
//                showRestart = true;
//                showShadowRestart(true); // 上次观看时间不为0,显示浮层
//                restartHandler.sendEmptyMessageDelayed(1, 5000); // 5秒后,关闭浮层
//            }
//        };
//    };
//
//    /**
//     * 是否显示浮层处理
//     */
//    public void restartVideoDeal() {
//        if (duration > 0) {
//            showRestart = true;
//            showShadowRestart(true); // 上次观看时间不为0,显示浮层
//            restartHandler.sendEmptyMessageDelayed(1, 5000); // 5秒后,关闭浮层
//        }
//    }
//
//}
