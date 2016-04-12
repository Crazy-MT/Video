package com.maotong.weibo.movie.coming;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maotong.weibo.R;

import java.util.List;

/**
 * Created by MaoTong on 2016/4/7.
 * QQ:974291433
 */
public class ComingAdapter extends RecyclerView.Adapter<ComingAdapter.ComingViewHolder> {

    private List<ComingModel> comingModels;
    private Context context;
    private LayoutInflater inflater;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ComingAdapter(Context context, List<ComingModel> comingModels) {
        this.comingModels = comingModels;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ComingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_coming_recycler, parent, false);
        return new ComingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ComingViewHolder holder, int position) {
        ComingModel comingModel = comingModels.get(position);
        holder.like.setText(comingModel.getWanttosee() + "人想看");
        holder.releaseDate.setText(comingModel.getRelease_date() + "上映");
        holder.name.setText(comingModel.getName());
        Glide.with(context).load(comingModel.getPoster_url()).into(holder.movieBg);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return comingModels.size();
    }

    class ComingViewHolder extends RecyclerView.ViewHolder {

        // 电影背景图
        private ImageView movieBg;
        //电影评分
        private TextView like;
        //评论数量
        private TextView releaseDate;
        //电影名
        private TextView name;
        //是否收藏
        private ImageView isLike;

        public ComingViewHolder(View itemView) {
            super(itemView);
            movieBg = (ImageView) itemView.findViewById(R.id.id_item_coming);
            like = (TextView) itemView.findViewById(R.id.id_item_coming_like);
            name = (TextView) itemView.findViewById(R.id.id_item_coming_name);
            releaseDate = (TextView) itemView.findViewById(R.id.id_item_coming_premiere_text);
            isLike = (ImageView) itemView.findViewById(R.id.id_item_coming_like_img);
        }
    }
}
