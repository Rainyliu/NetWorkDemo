package com.android.networkdemo.video;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/4/19 19:49
 */

public interface PlayerEngineListener {
    public void onTrackPlay(MediaVideoModel itemInfo);

    public void onTrackStop(MediaVideoModel itemInfo);

    public void onTrackPause(MediaVideoModel itemInfo);

    public void onTrackPrepareSync(MediaVideoModel itemInfo);

    public void onTrackPrepareComplete(MediaVideoModel itemInfo);

    public void onTrackStreamError(MediaVideoModel itemInfo);

    public void onTrackPlayComplete(MediaVideoModel itemInfo);
}
