package com.maotong.weibo.movie.pagelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maotong.weibo.R;
import com.maotong.weibo.movie.hotshowing.HotShowingModel;

import java.util.List;

/**
 * Created by MaoTong on 2016/4/7.
 * QQ:974291433
 */
public class PageListItemAdapter extends RecyclerView.Adapter<PageListItemAdapter.PageListItemViewHolder> {

    private List<HotShowingModel> hotShowingModels;
    private Context context;
    private LayoutInflater inflater;

    private interface OnItemClickListener {
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

    public PageListItemAdapter(Context context, List<HotShowingModel> hotShowingModels) {
        this.hotShowingModels = hotShowingModels;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public PageListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_pagelist_item_recycler, parent, false);
        return new PageListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PageListItemViewHolder holder, int position) {
        HotShowingModel hotShowingModel = hotShowingModels.get(position);
        holder.name.setText(hotShowingModel.getName());
        Glide.with(context).load(hotShowingModel.getPoster_url()).into(holder.movieImg);
    }

    @Override
    public int getItemCount() {
        return hotShowingModels.size();
    }

    class PageListItemViewHolder extends RecyclerView.ViewHolder {

        // 电影图
        private ImageView movieImg;
        //电影名
        private TextView name;

        public PageListItemViewHolder(View itemView) {
            super(itemView);
            movieImg = (ImageView) itemView.findViewById(R.id.id_page_list_item_movie_img);
            name = (TextView) itemView.findViewById(R.id.id_page_list_item_movie_text);
        }
    }
}
