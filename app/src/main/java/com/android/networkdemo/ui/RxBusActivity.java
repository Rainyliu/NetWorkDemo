package com.android.networkdemo.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.networkdemo.R;
import com.android.networkdemo.entity.Course;
import com.android.networkdemo.entity.Student;
import com.android.networkdemo.rxbus.UserEvent;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxBusActivity extends AppCompatActivity {
    Subscription rxSubscription;
    private String TAG = "liuyuting";
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);
        iv = (ImageView) findViewById(R.id.ivShow);
//        RxBus.get().register(this);
        rxSubscription = com.joy.rxbus.RxBus.get().toObservable(UserEvent.class)
                .subscribe(new Action1<UserEvent>() {
                               @Override
                               public void call(UserEvent userEvent) {
                                   long id = userEvent.getId();
                                   String name = userEvent.getName();
                                   Log.d(TAG, "call:---id= " + id + ";name=" + name);
                               }
                           }
                        , new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                // TODO: 处理异常
                                Log.d(TAG, "call: ---" + throwable.toString());
                            }
                        });
    }

    public void click(View v) {
//        startActivity(new Intent(this, SendEventActivity.class));
//        rxJavaDemo();
//        rxJavaDemo1();
//        rxJavaDemo2();
//        rxJavaDemo3();
//        rxJavaDemo4();
        rxJavaDemo5();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RxBus.get().unregister(this);
        if (!rxSubscription.isUnsubscribed()) {
            rxSubscription.unsubscribe();
        }
    }

//    @Subscribe
//    public void eat(String food){
//
//    }
//
////    @Subscribe(
////            thread = EventThread.IO,
////            tags = {
////                    @Tag(BusAction.EAT_MORE)
////            }
////    )
//    public void eatMore(List<String> foods) {
//        // purpose
//    }
//
//    @Produce
//    public String produceFood() {
//        return "This is bread!";
//    }
//
////    @Produce(
////            thread = EventThread.IO,
////            tags = {
////                    @Tag(BusAction.EAT_MORE)
////            }
////    )
//    public List<String> produceMoreFood() {
//        return Arrays.asList("This is breads!");
//    }
//
//    public void post() {
//        RxBus.get().post(this);
//    }
//
//    public void postByTag() {
//        RxBus.get().post(Constants.EventType.TAG_STORY, this);
//    }

    /**
     * 分发事件并打印
     */
    private void rxJavaDemo() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        final Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }
        };

        Observable observable = Observable.create(new Observable.OnSubscribe() {
            @Override
            public void call(Object o) {
                subscriber.onNext("aaaaa");
                subscriber.onNext("bbbbb");
                subscriber.onNext("ccccc");
                subscriber.onNext("ddddd");
                subscriber.onCompleted();
            }
        });

        observable.subscribe(subscriber);//订阅
    }

    /**
     * 每隔一秒打印一个数从零开始从此*5
     * 当程序退出的时候，仍然在打印,所以要在onPause，或者onStop中解除订阅关系
     * <p>
     * 上面的列子会每秒生成一个从 0 依次递增的整数，
     * 然后通过 map() 变换操作符后，变成了 5 的倍数的一个整数列。
     */
    private void rxJavaDemo1() {
        Observable.interval(1, TimeUnit.SECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 5;
                    }
                })
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.d(TAG, "call: value----" + aLong);
                    }
                });
    }

    /**
     *
     */
    private void rxJavaDemo2() {
        Observable obsevable = Observable.just("aaaaaa", "bbbbbb", "ccccccc", "dddddd");

        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observable = Observable.from(words);

        /**
         * 后台线程取数据，主线程显示
         */
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程,被创建的事件的内容 1、2、3、4 将会在 IO 线程发出
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程,因此 subscriber 数字的打印将发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "call: integer" + integer);
                    }
                });

    }

    private void rxJavaDemo3() {
        final int drawableRes = R.drawable.ic_launcher;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getTheme().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())//加载图片将会发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())//设置图片将会发生在主线程
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxBusActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        iv.setImageDrawable(drawable);
                    }
                });
    }

    /**
     * 变换
     * 所谓变换，就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列
     */
    private void rxJavaDemo4() {
        String url = "http://img3.imgtn.bdimg.com/it/u=4271053251,2424464488&fm=23&gp=0.jpg";
        Observable.just(url)//输入String，转换成bitMap
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String filePath) {
                        return getBitmapFromFilePath(filePath);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {//参数类型bitmap
                        showBitmap(bitmap);
                    }
                });
    }

    private void showBitmap(Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }

    /**
     * 从网络下载图片
     * @param filePath
     * @return
     */
    private Bitmap getBitmapFromFilePath(String filePath) {
        try {
            URL url = new URL(filePath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if(conn.getResponseCode() == 200){
                return BitmapFactory.decodeStream(conn.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * flatMap变换
     */
    private void rxJavaDemo5(){
        Course course1 = new Course("math");
        Course course2 = new Course("chinese");
        Course course3 = new Course("english");
        List<Course> list1 = new ArrayList<>();
        List<Course> list2 = new ArrayList<>();
        List<Course> list3 = new ArrayList<>();
        list1.add(course1);
        list2.add(course1);
        list2.add(course2);
        list3.add(course1);
        list3.add(course2);
        list3.add(course3);
        Student[] students = {new Student("lihua",list1),new Student("mingming",list2),new Student("xiaoqiang",list3)};
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String name) {
                Log.d(TAG, "onNext: "+name);
            }
        };

        Subscriber<Student> subscriber1 = new Subscriber<Student>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student student) {
                List<Course> courses = student.getCourses();
                for (int i = 0; i < courses.size(); i++) {
                    Course course = courses.get(i);
                    Log.d(TAG, course.getName());

                }
            }
        };
        
//        Observable.from(students)
//                .map(new Func1<Student, String>() {
//
//                    @Override
//                    public String call(Student student) {
//                        return student.getName();
//                    }
//                })
//                .subscribe(subscriber);

//        Observable.from(students)
//                .subscribe(subscriber1);

        Subscriber<Course> subscriber2 = new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.d(TAG, "onNext: "+course.getName());
            }
        };

        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(subscriber2);
    }





    @Override
    protected void onPause() {
        super.onPause();

    }
}
