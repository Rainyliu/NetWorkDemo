package tv.danmaku.ijk.media.example.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import tv.danmaku.ijk.media.example.R;

public class MainActivity extends AppCompatActivity {
    private String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
    String url1 = "http://video.cdn.hd1905.com/bbaqcz-NO1.ts";
    String url2 = "http://video.cdn.hd1905.com/movie/feichangrenfan1-needfix.ts?t=1493870367&k=7c889b624279d01eaf402780c0f38eda";
    String url3 = "http://video.cdn.hd1905.com/movie/shanghaiwang-1080P.mp4?t=1493955020&k=bed441fd9cc72df05012f4f46a5011bd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View v){
        switch (v.getId()){
            case R.id.btn://播放网络视频
                VideoActivity11.intentTo(this, url, "电视剧");
//                VideoActivity.intentTo(this, url, "电视剧");
                break;
        }
    }
}
