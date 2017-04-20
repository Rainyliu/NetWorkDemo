package com.android.networkdemo.video;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;

import com.android.networkdemo.utils.GlobalConsts;


public class VideoPlayEngineImpl extends AbstractMediaPlayEngine{
	
	private Context context;
	private SurfaceHolder mHolder = null;
	private SurfaceTexture mSurfaceTexture;
	private SurfaceView mSurfaceView;
	private OnBufferingUpdateListener mBufferingUpdateListener;
	private OnSeekCompleteListener mSeekCompleteListener;
	private OnErrorListener mOnErrorListener;
	private TextureView mTextureView;
	public String identifier;
	public String filmName;
	
	public  VideoPlayEngineImpl(Context context, SurfaceHolder holder) {
		super(context);
		this.context = context;
		setHolder(holder);
	}
	
	public void setSurfaceTexture(SurfaceTexture surface){
		this.mSurfaceTexture = surface;
//		if(mSurfaceTexture != null ){
//			mMediaPlayer.setSurface(new Surface(mSurfaceTexture));
//		}
	}
	
	public SurfaceTexture getSurfaceTexture(){
		return mSurfaceTexture;
	}
	
	public void setTextureView(TextureView v){
		mTextureView = v;
	}
	
	public void setHolder(SurfaceHolder holder){
		mHolder = holder;
	}
	
	public MediaPlayer getMediaPlay()
	{
		return mMediaPlayer;
	}
	
	public SurfaceHolder getSurfaceHolder(){
		return mHolder;
	}
	
	public void setOnBuffUpdateListener(OnBufferingUpdateListener listener){
		mBufferingUpdateListener = listener;
	}
	
	public void setOnSeekCompleteListener(OnSeekCompleteListener listener){
		mSeekCompleteListener = listener;
	}
	
	public void setOnErrorListener(OnErrorListener listener){
		mOnErrorListener = listener;
	}
	
	@Override
	public void skipTo(int time) {
		super.skipTo(time);
	}
	
	@Override
	protected boolean prepareSelf() {
		if (mMediaPlayer != null) {
			mMediaPlayer.reset();
			try {
				mMediaPlayer.setDataSource(mMediaInfo.getUrl());
				mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				if (mHolder != null){
					mHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
					mHolder.setFormat(PixelFormat.RGBA_8888);
					mMediaPlayer.setDisplay(mHolder);
				}
				if (mBufferingUpdateListener != null){
					mMediaPlayer.setOnBufferingUpdateListener(mBufferingUpdateListener);
				}
				if (mSeekCompleteListener != null){
					mMediaPlayer.setOnSeekCompleteListener(mSeekCompleteListener);
				}
				mMediaPlayer.setScreenOnWhilePlaying(true);
				mMediaPlayer.prepareAsync();
				mPlayState = PlayState.MPS_PARESYNC;
				performPlayListener(mPlayState);
			} catch (Exception e) {
				e.printStackTrace();
				mPlayState = PlayState.MPS_INVALID;
				performPlayListener(mPlayState);
				return false;
			}
			return true;
		}else {
			return false;
		}
	}

	@Override
	protected boolean prepareComplete(MediaPlayer mp) {
		if(identifier != null && identifier.equals("searchActivity")){
			//从搜索中收到电影且播放成功
			if(filmName != null && filmName.length() >= 0){

			}
//			LogUtil.e("搜索数据统计","从搜索中收到电影且播放成功 identifier>>"+identifier);
		}
		mPlayState = PlayState.MPS_PARECOMPLETE;
		if (mPlayerEngineListener != null){
			mPlayerEngineListener.onTrackPrepareComplete(mMediaInfo);
		}
		if (mHolder != null){
			CommonUtil.ViewSize viewSize = CommonUtil.getFitSize(mContext, mp.getVideoWidth(),mp.getVideoHeight());
			mHolder.setFixedSize(viewSize.width, viewSize.height);
		}
		GlobalConsts.isVideoPrepareComplete = true;
//		if(context instanceof XCCenterActivity && GlobalConsts.isPlayable == false) {
//			return true;
//		}
		mMediaPlayer.start();
		mPlayState = PlayState.MPS_PLAYING;
		performPlayListener(mPlayState);
		return true;
	}
}
