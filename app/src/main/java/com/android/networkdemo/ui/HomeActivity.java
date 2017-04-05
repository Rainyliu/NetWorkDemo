package com.android.networkdemo.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.networkdemo.R;
import com.android.networkdemo.ui.base.BaseFragmentActivity;
import com.android.networkdemo.utils.DeviceUtils;


public class HomeActivity extends BaseFragmentActivity {
    private Button tv,stop,keep,iv;
    public static final String TAG = "liuyuting";
    private String url = "http://newoss.maiziedu.com/qiniu/qcapp1.mp4";
//    private Disposable disposable;
    @Override
    public int getLayoutId() {
        //这里来获取activity的layout
        return R.layout.activity_home;
    }

    @Override
    public void initViews() {
        //这里用来初始化view`
        tv= findView(R.id.tv);
        iv= findView(R.id.iv);
        stop= findView(R.id.stop);
        keep= findView(R.id.keep);
    }

    @Override
    public void initListener() {
        //这里用来初始化点击事件
        setOnClick(tv);
        setOnClick(iv);
        setOnClick(stop);
        setOnClick(keep);
    }

    @Override
    public void initData() {
        //这里用来设置数据，获取数据，读取网络数据，这里所做的一起都可以在controller中实现
        initShop();
        initBanner();
    }

    private void initBanner() {

    }

    private void initShop() {

    }

    @Override
    public void processClick(View v) {
        //这里用来处理点击事件
        switch (v.getId()){
            case R.id.tv:
                Log.d(TAG, "processClick:==ip== 点击了按钮");
                iv.setText("ip:"+DeviceUtils.getLocalIPAddress()+"\r\nscreen:"+DeviceUtils.getScreenPix(getApplicationContext())
                +"\r\ndip2px:"+DeviceUtils.dip2px(this,200)
                +"\r\nsp2px:"+DeviceUtils.sp2px(this,24)
                +"\r\ngetStatusBarHeight:"+DeviceUtils.getStatusBarHeight(getApplicationContext())
                +"\r\ngetNavigationBarHeight:"+DeviceUtils.getNavigationBarHeight(getApplicationContext())
                +"\r\ngetNetType:"+DeviceUtils.getNetType(getApplicationContext())
                +"\r\nisPhone:"+DeviceUtils.isPhone(getApplicationContext())
                +"\r\ngetScreenSizeOfDevice:"+DeviceUtils.getScreenSizeOfDevice(getApplicationContext())
                +"\r\ngetScreenSizeOfDevice2:"+DeviceUtils.getScreenSizeOfDevice2(this));
//                Log.d(TAG, "processClick:==ip== "+ DeviceUtils.getLocalIPAddress());
//                Log.d(TAG, "processClick:==screen== "+ DeviceUtils.getScreenPix(getApplicationContext()));
//                Log.d(TAG, "processClick:==dip2px== "+ DeviceUtils.dip2px(this,200));
//                Log.d(TAG, "processClick:==px2dip== "+ DeviceUtils.px2dip(this,1920));
//                Log.d(TAG, "processClick:==sp2px== "+ DeviceUtils.sp2px(this,24));
//                Log.d(TAG, "processClick:==px2sp== "+ DeviceUtils.px2sp(this,40));
//                Log.d(TAG, "processClick:==getStatusBarHeight== "+ DeviceUtils.getStatusBarHeight(getApplicationContext()));
//                Log.d(TAG, "processClick:==getNavigationBarHeight== "+ DeviceUtils.getNavigationBarHeight(getApplicationContext()));
//                Log.d(TAG, "processClick:==getNetType== "+ DeviceUtils.getNetType(getApplicationContext()));
//                Log.d(TAG, "processClick:==isPhone== "+ DeviceUtils.isPhone(getApplicationContext()));
                break;
            case R.id.iv:
//                disposable = RxDownload.getInstance(getApplicationContext())
//                        .download(url)//只需传入url即可
//                        .subscribeOn(Schedulers.io())
//                        .subscribe(new Consumer<DownloadStatus>() {
//                                       @Override
//                                       public void accept(@NonNull DownloadStatus downloadStatus) throws Exception {
//                                           //DownloadStatus为下载进度
//                                           Log.d(TAG, "accept: downloadStatus"+downloadStatus.getFormatTotalSize());
//                                           Log.d(TAG, "accept: getFormatDownloadSize:"+downloadStatus.getFormatDownloadSize());
//                                           Log.d(TAG, "accept: getPercent:"+downloadStatus.getPercent());
//                                           Log.d(TAG, "accept: getPercentNumber:"+downloadStatus.getPercentNumber());
//                                           Log.d(TAG, "accept: getFormatStatusString:"+downloadStatus.getFormatStatusString());
//                                           Log.d(TAG, "accept: getTotalSize:"+downloadStatus.getTotalSize());
//                                       }
//                                   },
//                                new Consumer<Throwable>() {
//                                    @Override
//                                    public void accept(@NonNull Throwable throwable) throws Exception {
//                                        //下载失败
//                                        Log.d(TAG, "accept: 下载失败:"+throwable.toString());
//                                    }
//                                }, new Action() {
//                                    @Override
//                                    public void run() throws Exception {
//                                        //下载成功
//                                        Log.d(TAG, "run: 下载成功");
//
//
//                                    }
//                                });
                break;
            case R.id.stop:
//                //暂停下载
//                //获得订阅返回的Disposable.
//                //取消订阅, 即可暂停下载
//                if(disposable != null && !disposable.isDisposed()){
//                    disposable.dispose();
//                }
                break;
            case R.id.keep://继续下载(重新下载了)
                //重新调用download()方法，传入相同的url即可
//若该url支持断点续传则继续下载，若不支持则重新下载
//                disposable = RxDownload.getInstance(getApplicationContext())
//                        .download(url)//只需传入url即可
//                        .subscribeOn(Schedulers.io())
//                        .subscribe(new Consumer<DownloadStatus>() {
//                                       @Override
//                                       public void accept(@NonNull DownloadStatus downloadStatus) throws Exception {
//                                           Log.d(TAG, "accept: downloadStatus"+downloadStatus.getFormatTotalSize());
//                                           Log.d(TAG, "accept: getFormatDownloadSize:"+downloadStatus.getFormatDownloadSize());
//                                           Log.d(TAG, "accept: getPercent:"+downloadStatus.getPercent());
//                                           Log.d(TAG, "accept: getPercentNumber:"+downloadStatus.getPercentNumber());
//                                           Log.d(TAG, "accept: getFormatStatusString:"+downloadStatus.getFormatStatusString());
//                                           Log.d(TAG, "accept: getTotalSize:"+downloadStatus.getTotalSize());
//                                           //DownloadStatus为下载进度
//                                       }
//                                   },
//                                new Consumer<Throwable>() {
//                                    @Override
//                                    public void accept(@NonNull Throwable throwable) throws Exception {
//                                        //下载失败
//                                        Log.d(TAG, "accept: 下载失败:"+throwable.toString());
//                                    }
//                                }, new Action() {
//                                    @Override
//                                    public void run() throws Exception {
//                                        //下载成功
//                                        Log.d(TAG, "run: 下载成功");
//
//                                    }
//                                });
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
