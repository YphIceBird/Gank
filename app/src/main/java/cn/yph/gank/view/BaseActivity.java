package cn.yph.gank.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.yph.gank.presenter.BasePresenter;

public abstract class BaseActivity<T extends BasePresenter, L extends UIListener> extends AppCompatActivity {

    public T presenter;
    public L listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        listener = createUIListener();
        presenter.setListener(listener);
        presenter.initData();
    }

    protected abstract L createUIListener();

    public GankApplication getGankApplication() {
        return (GankApplication) getApplication();
    }


    public abstract T createPresenter();

}
