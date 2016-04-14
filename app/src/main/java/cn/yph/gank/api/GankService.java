package cn.yph.gank.api;

import cn.yph.gank.entity.MeizhiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yuanpenghao on 16/4/13.
 */
public interface GankService {
    @GET("福利/{Size}/{Page}"    )
    Call<MeizhiResponse> getMeiZhi(@Path("Size") int size, @Path("Page") int page);
}
