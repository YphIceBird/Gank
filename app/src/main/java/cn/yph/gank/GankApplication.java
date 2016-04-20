package cn.yph.gank;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.squareup.okhttp.Interceptor;

import okhttp3.OkHttpClient;

/**
 * Created by yuanpenghao on 16/4/19.
 */
public class GankApplication extends Application {

    private OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        Fresco.initialize(getApplicationContext());
        com.squareup.okhttp.OkHttpClient frescoOkHttp = new com.squareup.okhttp.OkHttpClient();
        frescoOkHttp.networkInterceptors().add(new com.facebook.stetho.okhttp.StethoInterceptor());
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, frescoOkHttp)
                .build();
        Fresco.initialize(this, config);
        okHttpClient = new OkHttpClient().newBuilder().addNetworkInterceptor(new StethoInterceptor())
                .build();

    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
