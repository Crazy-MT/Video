package com.maotong.weibo.movie.pagelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maotong.weibo.R;
import com.maotong.weibo.main.moviedetail.MovieDetailActivity;
import com.maotong.weibo.main.MovieModel;

import java.util.List;

/**
 * Created by MaoTong on 2016/4/7.
 * QQ:974291433
 */
public class PageListAdapter extends RecyclerView.Adapter<PageListAdapter.PageListViewHolder> {

    private List<PageListModel> pageListModels;
    private Context context;
    private LayoutInflater inflater;
    List<List<MovieModel>> mPageListMovie;


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

    public PageListAdapter(Context context, List<PageListModel> pageListModels, List<List<MovieModel>> mPageListMovie) {
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
        final List<MovieModel> movies = mPageListMovie.get(position);
        holder.name.setText(pageListModel.getName());
        holder.count.setText(movies.size()+"部");
        PageListItemAdapter pageListItemAdapter = new PageListItemAdapter(context , movies);
        holder.recyclerView.setAdapter(pageListItemAdapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , false));
        pageListItemAdapter.setOnItemClickListener(new PageListItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转到电影页面
                Intent intent = new Intent(context, MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(MovieDetailActivity.MOVIE, movies.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
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
        private TextView count;

        public PageListViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.id_item_pagelist_name);
            count = (TextView) itemView.findViewById(R.id.id_item_pagelist_count);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.id_item_pagelist_recycler);
        }
    }
}
