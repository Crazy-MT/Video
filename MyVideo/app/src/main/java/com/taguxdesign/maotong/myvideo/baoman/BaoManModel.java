package com.taguxdesign.maotong.myvideo.baoman;

import com.taguxdesign.maotong.myvideo.VideoModel;

/**
 * Created by MaoTong on 2016/3/30.
 * QQ:974291433
 */
public class BaoManModel extends VideoModel{

    private String img;

    public BaoManModel(String img ,String text, String href) {
        super(text, href);
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
