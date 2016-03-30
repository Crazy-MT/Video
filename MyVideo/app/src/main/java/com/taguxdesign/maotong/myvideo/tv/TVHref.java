package com.taguxdesign.maotong.myvideo.tv;

import android.util.Log;

import com.taguxdesign.maotong.myvideo.movie.VideoModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaoTong on 2016/3/29.
 * QQ:974291433
 */
public class TVHref {

    private static final String TAG = "TVHref";
    private static String URL = "http://cn163.net/2014the-tv-show/";
    private List<VideoModel> tvList;
    private static final String TAG_LINK = "http://cn163.net/archives/";

    public List<VideoModel> getAllHref() {

        Document doc = null;
        tvList = new ArrayList<>();
        try {
            doc = Jsoup.connect(URL).get();
            Elements links = doc.select("a[href]");
            /*Elements media = doc.select("[src]");
            Elements imports = doc.select("link[href]");

            mediaAndImports();*/

            for (Element link : links) {
                Log.e(TAG, "getAllHref: " + link.attr("abs:href") + " " + trim(link.text(), 35));
                addToList(link);
            }

        } catch (Exception e) {
            e.printStackTrace();
            tvList.add(new VideoModel(e.toString() , new String()));
        }

        return tvList;

    }

    private void mediaAndImports() {
        /*for (Element src : media) {
                if (src.tagName().equals("img"))
                    print(" * %s: <%s> %sx%s (%s)",
                            src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                            trim(src.attr("alt"), 20));
                else
                    print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
            }

            for (Element link : imports) {
                Log.e(TAG, "getAllHref: " + link.attr("abs:href") + " " + link.attr("rel"));
            }*/
    }

    private void addToList(Element link) {

        if (link.attr("abs:href").contains(TAG_LINK) && !link.attr("abs:href").contains(TAG_LINK+"tag")){
            VideoModel videoModel = new VideoModel(trim(link.text(), 35) , link.attr("abs:href"));
            tvList.add(videoModel);
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
