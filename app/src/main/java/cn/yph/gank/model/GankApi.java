package cn.yph.gank.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yuanpenghao on 16/4/13.
 */
public interface GankApi {
    @GET("福利/{Size}/{Page}"    )
    Call<MeizhiResponse> getMeiZhi(@Path("Size") int size, @Path("Page") int page);
}
