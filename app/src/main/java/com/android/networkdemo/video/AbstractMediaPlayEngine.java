package com.android.networkdemo.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;


public abstract class AbstractMediaPlayEngine implements IBasePlayEngine,
        OnCompletionListener, OnPreparedListener, OnErrorListener {
	
	protected MediaPlayer mMediaPlayer;
	protected MediaVideoModel 			mMediaInfo;							   								
	protected Context mContext;
	protected int 											mPlayState;
	public boolean	 										isSeeking =false;
	protected PlayerEngineListener 	mPlayerEngineListener;
	private boolean isError = false;
	
	protected abstract boolean prepareSelf();
	protected abstract boolean prepareComplete(MediaPlayer mp);
	
	protected  void defaultParam(){
		mMediaPlayer = new MediaPlayer();
		mMediaPlayer.setOnCompletionListener(this);	
		mMediaPlayer.setOnPreparedListener(this);
		mMediaPlayer.setOnErrorListener(this);
		mMediaInfo = null;
		mPlayState = PlayState.MPS_NOFILE;
	}
	
	public AbstractMediaPlayEngine(Context context){
		mContext = context;
		defaultParam();
	}
	
	public void setPlayerListener(PlayerEngineListener listener){
		mPlayerEngineListener = listener;
	}
		
	@Override
	public void play() {
		switch (mPlayState) {
		case PlayState.MPS_PAUSE:
			mMediaPlayer.start();
			mPlayState = PlayState.MPS_PLAYING;
			performPlayListener(mPlayState);
			break;
		case PlayState.MPS_STOP:
			prepareSelf();
			break;
		default:
			break;
		}
	}

	@Override
	public void pause() {
		switch (mPlayState) {
		case PlayState.MPS_PLAYING:
			mMediaPlayer.pause();
			mPlayState = PlayState.MPS_PAUSE;
			performPlayListener(mPlayState);
			break;
		default:
			break;
		}
	}

	@Override
	public void stop() {
		if (mPlayState != PlayState.MPS_NOFILE){
			mMediaPlayer.reset();
			mPlayState = PlayState.MPS_STOP;
			performPlayListener(mPlayState);
		}
	}
	
	@Override
	public void skipTo(int time) {
		isSeeking = true;
		switch (mPlayState) {
			case PlayState.MPS_PLAYING:
			case PlayState.MPS_PAUSE:
				int time2 = reviceSeekValue(time);
				mMediaPlayer.seekTo(time2);
				break;
			default:
				break;
		}
		isSeeking = false;
	}
	
	public void exit(){
		mMediaPlayer.stop();
		mMediaPlayer.release();
		mMediaInfo = null;
		mMediaPlayer = null;
		mPlayState = PlayState.MPS_NOFILE;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		prepareComplete(mp);
	}
	
	@Override
	public void onCompletion(MediaPlayer mp) {
		if (mPlayerEngineListener != null){
			if(!isError){
				mPlayerEngineListener.onTrackPlayComplete(mMediaInfo);
			} else {
				isError = false;
			}
		}
		
	}

	public boolean isPlaying() {
		return mPlayState == PlayState.MPS_PLAYING;
	}

	public boolean isPause(){
		return mPlayState == PlayState.MPS_PAUSE;
	}
	
	public void playMedia(MediaVideoModel mediaInfo){
		if (mediaInfo != null){
			mMediaInfo = mediaInfo;
			prepareSelf();
		}
	}
		
	public int getCurPosition(){
		if (mPlayState == PlayState.MPS_PLAYING || mPlayState == PlayState.MPS_PAUSE){
			if (mMediaPlayer.getCurrentPosition() < 0) {
				return 0;
			}
			return (int) mMediaPlayer.getCurrentPosition();
		}
		return 0;
	}
	
	public int getDuration(){
		switch(mPlayState){
			case PlayState.MPS_PLAYING:
			case PlayState.MPS_PAUSE:
			case PlayState.MPS_PARECOMPLETE:
				if (mMediaPlayer.getDuration() > 0) {
					return (int) mMediaPlayer.getDuration();
				}else {
					return mMediaInfo.getDuration();
				}
		}
		return 0;
	}
	
	public int getPlayState()
	{
		return mPlayState;
	}

	protected void performPlayListener(int playState)
	{
		if (mPlayerEngineListener != null){
			switch(playState){
				case PlayState.MPS_INVALID:
					mPlayerEngineListener.onTrackStreamError(mMediaInfo);
					break;
				case PlayState.MPS_STOP:
					mPlayerEngineListener.onTrackStop(mMediaInfo);
					break;
				case PlayState.MPS_PLAYING:
					mPlayerEngineListener.onTrackPlay(mMediaInfo);
					break;
				case PlayState.MPS_PAUSE:
					mPlayerEngineListener.onTrackPause(mMediaInfo);
					break;
				case PlayState.MPS_PARESYNC:
					mPlayerEngineListener.onTrackPrepareSync(mMediaInfo);
					break;
			}
		}
	}
	
	private int reviceSeekValue(int value)
	{
		if (value < 0){
			value = 0;
		}
		if (mMediaPlayer.getDuration() != 0) {
			if (value > mMediaPlayer.getDuration()){
				value = (int) mMediaPlayer.getDuration();
			}
		}else {
			if (value > mMediaInfo.getDuration()){
				value = mMediaInfo.getDuration();
			}
		}
		return value;
	}
	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		if (mPlayerEngineListener != null){
			isError = true;
			mPlayerEngineListener.onTrackStreamError(mMediaInfo);
//			prepareSelf();
		}
		return false;
	}
}
