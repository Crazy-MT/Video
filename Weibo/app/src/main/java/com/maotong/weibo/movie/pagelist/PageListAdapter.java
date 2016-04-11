package com.maotong.weibo.movie.pagelist;

import android.content.Context;
import android.graphics.Movie;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maotong.weibo.R;
import com.maotong.weibo.movie.hotshowing.HotShowingModel;
import com.maotong.weibo.utils.JsonResolveUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaoTong on 2016/4/7.
 * QQ:974291433
 */
public class PageListAdapter extends RecyclerView.Adapter<PageListAdapter.PageListViewHolder> {

    private List<PageListModel> pageListModels;
    private Context context;
    private LayoutInflater inflater;
    List<List<HotShowingModel>> mPageListMovie;


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

    public PageListAdapter(Context context, List<PageListModel> pageListModels, List<List<HotShowingModel>> mPageListMovie) {
        this.pageListModels = pageListModels;
        this.mPageListMovie = mPageListMovie;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public PageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_pagelist_recycler, parent, false);
        return new PageListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PageListViewHolder holder, int position) {
        PageListModel pageListModel = pageListModels.get(position);
        holder.name.setText(pageListModel.getName());
        List<HotShowingModel> movies = mPageListMovie.get(position);
        Log.e("tag", "onBindViewHolder: mPageListMovie" + mPageListMovie.toString());
        PageListItemAdapter pageListItemAdapter = new PageListItemAdapter(context , movies);
        holder.recyclerView.setAdapter(pageListItemAdapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , false));
    }

    private void setUpRecyclerView(PageListViewHolder holder) {

    }


    @Override
    public int getItemCount() {
        return pageListModels.size();
    }

    class PageListViewHolder extends RecyclerView.ViewHolder {

        //影单名
        private TextView name;

        //
        private RecyclerView recyclerView;

        public PageListViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.id_item_pagelist_name);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.id_item_pagelist_recycler);
        }
    }
}
