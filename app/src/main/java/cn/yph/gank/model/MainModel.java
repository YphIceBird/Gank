package cn.yph.gank.model;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yuanpenghao on 16/4/21.
 */
public class MainModel {

    Observable<MeizhiResponse> myObservable;

    private Call<MeizhiResponse> mzCall;

    private GankApi service;

    public MainModel() {
        service = NetworkConfig.getInstance().getGankRetrofit().create(GankApi.class);
    }

    public void loadData(int size, int page, Subscriber<MeizhiResponse> subscriber) {
        mzCall = service.getMeiZhi(size, page);
        myObservable = Observable.create(
                new Observable.OnSubscribe<MeizhiResponse>() {
                    @Override
                    public void call(Subscriber<? super MeizhiResponse> sub) {
                        try {
                            Response<MeizhiResponse> response = mzCall.execute();
                            MeizhiResponse meizhiResponse = response.body();
                            if (meizhiResponse.isError()) {
                                sub.onError(new Exception("error"));
                            } else {
                                sub.onNext(meizhiResponse);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        myObservable.subscribe(subscriber);
    }

}
