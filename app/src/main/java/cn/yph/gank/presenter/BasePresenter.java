package cn.yph.gank.presenter;

import cn.yph.gank.view.UIListener;

/**
 * Created by yuanpenghao on 16/4/21.
 */
public abstract class BasePresenter<T, L extends UIListener<T>> {

    L listener;

    public void setListener(L listener) {
        this.listener = listener;
    }

    public abstract void initData();

    public abstract void initDataSuccess(T result);

}
