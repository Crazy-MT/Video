package com.maotong.weibo.personal;

/**
 * Created by MaoTong on 2016/4/15.
 * QQ:974291433
 */
public class UpLikeRecyclerEvent {

    private boolean isNotifyRecycler = false;

    public boolean isNotifyRecycler() {
        return isNotifyRecycler;
    }

    public void setNotifyRecycler(boolean notifyRecycler) {
        isNotifyRecycler = notifyRecycler;
    }

    public UpLikeRecyclerEvent(boolean isNotifyRecycler) {
        this.isNotifyRecycler = isNotifyRecycler;
    }
}
