package com.taguxdesign.maotong.myvideo;

/**
 * Created by MaoTong on 2016/3/29.
 * QQ:974291433
 */
public class VideoModel {

    protected String text ;
    protected String href ;

    public VideoModel(String text, String href) {
        this.text = text;
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

