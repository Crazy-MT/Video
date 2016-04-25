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

import java.util.List;

/**
 * Created by MaoTong on 2016/4/7.
 * QQ:974291433
 */
public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {

    private List<Actor> actorModels;
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

    public ActorAdapter(Context context, List<Actor> actorModels) {
        this.actorModels = actorModels;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_actor_recycler, parent, false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ActorViewHolder holder, final int position) {
        final Actor actor = actorModels.get(position);
        Glide.with(context).load(actor.getUrl()).asBitmap().centerCrop().into(new BitmapImageViewTarget
                (holder.actorImg) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.actorImg.setImageDrawable(circularBitmapDrawable);
            }
        });
        holder.actorText.setText(actor.getName());
    }

    @Override
    public int getItemCount() {
        return actorModels.size();
    }

    class ActorViewHolder extends RecyclerView.ViewHolder {

        private ImageView actorImg;
        private TextView actorText;
        public ActorViewHolder(View itemView) {
            super(itemView);
            actorImg = (ImageView) itemView.findViewById(R.id.id_item_actor_img);
            actorText = (TextView) itemView.findViewById(R.id.id_item_actor_text);
        }
    }
}
