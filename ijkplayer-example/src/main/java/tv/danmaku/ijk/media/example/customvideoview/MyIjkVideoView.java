package tv.danmaku.ijk.media.example.customvideoview;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import tv.danmaku.ijk.media.example.R;

/**
 * 自定义播放控制界面
 * Author: Rainy <br>
 * Description: NetWorkDemo <br>
 * Since: 2017/4/24 0024 10:20 <br>
 */

public class MyIjkVideoView extends FrameLayout implements View.OnClickListener {
    // 关联的Activity
    private AppCompatActivity mAttachActivity;

    public MyIjkVideoView(@NonNull Context context) {
        this(context, null);
    }

    public MyIjkVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        _initView(context);
    }

    /**
     * 初始化UI
     * @param context
     */
    private void _initView(Context context) {
        if (context instanceof AppCompatActivity) {
            mAttachActivity = (AppCompatActivity) context;
        } else {
            throw new IllegalArgumentException("Context must be AppCompatActivity");
        }

        View.inflate(context, R.layout.custom_layout_player_view, this);
    }


    @Override
    public void onClick(View v) {

    }
}
