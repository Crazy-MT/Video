package com.maotong.weibo.movie.coming;

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
import com.maotong.weibo.personal.LoginStatusEvent;
import com.maotong.weibo.utils.JsonResolveUtils;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by MaoTong on 2016/4/7.
 * QQ:974291433
 */
public class ComingAdapter extends RecyclerView.Adapter<ComingAdapter.ComingViewHolder> {

    private List<MovieModel> comingModels;
    private Context context;
    private LayoutInflater inflater;
    private Oauth2AccessToken mAccessToken;
    private static String HANDLER_LIKE = "like";
    private static String HANDLER_LIKE_YES = "yes";
    private static String HANDLER_LIKE_NO = "no";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int position = msg.what;
            if (HANDLER_LIKE_YES.equals(bundle.get(HANDLER_LIKE))){
                comingModels.get(position).setIsLike(1);
            } else {
                comingModels.get(position).setIsLike(0);
            }
            notifyItemChanged(position);
            EventBus.getDefault().post(new LoginStatusEvent(true));
        }
    };
    public interface OnItemClickListener {
        void onItemClick(View view, int position , int isLike);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ComingAdapter(Context context, List<MovieModel> comingModels) {
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
    public void onBindViewHolder(final ComingViewHolder holder, final int position) {
        final MovieModel comingModel = comingModels.get(position);
        holder.like.setText("" + "人想看");
        holder.releaseDate.setText(comingModel.getRelease_date() + "上映");
        holder.name.setText(comingModel.getName());
        Glide.with(context).load(comingModel.getPoster_url()).into(holder.movieBg);

        if (comingModel.getIsLike() == 0){
            holder.isLike.setImageResource(R.mipmap.home_interested_normal);
        } else if(comingModel.getIsLike() == 1){
            holder.isLike.setImageResource(R.mipmap.home_interested_selected);
        }

        holder.isLikeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读取sp ， 判断是否登录 ， 如果没登录，则跳转到登录界面。如果登录了，则改变图片、然后将收藏信息发送给后台，后台更新数据表
                mAccessToken = AccessTokenKeeper.readAccessToken(context);
                if (mAccessToken != null && mAccessToken.isSessionValid()) {
                    if (comingModel.getIsLike() == 0){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean isSuccess = new JsonResolveUtils(context).setLikeMovie(mAccessToken.getUid(), comingModel.getId() , true);
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
                    } else { //点击之后取消收藏还未完成。发送请求 、 后台还没写。
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                boolean isSuccess = new JsonResolveUtils(context).setLikeMovie(mAccessToken.getUid(), comingModel.getId() , false);
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
                    int isLike = comingModel.getIsLike();
                    onItemClickListener.onItemClick(holder.itemView, pos , isLike);
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
        private LinearLayout isLikeClick;
        public ComingViewHolder(View itemView) {
            super(itemView);
            movieBg = (ImageView) itemView.findViewById(R.id.id_item_coming);
            like = (TextView) itemView.findViewById(R.id.id_item_coming_like);
            name = (TextView) itemView.findViewById(R.id.id_item_coming_name);
            releaseDate = (TextView) itemView.findViewById(R.id.id_item_coming_premiere_text);
            isLike = (ImageView) itemView.findViewById(R.id.id_item_coming_like_img);
            isLikeClick = (LinearLayout) itemView.findViewById(R.id.id_item_coming_like_click);
        }
    }
}
