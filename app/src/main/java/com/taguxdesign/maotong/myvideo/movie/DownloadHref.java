package com.taguxdesign.maotong.myvideo.movie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by MaoTong on 2016/3/29.
 * QQ:974291433
 */
public class DownloadHref {

    private static final String TAG = "DownloadHref";
    private String url ;

    public DownloadHref(String url) {
        this.url = url;
    }

    public String  getDownloadHref() {

        Document doc = null;
        String downloadHref = "";
        try {
            doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            Elements media = doc.select("[src]");
            Elements imports = doc.select("link[href]");

            for (Element link : links) {
                if (link.attr("abs:href").contains("ftp:")){
                    downloadHref = link.attr("abs:href");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            downloadHref = e.toString();
        }

        return downloadHref;

    }

    private static String trim(String s, int width) {
        if (s.length() > width) {
            return s.substring(0, width - 1) + ".";
        } else {
            return s;
        }
    }
}
