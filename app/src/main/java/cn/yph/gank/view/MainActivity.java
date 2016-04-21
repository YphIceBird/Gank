package cn.yph.gank.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.yph.gank.R;
import cn.yph.gank.model.MeizhiResponse;
import cn.yph.gank.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter, MainUIListener> implements MainUIListener {

    @Bind(R.id.gankBg)
    ImageView gankBg;
    @Bind(R.id.gankList)
    RecyclerView gankList;

    GankAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new GankAdapter(this);
        gankList.setAdapter(adapter);
        gankList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected MainUIListener createUIListener() {
        return this;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }


    @OnClick(R.id.nextPage)
    public void nextPage(View view) {
        presenter.getNextPage();
    }

    @Override
    public void initDataSuccess(MeizhiResponse result) {
        adapter.setData(result.getResults());
    }
}
