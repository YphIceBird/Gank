package cn.yph.gank.model;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import cn.yph.gank.Conifg;
import cn.yph.gank.view.GankApplication;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yuanpenghao on 16/4/21.
 */
public class NetworkConfig {

    private static NetworkConfig networkConfig;

    private static Retrofit gankRetrofit;

    public synchronized static NetworkConfig getInstance() {
        if (networkConfig == null) {
            networkConfig = new NetworkConfig(GankApplication.getInstance());
        }
        return networkConfig;
    }

    private NetworkConfig(GankApplication application) {
        com.squareup.okhttp.OkHttpClient frescoOkHttp = new com.squareup.okhttp.OkHttpClient();
        OkHttpClient okHttpClient;
        if (Conifg.DEBUG) {
            Stetho.initializeWithDefaults(application);
            frescoOkHttp.networkInterceptors().add(new com.facebook.stetho.okhttp.StethoInterceptor());
            okHttpClient = new OkHttpClient().newBuilder().addNetworkInterceptor(new StethoInterceptor())
                    .build();
        } else {
            okHttpClient = new OkHttpClient().newBuilder().build();
        }

        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(application, frescoOkHttp)
                .build();
        Fresco.initialize(application, config);

        gankRetrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }


    public Retrofit getGankRetrofit() {
        return gankRetrofit;
    }

}
