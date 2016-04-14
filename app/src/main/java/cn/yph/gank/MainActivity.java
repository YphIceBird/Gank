package cn.yph.gank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.yph.gank.api.GankService;
import cn.yph.gank.entity.MeizhiResponse;
import cn.yph.gank.entity.GankItem;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.gankBg)
    ImageView gankBg;
    @Bind(R.id.gankList)
    RecyclerView gankList;

    GankAdapter adapter;

    Call<MeizhiResponse> mzCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GankService service = retrofit.create(GankService.class);
        mzCall = service.getMeiZhi(10, 1);

        adapter = new GankAdapter(this);
        gankList.setAdapter(adapter);
        gankList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        myObservable.subscribe(mySubscriber);
    }

    Observable<List<GankItem>> myObservable = Observable.create(
            new Observable.OnSubscribe<List<GankItem>>() {
                @Override
                public void call(Subscriber<? super List<GankItem>> sub) {
                    try {
                        Response<MeizhiResponse> response = mzCall.execute();
                        MeizhiResponse meizhiResponse = response.body();
                        if (meizhiResponse.isError()) {
                            sub.onError(new Exception("error"));
                        } else {
                            sub.onNext(meizhiResponse.getResults());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    Subscriber<List<GankItem>> mySubscriber = new Subscriber<List<GankItem>>() {
        @Override
        public void onNext(List<GankItem> s) {
            adapter.setData(s);
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }
    };

}
