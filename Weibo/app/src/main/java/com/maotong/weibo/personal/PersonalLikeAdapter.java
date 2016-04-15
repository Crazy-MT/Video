package com.maotong.weibo.personal;

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
 * Created by MaoTong on 2016/4/15.
 * QQ:974291433
 */
public class PersonalLikeAdapter extends RecyclerView.Adapter<PersonalLikeAdapter.LikeViewHolder> {

    private Context mContext;
    private List<HotShowingModel> movieList;
    public IOnItemClickListener mItemClickListener;


    public PersonalLikeAdapter(Context mContext, List<HotShowingModel> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public LikeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_horizontal_movie_recycler, parent, false);
        return new LikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LikeViewHolder holder, int position) {
        HotShowingModel hotShowingModel = movieList.get(position);
        holder.name.setText(hotShowingModel.getName());
        Glide.with(mContext).load(hotShowingModel.getPoster_url()).into(holder.movieImg);
    }


    public IOnItemClickListener getmItemClickListener() {
        return mItemClickListener;
    }

    public void setmItemClickListener(IOnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private interface IOnItemClickListener{
        void onItemClick(View view , int position);
        void onItemLongClick(View view , int position);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class LikeViewHolder extends RecyclerView.ViewHolder {
        // 电影图
        private ImageView movieImg;
        //电影名
        private TextView name;

        public LikeViewHolder(View itemView) {
            super(itemView);
            movieImg = (ImageView) itemView.findViewById(R.id.id_horizontal_movie_img);
            name = (TextView) itemView.findViewById(R.id.id_horizontal_movie_text);
        }
    }
}
