package com.taguxdesign.maotong.myvideo.movie;

import com.taguxdesign.maotong.myvideo.VideoModel;

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
public class MovieHref {

    private static String URL = "http://www.dy2018.com/";
    private List<VideoModel> videoMap;

    public List<VideoModel> getAllHref() {

        Document doc = null;
        videoMap = new ArrayList<>();
        try {
            doc = Jsoup.connect(URL).get();
            Elements links = doc.select("a[href]");
            /*Elements media = doc.select("[src]");
            Elements imports = doc.select("link[href]");

            mediaAndImports();*/

            for (Element link : links) {
                addToList(link);
            }

        } catch (Exception e) {
            e.printStackTrace();
            videoMap.add(new VideoModel(e.toString(), new String()));
        }

        return videoMap;

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

        if (link.attr("abs:href").contains("http://www.dy2018.com/i/")) {
            VideoModel videoModel = new VideoModel(trim(link.text(), 35), link.attr("abs:href"));
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
