package com.android.networkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.networkdemo.entity.LoginResult;
import com.android.networkdemo.utils.retrofit.api.ApiConst;
import com.android.networkdemo.utils.retrofit.api.ApiInterface;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Route;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv;
    private Button btn,btn1;
    public static final String TAG = "liuyuting";
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");
    private OkHttpClient mClient;
    private OkHttpClient client;
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.btn);
        btn1 = (Button) findViewById(R.id.btn1);

        Log.i(TAG,"onCreate "+Thread.currentThread().getId()+"-----"+Thread.currentThread().getName());
        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        File sdCache = getExternalCacheDir();
        int cacheSize = 10*1024*1024;//10MiB
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Cache cache = new Cache(sdCache.getAbsoluteFile(),cacheSize);
        builder.cache(cache);
        mClient = builder.build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn://retrofit请求网络的例子
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ApiConst.MAIN)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
                Call<LoginResult> call = apiInterface.getData("lyk", "1234");
                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        Log.d("liuyuting", "onResponse: "+response.toString());
//                        if (response.isSuccessful()) {
//                            // do SomeThing
//                        } else {
//                            //直接操作UI
//                            tv.setText(response.toString());
//                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        // do onFailure代码
                    }
                });
                break;
            case R.id.btn1://OKhttp请求网络的例子
//                okHttp_synchronousGet();
//                okHttp_asynchronousGet();
//                okHttp_postFromParameters();
//                postAString();
//                postStreaming();
//                postFile();
//                useGsonToParseResponse();
//                useCache();
//                cancelOkhttp();
//                setOkHttpTimeOut();
//                reuseConfiguration();
                handleConfirm();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 同步get方法
     * 需要我们自己开启工作线程
     */
    private void okHttp_synchronousGet(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://api.github.com/";
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                try {
                    okhttp3.Response response = client.newCall(request).execute();
                    if(response.isSuccessful()){
                        /**
                         * 返回的json数据
                         *  {"current_user_url":"https://api.github.com/user",
                         *  "current_user_authorizations_html_url":"https://github.com/settings/connections/applications{/client_id}","authorizations_url":
                         *  "https://api.github.com/authorizations","code_search_url":"https://api.github.com/search/code?q={query}{&page,per_page,sort,order}",
                         *  "commit_search_url":"https://api.github.com/search/commits?q={query}{&page,per_page,sort,order}",
                         *  "emails_url":"https://api.github.com/user/emails","emojis_url":"https://api.github.com/emojis",
                         *  "events_url":"https://api.github.com/events","feeds_url":"https://api.github.com/feeds",
                         *  "followers_url":"https://api.github.com/user/followers","following_url":"https://api.github.com/user/following{/target}",
                         *  "gists_url":"https://api.github.com/gists{/gist_id}","hub_url":"https://api.github.com/hub","issue_search_url":
                         *  "https://api.github.com/search/issues?q={query}{&page,per_page,sort,order}","issues_url":"https://api.github.com/issues",
                         *  "keys_url":"https://api.github.com/user/keys","notifications_url":"https://api.github.com/notifications",
                         *  "organization_repositories_url":"https://api.github.com/orgs/{org}/repos{?type,page,per_page,sort}",
                         *  "organization_url":"https://api.github.com/orgs/{org}","public_gists_url":"https://api.github.com/gists/public",
                         *  "rate_limit_url":"https://api.github.com/rate_limit","repository_url":"https://api.github.com/repos/{owner}/{repo}",
                         *  "repository_search_url":"https://api.github.com/search/repositories?q={query}{&page,per_page,sort,order}",
                         *  "current_user_repositories_url":"https://api.github.com/user/repos{?type,page,per_page,sort}",
                         *  "starred_url":"https://api.github.com/user/starred{/owner}{/repo}","starred_gists_url":
                         *  "https://api.github.com/gists/starred","team_url":"https://api.github.com/teams","user_url":
                         *  "https://api.github.com/users/{user}","user_organizations_url":"https://api.github.com/user/orgs",
                         *  "user_repositories_url":"https://api.github.com/users/{user}/repos{?type,page,per_page,sort}",
                         *  "user_search_url":"https://api.github.com/search/users?q={query}{&page,per_page,sort,order}"}
                         */
                        Log.i(TAG,"callback thread id is "+Thread.currentThread().getId()+"-----"+Thread.currentThread().getName());
                        Log.d("liuyuting", "onClick: "+response.body().string());
//                        tv.setText(response.body().toString());
                    }else{
                        Log.i("liuyuting", "okHttp is request error");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 异步get方法
     * 不需要我们自己开启工作线程
     * 需要用到回调
     * 注意回调函数也是在子线程中的
     */
    private void okHttp_asynchronousGet(){
        Log.i(TAG,"main thread id is "+Thread.currentThread().getId());
        String url = "https://api.github.com/";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url)
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                // 注：该回调是子线程，非主线程
                Log.i(TAG,"callback thread id is "+Thread.currentThread().getId()+"-----"+Thread.currentThread().getName());
                Log.i(TAG,response.body().string());
                Log.i(TAG,response.headers().toString());
            }
        });
    }

    /**
     * Post 提交键值对
     * okhttp3使用FormBody.Builder创建请求的参数键值对
     */
    private void okHttp_postFromParameters(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 请求完整url：http://api.k780.com:88/?app=weather.future
                    // &weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json
                    String url = "http://api.k780.com:88/";
                    OkHttpClient client = new OkHttpClient();
                    String json = "{'app':'weather.future','weaid':'1','appkey':'10003','sign':'b59bc3ef6191eb9f747dd4e83c99f2a4','format':'json'}";
                    RequestBody formBody = new FormBody.Builder()
                            .add("weaid","1")
                            .add("appkey", "10003")
                            .add("sign","b59bc3ef6191eb9f747dd4e83c99f2a4")
                            .add("format","json")
                            .build();
                    Request request = new Request.Builder()
                            .url(url)
                            .post(formBody)
                            .build();
                    okhttp3.Response reponse = client.newCall(request).execute();
                    Log.d(TAG, "run: response.body"+reponse.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 可以使用post发送一串字符串，
     * 但是不建议超过1M的文本信息（以为字符串会先缓存到文本）
     *
     * 打印结果
     * run: ====<h2>
     <a id="user-content-releases" class="anchor" href="#releases" aria-hidden="true"><span aria-hidden="true" class="octicon octicon-link"></span></a>Releases</h2>

     <ul>
     <li>
     <em>1.0</em> May 6, 2013</li>
     <li>
     <em>1.1</em> June 15, 2013</li>
     <li>
     <em>1.2</em> August 11, 2013</li>
     </ul>
     *
     */
    private void postAString(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String postBody = ""
                        + "Releases\n"
                        + "--------\n"
                        + "\n"
                        + " * _1.0_ May 6, 2013\n"
                        + " * _1.1_ June 15, 2013\n"
                        + " * _1.2_ August 11, 2013\n";
                Request request = new Request.Builder()
                        .url("https://api.github.com/markdown/raw")
                        .post(RequestBody.create(MEDIA_TYPE_MARKDOWN,postBody))
                        .build();
                try {
                    okhttp3.Response respone = client.newCall(request).execute();

                    if(!respone.isSuccessful()) throw new IOException("Unexpected code"+respone);
                    Log.d(TAG, "run: ===="+respone.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * post可以将stream作为请求体，
     * 依赖Okio将数据写成输入流的形式
     */
    private void postStreaming(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return MEDIA_TYPE_MARKDOWN;
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {
                        sink.writeUtf8("Number5s\n");
                        sink.writeUtf8("--------------\n");
                        for (int i = 2; i < 997; i++) {
                            sink.writeUtf8(String.format("*%s = %s\n",i,factor(i)));
                        }
                    }

                    private String factor(int n){
                        for (int i = 2; i < n; i++) {
                            int x = n / i;
                            if(x * i == n){
                                return factor(x) + "x" + i;
                            }
                        }
                        return Integer.toString(n);

                    }
                };

                Request request = new Request.Builder()
                        .url("https://api.github.com/markdown/raw")
                        .post(requestBody)
                        .build();

                try {
                    okhttp3.Response respone = client.newCall(request).execute();
                    if(!respone.isSuccessful()) throw new IOException("Unexpected code"+respone);
                    Log.i(TAG,"callback thread id is "+Thread.currentThread().getId()+"-----"+Thread.currentThread().getName());
                    Log.d(TAG, "run: ===="+respone.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * post 一个file
     */
    private void postFile(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                File file = new File("README.md");

                Request request = new Request.Builder()
                        .url("https://api.github.com/markdown/raw")
                        .post(RequestBody.create(MEDIA_TYPE_MARKDOWN,file))
                        .build();

                try {
                    okhttp3.Response respone = client.newCall(request).execute();
                    if(!respone.isSuccessful()) throw new IOException("Unexpected code"+respone);
                    Log.i(TAG,"callback thread id is "+Thread.currentThread().getId()+"-----"+Thread.currentThread().getName());
                    Log.d(TAG, "run: ===="+respone.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Gson解析Response的Gson对象
     * Response对象的内容是个json字符串，可以使用Gson将字符串格式化为对象
     * ResponseBody.charStream()使用响应头中的Content-Type
     * 作为Response返回数据的编码方式，默认是UTF-8。
     */
    private void useGsonToParseResponse(){
        final OkHttpClient client = new OkHttpClient();
        final Gson gson = new Gson();
        new Thread(new Runnable() {
            @Override
            public void run() {
               Request request = new Request.Builder()
                       .url("https://api.github.com/gists/c2a7c39532239ff261be")
                       .build();

                try {
                    okhttp3.Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    Gist gist = gson.fromJson(response.body().charStream(),Gist.class);
                    for(Map.Entry<String,GistFile> entry:gist.files.entrySet()){
                        Log.d(TAG, "run: entry.getKey()=="+entry.getKey());
                        Log.d(TAG, "run: entry.getValue().content=="+entry.getValue().content);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    static class Gist{
        Map<String,GistFile> files;
    }
    
    static class GistFile{
        String content;
    }

    /**
     * 使用okHttp3中的缓存
     */
    private void useCache(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 打印结果：
     *execute: Response 1 response: Response{protocol=http/1.1, code=200, message=OK, url=https://publicobject.com/helloworld.txt}
     03-30 13:55:19.817 14108-14819/com.android.networkdemo D/liuyuting: execute: Response 1 cache response: null
     03-30 13:55:19.817 14108-14819/com.android.networkdemo D/liuyuting: execute: Response 1 network response: Response{protocol=http/1.1, code=200, message=OK, url=https://publicobject.com/helloworld.txt}
     03-30 13:55:20.067 14108-14819/com.android.networkdemo D/liuyuting: execute: Response 2 response: Response{protocol=http/1.1, code=200, message=OK, url=https://publicobject.com/helloworld.txt}
     03-30 13:55:20.067 14108-14819/com.android.networkdemo D/liuyuting: execute: Response 2 cache response: Response{protocol=http/1.1, code=200, message=OK, url=https://publicobject.com/helloworld.txt}
     03-30 13:55:20.068 14108-14819/com.android.networkdemo D/liuyuting: execute: Response 2 network response: null
     03-30 13:55:20.068 14108-14819/com.android.networkdemo D/liuyuting: execute: Response 2 equals Response 1?true

     但有时候即使在有缓存的情况下我们依然需要去后台请求最新的资源（比如资源更新了）
     这个时候可以使用强制走网络来要求必须请求网络数据
     */
    private void execute() throws Exception{
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();
        okhttp3.Response response1 = mClient.newCall(request).execute();
        if (!response1.isSuccessful()) throw new IOException("Unexpected code " + response1);
        
        String responseBody = response1.body().string();
        Log.d(TAG, "execute: Response 1 response: "+ response1);
        Log.d(TAG, "execute: Response 1 cache response: "+ response1.cacheResponse());
        Log.d(TAG, "execute: Response 1 network response: "+ response1.networkResponse());

        //同样的我们可以使用 FORCE_CACHE 强制只要使用缓存的数据，
        // 但如果请求必须从网络获取才有数据，但又使用了FORCE_CACHE 策略就会返回504错误，
        request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();//加上这一句就是即使有缓存也必须从网络中拿数据了
        okhttp3.Response response2 = mClient.newCall(request).execute();
        if (!response2.isSuccessful()) throw new IOException("Unexpected code " + response2);
        String response2Body = response2.body().string();
        Log.d(TAG, "execute: Response 2 response: "+ response2);
        Log.d(TAG, "execute: Response 2 cache response: "+ response2.cacheResponse());
        Log.d(TAG, "execute: Response 2 network response: "+ response2.networkResponse());
        Log.d(TAG, "execute: Response 2 equals Response 1?"+responseBody.equals(response2Body));
    }

    /**
     * okhttp3 取消请求
     * 如果一个okhttp3网络请求已经不再需要，可以使用Call.cancel()来终止正在准备的同步/异步请求。
     * 如果一个线程正在写一个请求或是读取返回的response，它将会接收到一个IOException。
     */
    private void cancelOkhttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://httpbin.org/delay/2")//This URL is served with a 2 second delay.
                        .build();
                final long startNanos = System.nanoTime();
                final okhttp3.Call call = mClient.newCall(request);

                // Schedule a job to cancel the call in 1 second.
                executor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        System.out.printf("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f);
                        call.cancel();
                        System.out.printf("%.2f Canceled call.%n", (System.nanoTime() - startNanos) / 1e9f);
                    }
                }, 1, TimeUnit.SECONDS);

                try {
                    System.out.printf("%.2f Executing call.%n", (System.nanoTime() - startNanos) / 1e9f);
                    okhttp3.Response response = call.execute();
                    System.out.printf("%.2f Call was expected to fail, but completed: %s%n", (System.nanoTime() - startNanos) / 1e9f, response);
                } catch (IOException e) {
                    System.out.printf("%.2f Call failed as expected: %s%n", (System.nanoTime() - startNanos) / 1e9f, e);
                }
            }

        }).start();
    }

    /**
     * okhttp支持设置连接超时，读写超时
     */
    private void setOkHttpTimeOut(){
        client = new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://httpbin.org/delay/2")//This URL is served with a 2 second delay.
                        .build();

                try {
                    okhttp3.Response response = client.newCall(request).execute();
                    System.out.println("Response completed: " + response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * okhttp3 复用okhttpclient配置
     * 所有HTTP请求的代理设置，超时，缓存设置等都需要在OkHttpClient中设置。
     * 如果需要更改一个请求的配置，可以使用 OkHttpClient.newBuilder()获取一个builder对象，
     * 该builder对象与原来OkHttpClient共享相同的连接池，配置等。
     *
     * I/System.out: Response 1 failed: java.net.SocketTimeoutException
     03-30 14:43:59.049 17003-17560/com.android.networkdemo I/System.out: [CDS]connect[httpbin.org/23.23.128.123:80] tm:10
     03-30 14:44:00.781 17003-17560/com.android.networkdemo I/System.out: Response 2 succeeded: Response{protocol=http/1.1, code=200, message=OK, url=http://httpbin.org/delay/1}
     */
    private void reuseConfiguration(){
        final OkHttpClient client = new OkHttpClient();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://httpbin.org/delay/1")// This URL is served with a 1 second delay.
                        .build();

                try {
                    // Copy to customize OkHttp for this request.
                    OkHttpClient copy = client.newBuilder()
                            .readTimeout(500,TimeUnit.MILLISECONDS)
                            .build();

                    okhttp3.Response response1 = copy.newCall(request).execute();
                    System.out.println("Response 1 succeeded: " + response1);
                } catch (IOException e) {
                    System.out.println("Response 1 failed: " + e);
                }


                try {
                    // Copy to customize OkHttp for this request.
                    OkHttpClient copy = client.newBuilder()
                            .readTimeout(3000,TimeUnit.MILLISECONDS)
                            .build();

                    okhttp3.Response response2 = copy.newCall(request).execute();
                    System.out.println("Response 2 succeeded: " + response2);
                } catch (IOException e) {
                    System.out.println("Response 2 failed: " + e);
                }
            }
        }).start();
    }

    /**
     * okhttp3 处理验证
     * OkHttp可以自动重试未经授权的请求
     *
     * okhttp3 会自动重试未验证的请求。
     * 当一个请求返回401 Not Authorized时，需要提供Anthenticator。
     *
     * 如果没有凭证可用，则返回null跳过重试
     *
     * Authenticating for response: Response{protocol=http/1.1, code=401, message=Unauthorized, url=https://publicobject.com/secrets/hellosecret.txt}
     03-30 15:05:19.037 31148-31589/com.android.networkdemo I/System.out: Challenges: [Basic realm="OkHttp Secrets"]
     */
    private void handleConfirm(){
        final OkHttpClient client = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, okhttp3.Response response) throws IOException {
                        System.out.println("Authenticating for response: " + response);
                        System.out.println("Challenges: " + response.challenges());

                        String credential = Credentials.basic("jesse", "password1");
                        if (credential.equals(response.request().header("Authorization"))) {
                            return null; // If we already failed with these credentials, don't retry.
                        }
                        return response.request().newBuilder()
                                .header("Authorization",credential)
                                .build();

                    }
                })
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://publicobject.com/secrets/hellosecret.txt")
                        .build();

                try {
                    okhttp3.Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
