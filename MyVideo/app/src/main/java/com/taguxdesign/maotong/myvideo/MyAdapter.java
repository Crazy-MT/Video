package com.taguxdesign.maotong.myvideo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by MaoTong on 2016/3/29.
 * QQ:974291433
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


    private List<VideoModel> videoMap;
    private LayoutInflater mInflater;

    public interface OnItemClickLister{
        void onItemClick(View view , int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLister mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickLister mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public MyAdapter(List<VideoModel> videoMap , Context context) {
        this.videoMap = videoMap;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_video , parent , false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.textText.setText(videoMap.get(position).getText());
        holder.hrefText.setText(videoMap.get(position).getHref());

        if (mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView , pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView , pos);
                    return false;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return videoMap.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textText;
        TextView hrefText;

        public MyViewHolder(View itemView) {
            super(itemView);
            textText = (TextView) itemView.findViewById(R.id.id_item_text);
            hrefText = (TextView) itemView.findViewById(R.id.id_item_href);
        }
    }
}
