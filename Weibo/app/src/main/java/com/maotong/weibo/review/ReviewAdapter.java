package com.maotong.weibo.review;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.maotong.weibo.R;

import java.util.List;

/**
 * Created by MaoTong on 2016/4/7.
 * QQ:974291433
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<ReviewModel> reviewModels;
    private Context context;
    private LayoutInflater inflater;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, int isLike, ImageView imageView);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ReviewAdapter(Context context, List<ReviewModel> reviewModels) {
        this.reviewModels = reviewModels;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_review_recycler, parent, false);
        return new ReviewViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ReviewViewHolder holder, final int position) {
        ReviewModel reviewModel = reviewModels.get(position);
        holder.movieName.setText(reviewModel.getMovieName());
        holder.content.setText(reviewModel.getText());
        holder.userName.setText(reviewModel.getUserName());
        Glide.with(context).load(reviewModel.getUserIcon()).asBitmap().centerCrop().into(new BitmapImageViewTarget
                (holder.userIconImg) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.userIconImg.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reviewModels.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        private ImageView userIconImg;
        private TextView userName;
        private TextView content;
        private TextView movieName;
        public ReviewViewHolder(View itemView) {
            super(itemView);
            userIconImg = (ImageView) itemView.findViewById(R.id.id_item_review_user_icon);
            userName = (TextView) itemView.findViewById(R.id.id_item_review_user_name);
            content = (TextView) itemView.findViewById(R.id.id_item_review_context);
            movieName= (TextView) itemView.findViewById(R.id.id_item_review_movie_name);
        }
    }
}
