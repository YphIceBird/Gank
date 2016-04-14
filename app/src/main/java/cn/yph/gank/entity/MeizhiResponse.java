package cn.yph.gank.entity;

import java.util.List;

/**
 * Created by yuanpenghao on 16/4/13.
 */
public class MeizhiResponse {

    private boolean error;
    private List<GankItem> results;

    public List<GankItem> getResults() {
        return results;
    }

    public boolean isError() {
        return error;
    }
}
