package cn.yph.gank.view;

import android.app.Application;

/**
 * Created by yuanpenghao on 16/4/19.
 */
public class GankApplication extends Application {

    public static GankApplication instance;

    public static synchronized GankApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
