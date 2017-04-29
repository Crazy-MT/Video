package com.taguxdesign.maotong.myvideo.baoman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.taguxdesign.maotong.myvideo.R;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by MaoTong on 2016/3/29.
 * QQ:974291433
 */
public class BaoManAdapter extends RecyclerView.Adapter<BaoManAdapter.MyViewHolder> {


    private int mBackground;
    private List<BaoManModel> mBaoManList;
    private LayoutInflater mInflater;
    private Context mContext;

    public interface OnItemClickLister {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLister mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickLister mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public BaoManAdapter(List<BaoManModel> videoMap, Context context) {
        this.mContext = context;
        this.mBaoManList = videoMap;
        this.mInflater = LayoutInflater.from(context);
        TypedValue mTypedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_baoman, parent, false);
        view.setBackgroundResource(mBackground);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (mBaoManList.size() == 1) {
            holder.textText.setText("请重试");
        }

        holder.textText.setText(mBaoManList.get(position).getText());
        holder.hrefText.setText(mBaoManList.get(position).getHref());
        Glide.with(mContext)
                .load(mBaoManList.get(position).getImg())
                .into(holder.img);

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mBaoManList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textText;
        TextView hrefText;
        CircleImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            textText = (TextView) itemView.findViewById(R.id.id_baoman_text);
            hrefText = (TextView) itemView.findViewById(R.id.id_baoman_href);
            img = (CircleImageView) itemView.findViewById(R.id.id_baoman_img);
        }
    }
}
