package com.maotong.weibo.main.moviedetail;

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

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by MaoTong on 2016/4/7.
 * QQ:974291433
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ActorViewHolder> {

    private List<Comment> commentModels;
    private Context context;
    private LayoutInflater inflater;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, int isLike);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CommentAdapter(Context context, List<Comment> commentModels) {
        this.commentModels = commentModels;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ActorViewHolder holder, final int position) {
        final Comment comment = commentModels.get(position);
        holder.content.setText(comment.getCommentText());
        holder.userName.setText(comment.getUserName());

        Glide.with(context).load(comment.getUserIcon()).asBitmap().centerCrop().into(new BitmapImageViewTarget
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
        return commentModels.size();
    }

    class ActorViewHolder extends RecyclerView.ViewHolder {

        private ImageView userIconImg;
        private TextView userName;
        private TextView content;
        public ActorViewHolder(View itemView) {
            super(itemView);
            userIconImg = (ImageView) itemView.findViewById(R.id.id_item_comment_user_icon);
            userName = (TextView) itemView.findViewById(R.id.id_item_comment_user_name);
            content = (TextView) itemView.findViewById(R.id.id_item_comment_context);
        }
    }
}
