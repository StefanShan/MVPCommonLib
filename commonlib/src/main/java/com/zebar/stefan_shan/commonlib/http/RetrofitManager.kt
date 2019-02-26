package com.zebar.stefan_shan.commonlib.http

import com.zebar.stefan_shan.commonlib.BaseApplication
import com.zebar.stefan_shan.commonlib.base.AppConstant
import com.zebar.stefan_shan.commonlib.util.LogUtil
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author: Stefan_Shan
 * @data: 2018/8/31
 * @desc: Retrofit处理类
 */
class RetrofitManager {

    companion object {
        fun getInstance(): RetrofitManager {
            return RetrofitManagerHodler.singleton
        }
    }

    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null

    private object RetrofitManagerHodler {
        internal val singleton = RetrofitManager()
    }

    //创建 Retrofit
    fun <T> create(service: Class<T>): T {
        return retrofit!!.create(service)
    }

    //取消请求
    fun cancelTag(tag: Any) {
        okHttpClient?.dispatcher()?.queuedCalls()
            ?.filter { tag == it.request().tag() }
            ?.forEach { it.cancel() }
        okHttpClient?.dispatcher()?.runningCalls()
            ?.filter { tag == it.request().tag() }
            ?.forEach { it.cancel() }
    }

    init {
        //配置缓存
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        val cache = Cache(BaseApplication.application.cacheDir, cacheSize.toLong())
        //配置请求拦截日志
        val logInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            LogUtil.d("InterceptorLog", message)
        })
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        //配置请求拦截 - 用于添加token等
        val requestInterceptor = Interceptor {
            LogUtil.d("requestInterceptor", it.request().url().toString())
            return@Interceptor it.proceed(it.request())
        }
        okHttpClient =
            OkHttpClient.Builder().cache(cache).addInterceptor(logInterceptor).addInterceptor(requestInterceptor)
                .build()
        retrofit = Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}