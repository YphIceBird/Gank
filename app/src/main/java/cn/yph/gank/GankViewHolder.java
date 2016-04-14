package cn.yph.gank;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by yuanpenghao on 16/4/13.
 */
public class GankViewHolder extends RecyclerView.ViewHolder{
    SimpleDraweeView image;
    TextView name;

    public GankViewHolder(View itemView) {
        super(itemView);
        image = (SimpleDraweeView) itemView.findViewById(R.id.itemImg);
        name = (TextView) itemView.findViewById(R.id.itemName);
    }
}
