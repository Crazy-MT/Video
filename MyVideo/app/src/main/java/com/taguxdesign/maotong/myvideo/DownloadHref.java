package com.taguxdesign.maotong.myvideo;

import android.util.Log;

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
public class DownloadHref {

    private static final String TAG = "DownloadHref";
    private String url ;

    public DownloadHref(String url) {
        this.url = url;
    }

    public String  getDownloadHref() {

        Document doc = null;
        String downloadHref = "";
        Log.e(TAG, "getDownloadHref: there" );
        try {
            doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            Elements media = doc.select("[src]");
            Elements imports = doc.select("link[href]");

            for (Element link : links) {
                Log.e(TAG, "DownloadHref: " + link.attr("abs:href") + " " + trim(link.text(), 35));
                if (link.attr("abs:href").contains("ftp:")){
                    downloadHref = link.attr("abs:href");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            Log.e(TAG, "getDownloadHref: there"+e.toString() );
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
