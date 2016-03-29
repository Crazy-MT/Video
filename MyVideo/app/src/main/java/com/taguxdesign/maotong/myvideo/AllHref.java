package com.taguxdesign.maotong.myvideo;

import android.graphics.LinearGradient;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MaoTong on 2016/3/29.
 * QQ:974291433
 */
public class AllHref {

    private static final String TAG = "AllHref";
    private static String URL = "http://www.dy2018.com/";
    private List<VideoModel> videoMap;
    private VideoModel videoModel;

    public List<VideoModel> getAllHref() {

        Document doc = null;
        videoMap = new ArrayList<>();
        try {
            doc = Jsoup.connect(URL).get();
            Elements links = doc.select("a[href]");
            Elements media = doc.select("[src]");
            Elements imports = doc.select("link[href]");

            for (Element link : imports) {
                Log.e(TAG, "getAllHref: " + link.attr("abs:href") + " " + link.attr("rel"));
            }

            for (Element link : links) {
                Log.e(TAG, "getAllHref: " + link.attr("abs:href") + " " + trim(link.text(), 35));
                addToList(link);
            }

        } catch (Exception e) {
            e.printStackTrace();
            videoMap.add(new VideoModel(new String() , new String()));
        }

        return videoMap;

    }

    private void addToList(Element link) {

        if (link.attr("abs:href").contains("http://www.dy2018.com/i/")){
            VideoModel videoModel = new VideoModel(trim(link.text(), 35) , link.attr("abs:href"));
            videoMap.add(videoModel);
        }
    }

    private static String trim(String s, int width) {
        if (s.length() > width) {
            return s.substring(0, width - 1) + ".";
        } else {
            return s;
        }
    }
}
