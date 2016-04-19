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
import com.maotong.weibo.main.MovieModel;

import java.util.List;

/**
 * Created by MaoTong on 2016/4/7.
 * QQ:974291433
 */
public class PageListItemAdapter extends RecyclerView.Adapter<PageListItemAdapter.PageListItemViewHolder> {

    private List<MovieModel> mMovieModels;
    private Context mContext;
    private LayoutInflater mInflater;

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

    public PageListItemAdapter(Context context, List<MovieModel> mMovieModels) {
        this.mMovieModels = mMovieModels;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public PageListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_horizontal_movie_recycler, parent, false);
        return new PageListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PageListItemViewHolder holder, final int position) {
        MovieModel movieModel = mMovieModels.get(position);
        holder.name.setText(movieModel.getName());
        Glide.with(mContext).load(movieModel.getPoster_url()).into(holder.movieImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovieModels.size();
    }

    class PageListItemViewHolder extends RecyclerView.ViewHolder {

        // 电影图
        private ImageView movieImg;
        //电影名
        private TextView name;

        public PageListItemViewHolder(View itemView) {
            super(itemView);
            movieImg = (ImageView) itemView.findViewById(R.id.id_horizontal_movie_img);
            name = (TextView) itemView.findViewById(R.id.id_horizontal_movie_text);
        }
    }
}
