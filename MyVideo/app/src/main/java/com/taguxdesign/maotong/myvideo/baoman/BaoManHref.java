package com.taguxdesign.maotong.myvideo.baoman;

import android.util.Log;

import com.taguxdesign.maotong.myvideo.movie.VideoModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by MaoTong on 2016/3/29.
 * QQ:974291433
 */
public class BaoManHref {

    private static final String TAG = "BaoManHref";
    private static String URL = "http://baozoumanhua.com/zhuanti/dashijian";
    private List<BaoManModel> tvList;
    private static final String TAG_LINK = "http://baozoumanhua.com/articles/";

    public List<BaoManModel> getAllHref() {

        Document doc = null;
        tvList = new ArrayList<>();
        try {
            doc = Jsoup.connect(URL).get();
            Elements links = doc.select("a[href]");
            Elements media = doc.select("[src]");
             /*Elements imports = doc.select("link[href]");

            mediaAndImports();*/

            for (Element src : media) {
                if (src.tagName().equals("img") && src.attr("abs:alt").contains("暴走大事件")) {
                    tvList.add(new BaoManModel(src.attr("abs:src"), src.attr("abs:alt").substring(32), new String()));
                    //Log.e(TAG, "getAllHref: tvList  图片链接" + src.attr("abs:src") + " 图片名" +src.attr("abs:alt"));
                    Log.e(TAG, "getAllHref: tvlist" + tvList.size());
                }
            }




            int j = 0;
            for (int i = 0; i < links.size(); i++) {
                String href = links.get(i).attr("abs:href");
                if (!href.contains("http://baozoumanhua.com/articles/11416848") && href.contains("http://baozoumanhua.com/articles")) {
                    if (j < tvList.size())
                        tvList.get(j).setHref(href);
                    j++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            tvList.add(new BaoManModel(e.toString(), new String(), new String()));
        }

        for (int i = 0; i < tvList.size(); i++) {
            Log.e(TAG, "getAllHref: tvList" + tvList.get(i).getHref());
        }

        return tvList;

    }

    private void mediaAndImports() {
        /*

            for (Element link : imports) {
                Log.e(TAG, "getAllHref: " + link.attr("abs:href") + " " + link.attr("rel"));
            }*/
    }


    private static String trim(String s, int width) {
        if (s.length() > width) {
            return s.substring(0, width - 1) + ".";
        } else {
            return s;
        }
    }
}
