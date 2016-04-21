package cn.yph.gank.presenter;

import cn.yph.gank.model.MainModel;
import cn.yph.gank.model.MeizhiResponse;
import cn.yph.gank.view.MainUIListener;
import rx.Subscriber;

/**
 * Created by yuanpenghao on 16/4/21.
 */
public class MainPresenter extends BasePresenter<MeizhiResponse, MainUIListener> {

    private MainModel mainModel;

    private int page = 1;
    private int size = 20;

    @Override
    public void initData() {
        mainModel = new MainModel();
        mainModel.loadData(size, page, mySubscriber);
    }

    @Override
    public void initDataSuccess(MeizhiResponse result) {
        listener.initDataSuccess(result);
    }

    public void getNextPage() {
        page++;
        mainModel.loadData(size, page, mySubscriber);
    }


    Subscriber<MeizhiResponse> mySubscriber = new Subscriber<MeizhiResponse>() {
        @Override
        public void onNext(MeizhiResponse s) {
            initDataSuccess(s);
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }
    };

}
