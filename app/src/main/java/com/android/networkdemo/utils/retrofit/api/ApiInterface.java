package com.android.networkdemo.utils.retrofit.api;

import com.android.networkdemo.entity.LoginResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author: liuyuting
 * Description: NetWorkDemo
 * Since: 2017/3/29 11:23
 *  @POST(ApiConst.CREATE)
    Observable<HttpResult> create(@Body Map map);
 */

public interface ApiInterface {

    @GET(ApiConst.LOGIN1)
    Call<LoginResult> getData(@Query("name") String name, @Query("password") String pw);
}
