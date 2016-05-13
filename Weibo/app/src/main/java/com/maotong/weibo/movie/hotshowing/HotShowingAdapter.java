package com.maotong.weibo.movie.hotshowing;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.maotong.weibo.R;
import com.maotong.weibo.api.AccessTokenKeeper;
import com.maotong.weibo.main.MovieModel;
import com.maotong.weibo.personal.UpLikeRecyclerEvent;
import com.maotong.weibo.utils.JsonResolveUtils;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by MaoTong on 2016/4/7.
 * QQ:974291433
 */
public class HotShowingAdapter extends RecyclerView.Adapter<HotShowingAdapter.HotShowingViewHolder> {

    private List<MovieModel> movieModels;
    private Context context;
    private LayoutInflater inflater;
    private static String HANDLER_LIKE = "like";
    private static String HANDLER_LIKE_YES = "yes";
    private static String HANDLER_LIKE_NO = "no";
    private Oauth2AccessToken mAccessToken;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int position = msg.what;
            if (HANDLER_LIKE_YES.equals(bundle.get(HANDLER_LIKE))){
                movieModels.get(position).setIsLike(1);
            } else {
                movieModels.get(position).setIsLike(0);
            }
            notifyItemChanged(position);
            EventBus.getDefault().post(new UpLikeRecyclerEvent(true));
        }
    };

    public interface OnItemClickListener {
        void onItemClick(View view, int position , int isLike , ImageView imageView);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public HotShowingAdapter(Context context, List<MovieModel> movieModels) {
        this.movieModels = movieModels;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public HotShowingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_hot_showing_recycler, parent, false);
        return new HotShowingViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final HotShowingViewHolder holder, final int position) {
        final MovieModel movieModel = movieModels.get(position);
        holder.comment.setText(movieModel.getScore_count() + "人点评");
        holder.score.setText(movieModel.getScore() + "");
        holder.name.setText(movieModel.getName());
        Glide.with(context).load(movieModel.getPoster_url()).into(holder.movieBg);
        if (movieModel.getIsLike() == 1) {
            holder.isLike.setImageResource(R.mipmap.home_interested_selected);
        } else {
            holder.isLike.setImageResource(R.mipmap.home_interested_normal);
        }

        holder.likeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读取sp ， 判断是否登录 ， 如果没登录，则跳转到登录界面。如果登录了，则改变图片、然后将收藏信息发送给后台，后台更新数据表
                mAccessToken = AccessTokenKeeper.readAccessToken(context);
                if (mAccessToken != null && mAccessToken.isSessionValid()) {
                    if (movieModel.getIsLike() == 0){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean isSuccess = new JsonResolveUtils(context).setLikeMovie(mAccessToken.getUid(), movieModel.getId() , true);
                                if (isSuccess) {
                                    Message message = new Message();
                                    Bundle bundle = new Bundle();
                                    bundle.putString( HANDLER_LIKE, HANDLER_LIKE_YES);
                                    message.setData(bundle);
                                    message.what = position;
                                    mHandler.sendMessage(message);
                                }
                            }
                        }).start();
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean isSuccess = new JsonResolveUtils(context).setLikeMovie(mAccessToken.getUid(), movieModel.getId() , false);
                                if (isSuccess) {
                                    Message message = new Message();
                                    Bundle bundle = new Bundle();
                                    bundle.putString( HANDLER_LIKE, HANDLER_LIKE_NO);
                                    message.setData(bundle);
                                    message.what = position;
                                    mHandler.sendMessage(message);
                                }
                            }
                        }).start();
                    }

                } else {
                    Toast.makeText(context, "请先登录", Toast.LENGTH_LONG).show();
                }
            }
        });
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    int isLike = movieModel.getIsLike();
                    onItemClickListener.onItemClick(holder.itemView, pos , isLike ,  holder.movieBg);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    class HotShowingViewHolder extends RecyclerView.ViewHolder {
        // 电影背景图
        private ImageView movieBg;
        //电影评分
        private TextView score;
        //评论数量
        private TextView comment;
        //电影名
        private TextView name;
        //是否收藏
        private ImageView isLike;
        private LinearLayout likeClick;

        public HotShowingViewHolder(View itemView) {
            super(itemView);
            movieBg = (ImageView) itemView.findViewById(R.id.id_item_hot_showing);
            score = (TextView) itemView.findViewById(R.id.id_item_hot_showing_score);
            name = (TextView) itemView.findViewById(R.id.id_item_hot_showing_name);
            comment = (TextView) itemView.findViewById(R.id.id_item_hot_showing_comment);
            isLike = (ImageView) itemView.findViewById(R.id.id_item_hot_showing_like);
            likeClick = (LinearLayout) itemView.findViewById(R.id.id_item_hot_showing_like_click);
        }
    }
}
