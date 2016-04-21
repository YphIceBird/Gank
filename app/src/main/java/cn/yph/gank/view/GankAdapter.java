package cn.yph.gank.view;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.yph.gank.R;
import cn.yph.gank.model.GankItem;

/**
 * Created by yuanpenghao on 16/4/13.
 */
public class GankAdapter extends RecyclerView.Adapter<GankViewHolder> {

    Context context;
    List<GankItem> data;

    public GankAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<GankItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public GankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GankViewHolder(View.inflate(context, R.layout.item_gank, null));
    }

    @Override
    public void onBindViewHolder(GankViewHolder holder, int position) {
        holder.image.setImageURI(Uri.parse(data.get(position).getUrl()));
        holder.name.setText(data.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }
}
